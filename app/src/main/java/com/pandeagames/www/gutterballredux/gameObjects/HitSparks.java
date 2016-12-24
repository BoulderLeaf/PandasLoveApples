package com.pandeagames.www.gutterballredux.gameObjects;

import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.collision.Manifold;
import org.jbox2d.collision.ManifoldPoint;
import org.jbox2d.collision.WorldManifold;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.contacts.Contact;
import org.jbox2d.dynamics.contacts.ContactEdge;

import com.pandeagames.R;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;

import com.pandeagames.www.gutterballredux.infoHolders.DrawInfo;
import com.pandeagames.www.gutterballredux.infoHolders.UpdateInfo;
import com.pandeagames.www.gutterballredux.gameControllers.Game;
import com.pandeagames.www.gutterballredux.Components.BodyComponent;
import com.pandeagames.www.gutterballredux.Components.DrawableGameComponent;
import com.pandeagames.www.gutterballredux.Components.interfaces.IContactCallback;

public class HitSparks extends DrawableGameComponent implements IContactCallback {
	private Spark[] sparks;
	private float fric;
	private Body body;
	private ContactEdge edge;
	private Contact contact;
	private Manifold manifold;
	private WorldManifold worldManifold;
	private Paint paint;
	private int sparkLimit=100;
	private Vec2 vel1;
	private Vec2 vel2;
	private BitmapDrawable grass;
	private  Rect des;
	private float contactVelocity = 4;
	public HitSparks(Game game) {
		super(game);
		sparks=new Spark[sparkLimit];
		for(int i=0;i<sparkLimit;i++){
			sparks[i] = new Spark();
		}
		game.getSimulation().addPostSolveListener(this);
		fric=0.5f;
		paint=new Paint();
		worldManifold=new WorldManifold();
		paint.setARGB(255, 253, 252, 241);
		 grass=(BitmapDrawable) game.getResources().getDrawable(R.drawable.grass);
		 
		// TODO Auto-generated constructor stub
	}
	@Override
	public int drawOrder(){
		return 1;
	}
	@Override
	public void update(UpdateInfo updateInfo){
		super.update(updateInfo);
		for(Spark spark : sparks){
			if(spark.active){
				spark.x+=spark.vx;
				spark.y+=spark.vy;
				float totalVel = (float)Math.abs(spark.vx)+(float)Math.abs(spark.vy);
				spark.rotation+=spark.vy*10*spark.dir;
				spark.vx=spark.vx*fric;
				spark.vy=spark.vy*fric+0.08f*totalVel;
				if(spark.vy<0.005&&spark.vx<0.005){
					spark.active=false;
				}
			}
		}
		
	}
	@Override
	public void draw(DrawInfo drawInfo){
		super.draw(drawInfo);
		for(Spark spark : sparks){
			if(spark.active){
			
				/*drawInfo.getCanvas().drawLine(
						gameView.toScreenX(spark.x-spark.vx),
						gameView.toScreenY(spark.y-spark.vy), 
						gameView.toScreenX(spark.x), 
						gameView.toScreenY(spark.y), 
						paint);*/
				//drawInfo.getCanvas().draw
				drawInfo.getCanvas().save();
				des = new Rect();
				des.set(
						(int)gameView.toScreenX(spark.x-0.05), 
						(int)gameView.toScreenY(spark.y-0.5), 
						(int)gameView.toScreenX(spark.x+0.15), 
						(int)gameView.toScreenY(spark.y+0.5));
				grass.setBounds(des);
				drawInfo.getCanvas().rotate(spark.rotation,des.exactCenterX(), des.exactCenterY());
				float totalVel = (float)Math.abs(spark.vx)+(float)Math.abs(spark.vy);
				if(totalVel<0.5){
					grass.setAlpha((int)(255*(totalVel/0.5)));
				}else{
					grass.setAlpha(255);
				}
				
				grass.draw(drawInfo.getCanvas());
				drawInfo.getCanvas().restore();
				
			}
		}
		
	}
	@Override
	public void onBeginContact(Contact c) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onEndContact(Contact c) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onPreSolve(Contact c, Manifold manifold) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onPostSolve(Contact c, ContactImpulse cImpulse) {
		// TODO Auto-generated method stub
		//cImpulse.
		BodyComponent body = (BodyComponent)c.getFixtureA().getBody().getUserData();
		if(c.isTouching() && body.getVelocity() > contactVelocity){
			manifold=c.getManifold();
			c.getWorldManifold(worldManifold);
				int count=0;
				game.getSoundPool().playRandom(game.getSoundPool().getPool().hit1,
						game.getSoundPool().getPool().hit2,
						game.getSoundPool().getPool().hit3,
						game.getSoundPool().getPool().hit4);
				//pt.localPoint.
				for(Spark spark : sparks){
					if(!spark.active){
						spark.active=true;
						//spark.x=pt.localPoint.x;
						//spark.y=pt.localPoint.y;
						spark.x=worldManifold.points[0].x;
						spark.y=worldManifold.points[0].y;
						//spark.vx=2*worldManifold.normal.x;
						//spark.vy=2*worldManifold.normal.y;
						
						vel1 = c.getFixtureA().getBody().getLinearVelocityFromWorldPoint(worldManifold.points[0]);
						vel2 = c.getFixtureB().getBody().getLinearVelocityFromWorldPoint(worldManifold.points[0]);
						
						
						spark.rotation=(int)(Math.random()*180);
						spark.dir=(int)(1-(Math.random()*2)*2);
						spark.vx=vel2.x/(5+(float)Math.random()*20);
						spark.vy=vel2.y/(5+(float)Math.random()*20);
						
						
						
						if(++count>5){
						break;
						}
					}
				}
		}
	}
}
