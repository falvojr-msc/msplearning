package com.msplearning.android.app;

import java.util.List;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.RestService;

import android.widget.ListView;

import com.msplearning.android.app.generic.GenericAsyncAuthActivity;
import com.msplearning.android.rest.AppUserRestClient;
import com.msplearning.android.widget.UserRequestListAdapter;
import com.msplearning.entity.AppUser;

/**
 * The AccessRequestsActivity class.
 *
 * @author Renan Johannsen de Paula (renanjp)
 */
@EActivity(R.layout.activity_access_requests)
public class AccessRequestsActivity extends GenericAsyncAuthActivity<MSPLearningApplication> {
	
	@ViewById(R.id.list_view_requests)
	protected ListView mListView;
	
	@RestService
	protected AppUserRestClient mAppUserRestClient;
	
	@Bean
	protected UserRequestListAdapter mUserRequestAdapter;
	
	@AfterViews
	protected void init() {
		super.showLoadingProgressDialog();
		this.loadRequests();
	}

	@Background
	protected void loadRequests() {
		List<AppUser> appUsers = this.mAppUserRestClient.findAll(/*new AppUserId(getApp().getId(),null)*/).getEntity();
		this.refreshRequests(appUsers);
		super.dismissProgressDialog();
	}
	
	@UiThread
	protected void refreshRequests(List<AppUser> appUsers) {
		this.mUserRequestAdapter.setContent(appUsers);
		this.mListView.setAdapter(this.mUserRequestAdapter);
	}

}
