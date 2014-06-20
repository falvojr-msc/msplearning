package com.msplearning.android.app.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * A {@link android.support.v4.app.FragmentStatePagerAdapter} that returns a fragment representing an object in the collection.
 */
public class SlidePagerAdapter extends FragmentStatePagerAdapter {

	public SlidePagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int i) {
		Fragment fragment = new SlideFragment();
		Bundle args = new Bundle();
		args.putInt(SlideFragment.ARG_OBJECT, i + 1); // Our object is just an integer :-P
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public int getCount() {
		// For this contrived example, we have a 100-object collection.
		return 100;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return "Slide " + (position + 1);
	}
}