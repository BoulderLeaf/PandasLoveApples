package com.pandeagames.www.gutterballredux.gameControllers.Levels.Geom;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;

import com.pandeagames.www.gutterballredux.gameControllers.Game;

public class CondoConundrumGeom extends LevelWalls {

	public CondoConundrumGeom(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void createBody(){
		createBody(world.createBody(bodyDef));
	}
	@Override
	public void createBody(Body body){
		 PolygonShape groundBox = new PolygonShape();
		 groundBox.setAsBox(3f,3f, new Vec2(9,11f),0.0f);
		 body.createFixture(groundBox,0.0f);
		 //
		 groundBox = new PolygonShape();
		 groundBox.setAsBox(3f,3f, new Vec2(9,21f),0.0f);
		 body.createFixture(groundBox,0.0f);
		 //
		 groundBox = new PolygonShape();
		 groundBox.setAsBox(3f,3f, new Vec2(9,31f),0.0f);
		 body.createFixture(groundBox,0.0f);
		 //
		 groundBox = new PolygonShape();
		 groundBox.setAsBox(3f,3f, new Vec2(15,39f),0.0f);
		 body.createFixture(groundBox,0.0f);
		 //
		 groundBox = new PolygonShape();
		 groundBox.setAsBox(3f,3f, new Vec2(20f,21f),0.0f);
		 body.createFixture(groundBox,0.0f);
		 //
		 groundBox = new PolygonShape();
		 groundBox.setAsBox(6f,3f, new Vec2(23f,31f),0.0f);
		 body.createFixture(groundBox,0.0f);
		 //
		super.createBody(body);
	}
}
