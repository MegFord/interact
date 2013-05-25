# cluster users based on feature vectors
import argparse
import io
import numpy as np
import pickle
import re
import string

from sklearn.feature_extraction.text import TfidfTransformer
from sklearn.cluster import MiniBatchKMeans

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
ap.add_argument('--clusters',
                metavar='CLUSTERS',
                type=int,
                default=10,
                help='number of clusters')
ap.add_argument('--seed',
                metavar='SEED',
                type=int,
                default=1234567,
                help='number of clusters')
ap.add_argument('--test',
                metavar='BINARY',
                type=lambda s: bool(int(s)),
                default=False,
                help='if 1, run doctests')

args = ap.parse_args()

def center2str(c, alpha, n):
   top_indices = c.argsort()[-n:][::-1]
   return ' '.join(['%s=%g' % (alpha[i],c[i]) for i in top_indices])

def print_centers(km, alpha):
   for i,c in enumerate(km.cluster_centers_):
      print 'cluster',i,center2str(c, alpha, 10)

def invert_dict(d):
   return dict([(idx,s) for (s,idx) in d.iteritems()])

def main():
   alpha, data = pickle.load(open(args.input, 'rb'))
   alpha = invert_dict(alpha)
   if args.tfidf:
      data = TfidfTransformer().fit_transform(data)
   km = MiniBatchKMeans(n_clusters=args.clusters, random_state=args.seed)
   km.fit(data)
   print_centers(km, alpha)

if (__name__ == '__main__'):
   if args.test:
      import doctest
      doctest.testmod()
   else:
      main()
