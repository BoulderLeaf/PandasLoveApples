package com.pandeagames.www.gutterballredux.gameControllers;
import java.util.ArrayList;
import java.util.List;

import com.pandeagames.www.gutterballredux.infoHolders.UpdateInfo;

import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.callbacks.QueryCallback;
import org.jbox2d.collision.AABB;
import org.jbox2d.collision.Manifold;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Fixture;
import org.jbox2d.dynamics.World;
import org.jbox2d.dynamics.contacts.Contact;
import org.jbox2d.common.*;

import com.pandeagames.www.gutterballredux.threads.BufferedList;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Point;
import android.os.IInterface;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

import com.pandeagames.www.gutterballredux.Components.AbstractComponent;
import com.pandeagames.www.gutterballredux.Components.AbstractGameComponent;
import com.pandeagames.www.gutterballredux.Components.BodyComponent;
import com.pandeagames.www.gutterballredux.Components.interfaces.IContactCallback;
import com.pandeagames.www.gutterballredux.Components.interfaces.IUserInputComponent;

public class Simulation extends AbstractGameComponent implements IUserInputComponent, QueryCallback, ContactListener {
	private World world;
	private Vec2 gravity;
	private float timeStep;
	private int velocityIterations;
	private int positionIterations;
	private Point worldSize;
	private BufferedList<BodyComponent> bodyList;
	private MotionEvent lastEvent;
	
	private List<IContactCallback> beginContactListeners;
	private List<IContactCallback> endContactListeners;
	private List<IContactCallback> preSolveListeners;
	private List<IContactCallback> postSolveListeners;
	
public Simulation(Game game, BufferedList<BodyComponent> bodyList){
	super(game);
	gravity = new Vec2(0.0f,3.0f);
	world = new World(gravity, true);
	timeStep = 1.0f/15.0f;
     velocityIterations = 1;
     positionIterations = 1;
     worldSize=new Point(24,40);
     this.bodyList=bodyList;
     game.addInputComponent(this);
     
     beginContactListeners = new ArrayList<IContactCallback>();
     endContactListeners = new ArrayList<IContactCallback>();
     preSolveListeners = new ArrayList<IContactCallback>();
     postSolveListeners = new ArrayList<IContactCallback>();
     
     world.setContactListener(this);
}
public Point getWorldSize(){
	return worldSize;
}
public World getWorld(){
	return world;
}
public Vec2 getGravity(){
	return gravity;
}
@Override
public void destroy() {
	// TODO Auto-generated method stub
	super.destroy();
}
@Override
public void update(UpdateInfo updateInfo) {
	world.step(timeStep, velocityIterations, positionIterations);
	//world.step(updateInfo.getDeltaTime()/100, velocityIterations, positionIterations);
	bodyList.clearBuffer();
}
@Override
public void onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
	
}
@Override
public void onTouch(View v, MotionEvent event) {
	if(event.getAction()==MotionEvent.ACTION_DOWN){
	lastEvent=event;
	world.queryAABB(this, new AABB(
			new Vec2(gameView.toWorldX(event.getRawX())-2,gameView.toWorldY(event.getRawY())-2),
			new Vec2(gameView.toWorldX(event.getRawX())+2,gameView.toWorldY(event.getRawY())+2)
			));
	lastEvent=event;
	}
}
@Override
public void onLongClick(View v) {
	
}
@Override
public void onClick(DialogInterface dialog, int which) {
	
}
@Override
public void onCreateContextMenu() {
	
}
@Override
public boolean reportFixture(Fixture fix) {
	BodyComponent  userData=(BodyComponent) fix.getBody().getUserData();
	userData.emitBodyTouch(lastEvent);
	return false;
}
@Override
public void beginContact(Contact c) {
	for(IContactCallback callback : beginContactListeners){
		callback.onBeginContact(c);
	}
}
@Override
public void endContact(Contact c) {
for(IContactCallback callback : endContactListeners){
	callback.onEndContact(c);
	}
}
@Override
public void postSolve(Contact c, ContactImpulse cImpulse) {
for(IContactCallback callback : postSolveListeners){
	callback.onPostSolve(c, cImpulse);
	}
}
@Override
public void preSolve(Contact c, Manifold manifold) {
for(IContactCallback callback : preSolveListeners){
	callback.onPreSolve(c, manifold);
	}
}
public void addBeginContactListener(IContactCallback bodyComp){
	beginContactListeners.add(bodyComp);
}
public void addEndContactListener(IContactCallback bodyComp){
	endContactListeners.add(bodyComp);
}
public void addPreSolveContactListener(IContactCallback bodyComp){
	preSolveListeners.add(bodyComp);
}
public void addPostSolveListener(IContactCallback bodyComp){
	postSolveListeners.add(bodyComp);
}
public void removeBeginContactListener(IContactCallback bodyComp){
	beginContactListeners.remove(bodyComp);
}
public void removeEndContactListener(IContactCallback bodyComp){
	endContactListeners.remove(bodyComp);
}
public void removePreSolveContactListener(IContactCallback bodyComp){
	preSolveListeners.remove(bodyComp);
}
public void removePostSolveListener(IContactCallback bodyComp){
	postSolveListeners.remove(bodyComp);
}
}
