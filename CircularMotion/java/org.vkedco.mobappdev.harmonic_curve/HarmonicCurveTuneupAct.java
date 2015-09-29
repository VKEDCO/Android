package org.vkedco.mobappdev.harmonic_curve;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

/*
 * ***********************************************************************
 * CirclarMotionTuneupAct.java is an activity that allows the user
 * to control which rotating circles are displayed and their amplitudes.
 * 
 * author: vladimir kulyukin
 *************************************************************************
 */

public class HarmonicCurveTuneupAct extends Activity 
implements OnSeekBarChangeListener
{
	HarmonicCurveApp mApp				= null;
	Resources mRes 						= null;
	HarmonicCurveTuneupAct mThisAct		= null;
	
	// Amplitude
	TextView mAmpValTV 		= null; 	SeekBar mAmpValSB 	= null;
	// Omega
	CheckBox mOmegaCB = null; SeekBar mOmegaValSB = null; TextView mOmegaValTV 	= null; 
	// Inverse Omega
	CheckBox mInvOmegaCB = null; TextView mInvOmegaValTV = null;	SeekBar mInvOmegaValSB = null;
	// Phase
	CheckBox mPhaseCB = null; SeekBar mPhaseValSB = null; TextView mPhaseValTV = null;
	// Confirm button
	Button mBtnConfirm = null;
	
	static final float SB_VALUE_SCALER = 40.0f;
	static final String LOGTAG = HarmonicCurveTuneupAct.class.getSimpleName() + "_TAG";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.harmonic_curve_tuneup);
		
		mBtnConfirm = (Button) this.findViewById(R.id.btn_confirm);
		mAmpValTV = (TextView) this.findViewById(R.id.tvAmpVal);
		mAmpValSB = (SeekBar) this.findViewById(R.id.sbAmp);
		mAmpValSB.setOnSeekBarChangeListener(this);
		
		mOmegaCB = (CheckBox) this.findViewById(R.id.cbOmega); 
		mOmegaValTV = (TextView) this.findViewById(R.id.tvOmegaVal);
		mOmegaValSB = (SeekBar) this.findViewById(R.id.sbOmega);
		mOmegaValSB.setOnSeekBarChangeListener(this);
		
		mInvOmegaCB = (CheckBox) this.findViewById(R.id.cbInverseOmega);
		mInvOmegaValTV = (TextView) this.findViewById(R.id.tvInverseOmegaVal);
		mInvOmegaValSB = (SeekBar) this.findViewById(R.id.sbInverseOmega);
		mInvOmegaValSB.setOnSeekBarChangeListener(this);
		
		mPhaseCB = (CheckBox) this.findViewById(R.id.cbPhase);
		mPhaseValTV = (TextView) this.findViewById(R.id.tvPhaseVal);
		mPhaseValSB = (SeekBar) this.findViewById(R.id.sbPhase);
		mPhaseValSB.setOnSeekBarChangeListener(this);
		
		mApp = (HarmonicCurveApp) this.getApplication();
		
		mThisAct = this;
		mRes = this.getResources();
		
		int amp = mApp.getA();
		mAmpValSB.setProgress((int)(amp*HarmonicCurveTuneupAct.SB_VALUE_SCALER));
		mAmpValTV.setText(Integer.toString(amp));
		
		int omega = (int)mApp.getOmega();
		mOmegaValSB.setProgress((int)(omega*HarmonicCurveTuneupAct.SB_VALUE_SCALER));
		mOmegaValTV.setText(Integer.toString(omega));
		mOmegaCB.setChecked(mApp.getIsOmegaChecked());
		
		int inv_omega = (int)mApp.getInvOmega();
		mInvOmegaValSB.setProgress((int)(inv_omega*HarmonicCurveTuneupAct.SB_VALUE_SCALER));
		mInvOmegaValTV.setText("1/"+Integer.toString(inv_omega));
		mInvOmegaCB.setChecked(mApp.getIsInvOmegaCHecked());
		
		int phi = (int)mApp.getPhi(); 
		mPhaseValSB.setProgress((int)(phi*HarmonicCurveTuneupAct.SB_VALUE_SCALER));
		this.setPhiVal(phi);
		mPhaseCB.setChecked(mApp.getIsPhiChecked());
		
		
		mBtnConfirm.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mThisAct.finish();
			}	
		}
		);
		
		mOmegaCB.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if ( ((CheckBox) v).isChecked() ) {
					mApp.setIsOmegaChecked(true);
				}
				else {
					mApp.setIsOmegaChecked(false);
				}}});
		
		mInvOmegaCB.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if ( ((CheckBox) v).isChecked() ) {
					mApp.setIsInvOmegaChecked(true);
				}
				else {
					mApp.setIsInvOmegaChecked(false);
				}}});
	}
		
	
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		Log.d(LOGTAG, "onRestoreInstanceState()");
		//checkSavedInstanceState(savedInstanceState);
	}
	
	@Override
	protected void onStart() {
		super.onStart();
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		
	}

	
	// The curve motion circle disappears as well.
	@Override
	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
		switch ( seekBar.getId() ) {
		case R.id.sbAmp: this.mAmpValTV.setText(Integer.toString((int)(progress/HarmonicCurveTuneupAct.SB_VALUE_SCALER))); 
			break;
		case R.id.sbOmega: this.mOmegaValTV.setText(Integer.toString((int)(progress/HarmonicCurveTuneupAct.SB_VALUE_SCALER))); 
			break;
		case R.id.sbInverseOmega: this.mInvOmegaValTV.setText("1/" + Integer.toString((int)(progress/HarmonicCurveTuneupAct.SB_VALUE_SCALER))); 
			break;
		case R.id.sbPhase:
			int phi = (int)(progress/HarmonicCurveTuneupAct.SB_VALUE_SCALER);
			if ( phi == 0 ) {
				this.mPhaseValTV.setText("0");
			}
			else {
				this.mPhaseValTV.setText("PI/" + phi);
			}
			break;
		}
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub	
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		switch ( seekBar.getId() ) {
		case R.id.sbAmp: 
			mApp.setA((int)(seekBar.getProgress()/HarmonicCurveTuneupAct.SB_VALUE_SCALER));
			break;
		case R.id.sbOmega: 
			mApp.setOmega((int)(seekBar.getProgress()/HarmonicCurveTuneupAct.SB_VALUE_SCALER));
			break;
		case R.id.sbInverseOmega: 
			mApp.setInvOmega((int)(seekBar.getProgress()/HarmonicCurveTuneupAct.SB_VALUE_SCALER));
			break;
		case R.id.sbPhase:
			int phi = (int)(seekBar.getProgress()/HarmonicCurveTuneupAct.SB_VALUE_SCALER);
			if ( phi == 0.0f ) {
				mPhaseCB.setChecked(false);
				mApp.setIsPhiChecked(false);
			}
			else {
				mPhaseCB.setChecked(true);
				mApp.setIsPhiChecked(true);
			}
			mApp.setPhi(phi);
			break;	
		}
		
	}
	
	private void setPhiVal(int phi) {
		if ( phi == 0 ) {
			mPhaseValTV.setText("0");
		}
		else {
			mPhaseValTV.setText("PI/" + phi);
		}
	}
}

