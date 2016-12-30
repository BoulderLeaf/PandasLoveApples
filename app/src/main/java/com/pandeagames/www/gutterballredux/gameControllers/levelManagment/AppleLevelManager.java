package com.pandeagames.www.gutterballredux.gameControllers.levelManagment;

import com.pandeagames.R;
import com.pandeagames.www.gutterballredux.gameControllers.Levels.AppleLevelDef;
import com.pandeagames.www.gutterballredux.gameControllers.Levels.LevelDef;
import com.pandeagames.www.gutterballredux.gameControllers.levelManagment.LevelManager.IStatusListener;
import com.pandeagames.www.gutterballredux.utils.JSON;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AppleLevelManager extends LevelManager {
	private int[] costs;
	public static final String LEVEL_UI_STATES = "levelUiStates";
	private SharedPreferences levelUIStates;
	public AppleLevelManager(Context context, int numLevels, int[] costs) {
		super(context, numLevels);

		this.costs=costs;

		levelUIStates = context.getSharedPreferences(LEVEL_UI_STATES, 0);

		levelDefs = parseLevelData(R.raw.levels_def);
	}
	public SharedPreferences getLevelUiStates(){
		return levelUIStates;
	}
	public void completeLevel(String id){
		super.completeLevel(id);
		for(int i=0;i<levelDefs.size();i++){
			AppleLevelDef levelDef = (AppleLevelDef)levelDefs.get(i);
			if(getStatus(levelDef.getId())==STATUS_LOCKED){
				if(getAppleCount()>=levelDef.getAppleLockCount()){
					unlockLevel(levelDef.getId());
					markForUnlock(levelDef.getId(), true);
				}
			}
		}
	}

	public void markForUnlock(String id, boolean status){
		SharedPreferences.Editor editor = levelUIStates.edit();
		editor.putBoolean(id, status);
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
	}
	/* Given the asset id of a JSON file, this method will read the contents of the file and output a list of level definitions.  */
	private List<LevelDef> parseLevelData(int asset){
		ArrayList<LevelDef> list = new ArrayList<LevelDef>();

		try {

			JSONObject obj = new JSONObject(JSON.loadJSONFromAsset(context.getResources(), asset));
			JSONArray levels = obj.getJSONArray("levels");

			JSONObject level;
			String id;
			int name, bg, bg_mini, bg_mini_locked, bg_mini_unplayed;

			for(int i =0; i < levels.length(); i++){

				level = levels.getJSONObject(i);
				id = level.getString("id");

				name = context.getResources().getIdentifier(id, "string", context.getPackageName());
				bg = context.getResources().getIdentifier(id+"_bg", "drawable", context.getPackageName());
				bg_mini = context.getResources().getIdentifier(id+"_bg_mini", "drawable", context.getPackageName());
				bg_mini_locked = context.getResources().getIdentifier(id+"_bg_mini_locked", "drawable", context.getPackageName());
				bg_mini_unplayed = context.getResources().getIdentifier(id+"_bg_mini_unplayed", "drawable", context.getPackageName());

				list.add(new AppleLevelDef(
						level.getString("id"),
						name == 0 ? R.string.level_name_default:name,
						level.getInt("appleCount"),
						bg == 0 ? R.drawable.background_01:bg,
						bg_mini == 0 ? R.drawable.background:bg_mini,
						bg_mini_locked == 0 ? R.drawable.disabled_level:bg_mini_locked,
						bg_mini_unplayed == 0 ? R.drawable.unlocked_level:bg_mini_unplayed
				));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return list;
	}

}
