package com.msplearning.android.widget.generic;

import java.io.Serializable;

import android.content.Context;
import android.widget.LinearLayout;

public abstract class AbstractItemView <E extends Serializable> extends LinearLayout {

	public AbstractItemView(Context context) {
		super(context);
	}

	public abstract void bind(E entity);
}
