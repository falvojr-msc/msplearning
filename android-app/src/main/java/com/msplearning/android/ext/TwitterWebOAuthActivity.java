/*
 * Copyright 2010-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.msplearning.android.ext;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.DuplicateConnectionException;
import org.springframework.social.oauth1.AuthorizedRequestToken;
import org.springframework.social.oauth1.OAuth1Parameters;
import org.springframework.social.oauth1.OAuthToken;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.connect.TwitterConnectionFactory;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import com.msplearning.android.app.R;

/**
 * @author Roy Clarkson
 */
public class TwitterWebOAuthActivity extends AbstractWebViewActivity {

	@SuppressWarnings("unused")
	private static final String TAG = TwitterWebOAuthActivity.class.getSimpleName();

	private static final String REQUEST_TOKEN_KEY = "request_token";

	private static final String REQUEST_TOKEN_SECRET_KEY = "request_token_secret";

	private ConnectionRepository connectionRepository;

	private TwitterConnectionFactory connectionFactory;

	private SharedPreferences twitterPreferences;

	// ***************************************
	// Activity methods
	// ***************************************
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.connectionRepository = this.getApplicationContext().getConnectionRepository();
		this.connectionFactory = this.getApplicationContext().getTwitterConnectionFactory();
		this.twitterPreferences = this.getSharedPreferences("TwitterConnectPreferences", Context.MODE_PRIVATE);
	}

	@Override
	public void onStart() {
		super.onStart();
		Uri uri = this.getIntent().getData();
		if (uri != null) {
			String oauthVerifier = uri.getQueryParameter("oauth_verifier");

			if (oauthVerifier != null) {
				this.getWebView().clearView();
				new TwitterPostConnectTask().execute(oauthVerifier);
			}
		} else {
			new TwitterPreConnectTask().execute();
		}
	}

	// ***************************************
	// Private methods
	// ***************************************
	private String getOAuthCallbackUrl() {
		return this.getString(R.string.twitter_oauth_callback_url);
	}

	private void displayTwitterAuthorization(OAuthToken requestToken) {
		// save for later use
		this.saveRequestToken(requestToken);

		// Generate the Twitter authorization URL to be used in the browser or web view
		String authUrl = this.connectionFactory.getOAuthOperations().buildAuthorizeUrl(requestToken.getValue(),
				OAuth1Parameters.NONE);

		// display the twitter authorization screen
		this.getWebView().loadUrl(authUrl);
	}

	private void displayDashboard() {
		// TODO: Implement integration to M-SPLearning
	}

	private void saveRequestToken(OAuthToken requestToken) {
		SharedPreferences.Editor editor = this.twitterPreferences.edit();
		editor.putString(REQUEST_TOKEN_KEY, requestToken.getValue());
		editor.putString(REQUEST_TOKEN_SECRET_KEY, requestToken.getSecret());
		editor.commit();
	}

	private OAuthToken retrieveRequestToken() {
		String token = this.twitterPreferences.getString(REQUEST_TOKEN_KEY, null);
		String secret = this.twitterPreferences.getString(REQUEST_TOKEN_SECRET_KEY, null);
		return new OAuthToken(token, secret);
	}

	private void deleteRequestToken() {
		this.twitterPreferences.edit().clear().commit();
	}

	// ***************************************
	// Private classes
	// ***************************************
	private class TwitterPreConnectTask extends AsyncTask<Void, Void, OAuthToken> {

		@Override
		protected void onPreExecute() {
			TwitterWebOAuthActivity.this.showProgressDialog("Initializing OAuth Connection...");
		}

		@Override
		protected OAuthToken doInBackground(Void... params) {
			// Fetch a one time use Request Token from Twitter
			return TwitterWebOAuthActivity.this.connectionFactory.getOAuthOperations().fetchRequestToken(TwitterWebOAuthActivity.this.getOAuthCallbackUrl(), null);
		}

		@Override
		protected void onPostExecute(OAuthToken requestToken) {
			TwitterWebOAuthActivity.this.dismissProgressDialog();
			TwitterWebOAuthActivity.this.displayTwitterAuthorization(requestToken);
		}

	}

	private class TwitterPostConnectTask extends AsyncTask<String, Void, Void> {

		@Override
		protected void onPreExecute() {
			TwitterWebOAuthActivity.this.showProgressDialog("Finalizing OAuth Connection...");
		}

		@Override
		protected Void doInBackground(String... params) {
			if (params.length <= 0) {
				return null;
			}

			final String verifier = params[0];

			OAuthToken requestToken = TwitterWebOAuthActivity.this.retrieveRequestToken();

			// Authorize the Request Token
			AuthorizedRequestToken authorizedRequestToken = new AuthorizedRequestToken(requestToken, verifier);

			// Exchange the Authorized Request Token for the Access Token
			OAuthToken accessToken = TwitterWebOAuthActivity.this.connectionFactory.getOAuthOperations().exchangeForAccessToken(
					authorizedRequestToken, null);

			TwitterWebOAuthActivity.this.deleteRequestToken();

			// Persist the connection and Access Token to the repository
			Connection<Twitter> connection = TwitterWebOAuthActivity.this.connectionFactory.createConnection(accessToken);

			try {
				TwitterWebOAuthActivity.this.connectionRepository.addConnection(connection);
			} catch (DuplicateConnectionException e) {
				// connection already exists in repository!
			}

			return null;
		}

		@Override
		protected void onPostExecute(Void v) {
			TwitterWebOAuthActivity.this.dismissProgressDialog();
			TwitterWebOAuthActivity.this.displayDashboard();
		}

	}

}
