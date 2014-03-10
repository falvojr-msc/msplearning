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

package com.msplearning.android.app.ext;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.DuplicateConnectionException;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Parameters;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.msplearning.android.app.MSPLearningApp;
import com.msplearning.android.app.R;
import com.msplearning.android.app.ext.generic.AbstractWebViewActivity;

/**
 * The FacebookWebOAuthActivity class, the activity for OAuth Facebook login.
 * 
 * @author Roy Clarkson (original author)
 * @author Venilton Falvo Junior (author of specialized implementations)
 */
public class FacebookWebOAuthActivity extends AbstractWebViewActivity<MSPLearningApp> {

	private static final String TAG = FacebookWebOAuthActivity.class.getSimpleName();

	private ConnectionRepository connectionRepository;

	private FacebookConnectionFactory connectionFactory;

	// ***************************************
	// Activity methods
	// ***************************************
	@SuppressLint("SetJavaScriptEnabled")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Facebook uses javascript to redirect to the success page
		this.getWebView().getSettings().setJavaScriptEnabled(true);

		// Using a custom web view client to capture the access token
		this.getWebView().setWebViewClient(new FacebookOAuthWebViewClient());

		this.connectionRepository = this.getApplicationContext().getConnectionRepository();
		this.connectionFactory = this.getApplicationContext().getFacebookConnectionFactory();
	}

	@Override
	public void onStart() {
		super.onStart();

		// display the Facebook authorization page
		this.getWebView().loadUrl(this.getAuthorizeUrl());
	}

	@Override
	protected void onResume() {
		super.onResume();

		// clear the Facebook session cookie
		CookieManager.getInstance().removeAllCookie();
	}

	// ***************************************
	// Private methods
	// ***************************************
	private String getAuthorizeUrl() {
		String redirectUri = this.getString(R.string.facebook_oauth_callback_url);
		String scope = this.getString(R.string.facebook_scope);

		/*
		 * Generate the Facebook authorization url to be used in the browser or web view the display=touch parameter
		 * requests the mobile formatted version of the Facebook authorization page
		 */
		OAuth2Parameters params = new OAuth2Parameters();
		params.setRedirectUri(redirectUri);
		params.setScope(scope);
		params.add("display", "touch");
		return this.connectionFactory.getOAuthOperations().buildAuthorizeUrl(GrantType.IMPLICIT_GRANT, params);
	}

	private void displayDashboard() {
		// TODO: Implement integration to M-SPLearning
	}

	// ***************************************
	// Private classes
	// ***************************************
	private class FacebookOAuthWebViewClient extends WebViewClient {

		/*
		 * The WebViewClient has another method called shouldOverridUrlLoading which does not capture the javascript
		 * redirect to the success page. So we're using onPageStarted to capture the url.
		 */
		@Override
		public void onPageStarted(WebView view, String url, Bitmap favicon) {
			// parse the captured url
			Uri uri = Uri.parse(url);
			Log.d(TAG, url);

			/*
			 * The access token is returned in the URI fragment of the URL. See the Desktop Apps section all the way
			 * at the bottom of this link:
			 * 
			 * http://developers.facebook.com/docs/authentication/
			 * 
			 * The fragment will be formatted like this:
			 * 
			 * #access_token=A&expires_in=0
			 */
			AccessGrant accessGrant = this.createAccessGrantFromUriFragment(uri.getFragment());
			if (accessGrant != null) {
				new CreateConnectionTask().execute(accessGrant);
			}

			/*
			 * if there was an error with the oauth process, return the error description
			 * 
			 * The error query string will look like this:
			 * 
			 * ?error_reason=user_denied&error=access_denied&error_description=The +user+denied+your+request
			 */
			if (uri.getQueryParameter("error") != null) {
				CharSequence errorReason = uri.getQueryParameter("error_description").replace("+", " ");
				Toast.makeText(FacebookWebOAuthActivity.this.getApplicationContext(), errorReason, Toast.LENGTH_LONG).show();
				FacebookWebOAuthActivity.this.displayDashboard();
			}
		}

		private AccessGrant createAccessGrantFromUriFragment(String uriFragment) {
			// confirm we have the fragment, and it has an access_token parameter
			if ((uriFragment != null) && uriFragment.startsWith("access_token=")) {

				/*
				 * The fragment also contains an "expires_in" parameter. In this
				 * example we requested the offline_access permission, which
				 * basically means the access will not expire, so we're ignoring
				 * it here
				 */
				try {
					// split to get the two different parameters
					String[] params = uriFragment.split("&");

					// split to get the access token parameter and value
					String[] accessTokenParam = params[0].split("=");

					// get the access token value
					String accessToken = accessTokenParam[1];

					// create the connection and persist it to the repository
					return new AccessGrant(accessToken);
				} catch (Exception e) {
					// don't do anything if the parameters are not what is expected
					Log.d(TAG, e.getLocalizedMessage(), e);
				}
			}
			return null;
		}

	}

	private class CreateConnectionTask extends AsyncTask<AccessGrant, Void, Void> {

		@Override
		protected Void doInBackground(AccessGrant... params) {
			if (params[0] != null) {
				// this method makes a network request to Facebook, so it must run off of the UI thread
				Connection<Facebook> connection = FacebookWebOAuthActivity.this.connectionFactory.createConnection(params[0]);
				try {
					// persist connection to the repository
					FacebookWebOAuthActivity.this.connectionRepository.addConnection(connection);
				} catch (DuplicateConnectionException e) {
					// connection already exists in repository!
					Log.d(TAG, e.getLocalizedMessage(), e);
				}
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			FacebookWebOAuthActivity.this.displayDashboard();
		}

	}
}
