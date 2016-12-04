package com.pandeagames.www.gutterballredux.gameObjects.animNodes;

public class BounceFollowNode extends JointNode {
	private float damp;
	public BounceFollowNode(float x, float y, float damp) {
		super(x, y);
		this.damp=damp;
	}

	public BounceFollowNode(JointNode attached, float x, float y, float damp) {
		super(attached, x, y);
		this.damp=damp;
	}
	@Override
	public void update(){
		super.update();
		vx+=dx/damp;
		vy+=dy/damp;
	}

}
