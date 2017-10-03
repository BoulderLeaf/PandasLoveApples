package com.pandeagames.www.gutterballredux.gameControllers.Levels;

import org.jbox2d.common.Vec2;

import android.graphics.drawable.BitmapDrawable;
import android.location.Address;

import com.pandeagames.www.gutterballredux.gameControllers.Game;
import com.pandeagames.www.gutterballredux.gameControllers.Levels.Geom.LevelWalls;
import com.pandeagames.www.gutterballredux.gameObjects.Actor;
import com.pandeagames.www.gutterballredux.gameObjects.Background;
import com.pandeagames.www.gutterballredux.gameObjects.Portal;
import com.pandeagames.www.gutterballredux.infoHolders.UpdateInfo;
import com.pandeagames.www.gutterballredux.Components.AbstractGameComponent;
import com.pandeagames.www.gutterballredux.Components.BodyComponent;

public class Level extends GeneratedLevel{
	private BodyComponent geometry;
	private Background bg;
	private Portal portal;
	private BottomCuller culler;
	private CullShaftRenderer cullRenderer;
	private GravityShift gShift;


public Level(Game game, BodyComponent geometry, LevelDef levelDef){
	super(game, levelDef);
	this.geometry=geometry;
	bg  = new Background(game, ((AppleLevelDef)levelDef).getBgResId());
	generateCuller(game);
	gShift = new GravityShift(game);
}
private void generateCuller(Game game){
	culler = new BottomCuller(game);
	cullRenderer = new CullShaftRenderer(game);
	culler.addBottomCullListener(cullRenderer);
}
public Level(Game game, LevelDef levelDef){
	this(game, new LevelWalls(game), levelDef);
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
