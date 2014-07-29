package com.msplearning.android.app.widget;

import org.androidannotations.annotations.EBean;

import com.msplearning.android.app.widget.generic.AbstractListAdapter;
import com.msplearning.entity.AppUser;

@EBean
public class AccessRequestListAdapter extends AbstractListAdapter<AppUser, AccessRequestItemView> {

	@Override
	public AccessRequestItemView buildSpecificViewUsingAA() {
		return AccessRequestItemView_.build(super.mContext);
	}

}