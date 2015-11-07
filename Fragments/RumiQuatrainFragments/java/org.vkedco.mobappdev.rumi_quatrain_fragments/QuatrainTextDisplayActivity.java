package org.vkedco.mobappdev.rumi_quatrain_fragments;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;

public class QuatrainTextDisplayActivity extends Activity {

	static final String LOGTAG = QuatrainTextDisplayActivity.class.getSimpleName() + "_TAG";
	
    @Override
	public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	Log.d(LOGTAG, "onCreate()");

        if (getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE) {
            // If the screen is in landscape, it means
        	// the RumiQuatrainMainActivity (the main activity) is 
        	// displayed with QuatrainNumbersFragment on the left
        	// and the QuatrainTextFragment on the right
        	// QuatrainTextActivity is not needed in landscape mode. 
        	// So finish and return.
        	Log.d(LOGTAG, "In Landscape Mode");
            finish();
            return;
        }

        if(getIntent() != null) {
        	Log.d(LOGTAG, "QTDAct Bundle's keys:");
        	for(String k : getIntent().getExtras().keySet()) {
        		Log.d(LOGTAG, "" + k + " --> " + getIntent().getIntExtra(k, -1));
        	}
            QuatrainTextDisplayFragment quatrain_source =
        	    QuatrainTextDisplayFragment.newInstance(getIntent().getExtras());

            this
            	.getFragmentManager()
            	.beginTransaction()
                .replace(android.R.id.content, quatrain_source)
                .commit();
        }
    }
}
