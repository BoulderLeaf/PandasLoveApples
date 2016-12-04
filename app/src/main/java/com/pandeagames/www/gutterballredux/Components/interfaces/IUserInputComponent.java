package com.pandeagames.www.gutterballredux.Components.interfaces;

import android.content.DialogInterface;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

public interface IUserInputComponent {
public void onKey(DialogInterface dialog, int keyCode, KeyEvent event);
public void onTouch(View v, MotionEvent event);
public void onLongClick(View v);
public void onClick(DialogInterface dialog, int which);
public void onCreateContextMenu();
}
