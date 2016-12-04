package com.pandeagames.www.gutterballredux.gameControllers.Levels;

import com.pandeagames.www.gutterballredux.gameControllers.Game;
import com.pandeagames.www.gutterballredux.gameControllers.Levels.Geom.CondoConundrumGeom;
import com.pandeagames.www.gutterballredux.gameControllers.Levels.Geom.HiddenDragonGeom;
import com.pandeagames.www.gutterballredux.Components.BodyComponent;

public class CondoConundrum extends ThrowLevel {

	public CondoConundrum(Game game, BodyComponent geometry) {
		super(game, geometry);
		// TODO Auto-generated constructor stub
		createPortal(9, 6);
		createPortal(9, 16);
		createPortal(14.5f, 31);
		createPortal(14.5f, 21);
		createPortal(20, 26);
		createPortal(9, 26);
	}

	public CondoConundrum(Game game) {
		this(game, new CondoConundrumGeom(game));
		// TODO Auto-generated constructor stub
	}
}
