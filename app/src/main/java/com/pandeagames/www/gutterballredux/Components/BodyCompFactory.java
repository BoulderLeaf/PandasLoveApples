package com.pandeagames.www.gutterballredux.Components;

import com.pandeagames.www.gutterballredux.gameControllers.Game;

public abstract class BodyCompFactory {
protected Game game;
	public BodyCompFactory(Game game) {
		// TODO Auto-generated constructor stub
		this.game=game;
	}
	public abstract BodyComponent getBodyComp();
	public abstract BodyComponent getBodyComp(float x, float y);
}
