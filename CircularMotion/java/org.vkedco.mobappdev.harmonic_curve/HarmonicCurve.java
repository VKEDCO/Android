package org.vkedco.mobappdev.harmonic_curve;

// author: vladimir kulyukin

class XYPoint {
	float mX;
	float mY;
	
	public XYPoint(float x, float y) {
		mX = x;
		mY = y;
	}
	
	public float getX() { return mX; }
	public float getY() { return mY; }
	public void setX(float x) { mX = x; }
	public void setY(float y) { mY = y; }
	
}

public class HarmonicCurve {
	
	private float mA 				= 0.0f;
	private float mOmega 			= 0.0f;
	private float mPhase			= 0.0f;
	private XYPoint mCurrentXYPos	= null;
	private float mScreenOriginX 	= 0.0f;
	private float mScreenOriginY	= 0.0f;
	private float mScalar			= 0.0f;
	private float mDeltaX			= 0.0f;
	private float mStartX			= 0.0f;
	private float mEndX				= 0.0f;
	
	public HarmonicCurve(float a, float omega, float phase, 
			float start_x, float end_x, float delta_x, float scalar, float screen_origin_x, float screen_origin_y) {
		mA = a;
		mOmega = omega;
		mPhase = phase;
		mCurrentXYPos = new XYPoint(start_x, this.computeY(start_x));
		mScreenOriginX = screen_origin_x;
		mScreenOriginY = screen_origin_y;
		mScalar = scalar;
		mDeltaX = delta_x;
		mStartX = start_x;
		mEndX   = end_x;
	}
	
	public float getA() { return mA; }
	public void setA(float a) { mA = a; }
	
	public float getOmega() { return mOmega; }
	public void setOmega(float w) { mOmega = w; } 
	
	public float getPhase() { return mPhase; }
	public void setPhase(float f) { mPhase = f; }
	
	public float computeScreenXCoord(float origin, float scalar, float real_coord) {
		return origin + scalar * real_coord;
	}
	
	public float computeScreenYCoord(float origin, float scalar, float real_coord) {
		return origin - scalar * real_coord;
	}
	
	public float computeY(float x) {
		return this.mA * (float)Math.sin(this.mOmega * x + this.mPhase); 
	}
	
	public float computeScreenX() {
		return computeScreenXCoord(this.mScreenOriginX, this.mScalar, this.mCurrentXYPos.getX());
	}
	
	public float computeScreenY() {
		return computeScreenYCoord(this.mScreenOriginY, this.mScalar, this.mCurrentXYPos.getY());
	}
	
	public void increaseX() {
		float next_x = mCurrentXYPos.getX() + mDeltaX;
		float next_y = computeY(next_x);
		mCurrentXYPos.setX(next_x);
		mCurrentXYPos.setY(next_y);
	}
	
	public void restartXYPos() {
		this.mCurrentXYPos.setX(this.mStartX);
		this.mCurrentXYPos.setY(this.computeY(this.mCurrentXYPos.getX()));
	}
	
	public float getStartX() {
		return this.mStartX;
	}
	
	public float getEndX() {
		return this.mEndX;
	}
	
	public float getRealX() { return this.mCurrentXYPos.getX(); }
	public float getRealY() { return this.mCurrentXYPos.getY(); }
	
	public float getDeltaX() { return this.mDeltaX; }
	
	

}
