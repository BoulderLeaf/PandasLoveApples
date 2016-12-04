package com.pandeagames.www.gutterballredux.gameObjects.animNodes;

import android.graphics.Canvas;
import android.graphics.Paint;

public class JointNode {
	protected JointNode attached;
	public float x, vx, y, vy, g;
	public float fric=1.1f;
	protected float dx;
	protected float dy;
	protected float d;
	public float a;
	public float angleJitter=0f;
	public JointNode( float x, float y) {
		// TODO Auto-generated constructor stub
		this.x=x;
		this.y=y;
	}
	public JointNode(JointNode attached, float x, float y) {
		// TODO Auto-generated constructor stub
		this(x, y);
		this.attached=attached;
	}
	public void update(){
		if(attached!=null){
			x+=vx;
			y+=vy;
			vx=vx/fric;
			vy=vy/fric+g;
			dx = attached.x-x;
			dy = attached.y-y;
			d  = (float)Math.sqrt(dx*dx + dy*dy);
			a=(float)Math.atan2(dy, dx)+angleJitter;
		}
	}
}
