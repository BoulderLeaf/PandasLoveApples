package com.pandeagames.www.gutterballredux.infoHolders;

import android.graphics.Canvas;
import android.graphics.Point;

public class DrawInfo {
	private Canvas canvas;
	private Point scaler;
	private Point screenSize;
	private float ratio;
	private float horizontalOffset;
public DrawInfo(Canvas canvas, Point screenSize, Point scaler){
	this.canvas=canvas;
	this.screenSize = screenSize;
	this.scaler=scaler;
	ratio=screenSize.y/scaler.y;
	horizontalOffset=(screenSize.x-scaler.x*ratio)/2;
}
public Canvas getCanvas(){
	return canvas;
}

}
