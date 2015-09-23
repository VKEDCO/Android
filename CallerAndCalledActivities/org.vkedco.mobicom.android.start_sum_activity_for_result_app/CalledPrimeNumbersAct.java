package org.vkedco.mobicom.android.start_sum_activity_for_result_app;

// *****************************
// author: vladimir kulyukin
// *****************************

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;

public class CalledPrimeNumbersAct extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Intent requestIntent = this.getIntent();
		Resources res = this.getResources();
		
		if ( requestIntent.hasExtra(res.getString(R.string.prime_request_key))) {
			int rslt = nth_prime(requestIntent.getIntExtra(res.getString(R.string.prime_request_key), 
					-1));
			if ( rslt == -1 ) {
				this.setResult(RESULT_CANCELED);
				this.finish();
			}
			else {
				Intent resultIntent = new Intent();
				resultIntent.putExtra(res.getString(R.string.prime_result_key), rslt);
				this.setResult(RESULT_OK, resultIntent);
				this.finish();
			}
		}
		else {
			this.setResult(RESULT_CANCELED);
			this.finish();
		}
	}
	
	// compute n-th prime
	private static int nth_prime(int n) {
		// Since activity must return fast, restrict the values of n
		if ( n > 20 ) {
			return -1;
		}
		if ( n == 0 ) return 2;
		if ( n == 1 ) return 3;
		int prime_count = 1;
		int curr_num = 4;
		while ( true ) {
			if ( is_prime(curr_num) ) {
				prime_count++;
			}
			if ( prime_count == n ) {
				return curr_num;
			}
			curr_num++;
		}
	}
	
	private static boolean is_prime(int x) {
		for(int div = 2; div <= (int)(Math.sqrt(x))+1; div++)
			if ( x % div == 0 )
				return false;
		return true;
	}

}
