package com.msplearning.android.app;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.RestService;
import org.springframework.web.client.RestClientException;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.msplearning.android.app.generic.GenericAsyncAuthActivity;
import com.msplearning.android.app.rest.AppUserRestClient;
import com.msplearning.entity.AppUser;
import com.msplearning.entity.AppUserId;
import com.msplearning.entity.Teacher;

/**
 * The DashboardActivity class.
 *
 * @author Venilton Falvo Junior (veniltonjr)
 */
@EActivity(R.layout.activity_dashboard)
public class DashboardActivity extends GenericAsyncAuthActivity<MSPLearningApplication> {

	@ViewById(R.id.button_manage_view_educational_content)
	Button mButtonManageViewEducationalContent;
	@ViewById(R.id.button_access_requests)
	Button mButtonAccessRequests;
	@ViewById(R.id.button_edit_profile)
	Button mButtonEditProfile;
	@ViewById(R.id.button_info)
	Button mButtonInfo;

	@RestService
	AppUserRestClient mAppUserRestClient;
	
	private boolean mIsTeacherOrAdmin;

	@SuppressLint("NewApi")
	@AfterViews
	void afterViews() {
		super.getActionBar().setSubtitle(R.string.app_subtitle_dashboard);

		if (super.getUser() == null) {
			this.resolveContentManagementCommonality(null);
		} else {
			this.findAppUserRelationship();
		}
	}

	@Background
	void findAppUserRelationship() {
		Long idApp = super.getApp().getId();
		Long idUser = super.getUser().getId();
		AppUser appUser;
		try {
			appUser = this.mAppUserRestClient.findById(idApp, idUser);
		} catch (RestClientException notFoundException) {
			appUser = new AppUser();
			appUser.setId(new AppUserId(idApp, idUser));
			appUser = this.mAppUserRestClient.insert(appUser);
			this.showToastAccessRequest();
		}
		this.resolveContentManagementCommonality(appUser);
	}

	@UiThread
	void showToastAccessRequest() {
		Toast.makeText(this, this.getString(R.string.toast_access_request), Toast.LENGTH_LONG).show();
	}

	@UiThread
	void resolveContentManagementCommonality(AppUser appUser) {
		if (appUser == null) {
			this.mButtonManageViewEducationalContent.setText(this.getString(R.string.action_view_educational_content));
		} else {
			mIsTeacherOrAdmin = super.getUser() instanceof Teacher || appUser.isAdmin();

			this.mButtonManageViewEducationalContent.setText(this.getString(mIsTeacherOrAdmin ? R.string.action_manage_disciplines : R.string.action_view_educational_content));
			this.mButtonManageViewEducationalContent.setEnabled(appUser.isActive());
			if (appUser.isAdmin()) {
				this.mButtonAccessRequests.setVisibility(View.VISIBLE);
			}
		}
	}

	@Click(R.id.button_manage_view_educational_content)
	void onManageViewEducationalContent() {
		if (mIsTeacherOrAdmin) {
			DisciplineListActivity_.intent(this).start();
		} else {
			Intent intent = new Intent(this, EducationalContentDemoActivity.class);
			startActivity(intent);
		}
	}

	@Click(R.id.button_access_requests)
	void onAccessRequests() {
		AccessRequestListActivity_.intent(this).start();
	}

	@Click(R.id.button_edit_profile)
	void onEditProfile() {
		//TODO: Edit Profile Functionality.
	}

	@Click(R.id.button_info)
	void onInfo() {
		//TODO: Info Functionality.
	}
}
