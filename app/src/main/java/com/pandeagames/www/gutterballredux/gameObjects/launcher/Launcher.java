package com.pandeagames.www.gutterballredux.gameObjects.launcher;

import java.util.ArrayList;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;

import com.pandeagames.www.gutterballredux.gameControllers.Game;
import com.pandeagames.www.gutterballredux.gameObjects.Actor;
import com.pandeagames.www.gutterballredux.infoHolders.DrawInfo;
import com.pandeagames.www.gutterballredux.infoHolders.UpdateInfo;
import android.content.DialogInterface;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import com.pandeagames.www.gutterballredux.droidControllers.SwingActivity;
import com.pandeagames.www.gutterballredux.Components.DrawableComponent;
import com.pandeagames.www.gutterballredux.Components.DrawableGameComponent;
import com.pandeagames.www.gutterballredux.Components.interfaces.IBodyCreationListener;
import com.pandeagames.www.gutterballredux.Components.interfaces.IUserInputComponent;

public class Launcher extends DrawableGameComponent implements IUserInputComponent{
	public static final int IDLE=0, DISABLED=1, PULLING=2;
	private int STATE=IDLE;
	private Paint paint;
	private Paint pullPaint;
	private int r=12;
	private boolean isPulling;
	private Point fingerPt;
	private float fingX;
	private float fingY;
	private int line_offset=0;
	private int lineSize=20;
	private int lineDist=10;
	private long delay=0;
	private double maxStrength = 28;
	private double baseStrength = 5;

	private long lastLaunchTime=0;
	private boolean enabled=true;
	//list of launcher listeners
	private ArrayList<ILauncherListener> launcherListeners;
	//finger velocity
	private float vx=0f;
	private float vy=0f;
	
	//pulling values;
	public double a, cos, sin;
	public int d;
	public float  dx, dy;
	public Launcher(Game activity, float x, float y) {
		super(activity);
		// TODO Auto-generated constructor stub
		setPos(x, y);
		fingerPt=new Point();
		pullPaint=new Paint();
		paint  = new Paint();
		paint.setARGB(255,255, 248, 206);
		pullPaint.setARGB(255, 253, 252, 241);
		pullPaint.setStrokeWidth(3);
		activity.addInputComponent(this);
		
		launcherListeners=new ArrayList<ILauncherListener>();
	}
	public int getState(){
		return STATE;
	}
	public float getFingX(){
		return fingX;
	}
	public float getFingY(){
		return fingY;
	}
	
