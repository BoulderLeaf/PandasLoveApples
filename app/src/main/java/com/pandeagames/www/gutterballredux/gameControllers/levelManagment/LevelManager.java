package com.pandeagames.www.gutterballredux.gameControllers.levelManagment;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;

public class LevelManager {
	public static final String UNLOCK_LVL_PREFS = "MyPrefsFile";
	public static final int STATUS_LOCKED = 0;
	public static final int STATUS_UNLOCKED = 1;
	public static final int STATUS_COMPLETE = 2;
	private int numLevels;
	private SharedPreferences levelPreferences;
	private int levelStatus[];
	private int levelsUnlocked = 0;
	private int levelsCompleted = 0;
	private int levelsLocked = 0;
	
	private Context context;
	
	private List<IStatusListener> statusListeners;
	
	public LevelManager(Context context, int numLevels) {
		this.numLevels = numLevels;
		this.context=context;
		levelStatus = new int[numLevels];
		levelPreferences = context.getSharedPreferences(UNLOCK_LVL_PREFS, 0);
		for (int i = 0; i < numLevels; i++) {
			levelStatus[i] = levelPreferences.getInt(Integer.toString(i),
					STATUS_LOCKED);
		}
		statusListeners=new ArrayList<LevelManager.IStatusListener>();
		updateCounts();
	}

	public int isUnlocked(int levelIndex) {
		if (levelIndex < numLevels) {
			return levelStatus[levelIndex];
		}
		return STATUS_LOCKED;
	}

	public void setStatus(int levelIndex, int status) {
		if (levelIndex < numLevels) {
			SharedPreferences.Editor editor = levelPreferences.edit();
			editor.putInt(Integer.toString(levelIndex), status);
			editor.commit();
			levelStatus[levelIndex] = status;
			updateCounts();
			for(IStatusListener listener : statusListeners){
				listener.levelStatusUpdate(levelIndex, status);
			}
		}
	}
	
	public int getStatus(int levelIndex){
		return levelPreferences.getInt(Integer.toString(levelIndex),STATUS_LOCKED);
	}

	public void unlockLevel(int levelIndex) {
		setStatus(levelIndex, STATUS_UNLOCKED);
	}

	public void lockLevel(int levelIndex) {
		setStatus(levelIndex, STATUS_LOCKED);
	}

	public void completeLevel(int levelIndex) {
		setStatus(levelIndex, STATUS_COMPLETE);
	}

	private void updateCounts() {
		levelsUnlocked = 0;
		levelsLocked = 0;
		levelsCompleted = 0;
		for (int i = 0; i < numLevels; i++) {
			if (levelStatus[i] == STATUS_COMPLETE) {
				levelsCompleted++;
				levelsUnlocked++;
			} else if (levelStatus[i] == STATUS_LOCKED) {
				levelsLocked++;
			} else if (levelStatus[i] == STATUS_UNLOCKED) {
				levelsUnlocked++;
			}
		}
	}
	
	public int getLevelsUnlocked() {
		return levelsUnlocked;
	}
	public int getLevelsLocked() {
		return levelsLocked;
	}
	public int getLevelsCompleted() {
		return levelsCompleted;
	}
	public int getNumLevels(){
		return numLevels;
	}
	public SharedPreferences getLevelPreferences(){
		return levelPreferences;
	}
	public void addStatusListener(IStatusListener listener){
		statusListeners.add(listener);
	}
	public void removeStatusListener(IStatusListener listener){
		statusListeners.remove(listener);
	}
	public interface IStatusListener{
		public void levelStatusUpdate(int levelIndex, int status);
	}
	public void reset(){
		for (int i = 0; i < numLevels; i++) {
			lockLevel(i);
		}
	}
	public void unlockAll(){
		for (int i = 0; i < numLevels; i++) {
			unlockLevel(i);
		}
	}
}
