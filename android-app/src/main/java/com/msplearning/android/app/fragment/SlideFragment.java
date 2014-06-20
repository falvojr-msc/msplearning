package com.msplearning.android.app.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.msplearning.android.app.R;

/**
 * A dummy fragment representing a section of the app, but that simply displays dummy text.
 */
public class SlideFragment extends Fragment {

	public static final String ARG_OBJECT = "slide";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_list_item, container, false);
		Bundle args = this.getArguments();
		((TextView) rootView.findViewById(R.id.editTextContent)).setText(Integer.toString(args.getInt(ARG_OBJECT)));
		return rootView;
	}
}
