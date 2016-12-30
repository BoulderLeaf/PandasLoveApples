package com.pandeagames.www.gutterballredux.gameControllers.Levels;

import com.pandeagames.www.gutterballredux.gameControllers.Game;
import com.pandeagames.www.gutterballredux.gameControllers.Levels.Geom.HiddenDragonGeom;
import com.pandeagames.www.gutterballredux.gameControllers.Levels.Geom.PiggyBackGeom;
import com.pandeagames.www.gutterballredux.Components.BodyComponent;

public class PiggyBack extends ThrowLevel {

	public PiggyBack(Game game, BodyComponent geometry, LevelDef levelDef) {
		super(game, geometry, levelDef);
		// TODO Auto-generated constructor stub
		createPortal(9, 3);
		createPortal(18, 3);
		createPortal(22, 7);
		createPortal(18, 17);
		createPortal(28, 21);
	}
	public PiggyBack(Game game, LevelDef levelDef) {
		this(game, new PiggyBackGeom(game), levelDef);
		// TODO Auto-generated constructor stub
	}
}
