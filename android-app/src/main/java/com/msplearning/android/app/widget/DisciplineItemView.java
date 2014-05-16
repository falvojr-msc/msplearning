package com.msplearning.android.app.widget;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import android.content.Context;
import android.widget.TextView;

import com.msplearning.android.app.R;
import com.msplearning.android.app.widget.generic.AbstractItemView;
import com.msplearning.entity.Discipline;

@EViewGroup(R.layout.widget_list_item)
public class DisciplineItemView extends AbstractItemView<Discipline> {

	@ViewById(R.id.textview_description)
	TextView mDisciplineName;

	public DisciplineItemView(Context context) {
		super(context);
	}

	@Override
	public void bind(Discipline discipline) {
		this.mDisciplineName.setText(discipline.getName());
	}
}
