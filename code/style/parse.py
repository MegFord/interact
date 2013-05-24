# parse twitter users into feature vectors
import argparse
import doctest
import re
import string

from sklearn.feature_extraction.text import CountVectorizer

ap = argparse.ArgumentParser(description=__doc__,
                             formatter_class=argparse.RawTextHelpFormatter)
ap.add_argument('--input',
                metavar='INPUT',
                default='data/users.tsv',
                help='users, one per line, each tweet tab-separated')

mention_regex = re.compile('@\S+')
punct_regex = re.compile('[%s]' % re.escape(string.punctuation))

def tokenize(s):
   '''
   >>> tokenize('hi @there you @person, who? #tag')
   ['hi', 'you', 'who', 'tag']
   '''
   s = mention_regex.sub('', s)
   s = punct_regex.sub(' ', s)
   s = re.sub('\s+', ' ', s)
   return s.strip().split()

def vectorize(lines):
   '''
   >>> vectorize(['a b c', 'b c'])[1].toarray()
   array([[1, 1, 1],
          [0, 1, 1]])
   '''
   vec = CountVectorizer(tokenizer=tokenize, min_df=0)
   data = vec.fit_transform(lines)
   return (vec.vocabulary_, data)

def main():
   pass


if (__name__ == '__main__'):
   main()
