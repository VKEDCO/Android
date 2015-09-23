package org.vkedco.mobicom.android.start_sum_activity_for_result_app;

// *****************************
// author: vladimir kulyukin
// *****************************

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;

public class CalledFibonacciNumbersAct extends Activity 
 {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Intent requestIntent = this.getIntent();
		Resources res = this.getResources();
		
		if ( requestIntent.hasExtra(res.getString(R.string.fibo_request_key))) {
			int rslt = fibo(requestIntent.getIntExtra(res.getString(R.string.fibo_request_key), 
					-1));
			if ( rslt == -1 ) {
				this.setResult(RESULT_CANCELED);
				this.finish();
			}
			else {
				Intent resultIntent = new Intent();
				resultIntent.putExtra(res.getString(R.string.fibo_result_key), rslt);
				this.setResult(RESULT_OK, resultIntent);
				
				this.finish();
			}
		}
		else {
			this.setResult(RESULT_CANCELED);
			this.finish();
		}
	}

	// compute n-th fibonacci number
	final private int fibo(int n) {
		// Since activity must return fast, restrict the values of n
		if ( n > 20 ) {
			return -1;
		}
		int prev = 0;
		int curr = 1;
		if ( n == 0 ) return prev;
		if ( n == 1 ) return curr;
		int nxt;
		while ( n > 0 ) {
			nxt = prev + curr;
			prev = curr;
			curr = nxt;
			--n;
		}
		return prev;
	}
	
	
	
	

}
