package org.vkedco.mobappdev.activity_lifecycle_tester;

// @author: vladimir kulyukin

import java.util.HashMap;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class ListOfUserFriends extends ListActivity {

	static HashMap<String, String> mPasswordMap = null;
	static HashMap<String, Integer> mXMLArrayMap = null;

	final static String WRONG_PASSWORD_MESSAGE = "WRONG PASSWORD";
	final static String WRONG_USER_NAME_MESSAGE = "WRONG USERNAME";

	final static String LOG_KEY = ListOfUserFriends.class.getSimpleName()
			+ "_LOG";

	static {
		mPasswordMap = new HashMap<String, String>();
		mPasswordMap.put("vladimir", "kulyukin");
		mPasswordMap.put("tanwir", "zaman");
	}

	static {
		mXMLArrayMap = new HashMap<String, Integer>();
		mXMLArrayMap.put("kulyukin", Integer.valueOf(R.array.vladimirs_friends));
		mXMLArrayMap.put("zaman",
				Integer.valueOf(R.array.tanwirs_friends));
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.profile);
		
		Log.d(ListOfUserFriends.LOG_KEY, "onCreate()");

		Intent profile = getIntent();
		final String userName = profile.getStringExtra("username");
		final String password = profile.getStringExtra("password");

		if (ListOfUserFriends.mPasswordMap.containsKey(userName)) {
			if (ListOfUserFriends.mPasswordMap.get(userName).equals(password)) {
				ArrayAdapter<CharSequence> adptr = ArrayAdapter
						.createFromResource(this,
								ListOfUserFriends.mXMLArrayMap.get(password)
										.intValue(),
								android.R.layout.simple_list_item_1);
				this.setListAdapter(adptr);
			} else {
				Toast.makeText(getApplicationContext(),
						ListOfUserFriends.WRONG_PASSWORD_MESSAGE,
						Toast.LENGTH_LONG).show();
				Log.d(ListOfUserFriends.LOG_KEY, "finishing");
				this.finish();
			}
		} else {
			Toast.makeText(getApplicationContext(),
					ListOfUserFriends.WRONG_USER_NAME_MESSAGE,
					Toast.LENGTH_LONG).show();
			Log.d(ListOfUserFriends.LOG_KEY, "finishing");
			this.finish();
		}
	}

	@Override
	protected void onStart() {
		super.onStart();
		Log.d(ListOfUserFriends.LOG_KEY, "onStart()");
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		Log.d(ListOfUserFriends.LOG_KEY, "onRestart()");
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.d(ListOfUserFriends.LOG_KEY, "onResume()");
	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.d(ListOfUserFriends.LOG_KEY, "onPause()");
	}

	@Override
	protected void onStop() {
		super.onStop();
		Log.d(ListOfUserFriends.LOG_KEY, "onStop()");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.d(LOG_KEY, "onDestroy()");
	}
}
