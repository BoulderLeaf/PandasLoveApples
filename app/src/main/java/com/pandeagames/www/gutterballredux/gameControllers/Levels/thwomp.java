package com.pandeagames.www.gutterballredux.gameControllers.Levels;

import com.pandeagames.R;

import com.pandeagames.www.gutterballredux.gameControllers.Game;
import com.pandeagames.www.gutterballredux.gameControllers.Levels.Geom.ThwompGeom;
import com.pandeagames.www.gutterballredux.gameObjects.AppleType;
import com.pandeagames.www.gutterballredux.gameObjects.BreakableBlockSmall;
import com.pandeagames.www.gutterballredux.Components.BodyComponent;

import org.jbox2d.common.Vec2;

public class thwomp extends ThrowLevel {
	public thwomp(Game game, BodyComponent geometry, LevelDef levelDef) {
		super(game, geometry, levelDef);
		// TODO Auto-generated constructor stub
		/*createPortal(1.5f, 15.5f);
		createPortal(22.5f, 15.5f);
		createPortal(9.5f, 1.5f);
		createPortal(14.5f, 1.5f);*/

		createPortal(1.5f, 1.5f, AppleType.GOLDEN);
		createPortal(4.5f, 1.5f, AppleType.GOLDEN);
		createPortal(7.5f, 1.5f, AppleType.GOLDEN);
		createPortal(10.5f, 1.5f, AppleType.GOLDEN);
		createPortal(13.5f, 1.5f, AppleType.GOLDEN);
		createPortal(16.5f, 1.5f, AppleType.GOLDEN);
		createPortal(19.5f, 1.5f, AppleType.GOLDEN);
		createPortal(22.5f, 1.5f, AppleType.GOLDEN);

		createPortal(22.5f, 4.5f);
		createPortal(22.5f, 7.5f);
		createPortal(22.5f, 10.5f);
		createPortal(22.5f, 13.5f);
		createPortal(22.5f, 16.5f);

		createPortal(22.5f, 25.5f);
		createPortal(22.5f, 28.5f);

		createPortal(1.5f, 4.5f);
		createPortal(1.5f, 7.5f);
		createPortal(1.5f, 10.5f);
		createPortal(1.5f, 13.5f);
		createPortal(1.5f, 16.5f);

		createPortal(1.5f, 25.5f);
		createPortal(1.5f, 28.5f);

		addBreakable(new BreakableBlockSmall(game, new Vec2(1.5f, 20.5f)));
		addBreakable(new BreakableBlockSmall(game, new Vec2(22.5f, 20.5f)));
	}

	public thwomp(Game game, LevelDef levelDef) {
		this(game, new ThwompGeom(game), levelDef);
		// TODO Auto-generated constructor stub
	}
	public void destroy(){
		super.destroy();
	}
}

