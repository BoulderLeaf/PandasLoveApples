package com.pandeagames.www.gutterballredux.gameControllers.Levels.Geom;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;

import com.pandeagames.www.gutterballredux.gameControllers.Game;

public class EffGeom extends LevelWalls {


	public EffGeom(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void createBody()
	{
		createBody(world.createBody(bodyDef));
	}
	@Override
	public void createBody(Body body){
		 PolygonShape groundBox = new PolygonShape();
		 groundBox.setAsBox(3f,3f, new Vec2(9,20f),0.0f);
		 body.createFixture(groundBox,0.0f);
		 //
		 groundBox = new PolygonShape();
		 groundBox.setAsBox(3f,21f, new Vec2(26,28f),0.0f);
		 body.createFixture(groundBox,0.0f);
		 //
		 groundBox = new PolygonShape();
		 groundBox.setAsBox(7f,3f, new Vec2(16,10f),0.0f);
		 body.createFixture(groundBox,0.0f);
		 //
		 groundBox = new PolygonShape();
		 groundBox.setAsBox(5.5f,5.5f, new Vec2(17.5f,35.5f),0.0f);
		 body.createFixture(groundBox,0.0f);
		 //
		super.createBody(body);
	}

}
