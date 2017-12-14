package com.pandeagames.www.gutterballredux.gameObjects.debry;

import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;

import com.pandeagames.R;
import com.pandeagames.www.gutterballredux.Components.BodyComponent;
import com.pandeagames.www.gutterballredux.gameControllers.BitmapPool;
import com.pandeagames.www.gutterballredux.gameControllers.Game;
import com.pandeagames.www.gutterballredux.gameObjects.GenericBody;

import org.jbox2d.common.Vec2;

/**
 * Created by ccove on 12/18/2016.
 */

public class Debry01 extends AbstractDebry {
    public Debry01(Game game, Vec2 pos, Vec2 velocity){
        super(game, pos, velocity);

        drawBody=false;

        drawRect = new Rect();
        verticies =  new Vec2[8];
        count = 8;
        asset = BitmapPool.getBitmapDrawable(game.getResources(), R.drawable.debry_01);

        verticies[0] = new Vec2(5.9f, 6.8f);
        verticies[1] = new Vec2(5.54f, 7.1f);
        verticies[2] = new Vec2(1.43f, 3.14f);
        verticies[3] = new Vec2(0.05f, 1.07f);
        verticies[4] = new Vec2(0.11f, 0.11f);
        verticies[5] = new Vec2(3.77f, 0.81f);
        verticies[6] = new Vec2(4.66f, 2.33f);
        verticies[7] = new Vec2(3.33f, 4.30f);

        drawRect.set(
                (int)gameView.toScreenX(0),
                (int)gameView.toScreenY(0),
                (int)gameView.toScreenX(6.0),
                (int)gameView.toScreenY(7.25)
        );
    }
    public Debry01(Game game, Vec2 pos) {
        this(game, pos, new Vec2());
    }
}
