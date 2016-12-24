package com.pandeagames.www.gutterballredux.gameObjects;

import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.pandeagames.www.gutterballredux.Components.BodyComponent;
import com.pandeagames.www.gutterballredux.Components.DrawableComponent;
import com.pandeagames.www.gutterballredux.Components.DrawableGameComponent;
import com.pandeagames.www.gutterballredux.gameControllers.Game;
import com.pandeagames.www.gutterballredux.infoHolders.DrawInfo;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

import java.util.List;

/**
 * Created by ccove on 12/15/2016.
 */

public class GenericBody extends BodyComponent {

    protected Rect drawRect;
    protected Vec2[] verticies;
    protected int count;
    protected BitmapDrawable asset;
    private float width = 0, height  = 0;

    public GenericBody(Game game, Rect drawRect, BitmapDrawable asset, Vec2[] verticies, int count, Vec2 pos){
        super(game);

        this.verticies = verticies;
        this.drawRect = drawRect;
        this.count = count;
        this.asset = asset;

        bodyDef.position.set(pos);
    }

    public GenericBody(Game game, Vec2 pos){
        super(game);

        bodyDef.position.set(pos);
    }

    public GenericBody(Game game, Vec2 pos, Vec2 velocity){
        this(game, pos);

        bodyDef.linearVelocity.set(velocity);
    }

    public GenericBody(Game game, Rect drawRect, BitmapDrawable asset, Vec2[] verticies, int count, Vec2 pos, Vec2 velocity){
        this(game, drawRect, asset, verticies, count, pos);

        bodyDef.linearVelocity.set(velocity);
    }

    @Override
    public void createBody(Body body){
        PolygonShape shape = new PolygonShape();

        for(int i = 0; i<count; i++){
            if(width<verticies[i].x){
                width = verticies[i].x;
            }
            if(height<verticies[i].y){
                height = verticies[i].y;
            }
        }

        //center the vertcies
        for(int i = 0; i<count; i++){
            verticies[i].set(verticies[i].x - width / 2, verticies[i].y - height / 2);
        }

        shape.set(verticies, count);

        FixtureDef  fixDef = new FixtureDef();

        fixDef.shape = shape;
        fixDef.density = 10;
        fixDef.friction = 0.5f;
        fixDef.restitution=0.5f;

        body.createFixture(fixDef);

        super.createBody(body);
    }

    @Override
    public void draw(DrawInfo drawInfo){
        super.draw(drawInfo);

        if(getBody() == null) {return;}

        float x=getX();
        float y=getY();

        Rect des = new Rect();

        des.set(
                (int)gameView.toScreenX(x- width/ 2),
                (int)gameView.toScreenY(y- height/ 2),
                (int)gameView.toScreenX(x- width/ 2)+drawRect.width(),
                (int)gameView.toScreenY(y- height/ 2)+drawRect.height());

        drawInfo.getCanvas().save();
        drawInfo.getCanvas().rotate(
                (float)(getBody().getAngle()/(Math.PI/180)),
                gameView.toScreenX(x),
                gameView.toScreenY(y));

        asset.setBounds(des);
        asset.draw(drawInfo.getCanvas());

        drawInfo.getCanvas().restore();
    }
}
