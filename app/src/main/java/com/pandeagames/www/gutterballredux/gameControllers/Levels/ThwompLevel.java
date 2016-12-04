package com.pandeagames.www.gutterballredux.gameControllers.Levels;

import com.pandeagames.www.gutterballredux.gameControllers.Game;
import com.pandeagames.www.gutterballredux.gameControllers.Levels.Geom.ThwompGeom;
import com.pandeagames.www.gutterballredux.gameObjects.Portal;
import com.pandeagames.www.gutterballredux.Components.BodyComponent;

public class ThwompLevel extends ThrowLevel {
	public ThwompLevel(Game game, BodyComponent geometry) {
		super(game, geometry);
		// TODO Auto-generated constructor stub
		createPortal(15, 5);
		createPortal(27, 17);
		createPortal(3, 17);
	}

	public ThwompLevel(Game game) {
		this(game, new ThwompGeom(game));
		// TODO Auto-generated constructor stub
	}
	public void destroy(){
		super.destroy();
	}

}

