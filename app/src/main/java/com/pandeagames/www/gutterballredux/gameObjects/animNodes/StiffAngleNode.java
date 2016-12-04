package com.pandeagames.www.gutterballredux.gameObjects.animNodes;

public class StiffAngleNode extends DoubleJointNode {
	private float aOffset;
	public StiffAngleNode(float x, float y, float r, float aOffset) {
		super(x, y, r);
		this.aOffset=aOffset;
	}

	public StiffAngleNode(JointNode attached,JointNode attached2,float x, float y, float r, float aOffset) {
		super(attached,attached2, x, y, r);
		this.aOffset=aOffset;
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
			x=attachedTo.x+(float)Math.cos(angleFrom.a+aOffset)*r;
			y=attachedTo.y+(float)Math.sin(angleFrom.a+aOffset)*r;
			a=angleFrom.a;
		}
		
	}
}
