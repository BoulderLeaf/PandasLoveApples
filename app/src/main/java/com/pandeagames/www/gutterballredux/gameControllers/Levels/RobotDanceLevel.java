package com.pandeagames.www.gutterballredux.gameControllers.Levels;

import com.pandeagames.www.gutterballredux.gameControllers.Game;
import com.pandeagames.www.gutterballredux.gameControllers.Levels.Geom.PiggyBackGeom;
import com.pandeagames.www.gutterballredux.gameControllers.Levels.Geom.RobotDanceGeom;
import com.pandeagames.www.gutterballredux.gameObjects.ActorFactory;
import com.pandeagames.www.gutterballredux.gameObjects.BodyEmmiter;
import com.pandeagames.www.gutterballredux.Components.BodyComponent;

public class RobotDanceLevel extends ThrowLevel {
	private BodyEmmiter emitter1, emitter2;
	public RobotDanceLevel(Game game, BodyComponent geometry) {
		super(game, geometry);
		// TODO Auto-generated constructor stub
		createPortal(15, 3);
		createPortal(10, 8);
		createPortal(20, 8);
		createPortal(15, 13);
		createPortal(10, 18);
		createPortal(15, 23);
		createPortal(20, 18);
		
		emitter1 = new BodyEmmiter(game, 6, 3, new ActorFactory(game));
		emitter2 = new BodyEmmiter(game, 24, 3, new ActorFactory(game));

	}
	public RobotDanceLevel(Game game) 
	{
		this(game, new RobotDanceGeom(game));
		// TODO Auto-generated constructor stub
	}

}
