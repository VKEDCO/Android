package org.vkedco.mobappdev.touchgesturecapture;

import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * **************************************************
 * Utility Class for a Touch Gesture Recognition Engine
 * It is a HashMap mapping strings to array lists of
 * motion events.
 *
 * @author: vladimir kulyukin
 * **************************************************
 */
public class TouchGestureDictionary {

    private HashMap<String, ArrayList<MotionEvent>> mGestures = null;

    public TouchGestureDictionary() {
        mGestures = new HashMap<>();
    }

    public void saveTouchGesture(String name, ArrayList<MotionEvent> motionEvents) {
        mGestures.put(name, motionEvents);
    }

    public double zoneVectorMatch(ArrayList<MotionEvent> motionEvents) {

        return 0;
    }

    public double euclidDistMatch(ArrayList<MotionEvent> motionEvents) {

        return 0;
    }

    public int getSize() { return mGestures.size(); }
}
