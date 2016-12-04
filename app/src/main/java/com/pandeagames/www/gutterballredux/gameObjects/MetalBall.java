package com.pandeagames.www.gutterballredux.gameObjects;

import com.pandeagames.www.gutterballredux.infoHolders.UpdateInfo;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.contacts.ContactEdge;

import com.pandeagames.www.gutterballredux.gameControllers.Game;
import com.pandeagames.www.gutterballredux.gameControllers.RadialCollider;
import com.pandeagames.www.gutterballredux.gameControllers.RadialID;
import com.pandeagames.www.gutterballredux.Components.BodyComponent;

public class MetalBall extends BodyComponent {
	
	private Vec2 pos;
	public MetalBall(Game game, Vec2 pos) {
		super(game);
		setPos(pos.x, pos.y);
		 bodyDef.type=BodyType.DYNAMIC;
	        bodyDef.position.set(pos);
		// TODO Auto-generated constructor stub
	        addCollisionGroup(CollisionGroups.METAL_BALL);
	       
	}
	@Override
	public void createBody(){
	     createBody(world.createBody(bodyDef));        
	}
	@Override
	public void createBody(Body body){
	     // PolygonShape dynamicBox = new PolygonShape();
	       CircleShape dynamicBox = new CircleShape();
	      //  dynamicBox.setAsBox(1.0f, 1.0f);
	       dynamicBox.m_radius=1.2f;
	        FixtureDef fixtureDef = new FixtureDef();
	        fixtureDef.shape=dynamicBox;
	        fixtureDef.density = 20.0f;
	        fixtureDef.friction = 20.0f;
	        fixtureDef.restitution=0.05f;
	        body.createFixture(fixtureDef);
		super.createBody(body);
	}
	@Override
	public void update(UpdateInfo info){
		super.update(info);
		ContactEdge contact = body.getContactList();
		while(contact!=null)
		{
			if(((BodyComponent)contact.other.getUserData()).containsCollisionGroup(CollisionGroups.LEVEL_GEOM)){
				destroy();
				break;
			}
		contact=contact.next;
		
			
		}
	}
	@Override
	public void destroy(){
		pos=null;
		super.destroy();
	}
}
