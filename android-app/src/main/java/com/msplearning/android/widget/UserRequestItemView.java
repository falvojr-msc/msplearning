package com.msplearning.android.widget;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import android.content.Context;
import android.widget.TextView;

import com.msplearning.android.app.R;
import com.msplearning.android.widget.generic.AbstractItemView;
import com.msplearning.entity.AppUser;

@EViewGroup(R.layout.widget_list_item)
public class UserRequestItemView extends AbstractItemView<AppUser> {

	@ViewById(R.id.textview_description)
	TextView mUserName;

	public UserRequestItemView(Context context) {
		super(context);
	}

	@Override
	public void bind(AppUser appUser) {
		this.mUserName.setText(appUser.getId().getUser().getFirstName());
	}
}
