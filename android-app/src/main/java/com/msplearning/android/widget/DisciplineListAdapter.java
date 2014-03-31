package com.msplearning.android.widget;

import org.androidannotations.annotations.EBean;

import com.msplearning.android.widget.generic.AbstractListAdapter;
import com.msplearning.entity.Discipline;

@EBean
public class DisciplineListAdapter extends AbstractListAdapter<Discipline, DisciplineItemView> {

	@Override
	public DisciplineItemView buildSpecificViewUsingAA() {
		return DisciplineItemView_.build(super.mContext);
	}

}