package com.pandeagames.www.gutterballredux.threads;

public class TickInfo {
	private int deltaTime;
	private int targetFps;
public  TickInfo(int deltaTime, int targetFps){
	this.deltaTime=deltaTime;
	this.targetFps = targetFps;
}
public int getDeltaTime(){
	return deltaTime;
}
public int getTargetFps(){
	return targetFps;
}
}
