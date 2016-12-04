package com.pandeagames.www.gutterballredux.gameObjects.animNodes;

public class RadialNode extends JointNode {
	protected float r;
	public RadialNode(float x, float y, float r) {
		super(x, y);
		this.r=r;
	}

	public RadialNode(JointNode attached, float x, float y, float r) {
		super(attached, x, y);
		this.r=r;
	}

}
