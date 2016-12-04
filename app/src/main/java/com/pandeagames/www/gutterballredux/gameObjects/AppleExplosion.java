package com.pandeagames.www.gutterballredux.gameObjects;

import android.graphics.Paint;
import com.pandeagames.www.gutterballredux.infoHolders.DrawInfo;
import com.pandeagames.www.gutterballredux.infoHolders.UpdateInfo;
import com.pandeagames.www.gutterballredux.gameControllers.Game;
import com.pandeagames.www.gutterballredux.Components.AbstractGameComponent;
import com.pandeagames.www.gutterballredux.Components.DrawableGameComponent;

public class AppleExplosion extends DrawableGameComponent {
	private float g;//gravity
	private float f;//friction
	private float d;//decay;
	private int juiceNum;
	private juice[] juiceList;
	private Paint paint;
	private int killInterval;//how many updated before destroying self
	public AppleExplosion(Game game, float x, float y) {
		this(game, x, y,0);
		
	}
	public AppleExplosion(Game game, AbstractGameComponent comp) {
		this(game,comp.getX(), comp.getY(), 0);
		
	}

	public AppleExplosion(Game game,float x, float y, int drawOrder) {
		super(game, drawOrder);
		juiceNum=15;
		juiceList=new juice[juiceNum];
		float strength;
		float a;
		float maxR=2;
		float maxV=3;
		for(int i=0;i<juiceNum;i++)
		{
			a=(float)Math.toRadians(Math.random()*360);
			strength=(float)Math.random();
			juiceList[i]=new juice(
					x+(float)Math.cos(a)*maxR*strength,
					y+(float)Math.sin(a)*maxR*strength,
					(float)Math.cos(a)*maxV*strength,
					(float)Math.sin(a)*maxV*strength,
					1-strength
					);
		}
		paint=new Paint();
		paint.setARGB(255, 255,225, 100);
		g=0.05f;
		f=0.40f;
		d=0.75f;
	}
	public AppleExplosion(Game game,AbstractGameComponent comp, int drawOrder) {
		this(game,comp.getX(), comp.getY(), drawOrder);
	}
	public void draw(DrawInfo drawInfo){
		super.draw(drawInfo);
		for(int i=0;i<juiceNum;i++)
		{
			drawInfo.getCanvas().drawCircle(
					gameView.toScreenX(juiceList[i].x), 
					gameView.toScreenY(juiceList[i].y), 
					gameView.toScreen(juiceList[i].r), 
					paint);
		}
	}
	public void update(UpdateInfo updateInfo){
		super.update(updateInfo);
		juice j;
		for(int i=0;i<juiceNum;i++)
		{
			j=juiceList[i];
			j.x+=j.vx;
			j.y+=j.vy;
			j.vy=j.vy*f;
			j.vx=j.vx*f;
			j.r=j.r*d;
		}
		if(killInterval++>50){
			destroy();
		}
	}
	private class juice{
		public float x;
		public float y;
		public float vx;
		public float vy;
		public float r;
		public juice(float x, float y, float vx, float vy, float r){
			this.x=x;
			this.y=y;
			this.vx=vx;
			this.vy=vy;
			this.r=r;
		}
	}

}
