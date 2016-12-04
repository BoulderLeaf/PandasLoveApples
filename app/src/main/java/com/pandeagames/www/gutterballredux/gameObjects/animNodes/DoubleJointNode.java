package com.pandeagames.www.gutterballredux.gameObjects.animNodes;

public class DoubleJointNode extends RadialNode {
	protected JointNode attached2;
	public DoubleJointNode(float x, float y, float r) {
		super(x, y, r);
		// TODO Auto-generated constructor stub
	}

	public DoubleJointNode(JointNode attached,JointNode attached2, float x, float y, float r) {
		super(attached, x, y, r);
		this.attached2=attached2;
		
	}
	public void update(){
		if(attached2!=null && attached!=null){
			dx = attached2.x-attached.x;
			dy = attached2.y-attached.y;
			
			x=attached.x+dx/2;
			y=attached.y+dy/2;
		}
		super.update();
	}

}
