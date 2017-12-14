package com.pandeagames.www.gutterballredux.gameObjects.debry;

import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;

import com.pandeagames.R;
import com.pandeagames.www.gutterballredux.gameControllers.BitmapPool;
import com.pandeagames.www.gutterballredux.gameControllers.Game;
import com.pandeagames.www.gutterballredux.gameObjects.GenericBody;

import org.jbox2d.common.Vec2;

/**
 * Created by ccove on 12/18/2016.
 */

public class Debry02 extends AbstractDebry {
    public Debry02(Game game, Vec2 pos, Vec2 velocity){
        super(game, pos, velocity);

        drawBody=false;

        drawRect = new Rect();
        count = 7;
        verticies =  new Vec2[count];
        asset = BitmapPool.getBitmapDrawable(game.getResources(), R.drawable.debry_02);

        verticies[0] = new Vec2(0.52f, 1.9f);
        verticies[1] = new Vec2(0.02f, 1.1f);
        verticies[2] = new Vec2(0.02f, 0.6f);
        verticies[3] = new Vec2(0.68f, 0.02f);
        verticies[4] = new Vec2(0.76f, 0.36f);
        verticies[5] = new Vec2(0.26f, 0.91f);
        verticies[6] = new Vec2(0.71f, 1.82f);

        drawRect.set(
                (int)gameView.toScreenX(0),
                (int)gameView.toScreenY(0),
                (int)gameView.toScreenX(0.8),
                (int)gameView.toScreenY(2)
        );
    }
    public Debry02(Game game, Vec2 pos) {
        this(game, pos, new Vec2());
    }
}
