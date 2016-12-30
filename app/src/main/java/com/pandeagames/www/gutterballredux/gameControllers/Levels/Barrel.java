package com.pandeagames.www.gutterballredux.gameControllers.Levels;

import com.pandeagames.www.gutterballredux.gameControllers.Game;
import com.pandeagames.www.gutterballredux.gameControllers.Levels.Geom.BarrelGeom;
import com.pandeagames.www.gutterballredux.gameControllers.Levels.Geom.BunkBedGeom;
import com.pandeagames.www.gutterballredux.Components.BodyComponent;

public class Barrel extends ThrowLevel {

	public Barrel(Game game, BodyComponent geometry, LevelDef levelDef) {
		super(game, geometry, levelDef);
		// TODO Auto-generated constructor stub
		createPortal(3, 3);
		createPortal(27, 3);
		createPortal(22, 30);
		
		createPortal(8, 17);
		createPortal(8, 23);
		createPortal(8, 29);
		createPortal(8, 35);
		createPortal(8, 41);
		createPortal(8, 47);
	}
	public Barrel(Game game, LevelDef levelDef) {
		this(game, new BarrelGeom(game), levelDef);
		// TODO Auto-generated constructor stub
	}

}
