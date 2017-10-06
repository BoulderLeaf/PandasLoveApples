package com.pandeagames.www.gutterballredux.gameControllers;

import com.pandeagames.www.gutterballredux.Components.AbstractComponent;
import com.pandeagames.www.gutterballredux.droidControllers.GutterBallApp;
import com.pandeagames.www.gutterballredux.gameControllers.Levels.Geom.GeneratedGeom;
import com.pandeagames.www.gutterballredux.gameControllers.Levels.Level;
import com.pandeagames.www.gutterballredux.gameControllers.Levels.LevelDef;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.pandeagames.R;

import com.pandeagames.www.gutterballredux.droidControllers.SwingActivity;

import com.pandeagames.www.gutterballredux.gameControllers.Levels.ThrowLevel;
import com.pandeagames.www.gutterballredux.threads.BufferedList;
import com.pandeagames.www.gutterballredux.threads.GameThread;
import com.pandeagames.www.gutterballredux.Components.BodyComponent;
import com.pandeagames.www.gutterballredux.Components.AbstractGameComponent;
import com.pandeagames.www.gutterballredux.Drawing.DebugDraw;

import android.content.Context;
import android.graphics.Point;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;

import com.pandeagames.BuildConfig;

public class Game extends SwingActivity {
	private Level level;
	private Simulation simulation;
	private GameThread gameThread;
	private BufferedList<AbstractGameComponent> gameComponentList;
	private BufferedList<BodyComponent> bodyList;
	private MediaPlayer mp;
	private RadialCollisionController radialCollision;
	private String levelId;
	private LevelDef levelDef;
	
	private GameSoundPool sp;
	
	private DebugDraw debugDraw;

	
	public Game() {
		super();
		
	}
	public GameSoundPool getSoundPool(){
		return sp;
	}
	public Level getLevel() {
		return level;
	}
	public String getLevelId() {
		return levelId;
	}
	public Simulation getSimulation() {
		return simulation;
	}
	public RadialCollisionController getRadialCollision(){
		return radialCollision;
	}

	public void addGameComponent(AbstractGameComponent gameComponent) {
		if(gameComponentList==null)return;
		gameComponentList.add(gameComponent);
	}

	public void removeGameComponent(AbstractGameComponent gameComponent) {
		if(gameComponentList==null)return;
		gameComponentList.remove(gameComponent);
	}

	public void clearGameComponents() {
		for(AbstractGameComponent comp:gameComponentList) {
			comp.markDestroy();
		}
	}

	public void addBodyComponent(BodyComponent bodyComponent) {
		if(bodyList==null)return;
		bodyList.add(bodyComponent);
	}

	public void removeBodyComponent(BodyComponent bodyComponent) {
		if(bodyList==null)return;
		bodyList.remove(bodyComponent);
	}

	protected void onStart() {
		super.onStart();
		
		//rest of sounds goes here
	}
	protected void onResume(){
		super.onRestart();
		gameThread = new GameThread(drawList, gameComponentList, this);
		gameThread.setRunning(true);
		gameThread.start();
		
		mp.start();
	}
	protected void onPause(){
		super.onPause();
		mp.pause();
		gameThread.setRunning(false);
	}
	protected void onRestart(){
	super.onRestart();
}

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState, new GameView(this));

		if(level==null)
		{
			Bundle bundle = getIntent().getExtras();
			levelId=bundle.getString("level");

			gameComponentList = new BufferedList<AbstractGameComponent>();
			bodyList = new BodyList<BodyComponent>();
			simulation = new Simulation(this, bodyList);

			if(BuildConfig.DEBUG_MODE)
			{
				debugDraw = new DebugDraw(this, bodyList);
			}

			radialCollision=new RadialCollisionController(this);

			loadLevel(levelId);

			scaler = new Point(simulation.getWorldSize().x,
				simulation.getWorldSize().y);
		}
		
		inputList.clearBuffer();
		
		//start sound systems;
		sp = new GameSoundPool(this, AudioManager.STREAM_MUSIC, 0);
		mp = MediaPlayer.create(this, R.raw.forest);
	}

	public void loadLevel(String levelId) {

		if(level != null) {
			level.markDestroy();
			level = null;
		}

		//this.clearComponents();
		//this.clearGameComponents();

		levelDef = ((GutterBallApp)getApplicationContext()).getLevelManager().getLevelById(levelId);

		try{
			Class<?> clazz = Class.forName("com.pandeagames.www.gutterballredux.gameControllers.Levels."+levelId);
			Constructor<?> constructor = clazz.getConstructor(Game.class, LevelDef.class);
			level = (Level) constructor.newInstance(this, levelDef);
		}catch(IllegalAccessException ex){
			Log.e("PANDAS LOVE APPLES", "Unable to generate Level", ex);
			return;
		}catch(ClassNotFoundException ex){

			level = new ThrowLevel(this, new GeneratedGeom(this, levelDef), levelDef);

		}catch(InstantiationException ex){
			Log.e("PANDAS LOVE APPLES", "Unable to generate Level", ex);
			return;
		}catch(NoSuchMethodException ex){
			Log.e("PANDAS LOVE APPLES", "Unable to generate Level", ex);
			return;
		}catch(InvocationTargetException ex){
			Log.e("PANDAS LOVE APPLES", "Unable to generate Level", ex);
			return;
		}
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		super.surfaceCreated(holder);
		gameThread.setView(view);
	}

	protected void onStop() {
		super.onStop();
		mp.stop();
	}

	protected void onDestroy() {
		super.onDestroy();
		level.destroy();
		simulation.destroy();
		radialCollision.destroy();
		mp.release();
		
		radialCollision=null;
		gameThread = null;
		level = null;
		simulation = null;
		mp=null;
	}

}

