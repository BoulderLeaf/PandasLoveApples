package com.pandeagames.www.gutterballredux.gameControllers;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.SurfaceView;

public class GameView extends SurfaceView {
	private Game game;
	private Point scaler;
	private Point screenSize;
	private float ratio;
	private float horizontalOffset;

	public GameView(Game game) {
		super(game);
		this.game = game;
		setBackgroundColor(242);
	}

	protected void onAttachedToWindow() {
		super.onAttachedToWindow();
		this.screenSize = game.getScreenSize();
		this.scaler = game.getScaler();
		ratio = screenSize.y / scaler.y;
		horizontalOffset = (screenSize.x - scaler.x * ratio) / 2;
	}

	public boolean onTouchEvent(MotionEvent e) {
		game.onTouch(this, e);
		return true;

	}

	public float toWorldX(float value) {
		return (value + horizontalOffset) / ratio;
	}

	public float toWorldY(float value) {
		return value / ratio;
	}

	public float toScreenX(float value) {
		return value * ratio + horizontalOffset;
	}

	public float toScreenY(float value) {
		return value * ratio;
	}
	public float toScreenX(double value) {
		return toScreenX((float)value);
	}

	public float toScreenY(double value) {
		return toScreenY((float)value);
	}

	public float toWorld(float value) {
		return value / ratio;
	}

	public float toScreen(float value) {
		return value * ratio;
	}
}
