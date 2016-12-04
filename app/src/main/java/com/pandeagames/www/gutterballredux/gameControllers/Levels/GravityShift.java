package com.pandeagames.www.gutterballredux.gameControllers.Levels;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.Display;
import android.view.WindowManager;

import com.pandeagames.www.gutterballredux.gameControllers.Game;
import com.pandeagames.www.gutterballredux.infoHolders.UpdateInfo;
import com.pandeagames.www.gutterballredux.Components.AbstractGameComponent;

public class GravityShift extends AbstractGameComponent implements SensorEventListener{
	private World world;
	 private SensorManager mSensorManager;
	  private Sensor mOrientation;
	  float azimuth_angle;
	  float pitch_angle;
	  float roll_angle;
	  Vec2 gravity;
	@SuppressWarnings("deprecation")
	public GravityShift(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
		world=game.getSimulation().getWorld();
		mSensorManager = (SensorManager) game.getSystemService(Context.SENSOR_SERVICE);
	    mOrientation = mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
	    mSensorManager.registerListener(this, mOrientation, 0);
	    gravity = world.getGravity().clone();
	}

	@Override
	public void update(UpdateInfo updateInfo) {
		// TODO Auto-generated method stub
		Display display = ((WindowManager) game.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
		//int rotation = display.getRotation();
		int rotation =game.getResources().getConfiguration().orientation;
		
		world.getGravity().set(gravity.x+roll_angle*-1/50, gravity.y);
		
		Vec2 g = world.getGravity();
		
		world.setGravity(world.getGravity());
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		azimuth_angle = event.values[0];
	    pitch_angle = event.values[1];
	    roll_angle = event.values[2];
	    // Do something with these orientation angles.
	}

}
