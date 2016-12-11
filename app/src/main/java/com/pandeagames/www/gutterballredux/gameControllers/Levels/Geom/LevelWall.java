package com.pandeagames.www.gutterballredux.gameControllers.Levels.Geom;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;

import com.pandeagames.www.gutterballredux.droidControllers.SwingActivity;

import com.pandeagames.www.gutterballredux.threads.SwingsThread;

import com.pandeagames.www.gutterballredux.gameControllers.Game;
import com.pandeagames.www.gutterballredux.gameControllers.Simulation;
import com.pandeagames.www.gutterballredux.gameObjects.LevelWallsEnum;
import com.pandeagames.www.gutterballredux.Components.AbstractComponent;
import com.pandeagames.www.gutterballredux.Components.BodyComponent;
import com.pandeagames.www.gutterballredux.Components.DrawableGameComponent;

public class LevelWall extends AbstractComponent {
private LevelWallsEnum wallPos;
private Body body;
private float wallThickness;
private Simulation sim;
public LevelWall(SwingActivity activity,Body body,LevelWallsEnum wallPos, Simulation simulation){
	super(activity);
this.wallPos=wallPos;
this.body=body;
wallThickness=1.0f;
sim=simulation;
createBody();


}
public void createBody(){
Vec2 pos=getPos();

 
 PolygonShape groundBox = new PolygonShape();
 groundBox.setAsBox(getBounds().x,getBounds().y, pos,0.0f);
 body.createFixture(groundBox,0.0f);
}
private Vec2 getPos(){
Vec2 out = new Vec2();
switch(wallPos){
case BOTTOM:
	out.set(sim.getWorldSize().x/2,sim.getWorldSize().y-wallThickness/2);
	break;
case TOP:
	out.set(sim.getWorldSize().x/2,-wallThickness/2);
	break;
case RIGHT:
	out.set(sim.getWorldSize().x+wallThickness/2, sim.getWorldSize().y/2);
	break;
case LEFT:
	out.set(-wallThickness/2,  sim.getWorldSize().y/2);
}
return out;
}
private Vec2 getBounds(){
Vec2 out = new Vec2();
switch(wallPos){
case BOTTOM:
	out.set(sim.getWorldSize().x/2, wallThickness/2);
	break;
case TOP:
	out.set(sim.getWorldSize().x/2, wallThickness/2);
	break;
case RIGHT:
	out.set(wallThickness/2, (sim.getWorldSize().y-wallThickness*2)/2);
	break;
case LEFT:
	out.set(wallThickness/2,   (sim.getWorldSize().y-wallThickness*2)/2);
}
return out;
}
@Override
public void destroy()
{
	super.destroy();
	wallPos=null;
	body=null;
	sim=null;
}
}
