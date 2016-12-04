package com.pandeagames.www.gutterballredux.gameObjects;

import com.pandeagames.www.gutterballredux.gameControllers.Game;

public class Forground extends Background {

	public Forground(Game activity) {
		super(activity);
		// TODO Auto-generated constructor stub
	}

	public Forground(Game activity, int id) {
		super(activity, id);
		// TODO Auto-generated constructor stub
	}

	public Forground(Game activity, int id, boolean fillCanvas) {
		super(activity, id, fillCanvas);
		// TODO Auto-generated constructor stub
	}

	public Forground(Game activity, int id, int drawOrder) {
		super(activity, id, drawOrder);
		// TODO Auto-generated constructor stub
	}

	public Forground(Game activity, int id, int drawOrder, boolean fillCanvas) {
		super(activity, id, drawOrder, fillCanvas);
		// TODO Auto-generated constructor stub
	}
	public int drawOrder(){
		return 100;
	}

}
