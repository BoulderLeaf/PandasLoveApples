package com.pandeagames.www.gutterballredux.gameControllers.levelManagment;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;

import com.pandeagames.R;
import com.pandeagames.www.gutterballredux.gameControllers.Levels.AppleLevelDef;
import com.pandeagames.www.gutterballredux.gameControllers.Levels.LevelDef;

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

	protected List<LevelDef> levelDefs;
	
	protected Context context;
	
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

	public void setStatus(String id, int status) {

			SharedPreferences.Editor editor = levelPreferences.edit();
			editor.putInt(id, status);
			editor.commit();

			updateCounts();
			for(IStatusListener listener : statusListeners) {
				listener.levelStatusUpdate(id, status);
			}
	}
	
	public int getStatus(String id){
		return levelPreferences.getInt(id,STATUS_LOCKED);
	}

	public void unlockLevel(String id) {
		setStatus(id, STATUS_UNLOCKED);
	}

	public void lockLevel(String id) {
		setStatus(id, STATUS_LOCKED);
	}

	public void completeLevel(String id) {
		setStatus(id, STATUS_COMPLETE);
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
		public void levelStatusUpdate(String id, int status);
	}
	public void reset(){
		for (int i = 0; i < levelDefs.size(); i++) {
			lockLevel(levelDefs.get(i).getId());
		}
		if(levelDefs.size() >=1){
			unlockLevel(levelDefs.get(0).getId());
		}
	}
	public void unlockAll(){
		for (int i = 0; i < levelDefs.size(); i++) {
			unlockLevel(levelDefs.get(i).getId());
		}
	}
	public List<LevelDef> getLevelDefs() {
		return levelDefs;
	}
}
