package com.pandeagames.www.gutterballredux.gameObjects;

import org.jbox2d.common.Vec2;

import com.pandeagames.R;

import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import com.pandeagames.www.gutterballredux.gameControllers.Game;
import com.pandeagames.www.gutterballredux.gameControllers.RadialCollider;
import com.pandeagames.www.gutterballredux.gameControllers.RadialID;
import com.pandeagames.www.gutterballredux.gameControllers.Simulation;
import com.pandeagames.www.gutterballredux.infoHolders.DrawInfo;
import com.pandeagames.www.gutterballredux.infoHolders.UpdateInfo;
import com.pandeagames.www.gutterballredux.droidControllers.SwingActivity;
import com.pandeagames.www.gutterballredux.Components.DrawableComponent;
import com.pandeagames.www.gutterballredux.Components.DrawableGameComponent;
import com.pandeagames.www.gutterballredux.Components.interfaces.IRadialCollider;

public class Portal extends DrawableGameComponent implements IRadialCollider {
private Paint paint;
private BitmapDrawable sphere;
private RadialCollider collider;
private Floater floater;
	private AppleType type;
	private float fallValue = 0;
private IObtainedCallback obtainedCallback;
	public Portal(Game activity, AppleType type) {
		this(activity, 0, 0, type);
	}
	public Portal(Game activity, float x, float y, AppleType type) {
		super(activity);
		setPos(x, y);

		this.type = type;

		//used to generate the floating animation of the apple
		floater=new Floater(activity, 2, 0.2f);

		fallValue = (float)(-15 + Math.random() * - 5);

		paint = new Paint();
		paint.setARGB(255, 107, 255, 107);

		//collider for when the apple is struck by the pandas.
		collider = new RadialCollider(activity,this, 2, RadialID.PORTAL);

		//choose artwork for apple based on apple type.
		if(type == AppleType.NORMAL){
			int ran = (int)(Math.random() * 5);
			switch(ran){
				case 0:
					sphere=(BitmapDrawable) game.getResources().getDrawable(R.drawable.green_sphere);
					break;
				case 1:
					sphere=(BitmapDrawable) game.getResources().getDrawable(R.drawable.apple_01);
					break;
				case 2:
					sphere=(BitmapDrawable) game.getResources().getDrawable(R.drawable.apple_03);
					break;
				case 3:
					sphere=(BitmapDrawable) game.getResources().getDrawable(R.drawable.apple_04);
					break;
				case 4:
					sphere=(BitmapDrawable) game.getResources().getDrawable(R.drawable.apple_05);
					break;
			}

		}
		else
		{
			sphere=(BitmapDrawable) game.getResources().getDrawable(R.drawable.golden_apple);
		}
	}
	@Override
	public void draw(DrawInfo info){
		super.draw(info);
		float x=getX();
		float y=getY()+floater.x/20;
		float r=1;

		Rect des = new Rect();
		des.set((int)gameView.toScreenX(x-r), 
				(int)gameView.toScreenY((y+fallValue-1.1875f)),
				(int)gameView.toScreenX(x+r),
				(int)gameView.toScreenY(y+fallValue+r));

		sphere.setBounds(des);
		sphere.draw(info.getCanvas());
	}
	@Override
	public void radialCollide(RadialCollider other) {
		if(other.getId()==RadialID.ACTOR)
		{
			obtained((Actor)other.getCollider());
		}
	}
	@Override
	public void destroy(){
		if(destroyed) {return;}
		collider.destroy();
	super.destroy();
	}
	public void obtained(Actor actor){
		if(obtainedCallback!=null)
		{
			obtainedCallback.portalObtained(this, actor);
		}
		AppleExplosion e = new AppleExplosion(game, this);
		e=null;
		destroy();
	}
	@Override
	public void update(UpdateInfo updateInfo){
		fallValue = fallValue * 0.85f;
		super.update(updateInfo);
	}
	public void setCallback(IObtainedCallback callback){
		this.obtainedCallback=callback;
	}

	public AppleType getType(){
		return type;
	}

	public interface IObtainedCallback{
		public void portalObtained(Portal portal, Actor actor);
	}
}
