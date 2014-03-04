package com.msplearning.android.app.base;

import org.androidannotations.annotations.App;
import org.androidannotations.annotations.EActivity;

import android.app.Activity;
import android.app.ProgressDialog;

import com.msplearning.android.app.MSPLearningApp;
import com.msplearning.android.ext.AbstractAsyncActivity;

/**
 * The BaseActivity class, the common {@link Activity} with {@link ProgressDialog} for asynchronous operations.
 * 
 * @author Venilton Falvo Junior (veniltonjr)
 */
@EActivity
public abstract class BaseActivity extends AbstractAsyncActivity {

	@App
	protected MSPLearningApp app;

	@Override
	public MSPLearningApp getApplicationContext() {
		return this.app;
	}
}
