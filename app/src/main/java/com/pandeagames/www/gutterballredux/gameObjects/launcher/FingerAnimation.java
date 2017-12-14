package com.pandeagames.www.gutterballredux.gameObjects.launcher;

import com.pandeagames.www.gutterballredux.gameControllers.BitmapPool;
import com.pandeagames.www.gutterballredux.infoHolders.DrawInfo;
import com.pandeagames.www.gutterballredux.infoHolders.UpdateInfo;

import com.pandeagames.R;

import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;

import com.pandeagames.www.gutterballredux.gameControllers.Game;
import com.pandeagames.www.gutterballredux.Components.DrawableGameComponent;

public class FingerAnimation extends DrawableGameComponent implements ILauncherListener {
	private int ANIM_STATE=0;
	private Launcher launcher;
	private BitmapDrawable hand;
	private int alpha;
	private float hand_y;
	private float scale=1;
	private int delay=0;
	private boolean launched;//stop drawing the hand afdter the first launch
	private float anim;
	public FingerAnimation(Game game, Launcher launcher) {
		super(game);
		initialize(launcher);
	}

	public FingerAnimation(Game game, int drawOrder, Launcher launcher) {
		super(game, drawOrder);
		initialize(launcher);
	}
	private void initialize(Launcher launcher){
		this.launcher=launcher;
		launcher.addLauncherListener(this);
		this.setPos(launcher.getX(), launcher.getY());
		hand = BitmapPool.getBitmapDrawable(game.getResources(), R.drawable.icon_hand);
		setState(2);
	}
	public int drawOrder(){
		return 100;
	}
	public void update(UpdateInfo updateInfo){
		super.update(updateInfo);
		
		switch(ANIM_STATE){
		case 0:
			//starting position into down position
			alpha=255-(int)(255*anim);
			scale=1f+1f*anim;
			hand_y=0;
			anim=anim*0.8f;
			if(anim<0.05){
				setState(1);
			}
			break;
		case 1:
			//down position into swipe
			alpha=(int)(255*anim);
			scale=1;
			hand_y=-(float)(8f-(8f*anim));
			anim=anim*0.8f;
			if(anim<0.05){
				setState(2);
			}
			break;
		case 2:
			//animation complete, place a delay in here
			if(++delay>10){
				setState(0);
			}
			break;
		}
		
	}
	public void draw(DrawInfo drawInfo){
		super.draw(drawInfo);

		if(hand == null){ return; }

		int handWidth=4;
		Rect des = new Rect(
				(int)gameView.toScreenX(getX()-handWidth*scale), 
				(int)gameView.toScreenY(getY()-handWidth*scale+hand_y-3), 
				(int)gameView.toScreenX(getX()+handWidth*scale), 
				(int)gameView.toScreenY(getY()+handWidth*scale+hand_y-3));
		hand.setBounds(des);
		hand.setAlpha(alpha);
		hand.draw(drawInfo.getCanvas());
	}
	private void setState(int animState){
		switch(animState){
		case 0:
			//starting position.
			alpha=0;
			hand_y=0;
			scale=2;
			break;
		case 1:
			//visible before swipe
			alpha=255;
			hand_y=0;
			scale=1;
			break;
		case 2:
			//complete
			alpha=0;
			hand_y=5;
			scale=1;
			break;
		}
		ANIM_STATE=animState;
		delay=0;
		anim=1f;
	}

	@Override
	public void enableLauncher(Launcher launcher) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void disableLauncher(Launcher launcher) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void launch(Launcher launcher, float x, float y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void touchLauncher(Launcher launcher, float x, float y) {
		// TODO Auto-generated method stub
		this.markDestroy();
	}

	@Override
	public void pullingLauncher(Launcher launcher, float x, float y) {
		// TODO Auto-generated method stub
		
	}
	public void destroy(){
		if(destroyed) {return;}
		super.destroy();
		launcher.removeLauncherListener(this);
		launcher=null;
		hand=null;
		Log.i("INFO", "DESTROY HAND");
	}
}
