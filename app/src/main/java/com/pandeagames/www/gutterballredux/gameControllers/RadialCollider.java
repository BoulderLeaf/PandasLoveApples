package com.pandeagames.www.gutterballredux.gameControllers;

import com.pandeagames.www.gutterballredux.infoHolders.Radial;
import com.pandeagames.www.gutterballredux.droidControllers.SwingActivity;
import com.pandeagames.www.gutterballredux.Components.AbstractComponent;
import com.pandeagames.www.gutterballredux.Components.interfaces.IRadialCollider;

public class RadialCollider extends AbstractComponent {
private float r;
private IRadialCollider collider;
private Radial radial;
private RadialID id;
private RadialCollisionController radialCollision;
	public RadialCollider(Game activity, IRadialCollider collider, float r, RadialID id) {
		super(activity);
		// TODO Auto-generated constructor stub
		this.r=r;
		this.collider=collider;
		this.id=id;
		radial = new Radial(collider.getX(), collider.getY(), r);
		radialCollision=activity.getRadialCollision();
		radialCollision.addCollider(this);
	}
	public float getX(){
		return collider.getX();
	}
	public float getY(){
		return collider.getY();
	}
	public Radial getRadial()
	{
		radial.set(collider.getX(), collider.getY(), r);
		return radial;
	}
	public IRadialCollider getCollider(){
		return collider;
	}
	public RadialID getId(){
		return id;
	}
	@Override
	public void destroy(){
		radialCollision.removeCollider(this);
		radialCollision=null;
		collider=null;
		radial=null;
		super.destroy();
	}

}
