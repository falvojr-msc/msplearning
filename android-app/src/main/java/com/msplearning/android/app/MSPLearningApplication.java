package com.msplearning.android.app;

import org.androidannotations.annotations.EApplication;
import org.springframework.security.crypto.encrypt.AndroidEncryptors;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.sqlite.SQLiteConnectionRepository;
import org.springframework.social.connect.sqlite.support.SQLiteConnectionRepositoryHelper;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.connect.TwitterConnectionFactory;

import android.app.Application;
import android.database.sqlite.SQLiteOpenHelper;

@EApplication
public class MSPLearningApplication extends Application {

	private ConnectionFactoryRegistry connectionFactoryRegistry;
	private SQLiteOpenHelper repositoryHelper;
	private ConnectionRepository connectionRepository;

	@Override
	public void onCreate() {
		this.connectionFactoryRegistry = new ConnectionFactoryRegistry();
		this.connectionFactoryRegistry.addConnectionFactory(new FacebookConnectionFactory(this.getFacebookAppId(), this.getFacebookAppSecret()));
		this.connectionFactoryRegistry.addConnectionFactory(new TwitterConnectionFactory(this.getTwitterConsumerToken(), this.getTwitterConsumerTokenSecret()));

		this.repositoryHelper = new SQLiteConnectionRepositoryHelper(this);
		this.connectionRepository = new SQLiteConnectionRepository(this.repositoryHelper, this.connectionFactoryRegistry, AndroidEncryptors.text("password", "5c0744940b5c369b"));
	}

	private String getFacebookAppId() {
		return this.getString(R.string.facebook_app_id);
	}

	private String getFacebookAppSecret() {
		return this.getString(R.string.facebook_app_secret);
	}

	private String getTwitterConsumerToken() {
		return this.getString(R.string.twitter_consumer_key);
	}

	private String getTwitterConsumerTokenSecret() {
		return this.getString(R.string.twitter_consumer_key_secret);
	}

	public ConnectionRepository getConnectionRepository() {
		return this.connectionRepository;
	}

	public FacebookConnectionFactory getFacebookConnectionFactory() {
		return (FacebookConnectionFactory) this.connectionFactoryRegistry.getConnectionFactory(Facebook.class);
	}

	public TwitterConnectionFactory getTwitterConnectionFactory() {
		return (TwitterConnectionFactory) this.connectionFactoryRegistry.getConnectionFactory(Twitter.class);
	}
}
