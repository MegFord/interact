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
                default=15,
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
ap.add_argument('--prune_ratio',
                metavar='PRUNE_RATIO',
                type=float,
                default=0.005,
                help='prune clusters with fraction of instances below this')
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
   return ' '.join(['%s=%.2f' % (alpha[i],c[i]) for i in top_indices])

def unpickle(f):
   return pickle.load(open(f, "rb"))

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
   parts = s.lower().split('\t')
   s = ' '.join(parts[1:])
   s = mention_re.sub('', s)
   s = punct_re.sub(' ', s)
   s = re.sub('\s+', ' ', s)
   global ct
   #if ct % 1000 == 0:
   #   print >> sys.stderr, 'line',ct
   ct += 1
   return s.strip().split()

def remove_small_clusters(clusterer):
   cc = clusterer.named_steps['cluster']
   new_centers = []
   labels = cc.labels_
   for i,c in enumerate(cc.cluster_centers_):
      if len([j for j in labels if i == j]) * 1.0 / len(labels) > args.prune_ratio:
         new_centers.append(c)
      else:
         print 'pruning',i
   cc.cluster_centers_ = np.array(new_centers)

def predict(clusterer):
   fp = io.open(args.input, mode='rt', encoding='utf8')
   clusterer.named_steps['cluster'].labels_ = clusterer.predict(fp)

def main():
   clusterer = Pipeline([('vect', CountVectorizer(tokenizer=tokenize, min_df=args.min_df)),
                         ('tfidf', TfidfTransformer()),
                         ('cluster', MiniBatchKMeans(n_clusters=args.clusters, random_state=args.seed))])
   fp = io.open(args.input, mode='rt', encoding='utf8')
   #lines = [l for l in fp][0:10000]
   clusterer.fit(fp)
   print 'clustered', len(clusterer.named_steps['cluster'].labels_)
   remove_small_clusters(clusterer)
   predict(clusterer)
   print_centers(clusterer)
   pickle.dump(clusterer, open(args.output, "wb"))

if (__name__ == '__main__'):
   if args.test:
      import doctest
      doctest.testmod()
   else:
      main()
