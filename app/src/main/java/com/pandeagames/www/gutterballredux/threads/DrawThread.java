package com.pandeagames.www.gutterballredux.threads;

import com.pandeagames.www.gutterballredux.infoHolders.DrawInfo;
import com.pandeagames.www.gutterballredux.infoHolders.UpdateInfo;

import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;

import com.pandeagames.www.gutterballredux.droidControllers.SwingActivity;

import android.graphics.Canvas;
import android.location.Address;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import com.pandeagames.www.gutterballredux.Components.AbstractComponent;
import com.pandeagames.www.gutterballredux.Components.AbstractGameComponent;
import com.pandeagames.www.gutterballredux.Components.interfaces.IDrawableComponent;

public class DrawThread extends SwingsThread  {
	public static final String threadName="DrawThread";
	protected BufferedList<IDrawableComponent> drawList;
private SurfaceView view;
	private boolean running;
	private SurfaceHolder holder;
	private SwingActivity activity;
	
	public DrawThread(BufferedList<IDrawableComponent> drawList, SwingActivity activity) {
		super(threadName);
		this.drawList=drawList;
this.activity=activity;
	}

public void setView(SurfaceView value){
	this.view=value;
}
public void setHolder(SurfaceHolder holder){
	//this.holder=holder;
}
	public DrawThread(BufferedList<IDrawableComponent> drawList, SwingActivity activity,ThreadGroup group) {
		super(group,threadName);
		this.drawList=drawList;
		this.activity=activity;
		// TODO Auto-generated constructor stub
	}

	public DrawThread(BufferedList<IDrawableComponent> drawList, SwingActivity activity,ThreadGroup group, long stackSize) {
		super(group, threadName, stackSize); 
		// TODO Auto-generated constructor stub
		this.drawList=drawList;
		this.activity=activity;
	}
	@Override
	public void tick(TickInfo tickInfo) {
		// TODO Auto-generated method stub
		if(view!=null){
		holder= view.getHolder();
		if(holder!=null) {


			Canvas c = view.getHolder().lockCanvas();

			if (c != null) {
				DrawInfo info = new DrawInfo(c, activity.getScreenSize(), activity.getScaler());
				for (IDrawableComponent comp : drawList) {
					comp.draw(info);
				}

				view.getHolder().unlockCanvasAndPost(c);
			}

			boolean hasBuffer = drawList.hasBuffer();
			drawList.clearBuffer();

			if (hasBuffer) {
				Collections.sort(drawList, IDrawableComponent.drawableComparator);
			}
		}
		}
	}
}
