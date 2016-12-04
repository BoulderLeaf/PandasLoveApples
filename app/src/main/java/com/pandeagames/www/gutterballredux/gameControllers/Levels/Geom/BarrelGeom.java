package com.pandeagames.www.gutterballredux.gameControllers.Levels.Geom;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;

import com.pandeagames.www.gutterballredux.gameControllers.Game;

public class BarrelGeom extends LevelWalls {
	public BarrelGeom(Game game) {
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
		 groundBox.setAsBox(3f,3f, new Vec2(22,25f),0.0f);
		 body.createFixture(groundBox,0.0f);
		 //
		 groundBox = new PolygonShape();
		 groundBox.setAsBox(1f,18f, new Vec2(11,31f),0.0f);
		 body.createFixture(groundBox,0.0f);
		 //
		 groundBox = new PolygonShape();
		 groundBox.setAsBox(1f,18f, new Vec2(18,31f),0.0f);
		 body.createFixture(groundBox,0.0f);
		 //
		 groundBox = new PolygonShape();
		 groundBox.setAsBox(4.5f,1f, new Vec2(14.5f,2f),0.0f);
		 body.createFixture(groundBox,0.0f);
		 //
		super.createBody(body);
	}

}
