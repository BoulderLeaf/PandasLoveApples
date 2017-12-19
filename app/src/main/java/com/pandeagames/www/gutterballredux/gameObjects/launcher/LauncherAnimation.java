package com.pandeagames.www.gutterballredux.gameObjects.launcher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import toxi.geom.Vec2D;
import toxi.physics.VerletParticle;
import toxi.physics.VerletPhysics;
import toxi.physics2d.ParticleString2D;
import toxi.physics2d.VerletParticle2D;
import toxi.physics2d.VerletPhysics2D;
import toxi.physics2d.VerletSpring2D;
import toxi.physics2d.behaviors.GravityBehavior;

import com.pandeagames.R;

import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;

import com.pandeagames.www.gutterballredux.gameControllers.BitmapPool;
import com.pandeagames.www.gutterballredux.infoHolders.DrawInfo;
import com.pandeagames.www.gutterballredux.infoHolders.UpdateInfo;
import com.pandeagames.www.gutterballredux.gameControllers.Game;
import com.pandeagames.www.gutterballredux.gameObjects.animNodes.BounceFollowNode;
import com.pandeagames.www.gutterballredux.gameObjects.animNodes.DoubleJointNode;
import com.pandeagames.www.gutterballredux.gameObjects.animNodes.JointNode;
import com.pandeagames.www.gutterballredux.gameObjects.animNodes.LooseFollowNode;
import com.pandeagames.www.gutterballredux.gameObjects.animNodes.StiffAngleNode;
import com.pandeagames.www.gutterballredux.Components.DrawableGameComponent;

public class LauncherAnimation extends DrawableGameComponent implements ILauncherListener {
	private Launcher launcher;
	private Paint paint;
	private Rect des;
	private VerletPhysics2D physics;
	private BitmapDrawable launcher_tree, basket, basket_full;
	
	//physics
	private ParticleString2D str1, str2;
	
	public LauncherAnimation(Game game, Launcher launcher) {
		super(game);
		
		physics= new VerletPhysics2D();
		physics.setTimeStep(0.2f);
	
		GravityBehavior g = new GravityBehavior(new Vec2D(0, 1));
		physics.addBehavior(g);
		
		VerletParticle2D p1 = new VerletParticle2D(launcher.getX()-2, launcher.getY()-3);//where the rope is tied
		VerletParticle2D p2 = new VerletParticle2D(launcher.getX()+2, launcher.getY()-3);//where the rope id tied
		
		p1.lock();//lock the ends of the ropes
		p2.lock();//lock the ends of the ropes
		
		List<VerletParticle2D> st1List = new ArrayList<VerletParticle2D>();
		List<VerletParticle2D> st2List = new ArrayList<VerletParticle2D>();
		st1List.add(p1);
		st2List.add(p2);
		int i=0;
		int stringDensity=8;
		VerletParticle2D p;
		//first string
		for(i=0;i<stringDensity;i++){
			p=new VerletParticle2D(launcher.getX()-2, launcher.getY()-3+i*0.6f);
			st1List.add(p);
		}
		//second string
		for(i=0;i<stringDensity;i++){
			p=new VerletParticle2D(launcher.getX()+2, launcher.getY()-3+i*0.6f);
			st2List.add(p);
		}
		
		str1 = new ParticleString2D(physics, st1List, 0.2f);
		str2 = new ParticleString2D(physics, st2List, 0.2f);
		
		VerletSpring2D s1 = new VerletSpring2D(str2.getTail(), str1.getTail(), 2, 1f);
		physics.addSpring(s1);

		paint = new Paint();
		paint.setARGB(255, 199, 221, 71);
		paint.setStrokeWidth(3);

		launcher_tree = BitmapPool.getBitmapDrawable(game.getResources(), R.drawable.tree_launcher);
		basket = BitmapPool. getBitmapDrawable(game.getResources(), R.drawable.leaf_bag);
		basket_full = BitmapPool.getBitmapDrawable(game.getResources(), R.drawable.leaf_bag_full);
		this.launcher=launcher;
		launcher.addLauncherListener(this);

		des = new Rect();
	}
	
