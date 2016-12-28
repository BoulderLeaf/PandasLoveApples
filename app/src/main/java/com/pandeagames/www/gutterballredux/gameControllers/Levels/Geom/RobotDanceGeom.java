package com.pandeagames.www.gutterballredux.gameControllers.Levels.Geom;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;

import com.pandeagames.www.gutterballredux.gameControllers.Game;

public class RobotDanceGeom extends LevelWalls {

	public RobotDanceGeom(Game game) {
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
		 groundBox.setAsBox(3f,3f, new Vec2(3f,7f),0.0f);
		 body.createFixture(groundBox,0.0f);

		groundBox = new PolygonShape();
		groundBox.setAsBox(3f,3f, new Vec2(3,18f),0.0f);
		body.createFixture(groundBox,0.0f);

		groundBox = new PolygonShape();
		groundBox.setAsBox(3f,3f, new Vec2(21,18f),0.0f);
		body.createFixture(groundBox,0.0f);

		groundBox = new PolygonShape();
		groundBox.setAsBox(6.5f,3f, new Vec2(17.5f,7f),0.0f);
		body.createFixture(groundBox,0.0f);

		super.createBody(body);
	}

}
