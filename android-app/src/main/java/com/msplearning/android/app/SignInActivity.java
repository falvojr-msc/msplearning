package com.msplearning.android.app;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OnActivityResult;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.RestService;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.msplearning.android.app.ext.FacebookWebOAuthActivity;
import com.msplearning.android.app.ext.TwitterWebOAuthActivity;
import com.msplearning.android.app.generic.GenericAsyncActivity;
import com.msplearning.android.rest.UserRestClient;
import com.msplearning.entity.User;
import com.msplearning.entity.common.Response;

/**
 * The LoginActivity class. Activity which displays a login screen to the user, offering registration as well.
 *
 * @author Venilton Falvo Junior (veniltonjr)
 */
@EActivity(R.layout.activity_sign_in)
public class SignInActivity extends GenericAsyncActivity<MSPLearningApplication> {

	// Intent's extra keys
	public static final String EXTRA_KEY_PASSWORD = "activity.signin.password";
	public static final String EXTRA_KEY_USERNAME = "activity.signin.username";

	// Intent's request code
	private static final int REQUEST_CODE_USER_REGISTER = 0;

	// Values for email and password at the time of the login attempt.
	private String mUsername;
	private String mPassword;

	// UI references.
	@ViewById(R.id.username)
	protected EditText mUsernameView;
	@ViewById(R.id.password)
	protected EditText mPasswordView;

	// RESTful client.
	@RestService
	protected UserRestClient mUserRESTfulClient;

	@AfterViews
	protected void init() {
		this.mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
				if ((id == R.id.login) || (id == EditorInfo.IME_NULL)) {
					SignInActivity.this.onSignIn();
					return true;
				}
				return false;
			}
		});
	}

	@Click(R.id.button_sign_in)
	protected void onSignIn() {

		// Reset errors.
		this.mUsernameView.setError(null);
		this.mPasswordView.setError(null);

		// Store values at the time of the login attempt.
		this.mUsername = this.mUsernameView.getText().toString();
		this.mPassword = this.mPasswordView.getText().toString();

		boolean cancel = false;
		View focusView = null;

		// Check for a valid password.
		boolean isEmpty = TextUtils.isEmpty(this.mPassword);
		if (isEmpty || (this.mPassword.length() < 4)) {
			this.mPasswordView.setError(this.getString(isEmpty ? R.string.error_field_required : R.string.error_invalid_password));
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
			// There was an error; don't attempt login and focus the first form field with an error.
			focusView.requestFocus();
		} else {
			super.showLoadingProgressDialog();
			this.authenticate();
		}
	}

	@Click(R.id.button_facebook)
	protected void onFacebookOAuth() {
		Intent intent = new Intent();
		intent.setClass(this, FacebookWebOAuthActivity.class);
		this.startActivity(intent);
		this.finish();
	}

	@Click(R.id.button_twitter)
	protected void onTwitterOAuth() {
		Intent intent = new Intent();
		intent.setClass(this, TwitterWebOAuthActivity.class);
		this.startActivity(intent);
		this.finish();
	}

	@Background
	protected void authenticate() {

		final User userAuth = new User();
		userAuth.setUsername(this.mUsername);
		userAuth.setPassword(this.mPassword);

		try {
			Response<User> responseAuth = this.mUserRESTfulClient.authenticate(userAuth);
			if (responseAuth.hasBusinessMessage()) {
				final Response<Void> responseUsername = this.mUserRESTfulClient.findByUsername(userAuth.getUsername());
				if (responseUsername.hasBusinessMessage()) {

					OnClickListener listenerYes = new OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int whichButton) {
							Intent intent = RegisterActivity_.intent(SignInActivity.this).get();
							intent.putExtra(EXTRA_KEY_USERNAME, SignInActivity.this.mUsername);
							intent.putExtra(EXTRA_KEY_PASSWORD, SignInActivity.this.mPassword);
							SignInActivity.this.startActivityForResult(intent, REQUEST_CODE_USER_REGISTER);
						}
					};
					OnClickListener listenerNo = new OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int whichButton) {
							SignInActivity.this.showFieldError(SignInActivity.this.mUsernameView, responseUsername.getBusinessMessage());
						}
					};

					this.showDialogConfirm( this.getString(R.string.title_dialog_register), this.getString(R.string.message_dialog_register), listenerYes, listenerNo);

				} else {
					this.showFieldError(this.mPasswordView, responseAuth.getBusinessMessage());
				}
			} else {
				super.getApplicationContext().getAppSettings().setUser(responseAuth.getEntity());
				this.redirectToDashboard();
			}
		} catch (Exception exception) {
			this.showDialogAlert(exception.getMessage(), null);
		} finally {
			super.dismissProgressDialog();
		}
	}

	@OnActivityResult(REQUEST_CODE_USER_REGISTER)
	protected void onResult(int resultCode) {
		if (resultCode == RESULT_OK) {
			this.redirectToDashboard();
		} else if (resultCode == RESULT_CANCELED) {
			this.showDialogAlert("Unexpected error", null);
		}
	}

	@UiThread
	protected void showFieldError(EditText editText, String message) {
		editText.setError(message);
		editText.requestFocus();
	}

	private void redirectToDashboard() {
		DashboardActivity_.intent(this).start();
		this.finish();
	}
}
