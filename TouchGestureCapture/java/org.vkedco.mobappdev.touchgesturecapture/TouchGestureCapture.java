package org.vkedco.mobappdev.touchgesturecapture;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/***************************************
 * Main Activity of a touch gesture recognition and
 * capture application.
 *
 * @author: vladimir kulyukin
 ***************************************
 */

public class TouchGestureCapture extends Activity
    implements View.OnClickListener
{
    TouchGestureCaptureView mTouchGestureCaptureView = null;
    Button mBtnAddGesture = null;
    Button mBtnEraseGesture = null;
    Button mBtnRecognizeGesture = null;
    TouchGestureDictionary mTouchGestureDictionary = null;
    EditText mEdTxtGestureName = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_gesture_capture);
        mTouchGestureCaptureView = (TouchGestureCaptureView) this.findViewById(R.id.touch_gesture_capture_view);
        mTouchGestureCaptureView.setDrawingCacheEnabled(true);
        mBtnAddGesture = (Button) findViewById(R.id.add_gesture);
        mBtnEraseGesture = (Button) findViewById(R.id.erase_gesture);
        mBtnRecognizeGesture = (Button) findViewById(R.id.recognize_gesture);
        mBtnAddGesture.setOnClickListener(this);
        mBtnEraseGesture.setOnClickListener(this);
        mBtnRecognizeGesture.setOnClickListener(this);
        mEdTxtGestureName = (EditText) findViewById(R.id.gesture_name);
        mTouchGestureDictionary = new TouchGestureDictionary();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_finger_point_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if ( mTouchGestureCaptureView != null ) {
            mTouchGestureCaptureView.clearCapturedMotionEvents();
        }
        else {
            mTouchGestureCaptureView = (TouchGestureCaptureView) this.findViewById(R.id.touch_gesture_capture_view);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void onClick(View v) {
        switch( v.getId() ) {
            case R.id.add_gesture:
                mTouchGestureDictionary
                        .saveTouchGesture(mEdTxtGestureName.getText().toString(),
                                mTouchGestureCaptureView.getCapturedMotionEvents());
                Log.i("GESTURE ADDED", Integer.toString(mTouchGestureDictionary.getSize()));
                break;
            case R.id.erase_gesture:
                break;
            case R.id.gesture_name:
                break;
        }

    }
}
