package com.msplearning.android.app;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;

import android.content.Intent;

import com.msplearning.android.app.generic.GenericAsyncRestActivity;

/**
 * The DisciplineActivity class.
 * 
 * @author Venilton Falvo Junior (veniltonjr)
 */
@EActivity(R.layout.activity_dashboard)
public class DashboardActivity extends GenericAsyncRestActivity<MSPLearningApplication> {

	@Click
	protected void btnContentManagement() {
		Intent intent = ContentManagementActivity_.intent(this.getApplicationContext()).get();
		this.startActivity(intent);
		this.finish();
	}

}
