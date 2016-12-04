package com.pandeagames.www.gutterballredux.gameControllers.Levels;

import android.graphics.drawable.BitmapDrawable;

import com.pandeagames.R;

import com.pandeagames.www.gutterballredux.gameControllers.Game;
import com.pandeagames.www.gutterballredux.gameControllers.Levels.Geom.HiddenDragonGeom;
import com.pandeagames.www.gutterballredux.gameObjects.Background;
import com.pandeagames.www.gutterballredux.gameObjects.Forground;
import com.pandeagames.www.gutterballredux.Components.BodyComponent;

public class HiddenDragonLevel extends ThrowLevel {
	private Forground forground;
	public HiddenDragonLevel(Game game, BodyComponent geometry) 
	{
		super(game, geometry,R.drawable.hiddendragon);
		// TODO Auto-generated constructor stub
		createPortal(8, 3);
		createPortal(16, 3);
		createPortal(24, 3);
		createPortal(3, 8);
		createPortal(16, 18);
		createPortal(21, 24);
		
		//forground=new Forground(game, R.drawable.hiddendragon_forground, 200,false);

	}

	public HiddenDragonLevel(Game game) {
		this(game, new HiddenDragonGeom(game));
		// TODO Auto-generated constructor stub
	}

}
