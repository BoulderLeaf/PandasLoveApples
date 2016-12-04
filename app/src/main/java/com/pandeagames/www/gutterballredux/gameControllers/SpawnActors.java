package com.pandeagames.www.gutterballredux.gameControllers;

import com.pandeagames.www.gutterballredux.gameObjects.Actor;

import org.jbox2d.common.Vec2;

import android.content.DialogInterface;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import com.pandeagames.www.gutterballredux.droidControllers.SwingActivity;
import com.pandeagames.www.gutterballredux.Components.AbstractComponent;
import com.pandeagames.www.gutterballredux.Components.interfaces.IUserInputComponent;

public class SpawnActors extends AbstractComponent implements IUserInputComponent {
private Game game;
	public SpawnActors(Game activity) {
		super(activity);
		this.game= activity;
		activity.addInputComponent(this);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		//@SuppressWarnings("unused")
		Actor actor = new Actor(game,new Vec2((int)((GameView)activity.getView()).toWorldX(event.getRawX()), (int)((GameView)activity.getView()).toWorldY(event.getRawY())));
		//DragController controller = new DragController(actor, game);
	}

	@Override
	public void onLongClick(View v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onCreateContextMenu() {
		// TODO Auto-generated method stub
		
	}

}
