package org.vkedco.mobicom.android.start_sum_activity_for_result_app;

/******************************************************************************
This is a caller activity that calls two activities: the first called
activity, CalledFibonacciNumbers.java, computes fibonacci numbers; the
second called activity, CalledPrimeNumbersAct.java, computes prime numbers.

 author: vladimir kulyukin
********************************************************************************/
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CallerAct extends Activity 
	implements OnClickListener
{
	// request codes
	final static int FIBO_REQUEST_CODE  = 1;
	final static int PRIME_REQUEST_CODE = 2;
	
	// names implicit intent actions to launch called activities
	final static String COMPUTE_FIBO_ACTION =  "org.vkedco.android.mobicom.fibonacci";
	final static String COMPUTE_PRIME_ACTION = "org.vkedco.android.mobicom.primes";
	
	EditText mEdTxtFiboN = null;
	EditText mEdTxtPrime  = null;
	
	Button mBtnExplFiboN  = null;
	Button mBtnExplPrime = null;
	Button mBtnImplFiboN  = null;
	Button mBtnImplPrime = null;
	
	Resources mRes = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start_activity_for_result_main_layout);
		
		mEdTxtFiboN  = (EditText) this.findViewById(R.id.edTxtFibocn);
		mEdTxtPrime  = (EditText) this.findViewById(R.id.edTxtPrime);
		
		mBtnExplFiboN  = (Button) this.findViewById(R.id.btnExplFibcn);
		mBtnExplPrime = (Button) this.findViewById(R.id.btnExplPrime);
		mBtnImplFiboN  = (Button) this.findViewById(R.id.btnImplFibcn);
		mBtnImplPrime = (Button) this.findViewById(R.id.btnImplPrime);
		
		mBtnExplFiboN.setOnClickListener(this);
		mBtnExplPrime.setOnClickListener(this);
		mBtnImplFiboN.setOnClickListener(this);
		mBtnImplPrime.setOnClickListener(this);
		
		mRes = getResources();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.start_activity_for_result_main, menu);
		return true;
	}
	
	@Override
	public void onClick(View v) {
		Button clickedButton = (Button) v;
		int nth;
		switch ( clickedButton.getId() ) {
		case R.id.btnExplFibcn:
			try {
				nth = Integer.parseInt(mEdTxtFiboN.getText().toString());
			}
			catch (NumberFormatException nfe) {
				Toast.makeText(getApplicationContext(), nfe.toString(), Toast.LENGTH_LONG).show();
				return;
			}
			Intent explFiboIntent = new Intent(this, CalledFibonacciNumbersAct.class);
			explFiboIntent
				.putExtra(mRes.getString(R.string.fibo_request_key), nth);
			startActivityForResult(explFiboIntent, CallerAct.FIBO_REQUEST_CODE); 
			return;
		case R.id.btnExplPrime:
			try { 
				nth = Integer.parseInt(this.mEdTxtPrime.getText().toString());
			} catch (NumberFormatException nfe) {
				Toast.makeText(getApplicationContext(), nfe.toString(), Toast.LENGTH_LONG).show();
				return;
			}
			Intent explPrimeIntent = new Intent(this, CalledPrimeNumbersAct.class);
			explPrimeIntent.putExtra(mRes.getString(R.string.prime_request_key), nth);
			startActivityForResult(explPrimeIntent, CallerAct.PRIME_REQUEST_CODE);
			return;
		case R.id.btnImplFibcn:
			try {
				nth = Integer.parseInt(mEdTxtFiboN.getText().toString());
			}
			catch (NumberFormatException nfe) {
				Toast.makeText(getApplicationContext(), nfe.toString(), Toast.LENGTH_LONG).show();
				return;
			}
			Intent implFiboIntent = new Intent(CallerAct.COMPUTE_FIBO_ACTION);
			implFiboIntent.putExtra(mRes.getString(R.string.fibo_request_key), nth);
			startActivityForResult(implFiboIntent, CallerAct.FIBO_REQUEST_CODE);
			return;
		case R.id.btnImplPrime:
			try {
				nth = Integer.parseInt(mEdTxtPrime.getText().toString());
			}
			catch (NumberFormatException nfe) {
				Toast.makeText(getApplicationContext(), nfe.toString(), Toast.LENGTH_LONG).show();
				return;
			}
			Intent implPrimeIntent = new Intent(CallerAct.COMPUTE_PRIME_ACTION);
			implPrimeIntent.putExtra(mRes.getString(R.string.prime_request_key), nth);
			startActivityForResult(implPrimeIntent, CallerAct.PRIME_REQUEST_CODE);
			return;
		}
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent returnedData) {
		super.onActivityResult(requestCode, resultCode, returnedData);
		
		switch ( requestCode ) {
		case CallerAct.FIBO_REQUEST_CODE:
			if ( resultCode == Activity.RESULT_OK ) {
				if ( returnedData.hasExtra(mRes.getString(R.string.fibo_result_key)) ) {
					Toast.makeText(this, 
							"Fibo = " + 
									returnedData
										.getIntExtra(mRes.getString(R.string.fibo_result_key), -1),
							Toast.LENGTH_LONG).show();
				}
				else {
					Toast.makeText(this, 
							"Fibo Computation Failure",
							Toast.LENGTH_LONG).show();
				}
			}
			return;
		case CallerAct.PRIME_REQUEST_CODE:
			if ( resultCode == Activity.RESULT_OK ) {
				if ( returnedData.hasExtra(mRes.getString(R.string.prime_result_key))) {
					int nth_prime = returnedData.getIntExtra(mRes.getString(R.string.prime_result_key), -1);
					Toast.makeText(this, "Prime = " + nth_prime, Toast.LENGTH_LONG).show();
				}
				else {
					Toast.makeText(this, "Prime Computation Failure", Toast.LENGTH_LONG).show();
				}
			}
			return;
		}
	}
	

}
