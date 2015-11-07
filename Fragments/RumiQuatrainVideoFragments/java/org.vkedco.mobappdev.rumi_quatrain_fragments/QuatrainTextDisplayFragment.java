package org.vkedco.mobappdev.rumi_quatrain_fragments;

import android.app.Activity;
import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.VideoView;

public class QuatrainTextDisplayFragment extends Fragment {
	
	static final String LOGTAG = QuatrainTextDisplayFragment.class.getSimpleName() + "_TAG";
	int mQuatrainIndex = 0;

	public static QuatrainTextDisplayFragment newInstance(int index) {
        Log.d(LOGTAG, "newInstance(" + index + ")");

        QuatrainTextDisplayFragment quatFrgmnt = new QuatrainTextDisplayFragment();

        // Supply index input as an argument.
		Bundle args = new Bundle();
		args.putInt(RumiQuatrainMainActivity.mRes.getString(R.string.quat_index_key), 
					index);
		quatFrgmnt.setArguments(args);
		return quatFrgmnt;
	}
	
	public static QuatrainTextDisplayFragment newInstance(Bundle bundle) {
		int index = bundle.getInt(RumiQuatrainMainActivity.mRes.getString(R.string.quat_index_key), 
				0);
        return newInstance(index);
	}

    @Override
    public void onInflate(Activity myActivity, AttributeSet attrs, Bundle savedInstanceState) {
    	Log.d(LOGTAG, "onInflate(): AttributeSet key-value pairs:");
    	for(int i=0; i<attrs.getAttributeCount(); i++)
            Log.d(LOGTAG, "" + attrs.getAttributeName(i) +
            		" = " + attrs.getAttributeValue(i));
    	super.onInflate(myActivity, attrs, savedInstanceState);
    }

	@Override
    public void onAttach(Activity myActivity) {
    	Log.d(LOGTAG, "onAttach(): activity is: " +
    			myActivity);
    	super.onAttach(myActivity);
    }

    @Override
    public void onCreate(Bundle myBundle) {
    	super.onCreate(myBundle);
    	
    	if(myBundle != null) {
    		Log.d(LOGTAG, "Bundle keys:");
            for(String key : myBundle.keySet()) {
                Log.d(LOGTAG, "    " + key);
            }
    	}
    	else {
            Log.d(LOGTAG, "Bundle is null");
    	}
    	
    	
    	mQuatrainIndex = getArguments()
    				.getInt(RumiQuatrainMainActivity.mRes.getString(R.string.quat_index_key),
    						0);
    }

    public int getDisplayedQuatrainIndex() {
    	return mQuatrainIndex;
    }

	@Override
	public View onCreateView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
        Log.d(LOGTAG, "onCreateView(): container = " + container);

        if(container == null) {
        	Log.d(LOGTAG, "Container is null.");
        	return null;
        }
       
        
        View v = inflater.inflate(R.layout.quatrain_text_display, container, false);
		TextView tvQuatrainText = (TextView) v.findViewById(R.id.tvQuatrainText);
		VideoView vvQuatrain=(VideoView)v.findViewById(R.id.videoViewQuatrain);
		String[] quatrain_lines = null;
		StringBuilder sb = new StringBuilder();
		
		final String uriSourceRose = "android.resource://" + getActivity().getPackageName() + "/" +
				R.raw.rose;
		final String uriSourcePinkRose = "android.resource://" + getActivity().getPackageName() + "/" +
				R.raw.prinkrose;
		final String uriSourceClover = "android.resource://" + getActivity().getPackageName() + "/" +
				R.raw.clover;
		final String uriSourceMushrooms = "android.resource://" + getActivity().getPackageName() + "/" +
				R.raw.mushrooms;
		final String uriSourceRussianOrnament = "android.resource://" + getActivity().getPackageName() + "/" +
				R.raw.russianornament;
		Uri videoUri = null;
		
		switch ( mQuatrainIndex ) {
		case 0: quatrain_lines = this.getActivity().getResources().getStringArray(R.array.q12);
				sb.append("Quatrain 12\n\n");
				//uriSource += "" + R.raw.plant_01;
				videoUri = Uri.parse(uriSourceRose);
				break;
		case 1: quatrain_lines = this.getActivity().getResources().getStringArray(R.array.q77);
				sb.append("Quatrain 77\n\n");
				videoUri = Uri.parse(uriSourcePinkRose);
				break;
		case 2: quatrain_lines = this.getActivity().getResources().getStringArray(R.array.q116);
				sb.append("Quatrain 116\n\n");
				videoUri = Uri.parse(uriSourceClover);
				break;
		case 3: quatrain_lines = this.getActivity().getResources().getStringArray(R.array.q494);
				sb.append("Quatrain 494\n\n");
				//uriSource += "" + R.raw.plant_04;
				videoUri = Uri.parse(uriSourceMushrooms);
				break;
		case 4: quatrain_lines = this.getActivity().getResources().getStringArray(R.array.q549);
				sb.append("Quatrain 549\n\n");
				videoUri = Uri.parse(uriSourceRussianOrnament);
				break;
		}
		
		for(String ln : quatrain_lines ) {
			sb.append(ln + "\n");
		}
		tvQuatrainText.setText(sb.toString());
		vvQuatrain.setVideoURI(videoUri);
		vvQuatrain.start();
		return v;
	}

    @Override
    public void onActivityCreated(Bundle savedState) {
    	super.onActivityCreated(savedState);
    	if(savedState != null) {
    		Log.d(LOGTAG, "onActivityCreated(): Bundle savedState contains:");
            for(String key : savedState.keySet()) {
                Log.d(LOGTAG, "    " + key);
            }
    	}
    	else {
            Log.d(LOGTAG, "onActivityCreated(): Bundle savedState is null");
    	}
        
    }

    @Override
    public void onStart() {
    	super.onStart();
    	Log.d(LOGTAG, "onStart(): qindex = " + mQuatrainIndex);
    }
    	

    @Override
    public void onResume() {
    	super.onResume();
    	Log.d(LOGTAG, "onResume(): qindex = " + mQuatrainIndex);
    	
    }

    @Override
    public void onPause() {
    	super.onPause();
    	Log.d(LOGTAG, "onPause(): qindex = " + mQuatrainIndex);
    	
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
    	super.onSaveInstanceState(outState);
    	Log.d(LOGTAG, "onSaveInstanceState(): qindex = " + mQuatrainIndex);
       
    }

    @Override
    public void onStop() {
    	super.onStop();
    	Log.d(LOGTAG, "onStop(): qindex = " + mQuatrainIndex);
    }

    @Override
    public void onDestroyView() {
    	super.onDestroyView();
    	Log.d(LOGTAG, "onDestroyView(): qindex =  " + mQuatrainIndex); 
    	
    }

    @Override
    public void onDestroy() {
    	super.onDestroy();
    	Log.d(LOGTAG, "onDestroy()" + mQuatrainIndex);
    }

    @Override
    public void onDetach() {
    	super.onDetach();
    	Log.d(LOGTAG, "onDetach()" + mQuatrainIndex);	
    }
}
