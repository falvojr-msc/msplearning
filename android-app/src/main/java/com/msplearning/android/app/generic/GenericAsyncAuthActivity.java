package com.msplearning.android.app.generic;

import org.androidannotations.annotations.EActivity;

import android.app.Application;

/**
 * The GenericLoggedUserAsyncActivity class extends {@link GenericAsyncActivity}. Set the loggedUser property on @AfterInject event.
 *
 * @author Venilton Falvo Junior (veniltonjr)
 */
@EActivity
public abstract class GenericAsyncAuthActivity<T extends Application> extends GenericAsyncActivity<T> {

	protected static final String TAG = GenericAsyncAuthActivity.class.getSimpleName();

}
