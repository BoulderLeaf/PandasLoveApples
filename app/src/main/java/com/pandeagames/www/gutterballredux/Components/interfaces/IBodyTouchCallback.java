package com.pandeagames.www.gutterballredux.Components.interfaces;

import android.view.MotionEvent;
import com.pandeagames.www.gutterballredux.Components.BodyComponent;

public interface IBodyTouchCallback {
public void onBodyTouch(BodyComponent bodyComp, MotionEvent event);
}
