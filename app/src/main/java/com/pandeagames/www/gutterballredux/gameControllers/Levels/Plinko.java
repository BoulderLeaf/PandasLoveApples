package com.pandeagames.www.gutterballredux.gameControllers.Levels;

import com.pandeagames.R;

import com.pandeagames.www.gutterballredux.gameControllers.Game;
import com.pandeagames.www.gutterballredux.gameControllers.Levels.Geom.BarrelGeom;
import com.pandeagames.www.gutterballredux.gameControllers.Levels.Geom.PlinkoGeom;
import com.pandeagames.www.gutterballredux.Components.BodyComponent;

public class Plinko extends ThrowLevel {

	public Plinko(Game game, BodyComponent geometry, LevelDef levelDef) {
		super(game, geometry, levelDef);
		// TODO Auto-generated constructor stub
		launcher.setX(4);
		
	}
	public Plinko(Game game, LevelDef levelDef) {
		this(game, new PlinkoGeom(game), levelDef);
		// TODO Auto-generated constructor stub
	}
}
