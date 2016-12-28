package com.pandeagames.www.gutterballredux.gameControllers.Levels;

import com.pandeagames.www.gutterballredux.gameControllers.Game;
import com.pandeagames.www.gutterballredux.gameControllers.Levels.Geom.PiggyBackGeom;
import com.pandeagames.www.gutterballredux.gameControllers.Levels.Geom.RobotDanceGeom;
import com.pandeagames.www.gutterballredux.gameObjects.ActorFactory;
import com.pandeagames.www.gutterballredux.gameObjects.BodyEmmiter;
import com.pandeagames.www.gutterballredux.Components.BodyComponent;
import com.pandeagames.www.gutterballredux.gameObjects.BreakableBlockMedium;

import org.jbox2d.common.Vec2;

public class RobotDanceLevel extends ThrowLevel {
	private BodyEmmiter emitter1, emitter2;
	public RobotDanceLevel(Game game, BodyComponent geometry) {
		super(game, geometry);
		// TODO Auto-generated constructor stub
		createPortal(3, 24);
		createPortal(20, 24);
		createPortal(8,18);
		createPortal(15, 18);
		createPortal(3, 13);
		createPortal(20, 13);
		createPortal(20, 3);
		createPortal(3, 3);

		addBreakable(new BreakableBlockMedium(game, new Vec2(3f, 30f)));
		addBreakable(new BreakableBlockMedium(game, new Vec2(9f, 30f)));
		addBreakable(new BreakableBlockMedium(game, new Vec2(15f, 30f)));
		addBreakable(new BreakableBlockMedium(game, new Vec2(21f, 30f)));

		addBreakable(new BreakableBlockMedium(game, new Vec2(8f, 7f)));

	}
	public RobotDanceLevel(Game game) 
	{
		this(game, new RobotDanceGeom(game));
		// TODO Auto-generated constructor stub
	}

}
