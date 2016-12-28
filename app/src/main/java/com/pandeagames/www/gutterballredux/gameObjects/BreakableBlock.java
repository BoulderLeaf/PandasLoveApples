package com.pandeagames.www.gutterballredux.gameObjects;

import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;

import com.pandeagames.www.gutterballredux.Components.BodyComponent;
import com.pandeagames.www.gutterballredux.gameControllers.Game;
import com.pandeagames.www.gutterballredux.infoHolders.DrawInfo;
import com.pandeagames.www.gutterballredux.infoHolders.UpdateInfo;

import org.jbox2d.common.Vec2;

import toxi.geom.Vec2D;

/**
 * Created by ccove on 12/15/2016.
 */

public class BreakableBlock extends Breakable {

    protected Vec2D drawOffset;
    protected double shakeAmount = 1;
    protected double maxCollideVelocity = 20;
    protected float shake;
    protected float friction = 0.9f;
    protected BitmapDrawable artMain;
    protected Bitmap artMainBitmap;
    protected float r = 2;
    protected float radiusMod = 0.2f;
    private Paint paint;

    public BreakableBlock(Game game, Vec2 pos){
        super(game, pos);

        bodyDef.allowSleep = true;
        drawOffset = new Vec2D();

        paint=new Paint();
        paint.setARGB(255, 255, 0, 0);
    }
    @Override
    protected void onCollide(BodyComponent other){
        super.onCollide(other);

        collideVelocity = other.getBody().getLinearVelocity().clone();

        shake = (float)(maxCollideVelocity / Math.min(other.getVelocity(), maxCollideVelocity) * shakeAmount);
    }
    @Override
    public void update(UpdateInfo updateInfo){
        super.update(updateInfo);

        drawOffset.clear();
        drawOffset.jitter((float)shake);

        shake = shake * friction;
    }
    @Override
    public void draw(DrawInfo info){
        super.draw(info);
        float x=getX() + drawOffset.x;
        float y=getY() + drawOffset.y;
       /* Rect des = new Rect();

        des.set((int)gameView.toScreenX(x-(r +radiusMod)),
                (int)gameView.toScreenY(y-(r +radiusMod)),
                (int)gameView.toScreenX(x+ (r +radiusMod)),
                (int)gameView.toScreenY(y+ (r + radiusMod)));
        artMain.setBounds(des);
        artMain.draw(info.getCanvas());*/

        Rect des = new Rect();
        des.set((int)gameView.toScreenX(x-(r +radiusMod)),
                (int)gameView.toScreenY(y-(r +radiusMod)),
                (int)gameView.toScreenX(x+ (r +radiusMod)),
                (int)gameView.toScreenY(y+ (r + radiusMod)));
        Rect src = new Rect();
        src.set(0,
                0,
                artMainBitmap.getWidth(),
                artMainBitmap.getHeight());


        info.getCanvas().drawBitmap(artMainBitmap, src, des, paint);
    }
}
