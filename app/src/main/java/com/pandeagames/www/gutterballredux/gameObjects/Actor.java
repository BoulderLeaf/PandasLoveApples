package com.pandeagames.www.gutterballredux.gameObjects;

import com.pandeagames.www.gutterballredux.infoHolders.DrawInfo;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

import com.pandeagames.R;

import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;

import com.pandeagames.www.gutterballredux.gameControllers.Game;
import com.pandeagames.www.gutterballredux.gameControllers.RadialCollider;
import com.pandeagames.www.gutterballredux.gameControllers.RadialID;
import com.pandeagames.www.gutterballredux.Components.BodyComponent;
import com.pandeagames.www.gutterballredux.Components.interfaces.IRadialCollider;

public class Actor extends BodyComponent implements IRadialCollider{
	private RadialCollider radialCollider;
	private Vec2 pos;
	private HitSparks sparks;
	private BitmapDrawable sphere;
	private float r;
	private Tail tail;
	private AnimalBody aBody;
	private Head head;
	public Actor(Game game, Vec2 pos) {
		super(game);
		this.pos=pos;
		 bodyDef.type=BodyType.DYNAMIC;
	        bodyDef.position.set(pos);
	        radialCollider = new RadialCollider(game, this, 0.5f, RadialID.ACTOR);
	        addCollisionGroup(CollisionGroups.ACTOR);
	        sphere=(BitmapDrawable) game.getResources().getDrawable(R.drawable.sphere);
	        r=0.5f;
	        drawBody=false;
	        tail = new Tail(game, this);
	        aBody = new AnimalBody(game,tail, this);
	        head = new Head(game, this, tail);
	}
	@Override
	public void createBody(){
	     createBody(world.createBody(bodyDef));        
	}
	@Override
	public void createBody(Body body){
	      //PolygonShape dynamicBox = new PolygonShape();
	       CircleShape dynamicBox = new CircleShape();
	        //dynamicBox.setAsBox(1.0f, 1.0f);
	       dynamicBox.m_radius=r;
	        FixtureDef fixtureDef = new FixtureDef();
	        fixtureDef.shape=dynamicBox;
	        fixtureDef.density = 10.0f;
	        fixtureDef.friction = 0.5f;
	        fixtureDef.restitution=0.5f;
	        body.createFixture(fixtureDef);
		super.createBody(body);
	}
	@Override
	public void draw(DrawInfo info)
	{
		super.draw(info);
		if(body!=null){
		Rect des = new Rect();
		Vec2 cent = body.getWorldCenter();
		des.set((int)gameView.toScreenX(cent.x-r), 
				(int)gameView.toScreenY(cent.y-r),
				(int)gameView.toScreenX(cent.x+r),
				(int)gameView.toScreenY(cent.y+r));
		sphere.setBounds(des);
		sphere.draw(info.getCanvas());
		}
		
	}
	@Override
	public void radialCollide(RadialCollider other) {
		// TODO Auto-generated method stub
		if(other.getId()==RadialID.PORTAL)
		{
			destroy();
		}
	}
	public int drawOrder(){
		return 201;
	}
	@Override
	public void destroy(){
		radialCollider.destroy();
		tail.destroy();
		aBody.destroy();
		head.destroy();
		///sparks.destroy();
		radialCollider=null;
		tail=null;
		pos=null;
		super.destroy();
	}

}
