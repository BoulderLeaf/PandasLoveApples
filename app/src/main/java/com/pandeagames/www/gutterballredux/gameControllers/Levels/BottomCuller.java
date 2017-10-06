package com.pandeagames.www.gutterballredux.gameControllers.Levels;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.pandeagames.www.gutterballredux.infoHolders.DrawInfo;
import com.pandeagames.www.gutterballredux.infoHolders.UpdateInfo;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.contacts.ContactEdge;

import com.pandeagames.R;

import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;

import com.pandeagames.www.gutterballredux.gameControllers.Game;
import com.pandeagames.www.gutterballredux.gameObjects.Actor;
import com.pandeagames.www.gutterballredux.gameObjects.CollisionGroups;
import com.pandeagames.www.gutterballredux.Components.BodyComponent;

public class BottomCuller extends BodyComponent {
	private List<IBottomCullListener> bottomCullListeners;
	public BottomCuller(Game game) {
		super(game);
		bottomCullListeners = new  ArrayList<IBottomCullListener>();

	}
	@Override
public void createBody(){
	createBody(world.createBody(bodyDef));
}
	public void createBody(Body body)
	{
		 PolygonShape groundBox = new PolygonShape();
		 groundBox.setAsBox(game.getSimulation().getWorldSize().x,1, new Vec2(game.getSimulation().getWorldSize().x/2,game.getSimulation().getWorldSize().y+5),0.0f);
		 body.createFixture(groundBox,0.0f);
		 //
		super.createBody(body);
	}
	@Override
	public void update(UpdateInfo info){
		super.update(info);
		if(body == null)  { return; }
		ContactEdge contact = body.getContactList();
		while(contact!=null)
		{
			if(((BodyComponent)contact.other.getUserData()).containsCollisionGroup(CollisionGroups.ACTOR)){
				destroyActor((Actor)contact.other.getUserData());
				((BodyComponent)contact.other.getUserData()).markDestroy();
				game.getSoundPool().playRandom(game.getSoundPool().getPool().die1,
						game.getSoundPool().getPool().die2,
						game.getSoundPool().getPool().die3,
						game.getSoundPool().getPool().die4);
				break;
			}
		contact=contact.next;
		
			
		}

	}
	private void destroyActor(Actor actor){
		for(IBottomCullListener listener : bottomCullListeners){
			listener.onBottomCull(actor);
		}

	}
	public void addBottomCullListener(IBottomCullListener listener){
		bottomCullListeners.add(listener);
	}
	public void removeBottomCullListener(IBottomCullListener listener){
		bottomCullListeners.remove(listener);
	}
	@Override 
	public void destroy(){
		if(destroyed) {return;}
		super.destroy();
		bottomCullListeners.clear();
		bottomCullListeners=null;
	}
}

