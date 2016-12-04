package com.pandeagames.www.gutterballredux.gameObjects;

import org.jbox2d.common.Vec2;

import com.pandeagames.www.gutterballredux.gameControllers.Game;
import com.pandeagames.www.gutterballredux.Components.BodyCompFactory;
import com.pandeagames.www.gutterballredux.Components.BodyComponent;

public class ActorFactory extends BodyCompFactory {

	public ActorFactory(Game game) {
		// TODO Auto-generated constructor stub
		super(game);
	}

	@Override
	public BodyComponent getBodyComp() {
		// TODO Auto-generated method stub
		return getBodyComp(0f,0f);
	}
	@Override
	public BodyComponent getBodyComp(float x, float y) {
		// TODO Auto-generated method stub
		return new MetalBall(game, new Vec2(x, y));
	}

}
