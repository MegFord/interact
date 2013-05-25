# cluster users based on feature vectors
import argparse
import io
import numpy as np
import pickle
import re
import string

from sklearn.feature_extraction.text import TfidfTransformer

ap = argparse.ArgumentParser(description=__doc__,
                             formatter_class=argparse.RawTextHelpFormatter)
ap.add_argument('--input',
                metavar='INPUT',
                default='data/users.vec',
                help='dictionary/vector pickle file')
ap.add_argument('--output',
                metavar='OUTPUT',
                default='data/users.clusters',
                help='pickled clusters')
ap.add_argument('--tfidf',
                metavar='TFIDF',
                type=lambda s: bool(int(s)),
                default=True,
                help='if 1, do tfidf transform')
ap.add_argument('--test',
                metavar='BINARY',
                type=lambda s: bool(int(s)),
                default=False,
                help='if 1, run doctests')

args = ap.parse_args()

def main():
   alpha, data = pickle.load(open(args.input, 'rb'))
   if args.tfidf:
      tfidf = TfidfTransformer()
      tfidf.fit(data)
      data = tfidf.transform(data)
   print data.getrow(0)

if (__name__ == '__main__'):
   if args.test:
      import doctest
      doctest.testmod()
   else:
      main()
