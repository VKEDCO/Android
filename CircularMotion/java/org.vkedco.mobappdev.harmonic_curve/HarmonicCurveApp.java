package org.vkedco.mobappdev.harmonic_curve;

/*
 * *************************************************
 * 
 * author: vladimir kulyukin
 *************************************************
 */
import android.app.Application;

public class HarmonicCurveApp extends Application {
	
	HarmonicCurveView mCurveView = null;
	int mA			= 1;
	float mPhi 		= 0.0f; 		boolean mIsPhiChecked 		= false;
	float mOmega 	= 1.0f;			boolean mIsOmegaChecked 	= true;
	float mInvOmega = 0.0f;			boolean mIsInvOmegaChecked 	= false;
	
	public HarmonicCurveApp() {
		mIsOmegaChecked = true;
		mIsInvOmegaChecked = false;
	}
	
	void setPainterView(HarmonicCurveView cw) { mCurveView = cw; }
	
	HarmonicCurveView getView() { return mCurveView; }
	
	void setA(int a) { mA = a; }
	int getA() { return mA; }	
	
	void setIsPhiChecked(boolean v) { mIsPhiChecked = v; }
	boolean getIsPhiChecked() { return mIsPhiChecked; }
	
	void setPhi(float v) { mPhi = v; }
	float getPhi() { return mPhi; }
	
	void setOmega(float v) { mOmega = v; }
	float getOmega() { return mOmega; }
	
	void setIsOmegaChecked(boolean v) { mIsOmegaChecked = v; }
	boolean getIsOmegaChecked() { return mIsOmegaChecked; }

	void setInvOmega(float v) { mInvOmega = v; }
	float getInvOmega() { return mInvOmega; }
	
	void setIsInvOmegaChecked(boolean v) { mIsInvOmegaChecked = v; }
	boolean getIsInvOmegaCHecked() { return mIsInvOmegaChecked; }

}
