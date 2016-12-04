package com.pandeagames.www.gutterballredux.gameControllers;

import android.content.SharedPreferences;

import com.pandeagames.R;

import com.pandeagames.www.gutterballredux.gameControllers.levelManagment.AppleLevelManager;
import com.pandeagames.www.gutterballredux.gameControllers.levelManagment.LevelManager;
import com.pandeagames.www.gutterballredux.gameControllers.levelManagment.LevelManager.IStatusListener;
import com.pandeagames.www.gutterballredux.droidControllers.SwingActivity;
import com.pandeagames.www.gutterballredux.Components.AbstractComponent;
import com.pandeagames.www.gutterballredux.Drawing.PhotoButton;

public class LevelLayoutController extends AbstractComponent implements IStatusListener {
	
	private LevelManager levelManager;
	private PhotoButton[] levelBtns;
	
	public LevelLayoutController(SwingActivity activity, LevelManager levelManager) {
		super(activity);
		this.levelManager=levelManager;
		levelBtns=new PhotoButton[levelManager.getNumLevels()];
		
	}
	public PhotoButton[] getLevelButtons(){
		return levelBtns;
	}

	@Override
	public void levelStatusUpdate(int levelIndex, int status) {
		// TODO Auto-generated method stub
		updateBtn(levelIndex);
	}
	public void initialize(){
		for(int i=0; i<levelManager.getNumLevels();i++){
			levelBtns[i]=(PhotoButton)activity.findViewById(getResByLevelIndex(i));
			updateBtn(i);
		}
	}
	private void updateBtn(int index){
		int status = levelManager.getStatus(index);
		levelBtns[index].setMarkForUnlock(((AppleLevelManager)levelManager).getLevelUiStates().getBoolean(Integer.toString(index),false));
		if(levelManager.getStatus(index)==LevelManager.STATUS_LOCKED){
			levelBtns[index].setEnabled(false);
		}else if(levelManager.getStatus(index)==LevelManager.STATUS_UNLOCKED){
			levelBtns[index].setEnabled(true);
			levelBtns[index].setPhotoRes(levelBtns[index].getUnlockedPhoto());
		}else if(levelManager.getStatus(index)==LevelManager.STATUS_COMPLETE){
			levelBtns[index].setEnabled(true);
			levelBtns[index].setPhotoRes(levelBtns[index].getPhoto());
		}
	}
	public int getResByLevelIndex(int index){
		switch(index){
		case 0:
			return R.id.level1;
		case 1:
			return R.id.level2;
		case 2:
			return R.id.level3;
		case 3:
			return R.id.level4;
		case 4:
			return R.id.level5;
		case 5:
			return R.id.level6;
		case 6:
			return R.id.level7;
		case 7:
			return R.id.level8;
		case 8:
			return R.id.level9;
		case 9:
			return R.id.level10;
		case 10:
			return R.id.level11;
		case 11:
			return R.id.level12;
		case 12:
			return R.id.level13;
		case 13:
			return R.id.level14;
		case 14:
			return R.id.level15;
		case 15:
			return R.id.level16;
		case 16:
			return R.id.level17;
		}
		return 0;
	}
}
