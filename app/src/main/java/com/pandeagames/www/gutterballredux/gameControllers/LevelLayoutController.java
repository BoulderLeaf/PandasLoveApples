package com.pandeagames.www.gutterballredux.gameControllers;

import android.content.SharedPreferences;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.pandeagames.R;

import com.pandeagames.www.gutterballredux.droidControllers.LevelSelectActivity;
import com.pandeagames.www.gutterballredux.gameControllers.Levels.AppleLevelDef;
import com.pandeagames.www.gutterballredux.gameControllers.Levels.LevelDef;
import com.pandeagames.www.gutterballredux.gameControllers.levelManagment.AppleLevelManager;
import com.pandeagames.www.gutterballredux.gameControllers.levelManagment.LevelManager;
import com.pandeagames.www.gutterballredux.gameControllers.levelManagment.LevelManager.IStatusListener;
import com.pandeagames.www.gutterballredux.droidControllers.SwingActivity;
import com.pandeagames.www.gutterballredux.Components.AbstractComponent;
import com.pandeagames.www.gutterballredux.Drawing.PhotoButton;

import java.util.Hashtable;
import java.util.List;

public class LevelLayoutController extends AbstractComponent implements IStatusListener {
	
	private LevelManager levelManager;
	private Hashtable<String, PhotoButton> buttons;
	
	public LevelLayoutController(SwingActivity activity, LevelManager levelManager) {
		super(activity);
		this.levelManager=levelManager;

		buttons = new Hashtable<String, PhotoButton>();

		List<LevelDef> levelDefs = levelManager.getLevelDefs();
		TableLayout layout = (TableLayout) activity.findViewById(R.id.levels);
		TableRow row = null;
		layout.setStretchAllColumns(true);
		for(int i=0; i<levelDefs.size(); i++){

			//create a new row every 3rd button
			if(i % 3 == 0){
				if(row != null){
					layout.addView(row, new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,TableLayout.LayoutParams.WRAP_CONTENT, 1.0f));
				}
				row = new TableRow(activity);

			}

			if(row == null) return;

			AppleLevelDef def = (AppleLevelDef)levelDefs.get(i);
			PhotoButton button = new PhotoButton(
					activity,
					def.getMiniBgResId(),
					def.getMiniBgResLockedId(),
					R.drawable.green_sphere,
					def.getMiniBgResUnplayedId(),
					1.68421f);

			button.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.MATCH_PARENT, 1f));

			button.levelDef = def;
			View.OnClickListener listener = new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					((LevelSelectActivity)getActivity()).selectLevel(((PhotoButton)v).levelDef);
				}
			};
			button.setOnClickListener(listener);

			row.addView(button);
			//row.addView(new Button(activity));
			buttons.put(def.getId(), button);
		}
	}

	@Override
	public void levelStatusUpdate(String id, int status) {
		// TODO Auto-generated method stub
		updateBtn(id);
	}
	public void initialize(){
		updateLoop();
	}
	private void updateLoop(){
		for(int i = 0; i<levelManager.getLevelDefs().size();i++){
			updateBtn(levelManager.getLevelDefs().get(i).getId());
		}
	}

	private void updateBtn(String id) {
		PhotoButton btn = buttons.get(id);
		int status = levelManager.getStatus(id);
		btn.setMarkForUnlock(((AppleLevelManager) levelManager).getLevelUiStates().getBoolean(id, false));
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