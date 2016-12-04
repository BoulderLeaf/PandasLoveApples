package com.pandeagames.www.gutterballredux.threads;

public class ThreadTicker {
	private SwingsThread thread;
	private float fps;
	private boolean running;
	private int lastUpdate;
public ThreadTicker(SwingsThread thread,float fps){
	this.thread=thread;
	this.fps=fps;
	lastUpdate=getTime();
}
public void start(){
	while(running){
		int time=getTime();
		int diff=getAbsDiff(time,lastUpdate);
		if(diff>fps){
			System.out.println(time+" : "+lastUpdate+" : "+diff);
			lastUpdate=getTime();
			thread.tick(new TickInfo(diff, (int)fps));
		}
	}
}
public void setRunning(boolean value){
	this.running=value;
}
public boolean getRunning(){
	return running;
}
protected int getTime(){
	
	return (int) Math.abs(System.nanoTime()/1000000);
}
protected int getAbsDiff(int val1, int val2){
	return Math.abs(val1-val2);
}
}
