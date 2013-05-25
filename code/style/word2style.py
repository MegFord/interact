import argparse
import codecs
from collections import defaultdict
import io
from math import sqrt
import operator
import pickle
import sys

from cluster import tokenize

# Given a fit clusterer, create and save context vectors for each word in each cluster.

sys.stdout = codecs.getwriter('utf8')(sys.stdout)

ap = argparse.ArgumentParser(description=__doc__,
                             formatter_class=argparse.RawTextHelpFormatter)
ap.add_argument('--clusterer',
                metavar='CLUSTERER',
                default='data/users.cluster',
                help='saved clusterer')
ap.add_argument('--input',
                metavar='INPUT',
                default='data/users.tsv',
                help='users, one per line, each tweet tab-separated')
ap.add_argument('--output',
                metavar='output',
                default='data/users.contexts',
                help='[all_contexts, cluster2contexts]')
ap.add_argument('--test',
                metavar='BINARY',
                type=lambda s: bool(int(s)),
                default=False,
                help='if 1, run doctests')
ap.add_argument('--window',
                metavar='WINDOW',
                type=int,
                default=2,
                help='window size (one-side) for word-context vectors')


args = ap.parse_args()


# Pickle can't handle lambda functions, so I give them names here.
def d_zero():
   return 0

def d_zero_dict():
   return defaultdict(d_zero)

def d_empty():
   return []

def make_contexts(rows, window):
   '''
   >>> c = make_contexts([[0,1,1,2]], 2)
   >>> [c[0][0], c[0][1], c[0][2]]
   [0, 2, 0]
   >>> [c[1][0], c[1][1], c[1][2]]
   [2, 2, 2]
   >>> [c[2][0], c[2][1], c[2][2]]
   [0, 2, 0]
   '''
   word2counts = defaultdict(d_zero_dict)
   for r in rows:
      for i,word in enumerate(r):
         for context in r[max(i-window,0):i] + r[i+1:i+window+1]:
            word2counts[word][context] += 1
   return word2counts

def norm(v):
   return sqrt(sum([vi * vi for vi in v.values()]))

def cosine(context1, context2):
   '''
   >>> cosine({1: 1, 2: 2}, {2: 3, 3: 1}) # doctest: +ELLIPSIS
   0.8485...
   '''
   num = sum([context1[k] * context2[k] for k in context1 if k in context2])
   return num / (norm(context1) * norm(context2))

def make_clusters(clusterer):
   labels = clusterer.named_steps['cluster'].labels_
   fp = io.open(args.input, mode='rt', encoding='utf8')
   li = 0
   cluster2tweets = defaultdict(d_empty)
   all_tweets = []
   for line in fp:
      parts = line.split('\t')
      user = parts[0]
      tweets = parts[1:]
      print 'user',user,len(tweets),'tweets, label=',labels[li]
      li += 1
      #if li > 100:
      #   break
      for tweet in tweets:
         t = tokenize(user+'\t'+tweet)
         cluster2tweets[labels[li]].append(t)
         all_tweets.append(t)
   return [cluster2tweets, all_tweets]

def make_cluster_contexts(cluster2tweets):
   cluster2contexts = dict()
   for c in cluster2tweets.keys():
      cluster2contexts[c] = make_contexts(cluster2tweets[c], args.window)
   return cluster2contexts

def find_top_matches(word_context, contexts, n):
   d = dict()
   for w in contexts.keys():
      d[w] = cosine(contexts[w], word_context)
   return [(w, d[w]) for w in sorted(d, key=d.get, reverse=True)][0:n]

def main():
   clusterer = pickle.load(open(args.clusterer, "rb"))
   cluster2tweets, tweets = make_clusters(clusterer)
   cluster2contexts = make_cluster_contexts(cluster2tweets)
   all_context = make_contexts(tweets, args.window)
   for c, context in enumerate(cluster2contexts.values()):
      print c,'good words', [('%s=%.2f' % (s,v)) for (s,v) in
                             find_top_matches(all_context['good'], context, 10)]
   pickle.dump([all_context, cluster2contexts], open(args.output, "wb"))

if (__name__ == '__main__'):
   if args.test:
      import doctest
      doctest.testmod()
   else:
      main()


