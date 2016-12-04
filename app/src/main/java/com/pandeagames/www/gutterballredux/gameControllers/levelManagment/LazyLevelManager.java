package com.pandeagames.www.gutterballredux.gameControllers.levelManagment;

import android.content.Context;
import com.pandeagames.www.gutterballredux.droidControllers.SwingActivity;

public class LazyLevelManager extends LevelManager {

	public LazyLevelManager(Context context, int numLevels) {
		super(context,numLevels);
		if(getLevelsUnlocked()==0){
			unlockLevel(0);
			unlockLevel(1);
			unlockLevel(2);
		}
	}
	public void completeLevel(int levelIndex){
		super.completeLevel(levelIndex);
		if(getLevelsUnlocked()-getLevelsCompleted()==getLevelsUnlocked()/3){
			if(getLevelsUnlocked()<getNumLevels()){
				int levelsUnlocked=getLevelsUnlocked();
				for(int i=levelsUnlocked;i<levelsUnlocked+3;i++){
					if(i<getNumLevels()){
						unlockLevel(i);
					}
				}
			}
		}
	}

	public void reset(){
		super.reset();
		unlockLevel(0);
		unlockLevel(1);
		unlockLevel(2);
	}
}
