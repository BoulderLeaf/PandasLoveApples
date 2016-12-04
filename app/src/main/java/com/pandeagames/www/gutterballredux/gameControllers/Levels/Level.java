package com.pandeagames.www.gutterballredux.gameControllers.Levels;

import org.jbox2d.common.Vec2;

import android.graphics.drawable.BitmapDrawable;
import android.location.Address;

import com.pandeagames.www.gutterballredux.gameControllers.Game;
import com.pandeagames.www.gutterballredux.gameControllers.SpawnActors;
import com.pandeagames.www.gutterballredux.gameControllers.Levels.Geom.LevelWalls;
import com.pandeagames.www.gutterballredux.gameObjects.Actor;
import com.pandeagames.www.gutterballredux.gameObjects.Background;
import com.pandeagames.www.gutterballredux.gameObjects.Portal;
import com.pandeagames.www.gutterballredux.infoHolders.UpdateInfo;
import com.pandeagames.www.gutterballredux.Components.AbstractGameComponent;
import com.pandeagames.www.gutterballredux.Components.BodyComponent;

public class Level extends AbstractGameComponent{
	private BodyComponent geometry;
	private Background bg;
	private Portal portal;
	private BottomCuller culler;
	private CullShaftRenderer cullRenderer;
	private GravityShift gShift;
	
public Level(Game game, BodyComponent geometry){
	super(game);
	this.geometry=geometry;
	//walls= new Level1Walls(game);
	/*Actor actor;// = new Actor(game, new Vec2(10, 10));
	DragController controller;
	actor = new Actor(game, new Vec2(20, 10));
	controller = new DragController(actor, game);
	actor = new Actor(game, new Vec2(4, 10));
	controller = new DragController(actor, game);
	actor = new Actor(game, new Vec2(4, 4));
	controller = new DragController(actor, game);
	/*actory = new Actor(game, new Vec2(6, 10));
	actory = new Actor(game, new Vec2(8, 8));
	actory = new Actor(game, new Vec2(2, 2));
	actory = new Actor(game, new Vec2(3, 6));
	for(int i=0;i<5;i++){
		for (int j=0;j<5;j++){
			actory = new Actor(game, new Vec2((float)(i*1.2+(i*0.5))+2, (float)(j*1.2+(j*0.5))+2));
		}
	}
//	Actor actor = new Actor(game,new Vec2((int)((GameView)activity.getView()).toWorldX(500), (int)((GameView)activity.getView()).toWorldY(500)));
	//DragController controller = new DragController(actor, game);*/
	//SpawnActors spawn=new SpawnActors(game);
	bg  = new Background(game);
generateCuller(game);

gShift = new GravityShift(game);

}
public Level(Game game, BodyComponent geometry, int bgId){
	super(game);
	this.geometry=geometry;
	bg  = new Background(game, bgId);
	generateCuller(game);
	gShift = new GravityShift(game);
}
private void generateCuller(Game game){
	culler = new BottomCuller(game);
	cullRenderer = new CullShaftRenderer(game);
	culler.addBottomCullListener(cullRenderer);
}
public Level(Game game){
	this(game, new LevelWalls(game));
}

@Override
public void update(UpdateInfo updateInfo) {
	// TODO Auto-generated method stub
	
}
@Override
public void destroy(){
	super.destroy();
	geometry.destroy();
	bg.destroy();
	culler.destroy();
	
	culler=null;
	geometry=null;
	bg=null;
}
}
