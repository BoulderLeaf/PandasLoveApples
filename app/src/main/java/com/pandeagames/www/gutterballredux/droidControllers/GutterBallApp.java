package com.pandeagames.www.gutterballredux.droidControllers;

import com.pandeagames.www.gutterballredux.gameControllers.levelManagment.AppleLevelManager;
import com.pandeagames.www.gutterballredux.gameControllers.levelManagment.LazyLevelManager;
import com.pandeagames.www.gutterballredux.gameControllers.levelManagment.LevelManager;
import android.app.Application;
import android.os.Bundle;

import net.hockeyapp.android.CrashManager;

public class GutterBallApp extends Application {
	private LevelManager levelManager;
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
}
