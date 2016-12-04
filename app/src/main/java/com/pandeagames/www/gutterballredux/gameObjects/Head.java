package com.pandeagames.www.gutterballredux.gameObjects;

import com.pandeagames.R;

import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import com.pandeagames.www.gutterballredux.infoHolders.DrawInfo;
import com.pandeagames.www.gutterballredux.infoHolders.UpdateInfo;
import com.pandeagames.www.gutterballredux.gameControllers.Game;
import com.pandeagames.www.gutterballredux.gameObjects.animNodes.JointNode;
import com.pandeagames.www.gutterballredux.gameObjects.animNodes.RestrainedStiffAngleNode;
import com.pandeagames.www.gutterballredux.gameObjects.animNodes.StiffAngleNode;
import com.pandeagames.www.gutterballredux.Components.AbstractGameComponent;
import com.pandeagames.www.gutterballredux.Components.DrawableGameComponent;

public class Head extends DrawableGameComponent {
	private AbstractGameComponent comp;
	private JointNode centerBodyNode;
	private StiffAngleNode stiffAngle;
	private Paint nodePaint;
	private BitmapDrawable headBitmap;

	private Tail tail;
	private RestrainedStiffAngleNode restrainedNode;
	private JointNode eyes;
	private Paint paint;
	private  Rect des;
	public Head(Game game, AbstractGameComponent comp, Tail tail) {
		super(game);
		this.comp=comp;
		this.tail=tail;
		nodePaint = new Paint();
		nodePaint.setARGB(255, 255, 255, 255);
		paint = new Paint();
		paint.setARGB(255, 216, 78, 36);
		centerBodyNode = new JointNode(comp.getX(), comp.getY());
		eyes = new JointNode(10, 50);
		stiffAngle = new StiffAngleNode(centerBodyNode,tail.getMainAngleNode(), comp.getX(), comp.getY(), 0.2f,0);
		restrainedNode = new RestrainedStiffAngleNode(stiffAngle, eyes,2,comp.getX(), comp.getY(), 0.5f);
		headBitmap=(BitmapDrawable) game.getResources().getDrawable(R.drawable.head);
		des = new Rect();
		//JointNode attached, JointNode attached2,
		//float x, float y, float r
	}
	public void update(UpdateInfo updateInfo){
		super.update(updateInfo);
		centerBodyNode.x=comp.getX();
		centerBodyNode.y=comp.getY();
		eyes.x=centerBodyNode.x;
		eyes.y=centerBodyNode.y+20;
		
		centerBodyNode.update();
		
		stiffAngle.update();
		restrainedNode.update();
	}
	public void draw(DrawInfo drawInfo){
		super.draw(drawInfo);
		//drawNode(drawInfo,stiffAngle);
		//drawNode(drawInfo,restrainedNode);
		/*drawInfo.getCanvas().drawCircle(
				gameView.toScreenX(restrainedNode.x), 
				gameView.toScreenY(restrainedNode.y), 
				gameView.toScreen(0.5f), 
				paint);*/
		
		des.set(
				(int)gameView.toScreenX(restrainedNode.x-0.5),
				(int)gameView.toScreenY(restrainedNode.y-1),
				(int)gameView.toScreenX(restrainedNode.x+0.5),
				(int)gameView.toScreenY(restrainedNode.y+1)
				);
		drawInfo.getCanvas().save();
		drawInfo.getCanvas().rotate(
				(float)Math.toDegrees(restrainedNode.a)+90, 
				gameView.toScreenX(restrainedNode.x), 
						gameView.toScreenY(restrainedNode.y));
		headBitmap.setBounds(des);
		headBitmap.draw(drawInfo.getCanvas());
		drawInfo.getCanvas().restore();
		
	}
	public int drawOrder(){
		return 300;
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
