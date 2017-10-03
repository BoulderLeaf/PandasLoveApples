package com.pandeagames.www.gutterballredux.droidControllers;

import com.pandeagames.www.gutterballredux.gameControllers.Simulation;
import com.pandeagames.www.gutterballredux.gameControllers.Levels.Level;

import com.pandeagames.www.gutterballredux.infoHolders.UpdateInfo;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;

import com.pandeagames.www.gutterballredux.threads.BufferedList;
import com.pandeagames.www.gutterballredux.threads.DrawThread;
import com.pandeagames.www.gutterballredux.threads.GameThread;
import com.pandeagames.www.gutterballredux.Components.AbstractComponent;
import com.pandeagames.www.gutterballredux.Components.AbstractGameComponent;
import com.pandeagames.www.gutterballredux.Components.DrawableGameComponent;
import com.pandeagames.www.gutterballredux.Components.interfaces.IDrawableComponent;
import com.pandeagames.www.gutterballredux.Components.interfaces.IUserInputComponent;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnKeyListener;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnAttachStateChangeListener;
import android.view.View.OnCreateContextMenuListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;

public class SwingActivity extends Activity implements SurfaceHolder.Callback,
		OnAttachStateChangeListener, OnClickListener, OnLongClickListener,
		OnKeyListener, OnTouchListener, OnCreateContextMenuListener {
	private DrawThread drawThread;
	protected GutterBallApp app;
	protected SurfaceView view;
	protected BufferedList<IDrawableComponent> drawList;
	protected BufferedList<IUserInputComponent> inputList;
	private BufferedList<AbstractComponent> componentList;
	private Canvas canvas;
	protected Point screenDim;
	protected Point scaler;
	protected Display display;
	protected float aspectRatio = 16 / 9;
	protected float displayAspectRatio;

	public SwingActivity() {

	}
	public GutterBallApp getGutterApp(){
		return app;
	}
	public Point getScreenSize() {
		return screenDim;
	}

	public Point getScaler() {
		return scaler;
	}

	public void setContentView(SurfaceView view) {
		super.setContentView(view);
		this.view = view;
		view.setZOrderOnTop(true);
	}

	public void setCanvas(Canvas canvas) {
		this.canvas = canvas;
	}

	public Canvas getCanvas() {
		return canvas;
	}

	public SurfaceView getView() {
		return view;
	}

	public void addDrawableComponent(IDrawableComponent drawable) {
		if(drawList==null)return;
		drawList.add(drawable);
	}

	public void removeDrawableComponent(IDrawableComponent drawable) {
		if(drawList==null)return;
		drawList.remove(drawable);
	}

	public void addInputComponent(IUserInputComponent component) {
		if(inputList==null)return;
		inputList.add(component);
	}

	public void removeInputComponent(IUserInputComponent component) {
		if(inputList==null)return;
		inputList.remove(component);
	}

	public void addComponent(AbstractComponent component) {
		if(componentList==null)return;
		componentList.add(component);
	}

	public void removeComponent(AbstractComponent component) {
		if(componentList==null)return;
		componentList.remove(component);
	}

	protected void onStart() {
		super.onStart();
		app = (GutterBallApp)getApplication();
		display = getWindowManager().getDefaultDisplay();
		screenDim = new Point();
		display.getSize(screenDim);
		displayAspectRatio = screenDim.y / screenDim.x;

	}

	protected void onCreate(Bundle savedInstanceState, SurfaceView view) {
		super.onCreate(savedInstanceState);
		scaler = new Point(1, 1);
		drawList = new BufferedList<IDrawableComponent>();
		inputList = new BufferedList<IUserInputComponent>();
		componentList = new BufferedList<AbstractComponent>();
		this.setContentView(view);
	
		// view.addOnAttachStateChangeListener(this);
		SurfaceHolder holder = view.getHolder();
		holder.addCallback(this);
		
	}

	protected void onStop() {
		super.onStop();
		if (drawThread != null)
			drawThread.setRunning(false);
	}

	protected void onDestroy() {
		super.onDestroy();
		if(drawList!=null)drawList.clear();
		
		if(inputList!=null)inputList.clear();
		if(componentList!=null)componentList.clear();
		drawList = null;
		inputList = null;
		componentList = null;
		drawThread = null;
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub

		if(inputList == null){
			return false;
		}

		for (IUserInputComponent comp : inputList) {
			if(comp == null){
				comp = null;
			}
			comp.onTouch(v, event);
		}
		inputList.clearBuffer();
		return false;
	}

	@Override
	public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		for (IUserInputComponent comp : inputList) {
			comp.onKey(dialog, keyCode, event);
		}
		return false;
	}

	@Override
	public boolean onLongClick(View v) {
		// TODO Auto-generated method stub
		for (IUserInputComponent comp : inputList) {
			comp.onLongClick(v);
		}
		return false;
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		// TODO Auto-generated method stub
		for (IUserInputComponent comp : inputList) {
			comp.onClick(dialog, which);
		}
	}

	public void updateGame() {
	}

	@Override
	public void onViewAttachedToWindow(View v) {

	}

	@Override
	public void onViewDetachedFromWindow(View v) {
		// TODO Auto-generated method stub

	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		drawThread = new DrawThread(drawList, this);
		drawThread.setView(view);
		drawThread.setHolder(holder);
		drawThread.setRunning(true);
		
		//holder.setFormat(PixelFormat.TRANSPARENT);
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub

	}
}
