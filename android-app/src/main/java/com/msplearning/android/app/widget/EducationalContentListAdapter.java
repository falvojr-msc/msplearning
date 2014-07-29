package com.msplearning.android.app.widget;

import org.androidannotations.annotations.EBean;

import com.msplearning.android.app.widget.generic.AbstractListAdapter;
import com.msplearning.entity.EducationalContent;

@EBean
public class EducationalContentListAdapter extends AbstractListAdapter<EducationalContent, EducationalContentItemView> {

	@Override
	public EducationalContentItemView buildSpecificViewUsingAA() {
		return EducationalContentItemView_.build(super.mContext);
	}
}