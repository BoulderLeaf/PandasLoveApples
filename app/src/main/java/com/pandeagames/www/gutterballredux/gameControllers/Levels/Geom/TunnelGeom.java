package com.pandeagames.www.gutterballredux.gameControllers.Levels.Geom;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;

import com.pandeagames.www.gutterballredux.gameControllers.Game;

public class TunnelGeom extends LevelWalls {


    public TunnelGeom(Game game) {
        super(game);
        // TODO Auto-generated constructor stub

        drawBody = false;
    }
    @Override
    public void createBody()
    {
        createBody(world.createBody(bodyDef));
    }
    @Override
    public void createBody(Body body){
        PolygonShape groundBox = new PolygonShape();
        groundBox.setAsBox(3f,4.5f, new Vec2(18f,31.5f),0.0f);
        body.createFixture(groundBox,0.0f);
        //
        groundBox = new PolygonShape();
        groundBox.setAsBox(3f,4.5f, new Vec2(18f,19.5f),0.0f);
        body.createFixture(groundBox,0.0f);
        //
        groundBox = new PolygonShape();
        groundBox.setAsBox(3f,4.5f, new Vec2(18f,7.5f),0.0f);
        body.createFixture(groundBox,0.0f);
        //


        //
        groundBox = new PolygonShape();
        groundBox.setAsBox(3f,3f, new Vec2(3,27f),0.0f);
        body.createFixture(groundBox,0.0f);
        //
        groundBox = new PolygonShape();
        groundBox.setAsBox(3f,3f, new Vec2(3,15f),0.0f);
        body.createFixture(groundBox,0.0f);
        //
        groundBox = new PolygonShape();
        groundBox.setAsBox(4.5f,3f, new Vec2(4.5f,3f),0.0f);
        body.createFixture(groundBox,0.0f);
        //
        super.createBody(body);
    }

}
