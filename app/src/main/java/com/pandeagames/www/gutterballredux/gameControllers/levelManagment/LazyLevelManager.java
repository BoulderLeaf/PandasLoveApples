package com.pandeagames.www.gutterballredux.gameControllers.levelManagment;

import android.content.Context;
import com.pandeagames.www.gutterballredux.droidControllers.SwingActivity;

public class LazyLevelManager extends LevelManager {

	public LazyLevelManager(Context context, int numLevels) {
		super(context,numLevels);
	}
	public void completeLevel(String id){
		super.completeLevel(id);
	}

	public void reset(){
		super.reset();
	}
}
