package com.pandeagames.www.gutterballredux.droidControllers;


import com.pandeagames.www.gutterballredux.gameControllers.Game;

import com.pandeagames.R;

import net.hockeyapp.android.CrashManager;
import net.hockeyapp.android.UpdateManager;

import com.pandeagames.www.gutterballredux.Drawing.PhotoButton;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class HomeActivity extends SwingActivity {
	public HomeActivity() {
		// TODO Auto-generated constructor stub
	}
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_menu);
		
		PhotoButton levelsBtn = (PhotoButton) findViewById(R.id.levels);
		levelsBtn.setPhotoRes(R.drawable.blurred_level);

		checkForUpdates();
	}
	public void onLevelsClick(View v)
	{
		// TODO Auto-generated method stub
		Intent a = new Intent(this, LevelSelectActivity.class);
		startActivity(a);
	}
	public void onAboutClick(View v)
	{
		// TODO Auto-generated method stub
	}
	@Override
	public void onResume() {
		super.onResume();
		// ... your own onResume implementation
		checkForCrashes();
	}
	@Override
	public void onPause() {
		super.onPause();
		unregisterManagers();
	}
	@Override
	public void onDestroy() {
		super.onDestroy();
		unregisterManagers();
	}
	private void checkForCrashes() {
		CrashManager.register(this);
	}

	private void checkForUpdates() {
		// Remove this for store builds!
		UpdateManager.register(this);
	}

	private void unregisterManagers() {
		UpdateManager.unregister();
	}
}
