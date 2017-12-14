package com.pandeagames.www.gutterballredux.gameObjects.scripts;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.pandeagames.R;
import com.pandeagames.www.gutterballredux.gameControllers.BitmapPool;
import com.pandeagames.www.gutterballredux.gameControllers.Game;
import com.pandeagames.www.gutterballredux.gameObjects.BreakableBlock;
import com.pandeagames.www.gutterballredux.gameObjects.CollisionGroups;
import com.pandeagames.www.gutterballredux.gameObjects.debry.GrassBlockDebry;
import com.pandeagames.www.gutterballredux.gameObjects.debry.SmallGrassBlockDebry;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.json.JSONObject;

/**
 * Created by ccove on 12/10/2016.
 */

public class BreakableBushSmall extends BreakableBlock {

    public BreakableBushSmall(Game game, JSONObject objectJSON) {
        this(game, new Vec2());
    }

    public BreakableBushSmall(Game game) {
        this(game, new Vec2());
    }

    public BreakableBushSmall(Game game, Vec2 pos){
        super(game, pos);

        this.collisionLayer = CollisionGroups.ACTOR;

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inDither = false;
        options.inJustDecodeBounds = false;
        options.inSampleSize = 1;
        options.mCancel = false;
        options.inPreferredConfig = Bitmap.Config.RGB_565;

        artMainBitmap = BitmapPool.getBitmap(game.getResources(), R.drawable.breakable_blocks_full, options);

        r = 1.5f;

        friction = 0.8f;
        shakeAmount = 0.1f;
    }
    @ Override
    public void createBody(Body body){
        PolygonShape groundBox = new PolygonShape();
        groundBox.setAsBox(1.45f,1.45f);
        body.createFixture(groundBox,0.0f);

        super.createBody(body);
    }
    protected void onBreak(){
        super.onBreak();
        GrassBlockDebry debry = new SmallGrassBlockDebry(this.game, getX(), getY());
    }
}
