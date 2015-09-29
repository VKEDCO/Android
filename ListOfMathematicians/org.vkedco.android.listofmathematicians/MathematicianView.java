package org.vkedco.android.listofmathematicians;

/*
 *************************************************
 * MathematicianView is a class that
 * customizes the appearance of Mathematician
 * objects in a ListView.
 * 
 * author: vladimir kulyukin
 ************************************************* 
 */

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

public class MathematicianView extends TextView {
	
	Paint mMarginPaint;
	Paint mLinePaint;
	int mBackgroundColor;
	float mLeftMarginDim;
	float mRightMarginDim;

	public MathematicianView(Context context) {
		super(context);
		init();
	}
	
	public MathematicianView(Context context, AttributeSet attrs, 
			int defStyle) 
	{
		super(context, attrs, defStyle);
		init();
	}
	
	public MathematicianView(Context context, AttributeSet attrs) {
		
		super(context, attrs);
		init();
	}
	
	private void init() {
    	// 1. Get a reference to our resource table.
    	Resources myRes = getResources();
    	
    	// 2. Create the paint brushes we will use in the onDraw method
    	// to paint the margins. Anti-Aliasing is a method of making the 
    	// eye believe that rough edges are smooth.
    	mMarginPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    	mMarginPaint.setColor(myRes.getColor(R.color.margin_clr));
    	
    	// 3. Create the paint brushes we will use in the onDraw method
    	// to paint the lines.
    	mLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    	mLinePaint.setColor(myRes.getColor(R.color.line_clr));
    	
    	// 4. Get the paper background color and the margin width
    	mBackgroundColor = myRes.getColor(R.color.background_clr);
    	mLeftMarginDim = myRes.getDimension(R.dimen.left_margin);
    	mRightMarginDim = myRes.getDimension(R.dimen.right_margin);
    }
	
	public void onDraw(Canvas canvas) {
    	// Color as paper
    	canvas.drawColor(mBackgroundColor);
    	
    	// Get the height and width of the canvas
    	int mh = getMeasuredHeight();
    	int mw = getMeasuredWidth();
    	// Draw ruled lines
    	// draw line from top left to top right
    	canvas.drawLine(5, 5, mw-5, 5, mLinePaint);
    	// draw line from top right to bottom right
    	canvas.drawLine(mw-5, 5, mw-5, mh-5, mLinePaint);
    	// draw line from bottom right to bottom left
    	canvas.drawLine(mw-5, mh-5, 5, mh-5, mLinePaint);
    	// draw line from bottom left to top left
    	canvas.drawLine(5, mh-5, 5, 5, mLinePaint);
    	
    	// Draw left margin
    	canvas.drawLine(mLeftMarginDim, 0, mLeftMarginDim, mh,
    			mMarginPaint);
    	// Draw right margin
    	canvas.drawLine(mw - mRightMarginDim, 0, mw - mRightMarginDim, mh, 
    			mMarginPaint);
    	
    	// Save the state of the canvas
    	canvas.save();
    	// move the text mMarginDim pixels left and 0 pixels down.
    	canvas.translate(mLeftMarginDim, 0);
    	
    	//canvas.drawText(mFirstName + " " mLastName, x, y, paint)
    	
    	// Use the TextView to render the text. The
    	// text will be generated via toString() of the
    	// Mathematician object.
    	super.onDraw(canvas);
    	// every save() must be matched with restore()
    	canvas.restore();
    }
}
