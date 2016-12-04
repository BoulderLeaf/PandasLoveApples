package com.pandeagames.www.gutterballredux.Components;

import com.pandeagames.www.gutterballredux.gameControllers.Game;
import com.pandeagames.www.gutterballredux.gameControllers.GameView;
import com.pandeagames.www.gutterballredux.infoHolders.UpdateInfo;
import android.app.Activity;

public abstract class AbstractGameComponent extends AbstractComponent {
	protected Game game;
	protected GameView gameView;
	private float vx, vy, prevX=0f, prevY=0f;

	public AbstractGameComponent(Game game) {
		super(game);
		gameView = (GameView) view;
		this.game=game;
		// TODO Auto-generated constructor stub
		game.addGameComponent(this);
	}

	public void update(UpdateInfo updateInfo){
		vx=prevX-getX();
		vy=prevY-getY();
		prevX=getX();
		prevY=getY();
	}
	public float getVX(){
		return vx;
	}
	public float getAbsV(){
		return Math.abs(vx)+Math.abs(vy);
	}
	public float getVY(){
		return vy;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		game.removeGameComponent(this);
		game = null;
		super.destroy();
		

	}
}
