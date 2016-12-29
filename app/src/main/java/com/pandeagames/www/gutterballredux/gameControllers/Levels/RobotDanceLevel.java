package com.pandeagames.www.gutterballredux.gameControllers.Levels;

import com.pandeagames.R;
import com.pandeagames.www.gutterballredux.gameControllers.Game;
import com.pandeagames.www.gutterballredux.gameControllers.Levels.Geom.PiggyBackGeom;
import com.pandeagames.www.gutterballredux.gameControllers.Levels.Geom.RobotDanceGeom;
import com.pandeagames.www.gutterballredux.gameObjects.ActorFactory;
import com.pandeagames.www.gutterballredux.gameObjects.AppleType;
import com.pandeagames.www.gutterballredux.gameObjects.BodyEmmiter;
import com.pandeagames.www.gutterballredux.Components.BodyComponent;
import com.pandeagames.www.gutterballredux.gameObjects.BreakableBlockMedium;
import com.pandeagames.www.gutterballredux.gameObjects.BreakableBlockSmall;

import org.jbox2d.common.Vec2;

public class RobotDanceLevel extends ThrowLevel {
	private BodyEmmiter emitter1, emitter2;
	public RobotDanceLevel(Game game, BodyComponent geometry) {
		super(game, geometry, R.drawable.roboto_dance);
		// TODO Auto-generated constructor stub

		createPortal(8,18);
		createPortal(15, 18);
		createPortal(3, 12.5f);
		createPortal(20, 12.5f);
		createPortal(20, 2, AppleType.GOLDEN);
		createPortal(3, 2);

		addBreakable(new BreakableBlockMedium(game, new Vec2(9f, 7f)));

		addBreakable(new BreakableBlockSmall(game, new Vec2(22.5f, 22.5f)));
		addBreakable(new BreakableBlockSmall(game, new Vec2(19.5f, 22.5f)));
		addBreakable(new BreakableBlockSmall(game, new Vec2(22.5f, 25.5f)));
		addBreakable(new BreakableBlockSmall(game, new Vec2(19.5f, 25.5f)));
		addBreakable(new BreakableBlockSmall(game, new Vec2(22.5f, 28.5f)));
		addBreakable(new BreakableBlockSmall(game, new Vec2(22.5f, 31.5f)));

		addBreakable(new BreakableBlockMedium(game, new Vec2(3f, 24f)));

		addBreakable(new BreakableBlockSmall(game, new Vec2(1.5f, 28.5f)));
		addBreakable(new BreakableBlockSmall(game, new Vec2(1.5f, 31.5f)));
		addBreakable(new BreakableBlockSmall(game, new Vec2(1.5f, 34.5f)));
		addBreakable(new BreakableBlockSmall(game, new Vec2(1.5f, 37.5f)));
		addBreakable(new BreakableBlockSmall(game, new Vec2(1.5f, 40.5f)));

	}
	public RobotDanceLevel(Game game) 
	{
		this(game, new RobotDanceGeom(game));
		// TODO Auto-generated constructor stub
	}

}
