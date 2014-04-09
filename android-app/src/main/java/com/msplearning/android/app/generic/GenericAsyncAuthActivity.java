package com.msplearning.android.app.generic;

import org.androidannotations.annotations.EActivity;

import com.msplearning.android.app.MSPLearningApplication;
import com.msplearning.entity.App;
import com.msplearning.entity.AppUserId;
import com.msplearning.entity.User;

/**
 * The GenericLoggedUserAsyncActivity class extends {@link GenericAsyncActivity}.
 *
 * @author Venilton Falvo Junior (veniltonjr)
 */
@EActivity
public abstract class GenericAsyncAuthActivity<T extends MSPLearningApplication> extends GenericAsyncActivity<T> {

	protected static final String TAG = GenericAsyncAuthActivity.class.getSimpleName();

	/**
	 * @return Logged {@link User}.
	 */
	public User getUser() {
		AppUserId appSettings = super.getApplicationContext().getAppSettings();
		return appSettings == null ? null : appSettings.getUser();
	}

	/**
	 * @return Current {@link App}.
	 */
	public App getApp() {
		AppUserId appSettings = super.getApplicationContext().getAppSettings();
		return appSettings == null ? null : appSettings.getApp();
	}


}
