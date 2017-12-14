package com.pandeagames.www.gutterballredux.gameObjects.debry;

import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;

import com.pandeagames.R;
import com.pandeagames.www.gutterballredux.Components.DrawableGameComponent;
import com.pandeagames.www.gutterballredux.gameControllers.BitmapPool;
import com.pandeagames.www.gutterballredux.gameControllers.Game;
import com.pandeagames.www.gutterballredux.gameObjects.GenericBody;
import com.pandeagames.www.gutterballredux.gameObjects.debry.Debry01;
import com.pandeagames.www.gutterballredux.gameObjects.debry.Debry02;
import com.pandeagames.www.gutterballredux.infoHolders.DrawInfo;
import com.pandeagames.www.gutterballredux.infoHolders.UpdateInfo;

import org.jbox2d.common.Vec2;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ccove on 12/15/2016.
 */

public class GrassBlockDebry extends DrawableGameComponent {

    protected List<GenericBody> bodies;
    private float[] positions;
    private float[] velocities;
    private float[] rotations;
    private BitmapDrawable grass;

    public  GrassBlockDebry(Game game, float x, float y, Vec2 linearVelocity){
        super(game);

        bodies = new ArrayList<>();

        Rect rect = getRect();
        positions = new float[getGrassCount() * 2];
        velocities = new float[getGrassCount() * 2];
        rotations = new float[getGrassCount()];

        for(int i = 0; i<getGrassCount()*2; i+=2){
            positions[i] = x+((float)Math.random() * rect.width() - (rect.width() / 2));
            positions[i+1] = y+((float)Math.random() * rect.height() - (rect.height() / 2));
            velocities[i] = getGrassVelocity() * ((positions[i] - x) / (rect.width() / 2));
            velocities[i+1] = getGrassVelocity() * ((positions[i+1] - y) / (rect.height() / 2));
        }
        for(int i = 0; i<getGrassCount(); i++){
            rotations[i] = (float) Math.random() * 360;
        }

        grass= BitmapPool.getBitmapDrawable(game.getResources(), R.drawable.grass);
    }
    public  GrassBlockDebry(Game game, float x, float y) {
        this(game, x, y ,new Vec2(0, 0));
    }
    @Override
    public void destroy(){
        super.destroy();

        positions = null;
        velocities = null;

        for (GenericBody body:bodies
             ) {
            body.destroy();
        }

        bodies.clear();
        bodies = null;
    }
    @Override
    public void update(UpdateInfo info){
        super.update(info);
        for(int i = 0; i<getGrassCount(); i+=2){
            positions[i] = positions[i] + velocities[i];
            positions[i+1] = positions[i+1] + velocities[i+1];
            velocities[i] = velocities[i] * getGrassFriction();
            velocities[i+1] = velocities[i+1] * getGrassFriction();
        }
    }

    @Override
    public void draw(DrawInfo info){
        super.draw(info);
        Rect des;
        for(int i = 0; i<getGrassCount(); i++){

            info.getCanvas().save();
            des = new Rect();
            des.set(
                    (int)gameView.toScreenX(positions[i]-0.05),
                    (int)gameView.toScreenY(positions[i+1]-0.5),
                    (int)gameView.toScreenX(positions[i]+0.15),
                    (int)gameView.toScreenY(positions[i+1]+0.5));
            grass.setBounds(des);
            info.getCanvas().rotate(rotations[i],des.exactCenterX(), des.exactCenterY());
            float totalVel = (float)Math.abs(velocities[i])+(float)Math.abs(velocities[i+1]);
            if(totalVel<0.5){
                grass.setAlpha((int)(255*(totalVel/0.5)));
            }else{
                grass.setAlpha(255);
            }

            grass.draw(info.getCanvas());
            info.getCanvas().restore();
        }
    }

    protected int getGrassCount(){
        return 15;
    }
    protected Rect getRect(){
        return new Rect(4, 4, 4, 4);
    }
    protected float getGrassVelocity(){
        return 1f;
    }
    protected float getGrassFriction(){
        return 0.8f;
    }
}
