package com.msplearning.android.app;

import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.Background;
import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.UiThread;
import com.googlecode.androidannotations.annotations.ViewById;
import com.googlecode.androidannotations.annotations.rest.RestService;
import com.msplearning.android.compatibility.interoperability.UserRESTfulClient;
import com.msplearning.android.widget.ProgressBarCustom;
import com.msplearning.entity.User;

/**
 * The LoginActivity class. Activity which displays a login screen to the user,
 * offering registration as well.
 * 
 * @author Venilton Falvo Junior (veniltonjr)
 */
@EActivity(R.layout.activity_login)
public class LoginActivity extends SherlockActivity {

	/**
	 * A dummy authentication store containing known user names and passwords.
	 * TODO: remove after connecting to a real authentication system.
	 */
	private static final String[] DUMMY_CREDENTIALS = new String[] { "foo@example.com:hello", "bar@example.com:world" };

	// Values for email and password at the time of the login attempt.
	private String mUsername;
	private String mPassword;

	// UI references.
	@ViewById(R.id.username)
	EditText mUsernameView;
	@ViewById(R.id.password)
	EditText mPasswordView;
	@ViewById(R.id.login_form)
	View mLoginFormView;
	@ViewById(R.id.login_progress_bar)
	ProgressBarCustom mProgressBarCustom;
	
	// RESTful client.
	@RestService
	protected UserRESTfulClient mUserRESTfulClient;

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getSupportMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	@AfterViews
	protected void init() {
		// Set up the login form.
		mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
				if (id == R.id.login || id == EditorInfo.IME_NULL) {
					signIn();
					return true;
				}
				return false;
			}
		});
	}

	/**
	 * Attempts to sign in or register the account specified by the login form.
	 * If there are form errors (invalid email, missing fields, etc.), the
	 * errors are presented and no actual login attempt is made.
	 */
	@Click(R.id.sign_in_button)
	protected void signIn() {

		// Reset errors.
		mUsernameView.setError(null);
		mPasswordView.setError(null);

		// Store values at the time of the login attempt.
		mUsername = mUsernameView.getText().toString();
		mPassword = mPasswordView.getText().toString();

		boolean cancel = false;
		View focusView = null;

		// Check for a valid password.
		if (TextUtils.isEmpty(mPassword)) {
			mPasswordView.setError(getString(R.string.error_field_required));
			focusView = mPasswordView;
			cancel = true;
		} else if (mPassword.length() < 4) {
			mPasswordView.setError(getString(R.string.error_invalid_password));
			focusView = mPasswordView;
			cancel = true;
		}

		// Check for a valid username.
		if (TextUtils.isEmpty(mUsername)) {
			mUsernameView.setError(getString(R.string.error_field_required));
			focusView = mUsernameView;
			cancel = true;
		} else if (mUsername.length() < 4) {
			mUsernameView.setError(getString(R.string.error_invalid_username));
			focusView = mUsernameView;
			cancel = true;
		}

		if (cancel) {
			// There was an error; don't attempt login and focus the first form field with an error.
			focusView.requestFocus();
		} else {
			this.mProgressBarCustom.showProgress(true, this.mLoginFormView);
			authenticate();
		}
	}

	@Background
	protected void authenticate() {
		boolean success = false;

		User userAuth = new User();
		userAuth.setUsername(this.mUsername);
		userAuth.setPassword(this.mPassword);
		
		success = this.mUserRESTfulClient.authenticate(userAuth);
		
		if (success) {
			this.mProgressBarCustom.showProgress(false, this.mLoginFormView);
		} else {
			User user = this.mUserRESTfulClient.findByUsername(userAuth.getUsername());
			this.mProgressBarCustom.showProgress(false, this.mLoginFormView);
			if (user == null) {
				// TODO: Ask if the user wants to register.
			} else {
				showIncorrectPasswordError();
			}
		}
	}

	@UiThread
	protected void showIncorrectPasswordError() {
		mPasswordView.setError(getString(R.string.error_incorrect_password));
		mPasswordView.requestFocus();
	}

}
