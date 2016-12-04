package com.pandeagames.www.gutterballredux.gameObjects.animNodes;

public class ConstrainedDoubleNode extends DoubleJointNode {

	public ConstrainedDoubleNode(float x, float y, float r) {
		super(x, y, r);
		// TODO Auto-generated constructor stub
	}

	public ConstrainedDoubleNode(JointNode attached, JointNode attached2,
			float x, float y, float r) {
		super(attached, attached2, x, y, r);
		// TODO Auto-generated constructor stub
	}
	public void update(){
		super.update();
		if(d*2>r){
			x=attached.x-(float)Math.cos(a)*r;
			y=attached.y-(float)Math.sin(a)*r;
		}else{
			x=attached2.x;
			y=attached2.y;
		}
	}
}
