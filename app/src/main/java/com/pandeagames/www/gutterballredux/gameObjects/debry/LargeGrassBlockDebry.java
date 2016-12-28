package com.pandeagames.www.gutterballredux.gameObjects.debry;

import android.graphics.Rect;

import com.pandeagames.www.gutterballredux.gameControllers.Game;

import org.jbox2d.common.Vec2;

/**
 * Created by ccove on 12/18/2016.
 */

public class LargeGrassBlockDebry extends GrassBlockDebry {
    public LargeGrassBlockDebry(Game game, float x, float y, Vec2 linearVelocity)
    {
        super(game, x, y, linearVelocity);

        bodies.add(new Debry01(game,new Vec2(x, y), linearVelocity));
        bodies.add(new Debry02(game,new Vec2(x -1, y +1), linearVelocity));
    }
    public LargeGrassBlockDebry(Game game, float x, float y)
    {
        super(game, x, y);

        bodies.add(new Debry01(game,new Vec2(x, y+1)));
        bodies.add(new Debry02(game,new Vec2(x -1, y +1)));
    }
    @Override
    protected int getGrassCount(){
        return 50;
    }
    @Override
    protected Rect getRect(){
        return new Rect(0, 0, 8, 8);
    }
    @Override
    protected float getGrassVelocity(){
        return 0.5f;
    }
    @Override
    protected float getGrassFriction(){
        return 0.90f;
    }
}
