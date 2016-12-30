package com.pandeagames.www.gutterballredux.gameControllers.Levels;

import com.pandeagames.www.gutterballredux.gameControllers.Game;
import com.pandeagames.www.gutterballredux.gameControllers.Levels.Geom.EffGeom;
import com.pandeagames.www.gutterballredux.gameControllers.Levels.Geom.HiddenDragonGeom;
import com.pandeagames.www.gutterballredux.gameObjects.ActorFactory;
import com.pandeagames.www.gutterballredux.gameObjects.BodyEmmiter;
import com.pandeagames.www.gutterballredux.Components.BodyComponent;

public class Eff extends ThrowLevel {
private BodyEmmiter emitter;
	public Eff(Game game, BodyComponent geometry, LevelDef levelDef) {
		super(game, geometry, levelDef);
		// TODO Auto-generated constructor stub
		createPortal(27, 5);
		createPortal(21, 15);
		createPortal(24, 3);
		createPortal(9, 15);
		createPortal(14, 20);
		createPortal(9, 25);
		createPortal(21, 28);
		emitter = new BodyEmmiter(game, 3, 3, new ActorFactory(game));
		
	}

	public Eff(Game game, LevelDef levelDef) {
		this(game, new EffGeom(game), levelDef);
		// TODO Auto-generated constructor stub
	}
}
