package com.pandeagames.www.gutterballredux.Components;

import com.pandeagames.www.gutterballredux.droidControllers.SwingActivity;
import com.pandeagames.www.gutterballredux.infoHolders.UpdateInfo;
import android.app.Activity;
import android.graphics.Point;
import android.view.SurfaceView;

public abstract class AbstractComponent {
	private boolean destroyed;
	protected SwingActivity activity;
	protected SurfaceView view;
	private float x=0;
	private float y=0;
	private boolean markedDestroy;
	private Point pt;
	private boolean enabled=true;
	private boolean hidden;
	public AbstractComponent(SwingActivity activity){
		pt=new Point();
		this.activity=activity;
		view=activity.getView();
		activity.addComponent(this);
	}
	public SwingActivity getActivity(){
		return activity;
	}

	/////////////////////// Component Position ///////////////////////
	
	public void setX(float value){
		x=value;
		pt.x=(int)value;
	}
	public void setY(float value){
		y=value;
		pt.y=(int)value;
	}
	public void setPos(float x, float y){
		setX(x);
		setY(y);
	}
	public float getX(){
		return x;
	}
	public float getY()
	{
		return y;
	}
	public Point getPt(){
		return pt;
	}
	
	/////////////////////// Component Destruction ///////////////////////
	public void destroy() {
		activity.removeComponent(this);
		activity=null;
		destroyed=true;
	}
	
	public boolean destroyed(){
		return destroyed;
	}
	public void markDestroy(){
		//mark for destruction. Will be removed from game lists. 
		markedDestroy=true;
	}
	public boolean getMarkDestroy(){
		return markedDestroy;
	}
	
	/////////////////////// Get/Set Enabled ///////////////////////
	
	public void setEnabled(boolean value){
		this.enabled=value;
	}
	public void disable(){
		setEnabled(false);
	}
	public void enable(){
		setEnabled(true);
	}
	public boolean isEnabled(){
		return enabled;
	}
	
	/////////////////////// Get/Set Hidden ///////////////////////
	
	public void setHidden(boolean value){
		this.hidden=value;
	}
	public void hide(){
		setHidden(true);
	}
	public void show(){
		setEnabled(false);
	}
	public boolean isHidden(){
		return hidden;
	}
}
