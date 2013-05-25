# cluster users based on feature vectors
import argparse
import io
import numpy as np
import pickle
import re
import string
import sys

from sklearn.cluster import MiniBatchKMeans
from sklearn.feature_extraction.text import TfidfTransformer
from sklearn.feature_extraction.text import CountVectorizer
from sklearn.pipeline import Pipeline

ap = argparse.ArgumentParser(description=__doc__,
                             formatter_class=argparse.RawTextHelpFormatter)
ap.add_argument('--clusters',
                metavar='CLUSTERS',
                type=int,
                default=20,
                help='number of clusters')
ap.add_argument('--input',
                metavar='INPUT',
                default='data/users.tsv',
                help='users, one per line, each tweet tab-separated')
ap.add_argument('--min_df',
                metavar='N',
                type=int,
                default=10,
                help='retained words must have at least this document frequency')
ap.add_argument('--output',
                metavar='OUTPUT',
                default='data/users.cluster',
                help='pickled clusterer pipeline')
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

mention_re = re.compile('@\S+')
punct_re = re.compile('[%s]' % re.escape(string.punctuation))
ct = 0

def center2str(c, alpha, n):
   top_indices = c.argsort()[-n:][::-1]
   return ' '.join(['%s=%g' % (alpha[i],c[i]) for i in top_indices])

def print_centers(pipeline):
   km = pipeline.named_steps['cluster']
   preds = km.labels_
   alpha = invert_dict(pipeline.named_steps['vect'].vocabulary_)
   for i,c in enumerate(km.cluster_centers_):
      tot = len([j for j in preds if i == j])
      print >> sys.stderr, 'cluster',i,'(',tot,')',center2str(c, alpha, 20)

def invert_dict(d):
   return dict([(idx,s) for (s,idx) in d.iteritems()])

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
      print >> sys.stderr, 'line',ct
   ct += 1
   return s.strip().split()

def vectorize(lines):
   r'''
   >>> args.min_df = 0
   >>> vectorize(['username\ta b c', 'username\tb c'])[1].toarray()
   array([[1, 1, 1],
          [0, 1, 1]])
   '''
   vec = CountVectorizer(tokenizer=tokenize, min_df=args.min_df)
   data = vec.fit_transform(lines)
   return [vec.vocabulary_, data]

def main():
   clusterer = Pipeline([('vect', CountVectorizer(tokenizer=tokenize, min_df=args.min_df)),
                         ('tfidf', TfidfTransformer()),
                         ('cluster', MiniBatchKMeans(n_clusters=args.clusters, random_state=args.seed))])
   fp = io.open(args.input, mode='rt', encoding='utf8')
   #lines = [l for l in fp][0:10000]
   clusterer.fit(fp)
   print_centers(clusterer)
   pickle.dump(clusterer, open(args.output, "wb"))

if (__name__ == '__main__'):
   if args.test:
      import doctest
      doctest.testmod()
   else:
      main()
