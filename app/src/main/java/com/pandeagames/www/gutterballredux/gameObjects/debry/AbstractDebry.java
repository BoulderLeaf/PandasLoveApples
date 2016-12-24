package com.pandeagames.www.gutterballredux.gameObjects.debry;

import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;

import com.pandeagames.www.gutterballredux.gameControllers.Game;
import com.pandeagames.www.gutterballredux.gameObjects.GenericBody;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

/**
 * Created by ccove on 12/18/2016.
 */

public abstract class AbstractDebry extends GenericBody {
    public AbstractDebry(Game game, Rect drawRect, BitmapDrawable asset, Vec2[] verticies, int count, Vec2 pos){
        super(game, drawRect, asset, verticies, count, pos);
        this.applyBOdyDefinition(bodyDef);
    }

    public AbstractDebry(Game game, Vec2 pos){
        super(game, pos);
        this.applyBOdyDefinition(bodyDef);
    }

    public AbstractDebry(Game game, Vec2 pos, Vec2 velocity){
        super(game, pos, velocity);
        this.applyBOdyDefinition(bodyDef);
    }

    public AbstractDebry(Game game, Rect drawRect, BitmapDrawable asset, Vec2[] verticies, int count, Vec2 pos, Vec2 velocity){
        super(game, drawRect, asset, verticies, count, pos, velocity);
        this.applyBOdyDefinition(bodyDef);
    }
    private void applyBOdyDefinition(BodyDef bodyDef) {
        bodyDef.type = BodyType.DYNAMIC;
        bodyDef.fixedRotation = false;
        bodyDef.angularDamping = 0.3f;
        bodyDef.angularDamping = 0.3f;
        bodyDef.allowSleep = false;
        bodyDef.linearDamping = 0.1f;
    }
}
