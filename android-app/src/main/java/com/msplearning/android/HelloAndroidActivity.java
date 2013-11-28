package com.msplearning.android;

import android.os.Bundle;
import android.view.Menu;

import com.actionbarsherlock.app.SherlockActivity;

public class HelloAndroidActivity extends SherlockActivity {

    /**
     * Called when the activity is first created.
     * @param savedInstanceState If the activity is being re-initialized after 
     * previously being shut down then this Bundle contains the data it most 
     * recently supplied in onSaveInstanceState(Bundle). <b>Note: Otherwise it is null.</b>
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(com.actionbarsherlock.view.Menu menu) {
    	return super.onCreateOptionsMenu(menu);
    }

}

