package com.msplearning.android.app.generic;

import org.androidannotations.annotations.EActivity;

import android.app.Application;

import com.msplearning.entity.User;

/**
 * The GenericLoggedUserAsyncActivity class extends {@link GenericAsyncActivity}. Set the loggedUser property on @AfterInject event.
 *
 * @author Venilton Falvo Junior (veniltonjr)
 */
@EActivity
public abstract class GenericLoggedUserAsyncActivity<T extends Application> extends GenericAsyncActivity<T> {

	protected static final String TAG = GenericLoggedUserAsyncActivity.class.getSimpleName();

	public static final String EXTRA_KEY_LOGGED_USER = "activity.common.loggedUser";

	public User getLoggedUser() {
		return (User) this.getIntent().getSerializableExtra(EXTRA_KEY_LOGGED_USER);
	}

	public void removeLoggedUser() {
		this.getIntent().removeExtra(EXTRA_KEY_LOGGED_USER);
	}

}
