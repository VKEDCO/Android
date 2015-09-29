package org.vkedco.mobappdev.harmonic_curve;

// author: vladimir kulyukin

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class HarmonicCurveMainAct extends ActionBarActivity {
	
	HarmonicCurveApp mApp = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_harmonic_curve_main);
		mApp = (HarmonicCurveApp)this.getApplication();
		HarmonicCurveView hcv = (HarmonicCurveView) this.findViewById(R.id.harmonic_curve_view);
		hcv.setApp(mApp);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.harmonic_curve_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_tuneup) {
			Intent i = new Intent(this, HarmonicCurveTuneupAct.class);
			/*
			i.putExtra(this.getResources().getString(R.string.amp_val), mApp.getA());
			i.putExtra(this.getResources().getString(R.string.is_omega_checked), mApp.getIsOmegaChecked());
			i.putExtra(this.getResources().getString(R.string.omega_val), mApp.getOmega());
			Log.v("TAG", "mApp.getIsOmegaChecked() = " + mApp.getIsOmegaChecked());
			i.putExtra(this.getResources().getString(R.string.is_omega_checked), mApp.getIsOmegaChecked());
			i.putExtra(this.getResources().getString(R.string.inv_omega_val), mApp.getInvOmega());
			i.putExtra(this.getResources().getString(R.string.is_inv_omega_checked), mApp.getIsInvOmegaCHecked());
			*/
			this.startActivity(i);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
