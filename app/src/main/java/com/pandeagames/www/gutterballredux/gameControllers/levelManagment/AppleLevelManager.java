package com.pandeagames.www.gutterballredux.gameControllers.levelManagment;

import com.pandeagames.www.gutterballredux.gameControllers.levelManagment.LevelManager.IStatusListener;
import android.content.Context;
import android.content.SharedPreferences;

public class AppleLevelManager extends LevelManager {
	private int[] costs;
	public static final String LEVEL_UI_STATES = "levelUiStates";
	private SharedPreferences levelUIStates;
	public AppleLevelManager(Context context, int numLevels, int[] costs) {
		super(context, numLevels);
		this.costs=costs;
		if(getLevelsUnlocked()==0){
			unlockLevel(0);
			unlockLevel(1);
			unlockLevel(2);
		}
		levelUIStates = context.getSharedPreferences(LEVEL_UI_STATES, 0);
	}
	public SharedPreferences getLevelUiStates(){
		return levelUIStates;
	}
	public void completeLevel(int levelIndex){
		super.completeLevel(levelIndex);
		for(int i=0;i<getNumLevels();i++){
			if(getStatus(i)==STATUS_LOCKED){
				if(getAppleCount()>=costs[i]){
					unlockLevel(i);
					markForUnlock(i, true);
				}
			}
		}
	}
	public void markForUnlock(int levelIndex, boolean status){
		SharedPreferences.Editor editor = levelUIStates.edit();
		editor.putBoolean(Integer.toString(levelIndex), status);
		editor.commit();
	}
	public int getAppleCount(){
		return getLevelPreferences().getInt("apples", 0);
	}
	public void addApple(){
		setAppleCount(1);
	}
	public void addApple(int count){
		setAppleCount(getAppleCount()+count);
	}
	public void setAppleCount(int count) {
		SharedPreferences.Editor editor = getLevelPreferences().edit();
		editor.putInt("apples", count);
		editor.commit();
	}
	public void reset(){
		super.reset();
		unlockLevel(0);
		unlockLevel(1);
		unlockLevel(2);
	}
}
