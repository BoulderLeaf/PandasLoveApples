package com.pandeagames.www.gutterballredux.gameObjects.animNodes;

public class RestrainedStiffAngleNode extends DoubleJointNode {
	private float damp;
	public RestrainedStiffAngleNode(float damp,float x, float y, float r) {
		super(x, y, r);
		// TODO Auto-generated constructor stub
		this.damp=damp;
	}

	public RestrainedStiffAngleNode(JointNode attached, JointNode attached2,
			float damp,float x, float y, float r) {
		super(attached, attached2, x, y, r);
		// TODO Auto-generated constructor stub
		this.damp=damp;
	}
	public void update()
	{
		super.update();
		if(attached!=null){
			JointNode attachedTo = attached;
			JointNode angleFrom = attached2;
			if(attached2==null){
				angleFrom = attached;
			}
			float targetAngle = (float)Math.atan2(attachedTo.y-angleFrom.y, attachedTo.x-angleFrom.x);
			float da   = (float)Math.atan2(Math.sin(targetAngle-attached.a), Math.cos(targetAngle-attached.a));
			x=attachedTo.x+(float)Math.cos(attachedTo.a+da/damp)*r;
			y=attachedTo.y+(float)Math.sin(attachedTo.a+da/damp)*r;
			a=attachedTo.a+da/damp;
		}
		
	}
}
