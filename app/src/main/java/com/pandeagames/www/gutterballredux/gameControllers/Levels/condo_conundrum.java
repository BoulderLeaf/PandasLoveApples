package com.pandeagames.www.gutterballredux.gameControllers.Levels;

import com.pandeagames.R;
import com.pandeagames.www.gutterballredux.gameControllers.Game;
import com.pandeagames.www.gutterballredux.gameControllers.Levels.Geom.CondoConundrumGeom;
import com.pandeagames.www.gutterballredux.Components.BodyComponent;
import com.pandeagames.www.gutterballredux.gameObjects.BreakableBlockLarge;
import com.pandeagames.www.gutterballredux.gameObjects.BreakableBlockMedium;
import com.pandeagames.www.gutterballredux.gameObjects.BreakableBlockSmall;

import org.jbox2d.common.Vec2;

public class condo_conundrum extends ThrowLevel {

	public condo_conundrum(Game game, BodyComponent geometry, LevelDef levelDef) {
		super(game, geometry, levelDef);
		// TODO Auto-generated constructor stub
		createPortal(9, 6);
		createPortal(9, 16);
		createPortal(14.5f, 31);
		createPortal(14.5f, 21);
		createPortal(20, 26);
		createPortal(9, 26);

		addBreakable(new BreakableBlockSmall(game, new Vec2(1.5f, 1.5f)));
		addBreakable(new BreakableBlockSmall(game, new Vec2(4.5f, 1.5f)));
		addBreakable(new BreakableBlockSmall(game, new Vec2(7.5f, 1.5f)));
		addBreakable(new BreakableBlockSmall(game, new Vec2(10.5f, 1.5f)));
		addBreakable(new BreakableBlockSmall(game, new Vec2(13.5f, 1.5f)));
		addBreakable(new BreakableBlockSmall(game, new Vec2(16.5f, 1.5f)));
		addBreakable(new BreakableBlockSmall(game, new Vec2(19.5f, 1.5f)));
		addBreakable(new BreakableBlockSmall(game, new Vec2(22.5f, 1.5f)));

		addBreakable(new BreakableBlockMedium(game, new Vec2(3f, 6f)));
		addBreakable(new BreakableBlockMedium(game, new Vec2(9f, 6f)));
		addBreakable(new BreakableBlockMedium(game, new Vec2(15f, 6f)));
		addBreakable(new BreakableBlockMedium(game, new Vec2(21f, 6f)));

		addBreakable(new BreakableBlockLarge(game, new Vec2(4f, 13f)));
		addBreakable(new BreakableBlockLarge(game, new Vec2(12f, 13f)));
		addBreakable(new BreakableBlockLarge(game, new Vec2(20f, 13f)));

	}

	public condo_conundrum(Game game, LevelDef levelDef) {
		this(game, new CondoConundrumGeom(game), levelDef);
		// TODO Auto-generated constructor stub
	}
}
