package org.vkedco.mobappdev.rumi_quatrain_fragments;


import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

public class RumiQuatrainMainActivity extends Activity {
    static final String LOGTAG = RumiQuatrainMainActivity.class.getSimpleName() + "_TAG";
	
    static Resources mRes = null;
    static FragmentManager mFrgmntMngr = null;
    static RumiQuatrainMainActivity mThisAct = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
		Log.d(LOGTAG, "onCreate()");
        super.onCreate(savedInstanceState);
		FragmentManager.enableDebugLogging(true);
        setContentView(R.layout.rumi_vid_fragments_main);
        mRes = getResources();
        mFrgmntMngr = getFragmentManager();
        mThisAct = this;
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
		Log.d(LOGTAG, "onAttachFragment(): fragment id = " + fragment.getId());
        super.onAttachFragment(fragment);
    }

    @Override
    public void onStart() {
    	super.onStart();
		Log.d(LOGTAG, "onStart()");
    }
    
    @Override
    public void onResume() {
    	super.onResume();
		Log.d(LOGTAG, "onResume()");
    }
    
    @Override
    public void onPause() {
    	super.onPause();
		Log.d(LOGTAG, "onPause()");
    }
    
    @Override
    public void onStop() {
    	super.onStop();
		Log.d(LOGTAG, "onStop()");
    }
    
    @Override
    public void onSaveInstanceState(Bundle savedState) {
    	super.onSaveInstanceState(savedState);
    	Log.d(LOGTAG, "onSaveInstanceState()");
        
    }

    @Override
    public void onDestroy() {
    	super.onDestroy();
		Log.d(LOGTAG, "onDestroy()");
    }
    
    static boolean isInLandscapeOrientation() {
        return mRes.getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE;
    }

    /**
     * displayQuatrainText() is used to show the details of a selected item, either by
     * displaying a fragment in-place in the current UI, or starting a
     * whole new activity in which it is displayed.
     */
    void displayQuatrainText(int quatrain_index) {
    	Log.d(LOGTAG, "displayQuatrainText(" + quatrain_index + ")");

        if ( isInLandscapeOrientation() ) {
            // Check what fragment is shown, replace if needed.
            QuatrainTextDisplayFragment qTxtFrgmnt = (QuatrainTextDisplayFragment)
                    mFrgmntMngr.findFragmentById(R.id.quatrain_text);
            if (qTxtFrgmnt == null || qTxtFrgmnt.getDisplayedQuatrainIndex() != quatrain_index) {
                // Make new fragment to show this selection.
                qTxtFrgmnt = QuatrainTextDisplayFragment.newInstance(quatrain_index);
                
                // Execute a transaction, replacing any existing
                // fragment inside the frame with the new one.
                Log.d(LOGTAG, "about to run FragmentTransaction...");
                FragmentTransaction frag_trans
                        = mFrgmntMngr.beginTransaction();
                
                frag_trans.setCustomAnimations(R.animator.bounce_in_down,
                		R.animator.fade_out);
                frag_trans.setCustomAnimations(R.animator.bounce_in_down,
                		R.animator.slide_out_right);
                //ft.setCustomAnimations(R.animator.fade_in,
                //		R.animator.fade_out);
                //ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                frag_trans.replace(R.id.quatrain_text, qTxtFrgmnt);
                //frag_trans.addToBackStack("QUATRAIN_TEXT_FRAGMENT");
                frag_trans.commit();
            }

        } else {
            // Otherwise we need to launch a new activity to display
            // the dialog fragment with selected text.
            Intent intent = new Intent();
            intent.setClass(mThisAct, QuatrainTextDisplayActivity.class);
            intent.putExtra(mRes.getString(R.string.quat_index_key), quatrain_index);
            this.startActivity(intent);
        }
    }
}
