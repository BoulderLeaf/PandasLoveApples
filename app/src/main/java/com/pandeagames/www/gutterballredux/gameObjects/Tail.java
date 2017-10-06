package com.pandeagames.www.gutterballredux.gameObjects;

import com.pandeagames.R;

import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import com.pandeagames.www.gutterballredux.infoHolders.DrawInfo;
import com.pandeagames.www.gutterballredux.infoHolders.UpdateInfo;
import com.pandeagames.www.gutterballredux.gameControllers.Game;
import com.pandeagames.www.gutterballredux.gameObjects.animNodes.JointNode;
import com.pandeagames.www.gutterballredux.gameObjects.animNodes.StiffChainNode;
import com.pandeagames.www.gutterballredux.Components.AbstractGameComponent;
import com.pandeagames.www.gutterballredux.Components.DrawableGameComponent;

public class Tail extends DrawableGameComponent {
	private AbstractGameComponent comp;
	private JointNode[] nodes;
	private int jointNum=3;
	private Paint paint;
	private BitmapDrawable tail_end;
	private BitmapDrawable tail_start;
	private  Rect des;
	private int dir=1;
	private float threshhold=0.5f;//threshhold for vibrating tail
	public Tail(Game game, AbstractGameComponent comp) {
		super(game);
		this.comp=comp;
		nodes=new JointNode[jointNum];
		nodes[0] = new JointNode(comp.getX(),comp.getY());
		nodes[1] = new StiffChainNode(nodes[0],comp.getX(),comp.getY(), 1f);
		nodes[2] = new StiffChainNode(nodes[1], comp.getX(),comp.getY(),1f);
		paint = new Paint();
		paint.setARGB(255, 255, 0, 0);
		tail_end=(BitmapDrawable) game.getResources().getDrawable(R.drawable.tail_end);
		tail_start=(BitmapDrawable) game.getResources().getDrawable(R.drawable.tail_start);
		des = new Rect();
	}
	public JointNode getMainAngleNode(){
		return nodes[1];
	}
	public int drawOrder(){
		return 201;
	}
	public Tail(Game game, int drawOrder) {
		super(game, drawOrder);
		// TODO Auto-generated constructor stub
	}
	public void draw(DrawInfo drawInfo){
		super.draw(drawInfo);
		boolean amDestroyed=destroyed();
		/*for(int i=0;i<jointNum;i++){
			drawInfo.getCanvas().drawCircle(
					gameView.toScreenX(nodes[i].x), 
					gameView.toScreenY(nodes[i].y), 
					gameView.toScreen(0.1f), 
					paint);
		}*/
		drawComplexTail(drawInfo);
	}
	public void update(UpdateInfo updateInfo){
		super.update(updateInfo);
		nodes[0].x=comp.getX();
		nodes[0].y=comp.getY();
		float absV = comp.getAbsV();
		if(comp.getAbsV()>threshhold){
			nodes[2].angleJitter=(comp.getAbsV()-threshhold)*dir/10;
			nodes[1].angleJitter=(comp.getAbsV()-threshhold)*dir/20;
			dir*=-1;
		}else{
			nodes[1].angleJitter=0f;
		}
		for(int i=0;i<jointNum;i++){
			nodes[i].y+=0.1;
			nodes[i].update();
		}
		
	}
	@Override
	public void destroy(){
		if(destroyed) {return;}
		paint=null;
		comp=null;
		for(int i=0;i<jointNum;i++){
			nodes[i]=null;
		}
		nodes=null;
		super.destroy();
		
	}
	private void drawComplexTail(DrawInfo drawInfo){
		
		des.set(
				(int)gameView.toScreenX(comp.getX()-0.5),
				(int)gameView.toScreenY(comp.getY()-0.5),
				(int)gameView.toScreenX(comp.getX()+0.5),
				(int)gameView.toScreenY(comp.getY()+1.5)
				);
		drawInfo.getCanvas().save();
		drawInfo.getCanvas().rotate(
				(float)Math.toDegrees(nodes[1].a)+90, 
				gameView.toScreenX(comp.getX()), 
						gameView.toScreenY(comp.getY()));
		tail_start.setBounds(des);
		tail_start.draw(drawInfo.getCanvas());
		drawInfo.getCanvas().restore();
		des.set(
				(int)gameView.toScreenX(nodes[1].x-0.5),
				(int)gameView.toScreenY(nodes[1].y-0.5),
				(int)gameView.toScreenX(nodes[1].x+0.5),
				(int)gameView.toScreenY(nodes[1].y+1.5)
				);
		
		drawInfo.getCanvas().save();
		drawInfo.getCanvas().rotate(
				(float)Math.toDegrees(nodes[2].a)+90, 
				gameView.toScreenX(nodes[1].x), 
						gameView.toScreenY(nodes[1].y));
		tail_end.setBounds(des);
		tail_end.draw(drawInfo.getCanvas());
		drawInfo.getCanvas().restore();
	}

}
