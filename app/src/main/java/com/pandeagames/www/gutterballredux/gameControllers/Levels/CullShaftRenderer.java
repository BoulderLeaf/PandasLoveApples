package com.pandeagames.www.gutterballredux.gameControllers.Levels;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.pandeagames.R;


import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import com.pandeagames.www.gutterballredux.infoHolders.DrawInfo;
import com.pandeagames.www.gutterballredux.infoHolders.UpdateInfo;
import com.pandeagames.www.gutterballredux.Components.DrawableGameComponent;
import com.pandeagames.www.gutterballredux.gameControllers.Game;
import com.pandeagames.www.gutterballredux.gameControllers.GameView;
import com.pandeagames.www.gutterballredux.gameObjects.Actor;

public class CullShaftRenderer extends DrawableGameComponent implements IBottomCullListener {
	private List<Culling> cullList;
	private BitmapDrawable lightShaft;
	public CullShaftRenderer(Game game) {
		super(game);
		lightShaft = (BitmapDrawable) game.getResources().getDrawable(R.drawable.light_pillar);
		cullList = new ArrayList<Culling>();
	}
	@Override
	public void update(UpdateInfo updateInfo){
		super.update(updateInfo);
		Iterator<Culling> iter = cullList.iterator();
		while(iter.hasNext()){
			Culling culling  = iter.next();
			if(culling.alpha<0.01){
				iter.remove();
			}else{
				culling.alpha=culling.alpha*(0.95f-0.3f*culling.alpha);
				//culling.y+=0.3;
			}
		}
	}
	
	@Override
	public void onBottomCull(Actor actor) {
		int jitterFactor = 2;
		Culling  culling= new Culling();
		culling.x=actor.getX();
		cullList.add(culling);
		culling.main=true;
		for(int i =0;i<4; i++){
			culling  = new Culling();
			float ran=(float)(Math.random()-Math.random())*jitterFactor;
			culling.x=actor.getX()+(ran)*jitterFactor;
			culling.scale=(jitterFactor-Math.abs(ran))/jitterFactor/2+0.5f;
			culling.alpha=culling.scale;
			cullList.add(culling);
		}
	}
	@Override
	public void draw(DrawInfo drawInfo){
		super.draw(drawInfo);
		for(Culling culling : cullList){
			float ratio = 60f/425f;
			Rect des = new Rect();
			des.set((int)gameView.toScreenX(culling.x-1*culling.scale), 
					(int)gameView.toScreenY(game.getSimulation().getWorldSize().y-2/ratio*culling.scale+culling.y),
					(int)gameView.toScreenX(culling.x+1*culling.scale),
					(int)gameView.toScreenY(game.getSimulation().getWorldSize().y+culling.y));
			lightShaft.setBounds(des);
			lightShaft.setAlpha((int)(255*culling.alpha));
			lightShaft.draw(drawInfo.getCanvas());
		}
	}
	private class Culling {
		public boolean main = false;//number will render over main shaft
		public float scale=1.0f;
		public float alpha=1.0f;
		public float x=0.0f;
		public float y=0.0f;
	}

}
