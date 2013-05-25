# Parse twitter users into word-count vectors. Pickle results.
import argparse
import io
import numpy as np
import pickle
import re
import string

from sklearn.feature_extraction.text import CountVectorizer

ap = argparse.ArgumentParser(description=__doc__,
                             formatter_class=argparse.RawTextHelpFormatter)
ap.add_argument('--input',
                metavar='INPUT',
                default='data/users.tsv',
                help='users, one per line, each tweet tab-separated')
ap.add_argument('--output',
                metavar='OUTPUT',
                default='data/users.vec',
                help='pickled vocab/sparse matrix pair')
ap.add_argument('--min_df',
                metavar='N',
                type=int,
                default=0,
                help='retained words must have at least this document frequency')
ap.add_argument('--test',
                metavar='BINARY',
                type=lambda s: bool(int(s)),
                default=False,
                help='if 1, run doctests')

args = ap.parse_args()

mention_re = re.compile('@\S+')
punct_re = re.compile('[%s]' % re.escape(string.punctuation))

ct = 0

def tokenize(s):
   r'''
   >>> tokenize('username\thi @there you @person, who? #tag')
   ['hi', 'you', 'who', 'tag']
   '''
   parts = s.split('\t')
   s = ' '.join(parts[1:])
   s = mention_re.sub('', s)
   s = punct_re.sub(' ', s)
   s = re.sub('\s+', ' ', s)
   global ct
   if ct % 1000 == 0:
      print 'line',ct
   ct += 1
   return s.strip().split()

def vectorize(lines):
   r'''
   >>> vectorize(['username\ta b c', 'username\tb c'])[1].toarray()
   array([[1, 1, 1],
          [0, 1, 1]])
   '''
   vec = CountVectorizer(tokenizer=tokenize, min_df=args.min_df)
   data = vec.fit_transform(lines)
   return [vec.vocabulary_, data]

def remove_empty_rows(data):
   data = data.tocsr()
   todel = [i for i in np.arange(data.shape[0]) if data[i].sum() == 0]
   print 'removing',len(todel),'zero rows'
   mask = np.ones(data.shape[0], dtype=bool)
   mask[todel] = False
   w = np.flatnonzero(mask)
   return data[w,:].tocoo()

def main():
   fp = io.open(args.input, mode='rt', encoding='utf8')
   v = vectorize(fp)
   v[1] = remove_empty_rows(v[1])
   print 'read',v[1].shape[0],'users and',len(v[0]),'words'
   pickle.dump(v, open(args.output, "wb"))

if (__name__ == '__main__'):
   if args.test:
      import doctest
      doctest.testmod()
   else:
      main()
