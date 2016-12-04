package com.pandeagames.www.gutterballredux.gameControllers;

import com.pandeagames.www.gutterballredux.infoHolders.UpdateInfo;
import com.pandeagames.www.gutterballredux.Components.AbstractGameComponent;
import com.pandeagames.www.gutterballredux.Components.BodyComponent;

public class BodyController extends AbstractGameComponent {
	protected BodyComponent bodyComp;
	public BodyController(BodyComponent bodyComp, Game game){
		super(game);
		this.bodyComp=bodyComp;
	}
	@Override
	public void update(UpdateInfo updateInfo) {
		// TODO Auto-generated method stub
		
	}
}

