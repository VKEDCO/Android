package org.vkedco.mobappdev.harmonic_curve;

/*
 * *************************************************
 * HarmonicCurveView.java is a custom view
 * used by CircularMotionMainActivity.java
 * author: vladimir kulyukin
 *************************************************
 */

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class HarmonicCurveView extends View {
	// Paints
	Paint mBackgroundPaint 			= null;
	Paint mForeCircleRedPaint 		= null;
	Paint mForeCircleBluePaint 		= null;
	Paint mForeCircleMagentaPaint 	= null;
	Paint mForeCircleGrayPaint 		= null;
	Paint mYellowPaint 				= null;
	Paint mAxisPaint 				= new Paint(Color.DKGRAY);
	
	private int mOriginX = 0;
	private int mOriginY = 0;
	
	private int mLengthOfXAxis = 0;
	private int mLengthOfYAxis = 0;
	
	private int mOriginY2 = 0;
	
	private HarmonicCurve mHarmonicCurve = null;
	private HarmonicCurve mUnmodifiedHarmonicCurve = null;
	
	private HarmonicCurveApp mApp = null;

	public HarmonicCurveView(Context context, AttributeSet atrs) {
		super(context, atrs);
		Log.d("CircularMotionPainterView", "constructor");
		mBackgroundPaint = new Paint();
		mBackgroundPaint.setColor(Color.TRANSPARENT);

		mForeCircleRedPaint = new Paint();
		mForeCircleRedPaint.setColor(Color.RED);
		mForeCircleRedPaint.setAntiAlias(true);

		mForeCircleBluePaint = new Paint();
		mForeCircleBluePaint.setColor(Color.BLUE);
		mForeCircleBluePaint.setAntiAlias(true);

		mForeCircleMagentaPaint = new Paint();
		mForeCircleMagentaPaint.setColor(Color.MAGENTA);
		mForeCircleMagentaPaint.setAntiAlias(true);

		mForeCircleGrayPaint = new Paint();
		mForeCircleGrayPaint.setColor(Color.DKGRAY);
		mForeCircleGrayPaint.setAntiAlias(true);
		
		mYellowPaint = new Paint();
		mYellowPaint.setColor(Color.YELLOW);
		mYellowPaint.setAntiAlias(true);

		mOriginX 		= 550;
		mOriginY 		= 100;
		mOriginY2 		= 400;
		
		mLengthOfXAxis = 2000;
		mLengthOfYAxis = 2000;
		
		// public HarmonicCurve(float a, float omega, float phase, 
		//   float start_x, float delta_x, float scalar, float screen_origin_x, float screen_origin_y)
		final float curve_start_x = (float)(-7*Math.PI);
		final float curve_end_x = (float)(7*Math.PI);
		final float a = 3.0f;
		final float omega = (float)(1/3.0f);
		final float phase = (float)(Math.PI/3.0);
		final float scalar = 20.0f;
		final float delta_x = (float)(Math.PI/100);
		mHarmonicCurve = new HarmonicCurve(1.0f, 1.0f, 0.0f, 
				curve_start_x,
				curve_end_x,
				delta_x, 
				scalar, mOriginX, mOriginY);
		
		mUnmodifiedHarmonicCurve = new HarmonicCurve(1.0f, 1.0f, 0.0f, 
				curve_start_x,
				curve_end_x,
				delta_x, 
				scalar, mOriginX, mOriginY2);
				 
	}

	// This method redraws the entire canvas
	public void draw(Canvas canvas) {
		final int width = canvas.getWidth();
		final int height = canvas.getHeight();
		// 1. draw background rectangle that covers the entire
		// canvas
		canvas.drawRect(0, 0, width, height, mBackgroundPaint);
		// 2. draw red circles on canvas
		// mRedMotionCircle.setPaint(mAxisPaint);
		// mBlueMotionCircle.setPaint(mAxisPaint);
		synchronized (this) {
			this.drawXAxis(canvas);
			this.drawYAxis(canvas);
			this.drawXAxis2(canvas);
			this.drawHarmonicCurve(canvas);
			this.drawOriginalHarmonicCurve(canvas);
			// 3. force redraw
			this.invalidate();
		}
	}
	
	
	private void drawHarmonicCurve(Canvas canvas) {
		//this.mHarmonicCurve.restartXYPos();
		this.mHarmonicCurve.setA((float) this.mApp.getA());
		if ( mApp.getIsOmegaChecked() ) {
			this.mHarmonicCurve.setOmega(this.mApp.getOmega());
		}
		else if ( mApp.getIsInvOmegaCHecked() ) {
			if ( mApp.getInvOmega() > 0.0f )
				this.mHarmonicCurve.setOmega(1.0f/this.mApp.getInvOmega());
			else
				this.mHarmonicCurve.setOmega(1.0f);
		}
		else {
			this.mHarmonicCurve.setOmega(1.0f);
		}
		if ( mApp.getIsPhiChecked() ) {
			if ( mApp.getPhi() > 0.0f )
				this.mHarmonicCurve.setPhase((float)(Math.PI/mApp.getPhi()));
			else
				this.mHarmonicCurve.setPhase(0.0f);
		}
		else {
			this.mHarmonicCurve.setPhase(0.0f);
		}
		
		float real_x = this.mHarmonicCurve.getStartX();
		float real_end_x = this.mHarmonicCurve.getEndX();
		float screen_x = 0.0f, screen_y = 0.0f;
		final int i = 3;
		int j = 0;
		int row_delta = 20;
		
		int curr_row = 100;
		while ( true ) 
		{
			if ( real_x > real_end_x ) {
				
				
				break;
			};
			
			screen_x = mHarmonicCurve.computeScreenX();
			screen_y = mHarmonicCurve.computeScreenY();
			canvas.drawCircle(screen_x, screen_y, 5, this.mForeCircleRedPaint);
			real_x += this.mHarmonicCurve.getDeltaX();
			this.mHarmonicCurve.increaseX();
		}
		this.mHarmonicCurve.restartXYPos();
	}
	
	private void drawOriginalHarmonicCurve(Canvas canvas) {
		float real_x = this.mUnmodifiedHarmonicCurve.getStartX();
		float real_end_x = this.mUnmodifiedHarmonicCurve.getEndX();
		float screen_x = 0.0f, screen_y = 0.0f;
		final int i = 3;
		int j = 0;
		int row_delta = 20;
		
		int curr_row = 100;
		while ( true ) 
		{
			if ( real_x > real_end_x ) {
				
				
				break;
			};
			
			screen_x = mUnmodifiedHarmonicCurve.computeScreenX();
			screen_y = mUnmodifiedHarmonicCurve.computeScreenY();
			canvas.drawCircle(screen_x, screen_y, 5, this.mForeCircleBluePaint);
			real_x += this.mUnmodifiedHarmonicCurve.getDeltaX();
			this.mUnmodifiedHarmonicCurve.increaseX();
			//canvas.drawText("(x,y) = " + screen_x + ", " + screen_y, 60, curr_row, this.mForeCircleBluePaint);
			//curr_row += row_delta;
		}
		this.mUnmodifiedHarmonicCurve.restartXYPos();
	}

	public void setOriginX(int x) {
		mOriginX = x;
	}

	public void setOriginY(int y) {
		mOriginY = y;
	}

	public int getOriginX() {
		return mOriginX;
	}

	public int getOriginY() {
		return mOriginY;
	}

	public void setLengthOfXAxis(int len) {
		mLengthOfXAxis = len;
	}

	public void setLengthOfYAxis(int len) {
		mLengthOfYAxis = len;
	}

	public int getLengthOfXAxis() {
		return mLengthOfXAxis;
	}

	public int getLengthOfYAxis() {
		return mLengthOfYAxis;
	}

	public void drawXAxis(Canvas canvas) {
		int half_len = (int) (this.mLengthOfXAxis / 2.0f);
		canvas.drawLine(this.mOriginX - half_len, this.mOriginY, this.mOriginX
				+ half_len, this.mOriginY, this.mAxisPaint);
	}
	
	public void drawXAxis2(Canvas canvas) {
		int half_len = (int) (this.mLengthOfXAxis / 2.0f);
		canvas.drawLine(this.mOriginX - half_len, this.mOriginY2, this.mOriginX
				+ half_len, this.mOriginY2, this.mAxisPaint);
	}

	public void drawYAxis(Canvas canvas) {
		int half_len = (int) (this.mLengthOfYAxis / 2.0f);
		canvas.drawLine(this.mOriginX, this.mOriginY - half_len, this.mOriginX,
				this.mOriginY + half_len, this.mAxisPaint);
	}
	
	public void setApp(HarmonicCurveApp hca) {
		this.mApp = hca;
	}
	
	
}