	@Override
	public void draw(DrawInfo info){

		super.draw(info);
		
		//Body
		info.getCanvas().drawCircle(gameView.toScreenX(getX()),
				gameView.toScreenY(getY()),
				gameView.toScreen(1), paint);
		//Pull Line
		if(isPulling){
			dx = gameView.toScreenX(getX())-fingerPt.x;
			dy = gameView.toScreenY(getY())-fingerPt.y;
			d = (int)Math.sqrt(dx*dx+dy*dy);
			line_offset+=d/80;
			//double a= Math.atan2(dy,  dx)/(Math.PI/180);
			a= Math.atan2(dy,  dx);
			
			cos = Math.cos(a);
			sin = Math.sin(a);

			
			if(line_offset>lineSize+lineDist){
				int r = (int)(line_offset%(lineSize+lineDist));
				line_offset=r;
			}
			line_offset=0;
			for(int i=(int)line_offset;i<d;i+=lineSize+lineDist){
				float x = (float)(cos*(i+lineSize));
				float y= (float)(sin*(i+lineSize));
				info.getCanvas().drawLine(
						(int)(gameView.toScreenX(getX())-x),
						(int)(gameView.toScreenY(getY())-y),
						(int)(gameView.toScreenX(getX())-cos*(i)),
						(int)(gameView.toScreenY(getY())-sin*(i)),
						pullPaint);
				
			}
			
			/*info.getCanvas().drawLine(
					fingerPt.x,
					fingerPt.y,
					gameView.toScreenX(getX()),
					gameView.toScreenY(getY()),
					pullPaint);*/
		}
		
	}
	public void disable(){
		setEnabled(false);
		for(ILauncherListener listener : launcherListeners){
			listener.disableLauncher(this);
		}
	}
	public void enable(){
		setEnabled(true);
		for(ILauncherListener listener : launcherListeners){
			listener.enableLauncher(this);
		}
	}
	public void setEnabled(boolean value){
		enabled=value;
		if(value==false){
			STATE=DISABLED;
		}else{
			STATE=IDLE;
		}
	}
	@Override
	public void onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		if(!enabled){return;}
			switch(event.getAction()){
			case MotionEvent.ACTION_UP:
				if(isPulling)stopPull(event);
				
				break;
				case MotionEvent.ACTION_DOWN:
					game.getSoundPool().playRandom(game.getSoundPool().getPool().rope1,
							game.getSoundPool().getPool().rope1);
					if(getCircleHit(gameView.toWorldX(event.getRawX()),gameView.toWorldY(event.getRawY()), 3, getX(), getY(), r)){
						for(ILauncherListener listener : launcherListeners){
							listener.touchLauncher(this, event.getRawX(), event.getRawY());
						}
						doPull(event);
					}
					break;
				case MotionEvent.ACTION_MOVE:
					if(isPulling){
						doPull(event);
						for(ILauncherListener listener : launcherListeners){
							listener.pullingLauncher(this, fingX, fingY);
						}
					}
					break;
			}
	}
	public void setDelay(long delay){
		this.delay=delay;
	}
	public void update(UpdateInfo updateInfo)
	{
		super.update(updateInfo);
		if(!enabled && updateInfo.getTime()-lastLaunchTime>delay){
			enable();
		}
	}
	private void stopPull(MotionEvent event){
		if(!enabled){return;}
		STATE=IDLE;
		//Spawn Actor
		Actor actor = new Actor(game, new Vec2(getX(), getY()));
		//Launch Actor
		float dx  = gameView.toWorld(fingX)-getX();
		float dy  = gameView.toWorld(fingY)-getY();
		double a= Math.atan2(dy,  dx);
		double d= Math.sqrt(dx*dx+dy*dy);
		
		//double vd = Math.sqrt(vx*vx+vy*vy);
		double vd = Math.sqrt(dx*dx+dy*dy);

		vd = vd > r ? r:vd;
		
		double cos = Math.cos(a);
		double sin = Math.sin(a);
		
		double dNew = Math.pow(d/100, 3);
		double dReduced = dNew*200;
		
		double power = baseStrength + (vd / r) * maxStrength;
		
		Vec2 force = new Vec2(
				(float)(cos*power), 
				(float)(sin*power));
		//Vec2 force = new Vec2((float)(cos*(d*10)), (float)(sin*(d*10)));
		//Vec2 force = new Vec2((float)(cos*(d/20+Math.abs(vx)*4)), (float)(sin*(d/20+Math.abs(vy)*4)));
		
		BodyLauncher launcher = new BodyLauncher(force, force);
		
		actor.addBodyCreationListener(launcher);
		
		isPulling=false;
		for(ILauncherListener listener : launcherListeners){
			listener.launch(this, fingX, fingY);
		}
		if(delay!=0){
			lastLaunchTime=System.currentTimeMillis();
			disable();
		}
		game.getSoundPool().playRandom(game.getSoundPool().getPool().swing1,
				game.getSoundPool().getPool().swing2);
	}
	private void doPull(MotionEvent event)
	{
		if(!enabled){return;}
		STATE=PULLING;
		
		isPulling=true;
	
		fingX=event.getRawX();
		fingY=event.getRawY();
		fingerPt.set((int)event.getRawX(), (int)event.getRawY());
		
		if(event.getHistorySize()>0){
			vx=0;
			vy=0;
			
			int limit = 1;
			if(event.getHistorySize()+1<limit){
				limit=event.getHistorySize();
			}
			
			for(int i=1;i<limit;i++){
				vx+=fingX-event.getHistoricalX(event.getHistorySize()-i);
				vy+=fingY-event.getHistoricalY(event.getHistorySize()-i);
			}
			if(vx!=0)vx=gameView.toWorld(vx/(limit-1));
			if(vy!=0)vy=gameView.toWorld(vy/(limit-1));
		}
	}

	@Override
	public void onLongClick(View v) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onCreateContextMenu() {
		// TODO Auto-generated method stub

	}
	private boolean getCircleHit(float x1, float y1,float r1, float x2, float y2, float r2){
		if(getDist(x1,y1,x2,y2)<r1+r2)return true;
		return false;
	}
	private float getDist(float x1, float y1, float x2, float y2){
		float dx = x2-x1;
		float dy = y2-y1;
		return (float) Math.sqrt(dx*dx+dy*dy);
		
	}
	public float getRadius(){
		return r;
	}
	public void addLauncherListener(ILauncherListener launcherListener){
		launcherListeners.add(launcherListener);
	}
	public void removeLauncherListener(ILauncherListener launcherListener){
		launcherListeners.remove(launcherListener);
	}
	@Override
	public void destroy(){
		super.destroy();
		paint=null;
		pullPaint=null;
		fingerPt=null;
		launcherListeners.clear();
		launcherListeners=null;
	}

		private class BodyLauncher implements IBodyCreationListener{
			private Vec2 force;
			private Vec2 point;
			public BodyLauncher(Vec2 force, Vec2 point){
				this.force=force;
				this.point=point;
			}

			@Override
			public void bodyCreated(Body body)
			{
				// TODO Auto-generated method stub
				body.setLinearVelocity(force);
				//body.applyForce(force, point);
			}
		}
		
}
