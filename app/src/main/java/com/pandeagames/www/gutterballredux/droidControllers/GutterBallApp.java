package com.pandeagames.www.gutterballredux.droidControllers;

import com.pandeagames.R;
import com.pandeagames.www.gutterballredux.gameControllers.levelManagment.AppleLevelManager;
import com.pandeagames.www.gutterballredux.gameControllers.levelManagment.LazyLevelManager;
import com.pandeagames.www.gutterballredux.gameControllers.levelManagment.LevelManager;
import android.app.Application;
import android.os.Bundle;

import android.app.Application;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

import net.hockeyapp.android.CrashManager;

public class GutterBallApp extends Application {
	private LevelManager levelManager;
	private Tracker mTracker;
	public GutterBallApp() {
		
	}
	public void onCreate(){

		if(levelManager==null)
		{
			//levelManager = new LazyLevelManager(this,17);
			levelManager = new AppleLevelManager(this, 17,new int[]{5,5, 5, 5, 5,10,10,10,10,20,20,20,20,20,30,30,30,30});
		}
	}
	public LevelManager getLevelManager(){
		return levelManager;
	}

	/**
	 * Gets the default {@link Tracker} for this {@link Application}.
	 * @return tracker
	 */
	synchronized public Tracker getDefaultTracker() {
		if (mTracker == null) {
			GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
			// To enable debug logging use: adb shell setprop log.tag.GAv4 DEBUG
			mTracker = analytics.newTracker(R.xml.global_tracker);
		}
		return mTracker;
	}
}
