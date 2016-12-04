package com.pandeagames.www.gutterballredux.gameObjects;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;

import com.pandeagames.www.gutterballredux.infoHolders.DrawInfo;
import com.pandeagames.www.gutterballredux.infoHolders.UpdateInfo;
import android.graphics.Paint;
import com.pandeagames.www.gutterballredux.gameControllers.Game;
import com.pandeagames.www.gutterballredux.gameObjects.animNodes.ConstrainedDoubleNode;
import com.pandeagames.www.gutterballredux.gameObjects.animNodes.JointNode;
import com.pandeagames.www.gutterballredux.gameObjects.animNodes.LooseFollowNode;
import com.pandeagames.www.gutterballredux.gameObjects.animNodes.StiffAngleNode;
import com.pandeagames.www.gutterballredux.Components.AbstractGameComponent;
import com.pandeagames.www.gutterballredux.Components.BodyComponent;
import com.pandeagames.www.gutterballredux.Components.DrawableGameComponent;
import com.pandeagames.www.gutterballredux.Components.interfaces.IBodyCreationListener;

public class AnimalBody extends DrawableGameComponent  {
	private BodyComponent comp;
	private Paint nodePaint;
	private JointNode centerBodyNode;
	private StiffAngleNode stiffAngle, stiffAngle2, stiffAngle3, stiffAngle4, stiffAngleFront;
	private Tail tail;
	
	private LooseFollowNode looseFollow, looseFollow2, looseFollow3, looseFollow4;
	private ConstrainedDoubleNode constrainedNode, constrainedNode2, constrainedNode3, constrainedNode4;
	private boolean firstUpdate;
	private Paint paint;
	public AnimalBody(Game game,Tail tail, BodyComponent comp) {
		super(game);
		this.comp=comp;
		nodePaint = new Paint();
		nodePaint.setARGB(255, 255, 255, 255);
		paint = new Paint();
		paint.setARGB(255, 66, 22, 11);
		centerBodyNode = new JointNode(comp.getX(), comp.getY());
		stiffAngleFront=new StiffAngleNode(centerBodyNode,tail.getMainAngleNode(), comp.getX(), comp.getY(), 0.5f,0);
		
		stiffAngle = new StiffAngleNode(centerBodyNode,tail.getMainAngleNode(), comp.getX(), comp.getY(), 0.3f,(float)Math.toRadians(125));
		looseFollow = new LooseFollowNode(stiffAngle,stiffAngle.x, stiffAngle.y, 3.5f);
		constrainedNode = new ConstrainedDoubleNode(stiffAngle,looseFollow,comp.getX(), comp.getY(), 0.5f);
		
		stiffAngle2 = new StiffAngleNode(centerBodyNode,tail.getMainAngleNode(), comp.getX(), comp.getY(), 0.3f,(float)Math.toRadians(-125));
		looseFollow2 = new LooseFollowNode(stiffAngle2,stiffAngle2.x, stiffAngle2.y, 3.5f);
		constrainedNode2 = new ConstrainedDoubleNode(stiffAngle2,looseFollow2,comp.getX(), comp.getY(), 0.5f);
		
		stiffAngle3 = new StiffAngleNode(stiffAngleFront,tail.getMainAngleNode(), comp.getX(), comp.getY(), 0.2f,(float)Math.toRadians(-75));
		looseFollow3 = new LooseFollowNode(stiffAngle3,stiffAngle3.x, stiffAngle3.y, 2f);
		constrainedNode3 = new ConstrainedDoubleNode(stiffAngle3,looseFollow3,comp.getX(), comp.getY(), 0.5f);
		
		stiffAngle4 = new StiffAngleNode(stiffAngleFront,tail.getMainAngleNode(), comp.getX(), comp.getY(), 0.2f,(float)Math.toRadians(75));
		looseFollow4 = new LooseFollowNode(stiffAngle4,stiffAngle4.x, stiffAngle4.y, 2f);
		constrainedNode4 = new ConstrainedDoubleNode(stiffAngle4,looseFollow4,comp.getX(), comp.getY(), 0.5f);
	}

	public int drawOrder(){
		return 200;
	}
	public AnimalBody(Game game, int drawOrder) {
		super(game, drawOrder);
		// TODO Auto-generated constructor stub
	}
	public void update(UpdateInfo updateInfo){
		super.update(updateInfo);
		if(centerBodyNode!=null){
			
		centerBodyNode.x=comp.getX();
		centerBodyNode.y=comp.getY();
		
		
		
		centerBodyNode.update();
		stiffAngleFront.update();
		stiffAngle.update();
		stiffAngle2.update();
		stiffAngle3.update();
		stiffAngle4.update();
		if(!firstUpdate && comp.getX()!=0.0f){
			looseFollow.x=stiffAngle.x;
			looseFollow.y=stiffAngle.y;
			looseFollow2.x=stiffAngle2.x;
			looseFollow2.y=stiffAngle2.y;
			looseFollow3.x=stiffAngle3.x;
			looseFollow3.y=stiffAngle3.y;
			looseFollow4.x=stiffAngle4.x;
			looseFollow4.y=stiffAngle4.y;
			firstUpdate=true;
			}
		looseFollow.update();
		looseFollow2.update();
		looseFollow3.update();
		looseFollow4.update();
		constrainedNode.update();
		constrainedNode2.update();
		constrainedNode3.update();
		constrainedNode4.update();
		}
	}
	public void draw(DrawInfo drawInfo){
		super.draw(drawInfo);
		
		//drawNode(drawInfo,centerBodyNode);
		//drawNode(drawInfo, stiffAngle);
		//drawNode(drawInfo, looseFollow);
		//drawNode(drawInfo, constrainedNode);
		//drawNode(drawInfo, constrainedNode2);
		float r=0.3f;
		drawInfo.getCanvas().drawCircle(
				gameView.toScreenX(constrainedNode.x), 
				gameView.toScreenY(constrainedNode.y), 
				gameView.toScreen(r), 
				paint);
		drawInfo.getCanvas().drawCircle(
				gameView.toScreenX(constrainedNode2.x), 
				gameView.toScreenY(constrainedNode2.y), 
				gameView.toScreen(r), 
				paint);
		drawInfo.getCanvas().drawCircle(
				gameView.toScreenX(constrainedNode3.x), 
				gameView.toScreenY(constrainedNode3.y), 
				gameView.toScreen(r), 
				paint);
		drawInfo.getCanvas().drawCircle(
				gameView.toScreenX(constrainedNode4.x), 
				gameView.toScreenY(constrainedNode4.y), 
				gameView.toScreen(r), 
				paint);
	}
	private void drawNode(DrawInfo drawInfo,JointNode node){
		if(node!=null){
		drawInfo.getCanvas().drawCircle(
				gameView.toScreenX(node.x), 
				gameView.toScreenY(node.y), 
				gameView.toScreen(0.1f), 
				nodePaint);
		}
	
	}
}
