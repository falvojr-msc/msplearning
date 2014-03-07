package com.msplearning.android.app;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;

import android.content.Intent;

import com.msplearning.android.app.base.BaseActivityRestSupport;
import com.msplearning.android.ext.FacebookWebOAuthActivity;
import com.msplearning.android.ext.TwitterWebOAuthActivity;

/**
 * The RegisterActivity class.
 * 
 * @author Venilton Falvo Junior (veniltonjr)
 */
@EActivity(R.layout.activity_choose_login)
public class ChooseLoginActivity extends BaseActivityRestSupport {

	@Click(R.id.btnLogInMSPL)
	protected void logInMSPLearning() {
		Intent intent = LoginActivity_.intent(this.getApplicationContext()).get();
		this.startActivity(intent);
		this.finish();
	}

	@Click(R.id.btnLogInFacebook)
	protected void logInFacebook() {
		Intent intent = new Intent();
		intent.setClass(this, FacebookWebOAuthActivity.class);
		this.startActivity(intent);
		this.finish();
	}

	@Click(R.id.btnLogInTwitter)
	protected void logInTwitter() {
		Intent intent = new Intent();
		intent.setClass(this, TwitterWebOAuthActivity.class);
		this.startActivity(intent);
		this.finish();
	}
}
