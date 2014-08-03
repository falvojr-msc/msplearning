package com.msplearning.android.app;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OnActivityResult;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.RestService;
import org.springframework.web.client.RestClientException;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.msplearning.android.app.generic.GenericAsyncActivity;
import com.msplearning.android.app.rest.UserRestClient;
import com.msplearning.android.ext.FacebookWebOAuthActivity;
import com.msplearning.android.ext.TwitterWebOAuthActivity;
import com.msplearning.entity.User;

/**
 * The LoginActivity class. Activity which displays a login screen to the user, offering registration as well.
 *
 * @author Venilton Falvo Junior (veniltonjr)
 */
@EActivity(R.layout.activity_sign_in)
public class SignInActivity extends GenericAsyncActivity<MSPLearningApplication> {

	// Values for email and password at the time of the login attempt.
	private String mEmail;
	private String mPassword;

	// UI references.
	@ViewById(R.id.email)
	EditText mEmailView;
	@ViewById(R.id.password)
	EditText mPasswordView;

	// RESTful client.
	@RestService
	UserRestClient mUserRestClient;

	@AfterViews
	void afterViews() {
		this.mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
				if (id == R.id.login || id == EditorInfo.IME_NULL) {
					SignInActivity.this.onSignIn();
					return true;
				}
				return false;
			}
		});
	}

	@Click(R.id.button_sign_in)
	void onSignIn() {

		// Reset errors.
		this.mEmailView.setError(null);
		this.mPasswordView.setError(null);

		// Store values at the time of the login attempt.
		this.mEmail = this.mEmailView.getText().toString();
		this.mPassword = this.mPasswordView.getText().toString();

		boolean cancel = false;
		View focusView = null;

		// Check for a valid password.
		boolean isEmpty = TextUtils.isEmpty(this.mPassword);
		if (isEmpty || this.mPassword.length() < 4) {
			this.mPasswordView.setError(this.getString(isEmpty ? R.string.error_required : R.string.error_invalid_password));
			focusView = this.mPasswordView;
			cancel = true;
		}

		// Check for a valid email.
		final boolean isEmptyEmail = TextUtils.isEmpty(this.mEmail);
		if (isEmptyEmail || !Patterns.EMAIL_ADDRESS.matcher(this.mEmail).matches()) {
			this.mEmailView.setError(this.getString(isEmptyEmail ? R.string.error_required : R.string.error_invalid_email));
			focusView = this.mEmailView;
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
	void onFacebookOAuth() {
		Intent intent = new Intent();
		intent.setClass(this, FacebookWebOAuthActivity.class);
		this.startActivity(intent);
		this.finish();
	}

	@Click(R.id.button_twitter)
	void onTwitterOAuth() {
		Intent intent = new Intent();
		intent.setClass(this, TwitterWebOAuthActivity.class);
		this.startActivity(intent);
		this.finish();
	}

	@Background
	void authenticate() {
		try {
			User credential = new User(this.mEmail, this.mPassword);
			super.getApplicationContext().getAppSettings().setUser(this.mUserRestClient.authenticate(credential));
			this.redirectToDashboard();
		} catch (RestClientException exceptionAuth) {
			try {
				this.mUserRestClient.findByEmail(this.mEmail);
				this.showFieldError(this.mPasswordView, this.getString(R.string.rest_error_password_incorrect));
			} catch (RestClientException exceptionUsername) {
				OnClickListener listenerYes = new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int whichButton) {
						Intent intent = UserActivity_.intent(SignInActivity.this).get();
						intent.putExtra(UserActivity.EXTRA_KEY_EMAIL, SignInActivity.this.mEmail);
						intent.putExtra(UserActivity.EXTRA_KEY_PASSWORD, SignInActivity.this.mPassword);
						SignInActivity.this.startActivityForResult(intent, REQUEST_CODE_CREATE);
					}
				};
				OnClickListener listenerNo = new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int whichButton) {
						SignInActivity.this.showFieldError(SignInActivity.this.mEmailView, SignInActivity.this.getString(R.string.rest_error_email_not_found));
					}
				};
				this.showDialogConfirm(
						this.getString(R.string.dialog_title_confirmation),
						this.getString(R.string.dialog_message_register), listenerYes, listenerNo);
			}
		} finally {
			super.dismissProgressDialog();
		}
	}

	@OnActivityResult(REQUEST_CODE_CREATE)
	void onResult(int resultCode) {
		if (resultCode == RESULT_OK) {
			this.redirectToDashboard();
		}
	}

	@UiThread
	void showFieldError(EditText editText, String message) {
		editText.setError(message);
		editText.requestFocus();
	}

	private void redirectToDashboard() {
		DashboardActivity_.intent(this).start();
		this.finish();
	}
}
