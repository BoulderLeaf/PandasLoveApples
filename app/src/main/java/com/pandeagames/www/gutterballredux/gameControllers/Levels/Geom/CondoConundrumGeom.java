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
		 //
		super.createBody(body);
	}
}
