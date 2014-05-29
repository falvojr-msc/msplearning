package com.msplearning.android.app.widget;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import android.content.Context;
import android.widget.TextView;

import com.msplearning.android.app.R;
import com.msplearning.android.app.widget.generic.AbstractItemView;
import com.msplearning.entity.Lesson;

@EViewGroup(R.layout.widget_list_item)
public class LessonItemView extends AbstractItemView<Lesson> {

	@ViewById(R.id.textview_description)
	TextView mLessonName;

	public LessonItemView(Context context) {
		super(context);
	}

	@Override
	public void bind(Lesson lesson) {
		this.mLessonName.setText(lesson.getName());
	}
}
