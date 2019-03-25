import re
import tweepy
from tweepy import OAuthHandler
from textblob import TextBlob
import csv
import matplotlib.pyplot as plt
from textblob.classifiers import NaiveBayesClassifier
class TwitterClient(object):
   
    def __init__(self):
        '''
        Class constructor or initialization method.
        '''
        # keys and tokens from the Twitter Dev Console
        consumer_key = ''
        consumer_secret = ''
        access_token = ''
        access_token_secret = ''
        # attempt authentication
        try:
            # create OAuthHandler object
            self.auth = OAuthHandler(consumer_key, consumer_secret)
            # set access token and secret
            self.auth.set_access_token(access_token, access_token_secret)
            # create tweepy API object to fetch tweets
            self.api = tweepy.API(self.auth)
        except:
            print("Error: Authentication Failed")
    def clean_tweet(self, tweet):
        '''
        Utility function to clean tweet text by removing links, special characters
        using simple regex statements.
        '''
        return ' '.join(re.sub("(@[A-Za-z0-9]+)|([^0-9A-Za-z \t])|(\w+:\/\/\S+)", " ", tweet).split())
    def get_tweet_sentiment(self, tweet):
        '''
        Utility function to classify sentiment of passed tweet
        using textblob's sentiment method
        '''
        # create TextBlob object of passed tweet text
       
        analysis = self.clean_tweet(tweet)
        # set sentiment
        with open('train.json', 'r') as fp:
            cl = NaiveBayesClassifier(fp, format="json")
        # set sentiment
        if cl.classify(analysis) == "Pos":
            return 'positive'
        elif  cl.classify(analysis) == "Neg":
            return 'negative'
        else:
            return 'neutral'
        

    def get_tweets(self, query, count = 10):
        '''
        Main function to fetch tweets and parse them.
        '''
        # empty list to store parsed tweets
        tweets = []
        try:
            # call twitter api to fetch tweets
           
            fetched_tweets = self.api.search(q = query, count = count)
            
            
           
            for tweet in fetched_tweets:
                # empty dictionary to store required params of a tweet
                parsed_tweet = {}
                     
                
                parsed_tweet['text'] = tweet.text
                
                parsed_tweet['sentiment'] = self.get_tweet_sentiment(tweet.text)
                # appending parsed tweet to tweets list
                if tweet.retweet_count > 0:
                    # if tweet has retweets, ensure that it is appended only once
                    if parsed_tweet not in tweets:
                        tweets.append(parsed_tweet)
                else:
                    tweets.append(parsed_tweet)
            # return parsed tweets
            return tweets
            
            
        except tweepy.TweepError as e:
            # print error (if any)
            print("Error : " + str(e))
def main():
    # creating object of TwitterClient Class
   
    api = TwitterClient()
   
    
    tweets = api.get_tweets(query = "#CSKvsRCB", count = 200)
   
    ptweets = [tweet for tweet in tweets if tweet['sentiment'] == 'positive']
    # percentage of positive tweets
    positive =format(100*len(ptweets)/len(tweets))
    print("Positive tweets percentage: {} %".format(100*len(ptweets)/len(tweets)))
    # picking negative tweets from tweets
    ntweets = [tweet for tweet in tweets if tweet['sentiment'] == 'negative']
    # percentage of negative tweets
    negative = format(100*len(ntweets)/len(tweets))
    print("Negative tweets percentage: {} %".format(100*len(ntweets)/len(tweets)))
    # percentage of neutral tweets
    neutral = format(100*(len(tweets) - len(ntweets) - len(ptweets))/len(tweets))
    print("Neutral tweets percentage: {} %".format(100*(len(tweets) - len(ntweets) - len(ptweets))/len(tweets)))
    # printing first 5 positive tweets
    labels = ['Positive [' + str(positive) + '%]',  'Neutral [' + str(neutral) + '%]',
                  'Negative [' + str(negative) + '%]']
    sizes = [positive, neutral, negative]
    colors = ['yellowgreen','lightgreen','darkgreen']
    patches, texts = plt.pie(sizes, colors=colors, startangle=90)
    plt.legend(patches, labels, loc="best")
    plt.title('How people are reacting on  by analyzing Tweets.')
    plt.axis('equal')
    plt.tight_layout()
    plt.show()
    print("\n\nPositive tweets:")
        
    for tweet in ptweets[:10]:
        csvFile = open('postive.csv', 'a')
        
        csvWriter = csv.writer(csvFile)
        print(tweet['text'].encode('utf-8'))
        csvWriter.writerow([ tweet['text'].encode('utf-8')])
        
        csvFile.close()
    print("\n\nNegative tweets:")
    for tweet in ntweets[:10]:
        csvFile = open('negative.csv', 'a')
    
        csvWriter = csv.writer(csvFile)
        csvWriter.writerow([ tweet['text'].encode('utf-8')])
        print(tweet['text'].encode('utf-8'))
        csvFile.close()
        

       
if __name__ == "__main__":
    # calling main function
     
    main()