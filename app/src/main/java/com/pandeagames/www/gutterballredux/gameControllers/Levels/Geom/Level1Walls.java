package com.pandeagames.www.gutterballredux.gameControllers.Levels.Geom;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;

import com.pandeagames.www.gutterballredux.gameControllers.Game;

public class Level1Walls extends LevelWalls {

	public Level1Walls(Game game) {
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
	 groundBox.setAsBox(5,2, new Vec2(6.0f, 50-25.0f),0.0f);
	 body.createFixture(groundBox,0.0f);
	 //
	groundBox = new PolygonShape();
	 groundBox.setAsBox(10,2, new Vec2(11.0f, 50-33.0f),0.0f);
	 body.createFixture(groundBox,0.0f);
	 //
		groundBox = new PolygonShape();
		 groundBox.setAsBox(5,2, new Vec2(6.0f, 50-37.0f),0.0f);
		 body.createFixture(groundBox,0.0f);
		 //
			groundBox = new PolygonShape();
			 groundBox.setAsBox(10,2, new Vec2(19.0f, 50-47.0f),0.0f);
			 body.createFixture(groundBox,0.0f);
			 super.createBody(body);
}
}
