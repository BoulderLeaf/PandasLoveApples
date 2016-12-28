package com.pandeagames.www.gutterballredux.gameControllers.Levels;

import com.pandeagames.R;

import com.pandeagames.www.gutterballredux.gameControllers.Game;
import com.pandeagames.www.gutterballredux.gameControllers.Levels.Geom.ThwompGeom;
import com.pandeagames.www.gutterballredux.gameObjects.BreakableBlockSmall;
import com.pandeagames.www.gutterballredux.gameObjects.Portal;
import com.pandeagames.www.gutterballredux.Components.BodyComponent;

import org.jbox2d.common.Vec2;

public class ThwompLevel extends ThrowLevel {
	public ThwompLevel(Game game, BodyComponent geometry) {
		super(game, geometry, R.drawable.thwomp);
		// TODO Auto-generated constructor stub
		createPortal(1.5f, 15.5f);
		createPortal(22.5f, 15.5f);
		createPortal(9.5f, 1.5f);
		createPortal(14.5f, 1.5f);

		addBreakable(new BreakableBlockSmall(game, new Vec2(1.5f, 20.5f)));
		addBreakable(new BreakableBlockSmall(game, new Vec2(22.5f, 20.5f)));
	}

	public ThwompLevel(Game game) {
		this(game, new ThwompGeom(game));
		// TODO Auto-generated constructor stub
	}
	public void destroy(){
		super.destroy();
	}
}

