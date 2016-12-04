package com.pandeagames.www.gutterballredux.gameObjects;

import com.pandeagames.www.gutterballredux.Components.AbstractGameComponent;
import com.pandeagames.www.gutterballredux.gameControllers.Game;
import com.pandeagames.www.gutterballredux.infoHolders.UpdateInfo;

public class Floater extends AbstractGameComponent {
 
	private AbstractGameComponent floater;
	public float x;
	private float v;
	private int dir=1;
	private float damp;
	private int amp;
	public Floater(Game game, int amp, float damp) {
		super(game);
		// TODO Auto-generated constructor stub
		this.floater=floater;
		this.amp=amp;
		x=(float)Math.random()*amp*2-amp;
		v=0;
		this.damp=damp;
	}

	@Override
	public void update(UpdateInfo updateInfo) {
		// TODO Auto-generated method stub
		if(x>amp){
			dir=-1;
		}else if(x<-amp){
			dir=1;
		}
		v+=dir;
		v=v*0.9f;
		x=x+v*damp;
		
	}

}
