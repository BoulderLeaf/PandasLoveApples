package com.pandeagames.www.gutterballredux.droidControllers;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.pandeagames.R;

import com.pandeagames.www.gutterballredux.gameControllers.Game;
import com.pandeagames.www.gutterballredux.gameControllers.LevelLayoutController;
import com.pandeagames.www.gutterballredux.gameControllers.Levels.LevelDef;
import com.pandeagames.www.gutterballredux.gameControllers.levelManagment.AppleLevelManager;
import com.pandeagames.www.gutterballredux.gameControllers.levelManagment.LazyLevelManager;
import com.pandeagames.www.gutterballredux.gameControllers.levelManagment.LevelManager;

import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LevelSelectActivity extends SwingActivity implements android.view.View.OnClickListener {
	private static final String UNLOCKED_STATE="levelsUnlocked"; 
	private String selectedLevel="";
	private LevelManager levelManager;
	private Button resetLevelsButton;
	private Button unlockLevelsButton;
	private TextView appleCount;
	private LevelLayoutController levelLayoutController;
	public static int savedUnlockCount;
	private Tracker mTracker;
	public LevelSelectActivity() {
		// TODO Auto-generated constructor stub
		super();
	}
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);

		// Obtain the shared Tracker instance.
		GutterBallApp application = (GutterBallApp) getApplication();
		mTracker = application.getDefaultTracker();

		setContentView(R.layout.level_select);
		levelManager = ((GutterBallApp)getApplicationContext()).getLevelManager();
		if(levelLayoutController==null){
			levelLayoutController = new LevelLayoutController(this, levelManager);
		}
		if(levelManager.getLevelsUnlocked()!=savedUnlockCount && savedUnlockCount!=0){
				//means that new levels have been unlocked by the level manager
				LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

				View layout = inflater.inflate(R.layout.new_levels_toast, (ViewGroup)findViewById(R.id.custom_toast_layout)); 
				Toast toast = Toast.makeText(this, "New Levels unlocked!" , Toast.LENGTH_LONG);
				toast.setGravity(Gravity.BOTTOM, 0, 0);
				toast.setView(layout);
				toast.show();
		}
		resetLevelsButton = (Button)findViewById(R.id.appOptions);
		resetLevelsButton.setOnClickListener(this);
		unlockLevelsButton = (Button)findViewById(R.id.unlockAllLevels);
		unlockLevelsButton.setOnClickListener(this);
		appleCount = (TextView)findViewById(R.id.appleCount);
	}
	protected void onStart(){
		super.onStart();
		GutterBallApp app = super.app;
		AppleLevelManager manager =(AppleLevelManager)(app.getLevelManager()); 
		int count=(manager).getAppleCount();
		appleCount.setText(Integer.toString(count));
		//appleCount.setText((manager).getAppleCount());
	}
	protected void onResume(){
		super.onResume();
		levelLayoutController.initialize();

		String name = "GutterBallApp";

		mTracker.setScreenName("Image~" + name);
		mTracker.send(new HitBuilders.ScreenViewBuilder().build());
	}
	public void selectLevel(LevelDef def)
	{
		selectedLevel = def.getId();
		playLevel();
	}
	
	protected void onSaveInstanceState(Bundle outState){
		super.onSaveInstanceState(outState);
		outState.putInt(UNLOCKED_STATE, levelManager.getLevelsUnlocked());
	}
	private void playLevel()
	{
		Intent game = new Intent(this, Game.class);
		game.putExtra("level", selectedLevel);
		startActivityForResult(game,1);
	}
	protected void onPause(){
		super.onPause();
		savedUnlockCount= levelManager.getLevelsUnlocked();
	}
	@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
     super.onActivityResult(requestCode, resultCode, data);
	     if(resultCode==RESULT_OK){
	        Intent refresh = new Intent(this, LevelSelectActivity.class);
	        startActivity(refresh);
	        this.finish();
	     }
    }
	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		if(resetLevelsButton==view){
			levelManager.reset();
			levelLayoutController.initialize();
			((AppleLevelManager)levelManager).setAppleCount(0);
			appleCount.setText(Integer.toString(0));
		}else if(unlockLevelsButton==view){
			levelManager.unlockAll();
			levelLayoutController.initialize();
			
		}
	}

}
