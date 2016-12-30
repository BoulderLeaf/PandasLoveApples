package com.pandeagames.www.gutterballredux.gameControllers.Levels;

import com.pandeagames.www.gutterballredux.gameControllers.Game;
import com.pandeagames.www.gutterballredux.gameControllers.Levels.Geom.Level1Walls;
import com.pandeagames.www.gutterballredux.gameObjects.Portal;
import com.pandeagames.www.gutterballredux.Components.BodyComponent;

public class PingPong extends ThrowLevel {
	public PingPong(Game game, BodyComponent geometry, LevelDef levelDef) {
		super(game, geometry, levelDef);
		// TODO Auto-generated constructor stub
		createPortal(3, 8);
		createPortal(3, 21);
	}
	public PingPong(Game game, LevelDef levelDef){
		this(game, new Level1Walls(game), levelDef);
	}

}
