package com.pandeagames.www.gutterballredux.gameControllers.Levels;

import java.util.ArrayList;
import java.util.List;

import com.pandeagames.R;
import com.pandeagames.www.gutterballredux.Components.AbstractGameComponent;
import com.pandeagames.www.gutterballredux.droidControllers.GutterBallApp;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.pandeagames.www.gutterballredux.Components.BodyComponent;
import com.pandeagames.www.gutterballredux.gameControllers.Game;
import com.pandeagames.www.gutterballredux.gameControllers.levelManagment.AppleLevelManager;
import com.pandeagames.www.gutterballredux.gameObjects.Actor;
import com.pandeagames.www.gutterballredux.gameObjects.AppleType;
import com.pandeagames.www.gutterballredux.gameObjects.Breakable;
import com.pandeagames.www.gutterballredux.gameObjects.ComboDisplay;
import com.pandeagames.www.gutterballredux.gameObjects.EndLevelDialog;
import com.pandeagames.www.gutterballredux.gameObjects.GameObjectUtils;
import com.pandeagames.www.gutterballredux.gameObjects.HitSparks;
import com.pandeagames.www.gutterballredux.gameObjects.Portal;
import com.pandeagames.www.gutterballredux.gameObjects.Portal.IObtainedCallback;
import com.pandeagames.www.gutterballredux.gameObjects.ScoreComboUI;
import com.pandeagames.www.gutterballredux.gameObjects.launcher.FingerAnimation;
import com.pandeagames.www.gutterballredux.gameObjects.launcher.Launcher;
import com.pandeagames.www.gutterballredux.gameObjects.launcher.LauncherAnimation;
import com.pandeagames.www.gutterballredux.gameObjects.launcher.LauncherTouchCircle;
import com.pandeagames.www.gutterballredux.gameObjects.scripts.apple;
import com.pandeagames.www.gutterballredux.gameObjects.scripts.golden_apple;
import com.pandeagames.www.gutterballredux.infoHolders.StageScore;

import org.json.JSONObject;

public class ThrowLevel extends Level implements IObtainedCallback {
protected LauncherTouchCircle launcherCircle;
protected Launcher launcher;
protected LauncherAnimation launcherAnim;
protected List<Portal> portalList;
	protected List<Breakable> breakableList;
protected HitSparks sparks;
protected GutterBallApp app;
protected int appleCount=0;
	private EndLevelDialog endLevelDialog;
	private ScoreComboUI scoreUI;
	private StageScore _score;
	public ThrowLevel(Game game, BodyComponent geometry, LevelDef levelDef) {
		super(game, geometry, levelDef);
	}
	@Override
	protected void init(){
		_score = new StageScore();
		_score.setApplePointValue(100);
		_score.setPar(5);
		_score.setParValue(500);
		scoreUI= new ScoreComboUI(game, _score);
		launcher = new Launcher(game, _score, 12, 38);
		launcher.setDelay(1000);
		portalList =new ArrayList<Portal>();
		breakableList = new ArrayList<Breakable>();
		sparks = new HitSparks(game);
		app =(GutterBallApp) game.getApplicationContext();
		launcherCircle = new LauncherTouchCircle(game, launcher);
		launcherAnim = new LauncherAnimation(game, launcher);
		FingerAnimation fa = new FingerAnimation(game, launcher);
		super.init();
	}
	@Override
	public void destroy(){
		if(destroyed) {return;}
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

		scoreUI.destroy();
		scoreUI = null;

		launcherCircle.destroy();
		launcherCircle = null;

		launcherAnim.destroy();
		launcherAnim = null;

		sparks.destroy();
		sparks = null;

		app = null;

		if(endLevelDialog != null)
		{
			endLevelDialog.destroy();
			endLevelDialog = null;
		}

		super.destroy();
	}
	public void portalObtained(Portal portal, Actor actor) {
		portalList.remove(portal);
		game.getSoundPool().play(game.getSoundPool().getPool().squish, 0.99f, 0.99f, 1, 0, 1.0f);
		_score.addApple(actor.getComboToken(), portal.getType());

		if(portal.getType() == AppleType.GOLDEN) {
			ComboDisplay comboDisplay = new ComboDisplay(game, portal.getX(), portal.getY(), _score.getCombo(actor.getComboToken()));
		}

		if(portalList.size()==0){
			((AppleLevelManager)(game.getGutterApp().getLevelManager())).addApple(appleCount);
			//have completed the level
			//app.getLevelManager().completeLevel(game.getLevelId());
			//game.setResult(game.RESULT_OK, null);

			//game.finish();

			launcher.disable();

			this.endLevelDialog = new EndLevelDialog(game);
			this.endLevelDialog.displayDialog(_score);
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

	@Override
	protected void initializeGameComponent(AbstractGameComponent gameComponent, JSONObject object)
	{
		super.initializeGameComponent(gameComponent, object);

		if(gameComponent instanceof Portal)
		{
			appleCount++;
			((Portal)gameComponent).setCallback(this);
			portalList.add((Portal)gameComponent);
		}
	}

	protected void addBreakable(Breakable breakable)
	{
		breakableList.add(breakable);
	}

}
