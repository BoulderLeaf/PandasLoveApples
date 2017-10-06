package com.pandeagames.www.gutterballredux.threads;

import com.pandeagames.www.gutterballredux.gameControllers.Game;
import com.pandeagames.www.gutterballredux.infoHolders.UpdateInfo;

import java.util.Vector;

import com.pandeagames.www.gutterballredux.droidControllers.SwingActivity;

import com.pandeagames.www.gutterballredux.Components.AbstractComponent;
import com.pandeagames.www.gutterballredux.Components.AbstractGameComponent;
import com.pandeagames.www.gutterballredux.Components.interfaces.IDrawableComponent;

public class GameThread extends DrawThread {
	public static final String threadName="GameThread";
	private BufferedList<AbstractGameComponent> updateList;
	public GameThread(BufferedList<IDrawableComponent> drawList,BufferedList<AbstractGameComponent> updateList, SwingActivity activity) {
		super(drawList, activity);
		this.updateList=updateList;
	}
	public GameThread(BufferedList<IDrawableComponent> drawList,BufferedList<AbstractGameComponent> updateList, SwingActivity activity,ThreadGroup group) {
		super(drawList,activity,group);
		this.updateList=updateList;
		// TODO Auto-generated constructor stub
	}
	public GameThread(BufferedList<IDrawableComponent> drawList,BufferedList<AbstractGameComponent> updateList, SwingActivity activity,ThreadGroup group, long stackSize) {
		super(drawList, activity,group, stackSize);
		this.updateList=updateList;
		// TODO Auto-generated constructor stub
	}
	@Override
	public void tick(TickInfo tickInfo) {
		UpdateInfo  info = new UpdateInfo(tickInfo);
		for(AbstractGameComponent comp : updateList){
			if(!comp.destroyed() && !comp.getMarkDestroy()){
				comp.update(info);
			}
			
		}
		updateList.clearBuffer();
		drawList.clearRemoveBuffer();
		super.tick(tickInfo);
		for(AbstractGameComponent comp : updateList){
			if(comp.getMarkDestroy()){
				comp.destroy();
			}
		}
	}
}
