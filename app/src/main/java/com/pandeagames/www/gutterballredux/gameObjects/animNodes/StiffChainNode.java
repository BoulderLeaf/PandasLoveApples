package com.pandeagames.www.gutterballredux.gameObjects.animNodes;

public class StiffChainNode extends RadialNode {

	public StiffChainNode(float x, float y, float r) {
		super(x, y, r);
		// TODO Auto-generated constructor stub
	}

	public StiffChainNode(JointNode attached, float x, float y, float r) {
		super(attached, x, y, r);
		// TODO Auto-generated constructor stub
	}
	public void update(){
		super.update();
		if(attached!=null){
			if(d>r){
				x+=Math.cos(a)*(d-r);
				y+=Math.sin(a)*(d-r);
			}else if(d<r){
				x-=Math.cos(a)*(r-d);
				y-=Math.sin(a)*(r-d);
			}
		}
	}
}
