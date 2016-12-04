package com.pandeagames.www.gutterballredux.gameControllers.Levels.Geom;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;

import com.pandeagames.www.gutterballredux.gameControllers.Game;

public class HiddenDragonGeom extends LevelWalls {

	public HiddenDragonGeom(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
		drawBody=false;
	}
	@Override
	public void createBody(){
		createBody(world.createBody(bodyDef));
	}
	@Override
	public void createBody(Body body){
		 PolygonShape groundBox = new PolygonShape();
		 groundBox.setAsBox(3f,3f, new Vec2(8,8f),0.0f);
		 body.createFixture(groundBox,0.0f);
		 //
		 groundBox = new PolygonShape();
		 groundBox.setAsBox(3f,3f, new Vec2(16,8f),0.0f);
		 body.createFixture(groundBox,0.0f);
		 //
		 groundBox = new PolygonShape();
		 groundBox.setAsBox(3f,3f, new Vec2(24,8f),0.0f);
		 body.createFixture(groundBox,0.0f);
		 //
		 groundBox = new PolygonShape();
		 groundBox.setAsBox(3f,3f, new Vec2(16,23f),0.0f);
		 body.createFixture(groundBox,0.0f);
		 //
		 groundBox = new PolygonShape();
		 groundBox.setAsBox(3f,3f, new Vec2(22f,29f),0.0f);
		 body.createFixture(groundBox,0.0f);
		 //
		super.createBody(body);
	}
}
