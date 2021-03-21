package com.cts_techacademy.entity;

import javax.persistence.*;

@Entity
@Table(name = "tweets")
public class Tweets {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tweet_id")
	private long tweet_id;

	@Column(name = "user_assoc_id")
	private long userAssocId;
	
	@Column(name = "tweet")
	private String tweet;

	@Column(name = "tweet_extn")
	private String tweetExtn;

	@Override
	public String toString() {
		return "Tweets{" +
				"tweet_id=" + tweet_id +
				", userAssocId=" + userAssocId +
				", tweet='" + tweet + '\'' +
				", tweetExtn='" + tweetExtn + '\'' +
				'}';
	}

	public Tweets() {
		super();
	}


	public Tweets(String tweet, String tweetExtn) {
		this.tweet = tweet;
		this.tweetExtn = tweetExtn;
	}

	public long getTweet_id() {
		return tweet_id;
	}

	public void setTweet_id(long tweet_id) {
		this.tweet_id = tweet_id;
	}

	public long getUserAssocId() {
		return userAssocId;
	}

	public void setUserAssocId(long userAssocId) {
		this.userAssocId = userAssocId;
	}

	public String getTweet() {
		return tweet;
	}

	public void setTweet(String tweet) {
		this.tweet = tweet;
	}

	public String getTweetExtn() {
		return tweetExtn;
	}

	public void setTweetExtn(String tweetExtn) {
		this.tweetExtn = tweetExtn;
	}
	
	
}
