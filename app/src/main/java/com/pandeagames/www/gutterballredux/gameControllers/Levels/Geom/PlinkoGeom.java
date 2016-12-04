package com.pandeagames.www.gutterballredux.gameControllers.Levels.Geom;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.FixtureDef;

import com.pandeagames.www.gutterballredux.gameControllers.Game;

public class PlinkoGeom extends LevelWalls {

	public PlinkoGeom(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void createBody(Body body){
		 CircleShape dynamicBox;
		 int r=1;
		 float dist=4f;
		 float offsetX=7f;
		 float offsetY=12f;
		 float rowOffset = 2f;
		 float activeRowOffset;
		 for(int i=0;i<6;i++){
			 for(int j=0;j<8;j++) {
				 activeRowOffset = j % 2 == 0 ? 0:rowOffset;
				 dynamicBox = new CircleShape();
			        //dynamicBox.setAsBox(1.0f, 1.0f);
				 dynamicBox.m_p.set(i*dist+offsetX+activeRowOffset , j*dist+offsetY);
			       dynamicBox.m_radius=r;
			       body.createFixture(dynamicBox,0.0f);
			 }
		 }
		 
		 PolygonShape groundBox = new PolygonShape();
		 groundBox.setAsBox(0.5f,22f, new Vec2(6.5f,29f),0.0f);
		 body.createFixture(groundBox,0.0f);
		
		 groundBox = new PolygonShape();
		 
		 Vec2[] v = new Vec2[3];
		 v[2]=new Vec2(1,1);
		 v[1]=new Vec2(1,8);
		 v[0]=new Vec2(8,1);
		 groundBox.set(v, 3);
		body.createFixture(groundBox,1f);
		 
		super.createBody(body);
	}
}
