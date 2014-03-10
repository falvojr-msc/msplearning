package com.msplearning.android.app;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.RestService;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.msplearning.android.app.generic.AbstractAsyncRestActivity;
import com.msplearning.android.app.interoperability.UserRestClient;
import com.msplearning.entity.User;
import com.msplearning.entity.common.Response;

/**
 * The LoginActivity class. Activity which displays a login screen to the user,
 * offering registration as well.
 * 
 * @author Venilton Falvo Junior (veniltonjr)
 */
@EActivity(R.layout.activity_login)
public class LoginActivity extends AbstractAsyncRestActivity<MSPLearningApp> {

	public static final String KEY_PASSWORD = "password";
	public static final String KEY_USERNAME = "username";

	// Values for email and password at the time of the login attempt.
	private String mUsername;
	private String mPassword;

	// UI references.
	@ViewById(R.id.txtUsername)
	protected EditText mUsernameView;
	@ViewById(R.id.txtPassword)
	protected EditText mPasswordView;

	// RESTful client.
	@RestService
	protected UserRestClient mUserRESTfulClient;

	@AfterViews
	protected void init() {
		// Set up the login form.
		this.mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
				if ((id == R.id.login) || (id == EditorInfo.IME_NULL)) {
					LoginActivity.this.signIn();
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
	@Click(R.id.btnSignIn)
	protected void signIn() {

		// Reset errors.
		this.mUsernameView.setError(null);
		this.mPasswordView.setError(null);

		// Store values at the time of the login attempt.
		this.mUsername = this.mUsernameView.getText().toString();
		this.mPassword = this.mPasswordView.getText().toString();

		boolean cancel = false;
		View focusView = null;

		// Check for a valid password.
		if (TextUtils.isEmpty(this.mPassword)) {
			this.mPasswordView.setError(this.getString(R.string.error_field_required));
			focusView = this.mPasswordView;
			cancel = true;
		} else if (this.mPassword.length() < 4) {
			this.mPasswordView.setError(this.getString(R.string.error_invalid_password));
			focusView = this.mPasswordView;
			cancel = true;
		}

		// Check for a valid username.
		if (TextUtils.isEmpty(this.mUsername)) {
			this.mUsernameView.setError(this.getString(R.string.error_field_required));
			focusView = this.mUsernameView;
			cancel = true;
		}

		if (cancel) {
			// There was an error; don't attempt login and focus the first form
			// field with an error.
			focusView.requestFocus();
		} else {
			super.showLoadingProgressDialog();
			this.authenticate();
		}
	}

	@Background
	protected void authenticate() {

		final User userAuth = new User();
		userAuth.setUsername(this.mUsername);
		userAuth.setPassword(this.mPassword);

		try {
			Response responseAuth = this.mUserRESTfulClient.authenticate(userAuth);
			if (responseAuth.hasBusinessMessage()) {
				Response responseUsername = this.mUserRESTfulClient.findByUsername(userAuth.getUsername());
				if (responseUsername.hasBusinessMessage()) {
					this.showDialogConfirmRegister(responseUsername.getBusinessMessage());
				} else {
					this.showIncorrectPasswordError(responseAuth.getBusinessMessage());
				}
			}
		} catch (Exception exception) {
			this.showDialogAlertError(exception.getMessage());
		} finally {
			super.dismissProgressDialog();
		}
	}

	@UiThread
	protected void showDialogAlertError(String message) {
		new AlertDialog.Builder(this).setTitle(this.getString(R.string.title_dialog_error)).setMessage(message)
		.setIcon(android.R.drawable.ic_dialog_alert).setNeutralButton(android.R.string.ok, null).show();
	}

	@UiThread
	protected void showDialogConfirmRegister(final String message) {
		new AlertDialog.Builder(this).setTitle(this.getString(R.string.title_dialog_register)).setMessage(this.getString(R.string.message_dialog_register))
		.setIcon(android.R.drawable.ic_dialog_info).setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int whichButton) {
				Intent intent = RegisterActivity_.intent(LoginActivity.this.getApplicationContext()).get();
				intent.putExtra(KEY_USERNAME, LoginActivity.this.mUsername);
				intent.putExtra(KEY_PASSWORD, LoginActivity.this.mPassword);
				LoginActivity.this.startActivity(intent);
				LoginActivity.this.finish();
			}
		}).setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int whichButton) {
				LoginActivity.this.showNotFoundUserError(message);
			}
		}).show();
	}

	@UiThread
	protected void showIncorrectPasswordError(String message) {
		this.mPasswordView.setError(message);
		this.mPasswordView.requestFocus();
	}

	@UiThread
	protected void showNotFoundUserError(String message) {
		this.mUsernameView.setError(message);
		this.mUsernameView.requestFocus();
	}
}
