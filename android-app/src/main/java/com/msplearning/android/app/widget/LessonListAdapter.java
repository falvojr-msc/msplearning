package com.msplearning.android.app.widget;

import org.androidannotations.annotations.EBean;

import com.msplearning.android.app.widget.generic.AbstractListAdapter;
import com.msplearning.entity.Lesson;

@EBean
public class LessonListAdapter extends AbstractListAdapter<Lesson, LessonItemView> {

	@Override
	public LessonItemView buildSpecificViewUsingAA() {
		return LessonItemView_.build(super.mContext);
	}

}