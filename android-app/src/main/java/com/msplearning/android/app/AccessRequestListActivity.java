package com.msplearning.android.app;

import java.util.Arrays;
import java.util.List;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.RestService;

import android.view.MenuItem;
import android.widget.ListView;

import com.msplearning.android.app.generic.GenericActivityListView;
import com.msplearning.android.app.rest.AppUserRestClient;
import com.msplearning.android.app.widget.AccessRequestItemView;
import com.msplearning.android.app.widget.AccessRequestListAdapter;
import com.msplearning.android.app.widget.generic.AbstractListAdapter;
import com.msplearning.entity.AppUser;

/**
 * The AccessRequestsActivity class.
 *
 * @author Renan Johannsen de Paula (renanjp)
 */
@EActivity(R.layout.activity_access_request_list)
public class AccessRequestListActivity extends GenericActivityListView<AppUser> {

	@ViewById(R.id.list_view_access_requests)
	protected ListView mListView;

	@RestService
	protected AppUserRestClient mAppUserRestClient;

	@Bean
	protected AccessRequestListAdapter mUserRequestAdapter;

	@Override
	protected ListView getListView() {
		return this.mListView;
	}

	@Override
	protected AbstractListAdapter<AppUser, AccessRequestItemView> getListAdapter() {
		return this.mUserRequestAdapter;
	}

	@Override
	protected List<AppUser> findListItens() {
		return Arrays.asList(this.mAppUserRestClient.findAccessRequests(this.getApp().getId(),this.getUser().getId()));
	}

	@Override
	protected void onContextItemSelected(MenuItem item, AppUser selectedItem) {

	}

}
