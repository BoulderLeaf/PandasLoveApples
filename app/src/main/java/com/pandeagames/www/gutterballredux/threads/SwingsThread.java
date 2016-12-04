package com.pandeagames.www.gutterballredux.threads;

import java.util.Vector;

import com.pandeagames.www.gutterballredux.Components.AbstractGameComponent;

public abstract class SwingsThread extends Thread {
protected ThreadTicker ticker;	
	public SwingsThread(String threadName) {
		super(threadName);
		ticker = new ThreadTicker(this, 1000/30);
	}

	public SwingsThread(ThreadGroup group,String threadName) {
		super(group,threadName);
		// TODO Auto-generated constructor stub
	}
	public SwingsThread(ThreadGroup group,String threadName, long stackSize) {
		super(group,null,threadName, stackSize);
		// TODO Auto-generated constructor stub
	}
	@Override
public void run(){
	super.run();
	ticker.start();
	ticker.getClass();
}
	public void setRunning(boolean value){
		ticker.setRunning(value);
	}
	public boolean getRunning(){
		return ticker.getRunning();
	}
	public abstract void tick(TickInfo tickInfo);
}
