package com.pandeagames.www.gutterballredux.gameControllers.Levels;

import com.pandeagames.www.gutterballredux.gameControllers.Game;
import com.pandeagames.www.gutterballredux.gameControllers.Levels.Geom.RobotDanceGeom;
import com.pandeagames.www.gutterballredux.gameObjects.AppleType;
import com.pandeagames.www.gutterballredux.gameObjects.BodyEmmiter;
import com.pandeagames.www.gutterballredux.Components.BodyComponent;
import com.pandeagames.www.gutterballredux.gameObjects.scripts.BreakableBushMedium;
import com.pandeagames.www.gutterballredux.gameObjects.scripts.BreakableBushSmall;

import org.jbox2d.common.Vec2;

public class robot_dance extends ThrowLevel {
	private BodyEmmiter emitter1, emitter2;
	public robot_dance(Game game, BodyComponent geometry, LevelDef levelDef) {
		super(game, geometry, levelDef);
		// TODO Auto-generated constructor stub


		createPortal(1.5f, 2);
		createPortal(4.5f, 2);
		createPortal(7.5f, 2);
		createPortal(10.5f, 2);
		createPortal(13.5f, 2, AppleType.GOLDEN);
		createPortal(16.5f, 2, AppleType.GOLDEN);
		createPortal(19.5f, 2, AppleType.GOLDEN);
		createPortal(22.5f, 2, AppleType.GOLDEN);

		createPortal(1.5f, 12.5f);
		createPortal(4.5f, 12.5f);
		createPortal(7.5f, 12.5f);
		createPortal(10.5f, 12.5f);
		createPortal(13.5f, 12.5f);
		createPortal(16.5f, 12.5f);
		createPortal(19.5f, 12.5f);
		createPortal(22.5f, 12.5f);

		createPortal(7.5f,15.5f);
		createPortal(10.5f,15.5f);
		createPortal(13.5f,15.5f);
		createPortal(16.5f,15.5f);

		createPortal(7.5f,18.5f);
		createPortal(10.5f,18.5f);
		createPortal(13.5f,18.5f);
		createPortal(16.5f,18.5f);

		createPortal(7.5f,21.5f);
		createPortal(10.5f,21.5f);
		createPortal(13.5f,21.5f);
		createPortal(16.5f,21.5f);

		addBreakable(new BreakableBushMedium(game, new Vec2(9f, 7f)));

		addBreakable(new BreakableBushSmall(game, new Vec2(22.5f, 22.5f)));
		addBreakable(new BreakableBushSmall(game, new Vec2(19.5f, 22.5f)));
		addBreakable(new BreakableBushSmall(game, new Vec2(22.5f, 25.5f)));
		addBreakable(new BreakableBushSmall(game, new Vec2(19.5f, 25.5f)));
		addBreakable(new BreakableBushSmall(game, new Vec2(22.5f, 28.5f)));
		addBreakable(new BreakableBushSmall(game, new Vec2(22.5f, 31.5f)));

		addBreakable(new BreakableBushMedium(game, new Vec2(3f, 24f)));

		addBreakable(new BreakableBushSmall(game, new Vec2(1.5f, 28.5f)));
		addBreakable(new BreakableBushSmall(game, new Vec2(1.5f, 31.5f)));
		addBreakable(new BreakableBushSmall(game, new Vec2(1.5f, 34.5f)));
		addBreakable(new BreakableBushSmall(game, new Vec2(1.5f, 37.5f)));
		addBreakable(new BreakableBushSmall(game, new Vec2(1.5f, 40.5f)));

	}
	public robot_dance(Game game, LevelDef levelDef)
	{
		this(game, new RobotDanceGeom(game), levelDef);
		// TODO Auto-generated constructor stub
	}

}
