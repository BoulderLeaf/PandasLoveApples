package com.pandeagames.www.gutterballredux.infoHolders;

import android.graphics.PointF;

public class Radial extends PointF {
	public float  r;
public Radial(float x, float y, float r){
	super(x, y);
	set(x, y, r);
}
public void set(float x, float y, float r){
	super.set(x, y);
	this.r=r;
}
@Override
public Radial clone(){
	return new Radial(x, y, r);
}
}
