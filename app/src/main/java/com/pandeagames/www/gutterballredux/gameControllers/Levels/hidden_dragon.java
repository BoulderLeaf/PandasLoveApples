package com.pandeagames.www.gutterballredux.gameControllers.Levels;

import com.pandeagames.R;

import com.pandeagames.www.gutterballredux.gameControllers.Game;
import com.pandeagames.www.gutterballredux.gameControllers.Levels.Geom.HiddenDragonGeom;
import com.pandeagames.www.gutterballredux.gameObjects.Forground;
import com.pandeagames.www.gutterballredux.Components.BodyComponent;

public class hidden_dragon extends ThrowLevel {
	private Forground forground;
	public hidden_dragon(Game game, BodyComponent geometry, LevelDef levelDef)
	{
		super(game, geometry,levelDef);
		// TODO Auto-generated constructor stub
		createPortal(8, 3);
		createPortal(16, 3);
		createPortal(24, 3);
		createPortal(3, 8);
		createPortal(16, 18);
		createPortal(21, 24);
		
		//forground=new Forground(game, R.drawable.hiddendragon_forground, 200,false);

	}

	public hidden_dragon(Game game, LevelDef levelDef) {
		this(game, new HiddenDragonGeom(game), levelDef);
		// TODO Auto-generated constructor stub
	}

}