	@Override
	public void draw(DrawInfo info){
		super.draw(info);
		
	
		des.set(
				(int)gameView.toScreenX(launcher.getX()-7-0.5),
				(int)gameView.toScreenY(launcher.getY()-7-0.5),
				(int)gameView.toScreenX(launcher.getX()+7-0.5),
				(int)gameView.toScreenY(launcher.getY()+7-0.5)
				);
		launcher_tree.setBounds(des);
		launcher_tree.draw(info.getCanvas());
		float midX = (str1.getTail().x+str2.getTail().x)/2;
		float midY = (str1.getTail().y+str2.getTail().y)/2;
		des.set(
				(int)gameView.toScreenX(midX-2),
				(int)gameView.toScreenY(midY-2),
				(int)gameView.toScreenX(midX+2),
				(int)gameView.toScreenY(midY+2)
				);
		
		float dx = str2.getTail().x-str1.getTail().x;
		float dy = str2.getTail().y-str1.getTail().y;
		double a=Math.atan2(dy, dx)/(Math.PI/180);
		
		info.getCanvas().save();
		info.getCanvas().rotate((float)a,gameView.toScreenX(midX),gameView.toScreenY(midY));
		if(launcher.getState()==Launcher.DISABLED){
			basket.setBounds(des);
			basket.draw(info.getCanvas());
		}else{
			basket_full.setBounds(des);
			basket_full.draw(info.getCanvas());
		}
		
		info.getCanvas().restore();
	
		int i;
		//Draw the first rope
		for(i=1;i<str1.particles.size();i++){
			info.getCanvas().drawLine(
					(int)(gameView.toScreenX(str1.particles.get(i-1).x)),
					(int)(gameView.toScreenY(str1.particles.get(i-1).y)),
					(int)(gameView.toScreenX(str1.particles.get(i).x)),
					(int)(gameView.toScreenY(str1.particles.get(i).y)),
					paint);
		}
		//draw the second rope
		for(i=1;i<str2.particles.size();i++){
			info.getCanvas().drawLine(
					(int)(gameView.toScreenX(str2.particles.get(i-1).x)),
					(int)(gameView.toScreenY(str2.particles.get(i-1).y)),
					(int)(gameView.toScreenX(str2.particles.get(i).x)),
					(int)(gameView.toScreenY(str2.particles.get(i).y)),
					paint);
		}
		/*for(VerletParticle2D p  : physics.particles ){
			info.getCanvas().drawCircle(
					gameView.toScreenX(p.getPreviousPosition().x), 
					gameView.toScreenY(p.getPreviousPosition().y), 
					gameView.toScreen(0.1f), 
					paint);
		}*/
		
		

	}
	@Override
	public void update(UpdateInfo updateInfo){
		super.update(updateInfo);
		physics.update();
		if(launcher.getState()==Launcher.PULLING){
			
			
			
			str1.getTail().addForce(new Vec2D(
					(gameView.toScreenX(launcher.getX())-launcher.getFingX())/1000, 
					(gameView.toScreenY(launcher.getY())-launcher.getFingY())/1000
					));
			
			str2.getTail().addForce(new Vec2D(
					(gameView.toScreenX(launcher.getX())-launcher.getFingX())/1000, 
					(gameView.toScreenY(launcher.getY())-launcher.getFingY())/1000
					));
		}
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
		str1.getTail().addForce(new Vec2D(
				(gameView.toScreenX(launcher.getX())-launcher.getFingX())/50*-1, 
				(gameView.toScreenY(launcher.getY())-launcher.getFingY())/50*-1
				));
		
		str2.getTail().addForce(new Vec2D(
				(gameView.toScreenX(launcher.getX())-launcher.getFingX())/50*-1, 
				(gameView.toScreenY(launcher.getY())-launcher.getFingY())/50*-1
				));
	}
	@Override
	public void touchLauncher(Launcher launcher, float x, float y) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void pullingLauncher(Launcher launcher, float x, float y) {
		// TODO Auto-generated method stub
		
	}
	
}
