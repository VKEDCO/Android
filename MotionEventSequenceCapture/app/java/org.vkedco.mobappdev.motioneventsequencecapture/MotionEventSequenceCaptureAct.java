package org.vkedco.mobappdev.motioneventsequencecapture;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.RelativeLayout;

public class MotionEventSequenceCaptureAct extends Activity
    implements OnTouchListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motion_event_sequence_capture);
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.motion_event_layout);
        layout.setOnTouchListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_motion_event_sequence_capture, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    final String MOTION_LOG_TAG = "LOG_TAG";
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouch(View v, MotionEvent ev) {
        Log.i(MOTION_LOG_TAG, "----------------------");
        Log.i(MOTION_LOG_TAG, "Current view in onTouch() is " + v.getTag().toString());
        Log.i(MOTION_LOG_TAG, describeMotionEvent(ev));
        return true;
    }

    final static String ACTION_DOWN     = "ACTION_DOWN";
    final static String ACTION_UP       = "ACTION_UP";
    final static String ACTION_MOVE     = "ACTION_MOVE";
    final static String ACTION_UNKNOWN  = "ACTION_UNKNOWN";
    protected static String describeMotionEvent(MotionEvent motion_event) {
        StringBuilder result = new StringBuilder(500);
        int action_type = motion_event.getAction();
        String action_name = null;
        switch ( action_type ) {
            case MotionEvent.ACTION_DOWN:   action_name = ACTION_DOWN; break;
            case MotionEvent.ACTION_UP:     action_name = ACTION_UP;   break;
            case MotionEvent.ACTION_MOVE:   action_name = ACTION_MOVE; break;
            default: action_name = ACTION_UNKNOWN; break;
        }
        result.append("Action: ").append(motion_event.getAction()).append("\n");
        result.append("Action Name: ").append(action_name).append("\n");
        int numPointers = motion_event.getPointerCount();
        result.append("Number of event's pointers: ").append(numPointers).append("\n");
        int currPointer = 0;
        while ( currPointer < numPointers ) {
            result.append(" Location: ").append("X = " + motion_event.getX(currPointer)).append(", ");
            result.append(" Y = ").append(motion_event.getY(currPointer)).append("\n");
            result.append(" Pressure: ").append(motion_event.getPressure(currPointer));
            result.append(" Size: ").append(motion_event.getSize(currPointer)).append("\n");
            currPointer++;
        }
        result.append("Downtime: ").append(motion_event.getDownTime()).append("ms\n");
        result.append("Event time: ").append(motion_event.getEventTime()).append("ms");
        result.append(" Elapsed: ").append(motion_event.getEventTime() -
                motion_event.getDownTime());
        result.append(" ms\n");
        return result.toString();
    }
}

