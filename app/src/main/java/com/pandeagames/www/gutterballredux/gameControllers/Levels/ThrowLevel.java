package com.pandeagames.www.gutterballredux.gameControllers.Levels;

import java.util.ArrayList;
import java.util.List;

import com.pandeagames.www.gutterballredux.droidControllers.GutterBallApp;

import android.graphics.drawable.BitmapDrawable;

import com.pandeagames.www.gutterballredux.Components.BodyComponent;
import com.pandeagames.www.gutterballredux.gameControllers.Game;
import com.pandeagames.www.gutterballredux.gameControllers.levelManagment.AppleLevelManager;
import com.pandeagames.www.gutterballredux.gameObjects.AppleType;
import com.pandeagames.www.gutterballredux.gameObjects.Breakable;
import com.pandeagames.www.gutterballredux.gameObjects.HitSparks;
import com.pandeagames.www.gutterballredux.gameObjects.Portal;
import com.pandeagames.www.gutterballredux.gameObjects.Portal.IObtainedCallback;
import com.pandeagames.www.gutterballredux.gameObjects.launcher.FingerAnimation;
import com.pandeagames.www.gutterballredux.gameObjects.launcher.Launcher;
import com.pandeagames.www.gutterballredux.gameObjects.launcher.LauncherAnimation;
import com.pandeagames.www.gutterballredux.gameObjects.launcher.LauncherTouchCircle;

public abstract class ThrowLevel extends Level implements IObtainedCallback {
protected LauncherTouchCircle launcherCircle;
protected Launcher launcher;
protected LauncherAnimation launcherAnim;
protected List<Portal> portalList;
	protected List<Breakable> breakableList;
protected HitSparks sparks;
protected GutterBallApp app;
protected int appleCount=0;
	public ThrowLevel(Game game, BodyComponent geometry) {
		super(game, geometry);
		initialize();
	}
	public ThrowLevel(Game game, BodyComponent geometry, int bgId) {
		super(game, geometry, bgId);
		initialize();
	}
	private void initialize(){
		launcher = new Launcher(game, 12, 38);
		launcher.setDelay(1000);
		portalList =new ArrayList<Portal>();
		breakableList = new ArrayList<Breakable>();
		sparks = new HitSparks(game);
		app =(GutterBallApp) game.getApplicationContext();
		launcherCircle = new LauncherTouchCircle(game, launcher);
		launcherAnim = new LauncherAnimation(game, launcher);
		FingerAnimation fa = new FingerAnimation(game, launcher);
	}
	@Override
	public void destroy(){
		for(Portal p : portalList)
		{
			if(!p.destroyed())p.destroy();
		}

		for(Breakable b : breakableList)
		{
			if(!b.destroyed())b.destroy();
		}

		portalList.clear();
		portalList = null;

		breakableList.clear();
		breakableList = null;

		launcher.destroy();
		launcher=null;
		super.destroy();
	}
	public void portalObtained(Portal portal){
		portalList.remove(portal);
		game.getSoundPool().play(game.getSoundPool().getPool().squish, 0.99f, 0.99f, 1, 0, 1.0f);
		if(portalList.size()==0){
			((AppleLevelManager)(game.getGutterApp().getLevelManager())).addApple(appleCount);
			//have completed the level
			app.getLevelManager().completeLevel(game.getLevelIndex());
			game.setResult(game.RESULT_OK, null);
			//finish();
			game.finish();
		}
	}
	protected void createPortal(float x, float y)
	{
		this.createPortal(x, y, AppleType.NORMAL);
	}


	protected void createPortal(float x, float y, AppleType type)
	{
		appleCount++;
		Portal portal = new Portal(game, x, y, type);
		portal.setCallback(this);
		portalList.add(portal);
	}

	protected void addBreakable(Breakable breakable)
	{
		breakableList.add(breakable);
	}

}
