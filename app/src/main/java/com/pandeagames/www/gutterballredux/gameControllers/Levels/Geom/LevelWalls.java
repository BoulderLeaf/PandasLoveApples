package com.pandeagames.www.gutterballredux.gameControllers.Levels.Geom;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;

import com.pandeagames.www.gutterballredux.gameControllers.Game;
import com.pandeagames.www.gutterballredux.gameObjects.CollisionGroups;
import com.pandeagames.www.gutterballredux.gameObjects.LevelWallsEnum;
import com.pandeagames.www.gutterballredux.Components.AbstractComponent;
import com.pandeagames.www.gutterballredux.Components.BodyComponent;

public class LevelWalls extends BodyComponent {
private LevelWall[] walls;
	public LevelWalls(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
addCollisionGroup(CollisionGroups.LEVEL_GEOM);
	}
	public void createBody(){
createBody(world.createBody(bodyDef));
		
	}
	@Override 
	public void createBody(Body body){
		walls=new LevelWall[3];
		walls[0]=new LevelWall(activity,body, LevelWallsEnum.TOP, simulation);
		walls[1]=new LevelWall(activity,body, LevelWallsEnum.RIGHT, simulation);
		walls[2]=new LevelWall(activity,body, LevelWallsEnum.LEFT, simulation);
		super.createBody(body);
	}
	
	@Override
	public void destroy(){
		if(destroyed) {return;}
		super.destroy();
		for(int i=0;i<walls.length;i++){
			walls[i].destroy();
			walls[i]=null;
		}
		walls=null;
	}
}
