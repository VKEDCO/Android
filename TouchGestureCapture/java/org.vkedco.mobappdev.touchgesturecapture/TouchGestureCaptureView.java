package org.vkedco.mobappdev.touchgesturecapture;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

/**
 ***********************************************************
 * Custom View that allows us to capture touch gestures
 * as sequences of motion events.
 *
 * @author: Vladimir Kulyukin
 ***********************************************************
 */
public class TouchGestureCaptureView extends View {

    Paint mBackgroundPaint;
    Paint mLinePaint;

    private ArrayList<MotionEvent> mCapturedMotionEvents;
    final int LINE_STROKE_WIDTH    = 10;
    final float TOUCH_DIST = 3;
    private float mTouchX;
    private float mTouchY;
    private Path mGesturePath;

    public TouchGestureCaptureView(Context context, AttributeSet atts) {
        super(context, atts);

        mBackgroundPaint = new Paint();
        mBackgroundPaint.setColor(Color.GREEN);
        mBackgroundPaint.setAntiAlias(true);

        mLinePaint = new Paint();
        mLinePaint.setColor(Color.RED);
        mLinePaint.setAntiAlias(true);

        mLinePaint.setDither(true);
        mLinePaint.setStyle(Paint.Style.STROKE);
        mLinePaint.setStrokeJoin(Paint.Join.ROUND);
        mLinePaint.setStrokeCap(Paint.Cap.ROUND);
        mLinePaint.setStrokeWidth(LINE_STROKE_WIDTH);
        mGesturePath = new Path();
        mCapturedMotionEvents = new ArrayList<>();
    }

    @Override
    protected void onSizeChanged(int width, int height, int old_width, int old_height) {
        super.onSizeChanged(width, height, old_width, old_height);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        final int width = canvas.getWidth();
        final int height = canvas.getHeight();
        canvas.drawRect(0, 0, width, height, mBackgroundPaint);
        canvas.drawPath(mGesturePath, mLinePaint);
    }

    // start capturing a touch gesture
    void startTouchGesture(float x, float y) {
        clearCapturedMotionEvents();
        mGesturePath.reset();
        mGesturePath.moveTo(x, y);
        mTouchX = x;
        mTouchY = y;
    }

    // continue capturing a touch gesture
    void continueTouchGesture(float x, float y) {
        final float dx = Math.abs(x - mTouchX);
        final float dy = Math.abs(y - mTouchY);
        if ( dx >= TOUCH_DIST && dy >= TOUCH_DIST ) {
            mGesturePath.quadTo(mTouchX, mTouchY, (mTouchX + x)/2, (mTouchY + y)/2);
            mTouchX = x;
            mTouchY = y;
        }
    }

    // stop captureing a touch gesture.
    void stopTouchGesture() {
        mGesturePath.lineTo(mTouchX, mTouchY);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        final float x = event.getX(); final float y = event.getY();
        switch( event.getAction() ) {
            // start capturing when ACTION_DOWN is detected
            case MotionEvent.ACTION_DOWN:
                startTouchGesture(x, y);
                mCapturedMotionEvents.add(event);
                // force onDraw() to be called
                invalidate();
                break;
            // continue capturing so long as ACTION_MOVE is detected
            case MotionEvent.ACTION_MOVE:
                continueTouchGesture(x, y);
                mCapturedMotionEvents.add(event);
                invalidate();
                break;
            // when ACTION_UP is detected, the gesture is over
            case MotionEvent.ACTION_UP:
                stopTouchGesture();
                mCapturedMotionEvents.add(event);
                invalidate();
                break;
        }
        return true;
    }

    public void clearCapturedMotionEvents() { mCapturedMotionEvents.clear(); }

    public ArrayList<MotionEvent> getCapturedMotionEvents() { return mCapturedMotionEvents; }
}
