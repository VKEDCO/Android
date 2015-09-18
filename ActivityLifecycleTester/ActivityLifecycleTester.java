package org.vkedco.mobappdev.activity_lifecycle_tester;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class ActivityLifecycleTesterAct extends Activity implements OnClickListener {

	EditText mEdTxtUsername;
	EditText mEdTxtPwd;
	Button mBtnSignIn;
	
	static final String LOG_KEY = ActivityLifecycleTesterAct.class.getSimpleName() + "_LOG";
	static final String LOG_FILE_NAME = "activity_lifecycle_log.txt";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Log.d(ActivityLifecycleTesterAct.LOG_KEY, "onCreate()");
		mEdTxtUsername = (EditText) findViewById(R.id.usernameTxt);
		mEdTxtPwd = (EditText) findViewById(R.id.passwdTxt);
		mEdTxtUsername.setText("student");
		mEdTxtPwd.setText("password");
		mBtnSignIn = (Button) findViewById(R.id.signInBtn);
		mBtnSignIn.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		Intent i = new Intent(getApplicationContext(), ListOfUserFriends.class);
		// you would have to encrypt user name and password here
		i.putExtra("username", mEdTxtUsername.getText().toString());
		i.putExtra("password", mEdTxtPwd.getText().toString());
    	startActivity(i); 
		
	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.d(LOG_KEY, "onPause()");
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		Log.d(LOG_KEY, "onRestart()");
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.d(LOG_KEY, "onResume()");
	}

	@Override
	protected void onStart() {
		super.onStart();
		Log.d(LOG_KEY, "onStart()");
	}

	@Override
	protected void onStop() {
		super.onStop();
		Log.d(LOG_KEY, "onStop()");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.d(LOG_KEY, "onDestroy()");
	}
}
