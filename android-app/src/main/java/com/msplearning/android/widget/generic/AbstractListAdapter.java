package com.msplearning.android.widget.generic;

import java.io.Serializable;
import java.util.List;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;

@EBean
public abstract class AbstractListAdapter<E extends Serializable, T extends AbstractItemView<E>> extends BaseAdapter implements ListAdapter {

	@RootContext
	protected Context mContext;

	protected List<E> mContent;

	public void setContent(List<E> content) {
		this.mContent = content;
	}

	@Override
	public int getCount() {
		return this.mContent.size();
	}

	@Override
	public E getItem(int position) {
		return this.mContent.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	@SuppressWarnings("unchecked")
	public T getView(int position, View convertView, ViewGroup parent) {
		T personItemView = null;
		if (convertView == null) {
			personItemView = this.buildSpecificViewUsingAA();
		} else {
			personItemView = (T) convertView;
		}
		personItemView.bind(this.getItem(position));

		return personItemView;
	}

	public abstract T buildSpecificViewUsingAA();
}
