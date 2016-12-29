package com.pandeagames.www.gutterballredux.gameControllers;

import android.content.SharedPreferences;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

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
		TableLayout levels = (TableLayout)activity.findViewById(R.id.levels);
		updateLoop(levels);
	}
	private void updateLoop(ViewGroup group){
		for(int i=0; i<group.getChildCount();i++){
			View child = group.getChildAt(i);
			if(child instanceof PhotoButton){
				updateBtn(child.getId());
			}
			else if(child instanceof ViewGroup){
				updateLoop((ViewGroup) child);
			}
		}
	}

	private void updateBtn(int id) {
		PhotoButton btn = (PhotoButton)activity.findViewById(id);
		int status = levelManager.getStatus(id);
		btn.setMarkForUnlock(((AppleLevelManager) levelManager).getLevelUiStates().getBoolean(Integer.toString(id), false));
		if (levelManager.getStatus(id) == LevelManager.STATUS_LOCKED) {
			btn.setEnabled(false);
		} else if (levelManager.getStatus(id) == LevelManager.STATUS_UNLOCKED) {
			btn.setEnabled(true);
			btn.setPhotoRes(btn.getUnlockedPhoto());
		} else if (levelManager.getStatus(id) == LevelManager.STATUS_COMPLETE) {
			btn.setEnabled(true);
			btn.setPhotoRes(btn.getPhoto());
		}
	}
}
