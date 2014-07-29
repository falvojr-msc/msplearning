package com.msplearning.android.app.widget;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import android.content.Context;
import android.widget.TextView;

import com.msplearning.android.app.R;
import com.msplearning.android.app.widget.generic.AbstractItemView;
import com.msplearning.entity.EducationalContent;

@EViewGroup(R.layout.widget_list_item)
public class EducationalContentItemView extends AbstractItemView<EducationalContent> {

	@ViewById(R.id.textview_description)
	TextView mEducationalContentTitle;

	public EducationalContentItemView(Context context) {
		super(context);
	}

	@Override
	public void bind(EducationalContent educationalContent) {
		this.mEducationalContentTitle.setText(educationalContent.getTitle());
	}
}
