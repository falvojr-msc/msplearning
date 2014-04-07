package com.msplearning.android.app;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import android.content.Intent;
import android.widget.TextView;

import com.msplearning.android.app.generic.GenericAsyncAuthActivity;

/**
 * The DisciplineActivity class.
 *
 * @author Venilton Falvo Junior (veniltonjr)
 */
@EActivity(R.layout.activity_dashboard)
public class DashboardActivity extends GenericAsyncAuthActivity<MSPLearningApplication> {

	@ViewById(R.id.welcome)
	protected TextView mWelcome;

	@AfterInject
	protected void init() {
		if (super.getApplicationContext().getAppSettings().getUser() != null) {
			this.showWelcomeMessage();
		}
	}

	@UiThread
	protected void showWelcomeMessage() {
		this.mWelcome.setText(String.format("Welcome, %s!", super.getApplicationContext().getAppSettings().getUser().getFirstName()));
	}

	@Click(R.id.button_content)
	protected void onContentManagementOrView() {
		Intent intent = DisciplineListActivity_.intent(this.getApplicationContext()).get();
		this.startActivity(intent);
		this.finish();
	}

}
