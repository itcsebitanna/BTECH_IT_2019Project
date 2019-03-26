sentiment analysis of tweets
==========  
Twitter Sentiment Analysis using Python  
```
============================================================================================= 
Under the Guidance of						Submitted By,
	R. Renuga Devi 						A. Aravind	(810015205007)
	Teaching Fellow 					S. Aravind	(810015205008)
	Department of IT
	BIT Campus
	Trichy.
============================================================================================== 
```

Usage
----------------
#### Installation:
Note: You may need to run these commands as superuser.  
```
$ pip install scikit-learn 
```
Tweepy: tweepy is the python client for the official Twitter API.  
Install it using following pip command:  
```
$ pip install tweepy
```
TextBlob: textblob is the python library for processing textual data.  
Install it using following pip command:  
```
$ pip install textblob
```
Also, we need to install some NLTK corpora using following command:  
```
$ python -m textblob.download_corpora
```
(Corpora is nothing but a large and structured set of texts.)  
#### Authentication:
In order to fetch tweets through Twitter API, one needs to register an App through their twitter account.  
Follow these steps for the same:  
* Open this [link](https://apps.twitter.com/) and click the button: 'Create New App'  
* Fill the application details. You can leave the callback url field empty.  
* Once the app is created, you will be redirected to the app page.  
* Open the 'Keys and Access Tokens' tab.  
* Copy 'Consumer Key', 'Consumer Secret', 'Access token' and 'Access Token Secret'.  
  
#### To run:
```
$ python twitter_analysis.py
```
