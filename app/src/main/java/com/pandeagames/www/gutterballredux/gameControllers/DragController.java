package com.pandeagames.www.gutterballredux.gameControllers;

import com.pandeagames.www.gutterballredux.infoHolders.UpdateInfo;

import org.jbox2d.collision.AABB;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;

import android.content.DialogInterface;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import com.pandeagames.www.gutterballredux.Components.BodyComponent;
import com.pandeagames.www.gutterballredux.Components.interfaces.IBodyTouchCallback;
import com.pandeagames.www.gutterballredux.Components.interfaces.IUserInputComponent;

public class DragController extends BodyController implements IBodyTouchCallback, IUserInputComponent {
private boolean doDrag=false;
private float fingerX;
private float fingerY;
private float oldX;
private float oldY;
private float vx;
private float vy;
private float xOffset=0.0f;
private float yOffset=0.0f;
	public DragController(BodyComponent bodyComp, Game game) {
		super(bodyComp, game);
		// TODO Auto-generated constructor stub
		bodyComp.addOnBodyTouchCallback(this);
		game.addInputComponent(this);
	}
	@Override 
	public void update(UpdateInfo info){
		super.update(info);
		if(doDrag){
			vx=fingerX-oldX;
			vy=fingerY-oldY;
			oldX=fingerX;
			oldY=fingerY;
			Body body = bodyComp.getBody();
			
			Simulation sim=game.getSimulation();
			Vec2 gravity = sim.getGravity();
			
			body.setTransform(new Vec2(gameView.toWorldX(fingerX-xOffset), gameView.toWorldY(fingerY-yOffset)), 0.0f);
		}
	}

	@Override
	public void onBodyTouch(BodyComponent bodyComp, MotionEvent event) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		if(event.getAction()==MotionEvent.ACTION_DOWN){
			//xOffset=gameView.toScreenX(bodyComp.getBody().getTransform().position.x)-event.getRawX();
		//	yOffset=gameView.toScreenY(bodyComp.getBody().getTransform().position.y)-event.getRawY();
			doDrag=true;
		}
	}
	@Override
	public void onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		if(event.getAction()==MotionEvent.ACTION_UP){
			if(doDrag){
				bodyComp.getBody().setLinearVelocity(new Vec2(vx, vy));
			}
			doDrag=false;
		}
		if(event.getAction()==MotionEvent.ACTION_DOWN ||
			event.getAction()==MotionEvent.ACTION_MOVE){
			fingerX=event.getRawX();
			fingerY=event.getRawY();
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

}
