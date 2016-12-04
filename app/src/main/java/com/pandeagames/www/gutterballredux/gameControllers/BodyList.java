package com.pandeagames.www.gutterballredux.gameControllers;

import java.util.ArrayList;

import org.jbox2d.dynamics.World;

import com.pandeagames.www.gutterballredux.Components.BodyComponent;

import com.pandeagames.www.gutterballredux.threads.BufferedList;

public class BodyList<BodyComponent> extends BufferedList<BodyComponent> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private World world;
	public BodyList() {
		// TODO Auto-generated constructor stub
		super();
	}
	@Override
	public void clearBuffer(){
		for(BodyComponent bodyComp : addBuffer){
			((com.pandeagames.www.gutterballredux.Components.BodyComponent) bodyComp).createBody();
		}
		super.clearBuffer();
	}
}
