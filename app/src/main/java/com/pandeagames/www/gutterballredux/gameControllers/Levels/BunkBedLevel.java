package com.pandeagames.www.gutterballredux.gameControllers.Levels;

import com.pandeagames.www.gutterballredux.gameControllers.Game;
import com.pandeagames.www.gutterballredux.gameControllers.Levels.Geom.BunkBedGeom;
import com.pandeagames.www.gutterballredux.Components.BodyComponent;

public class BunkBedLevel extends ThrowLevel {

	public BunkBedLevel(Game game, BodyComponent geometry) {
		super(game, geometry);
		// TODO Auto-generated constructor stub
		createPortal(3, 9);
		createPortal(3, 20);
		createPortal(3, 31);
	}
	public BunkBedLevel(Game game) {
		this(game, new BunkBedGeom(game));
		// TODO Auto-generated constructor stub
	}
}
