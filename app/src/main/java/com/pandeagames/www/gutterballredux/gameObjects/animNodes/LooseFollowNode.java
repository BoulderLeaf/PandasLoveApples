package com.pandeagames.www.gutterballredux.gameObjects.animNodes;


public class LooseFollowNode extends JointNode {
	private float damp;
	public LooseFollowNode(float x, float y, float damp) 
	{
		super(x, y);
		this.damp=damp;
	}
	public LooseFollowNode(JointNode attached, float x, float y, float damp) {
		super(attached, x, y);
		this.damp=damp;
	}
	public void update()
	{
		super.update();
		x+=dx/damp/1.5;
		y+=dy/damp;
	}
}
