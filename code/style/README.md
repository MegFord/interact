Consider three ways of extolling the virtues of Tai Chi:

1. Dude, Tai Chi rocks!
2. Tai chi provides serious health benefits.
3. Dont u like 2 do tai chi?

The goal is to pick one of those three choices based on information from a user's social media presence.

The general idea is to
1. Cluster a large number of social media users by linguistic style (aka "register"). The output is a list of "style clusters".
2. Assign a new user to one of these style clusters based on the text in their social media feeds.
3. Select a dialog statement based on the selected style cluster.



The approach we'll take is:
1. Cluster users by LIWC vectors over their Twitter messages
2. For a given word, find the most similar word W in each LIWC cluster
    2a. Compute the context vector of W over all data
    2b. Find the M most similar words in each cluster, based on their cluster-specific context vector
3. For a new user, find their closest LIWC cluster, and select the corresponding dialog statement


Other approaches considered:

- Alternatively, we may want to take as input a dialog statement, and output similar words that can be substituted to rephrase the statement. This seems harder.
- Pointwise Mutual Information for synonym discovery

The synonym approach:

1. Given N documents and M words, for each word output the list of synonyms
  1a. discover these based on pointwise mutual information of context vectors
2. For each user, create a vector that is the frequency with which they use each of these synonyms
3. Cluster these users using these vectors
4. For a dialog statement, generate synonyms from each cluster 


