package com.pandeagames.www.gutterballredux.gameControllers.Levels.Geom;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;

import com.pandeagames.www.gutterballredux.gameControllers.Game;

public class BunkBedGeom extends LevelWalls {

	public BunkBedGeom(Game game) {
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
		 groundBox.setAsBox(9f,1.5f, new Vec2(10,12.5f),0.0f);
		 body.createFixture(groundBox,0.0f);
		 //
		 groundBox = new PolygonShape();
		 groundBox.setAsBox(9f,1.5f, new Vec2(10,23.5f),0.0f);
		 body.createFixture(groundBox,0.0f);
		 //
		 groundBox = new PolygonShape();
		 groundBox.setAsBox(9f,1.5f, new Vec2(10,34.5f),0.0f);
		 body.createFixture(groundBox,0.0f);
		 //
		super.createBody(body);
	}
}
