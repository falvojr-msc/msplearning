package com.msplearning.android.app.widget;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import android.content.Context;
import android.widget.TextView;

import com.msplearning.android.app.R;
import com.msplearning.android.app.widget.generic.AbstractItemView;
import com.msplearning.entity.AppUser;

@EViewGroup(R.layout.widget_list_item)
public class AccessRequestItemView extends AbstractItemView<AppUser> {

	@ViewById(R.id.textview_description)
	TextView mUserName;

	public AccessRequestItemView(Context context) {
		super(context);
	}

	@Override
	public void bind(AppUser appUser) {
		this.mUserName.setText(appUser.getId().getUser().getFirstName());
	}
}
