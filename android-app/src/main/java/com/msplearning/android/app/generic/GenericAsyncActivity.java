package com.msplearning.android.app.generic;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;

import android.app.Application;

import com.msplearning.android.app.ext.generic.AbstractAsyncActivity;

/**
 * The GenericAsyncActivity class extends {@link AbstractAsyncActivity}. Set the @UiThread on dismissProgressDialog method.
 *
 * @author Venilton Falvo Junior (veniltonjr)
 */
@EActivity
public abstract class GenericAsyncActivity<T extends Application> extends AbstractAsyncActivity<T> {

	protected static final String TAG = GenericAsyncActivity.class.getSimpleName();

	@UiThread
	@Override
	public void dismissProgressDialog() {
		super.dismissProgressDialog();
	}

}
