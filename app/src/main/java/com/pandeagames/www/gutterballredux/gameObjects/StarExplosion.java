package com.pandeagames.www.gutterballredux.gameObjects;

import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.content.res.ResourcesCompat;

import com.pandeagames.R;
import com.pandeagames.www.gutterballredux.Components.DrawableGameComponent;
import com.pandeagames.www.gutterballredux.gameControllers.Game;
import com.pandeagames.www.gutterballredux.gameControllers.Simulation;
import com.pandeagames.www.gutterballredux.infoHolders.DrawInfo;
import com.pandeagames.www.gutterballredux.infoHolders.UpdateInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ccove on 11/19/2017.
 */

public class StarExplosion extends DrawableGameComponent {
    private List<StarObj> stars;
    private int numStars;
    private int baseStarCount = 2;
    private BitmapDrawable starArt;
    private float friction = 0.90f;
    private Rect dest;
    public StarExplosion(Game game,float x, float y, int drawOrder, int combo) {
        super(game, drawOrder);

        stars = new ArrayList<StarObj>();
        dest = new Rect();
        starArt = (BitmapDrawable) ResourcesCompat.getDrawable(game.getResources(), R.drawable.star, null);

        StarObj star;

        this.setPos(x, y);

        numStars = baseStarCount + combo * 3;
        float f = 0.5f;
        float r = 1f;
        float angleOffset = 0;
        float size = 1;
        for(int i =0; i<numStars; i++) {
            star = new StarObj();
            star.size = 1;
            stars.add(star);

            float a = (float)(Math.PI / 180) * ((360/numStars) * i + angleOffset);
            float cos = (float)Math.cos(a);
            float sin = (float)Math.sin(a);

            star.x = getX() + cos * r;
            star.y = getY() + sin * r;

            star.vx = cos*f;
            star.vy = sin*f;
        }
    }

    @Override
    public void draw(DrawInfo info){
        super.draw(info);

        for(StarObj star:stars) {
            dest.set(
                    (int)gameView.toScreenX(star.x - star.size/2),
                    (int)gameView.toScreenY(star.y - star.size / 2),
                    (int)gameView.toScreenX(star.x + star.size / 2),
                    (int)gameView.toScreenY(star.y+star.size/2)
            );

            starArt.setBounds(dest);
            starArt.draw(info.getCanvas());
        }
    }

    @Override
    public void update(UpdateInfo info) {
        super.update(info);

        for(StarObj star:stars) {
            star.x+=star.vx;
            star.y+=star.vy;

            star.vx = star.vx * friction;
            star.vy = star.vy * friction;

            float totalSpeed = Math.abs(star.vx) + Math.abs(star.vy);

            if(totalSpeed>0.05)
            {
                star.size = star.size * (1+totalSpeed / 10);
            }
            else
            {
                star.size = star.size * (1+star.scaleDown);
                star.scaleDown = star.scaleDown - 0.05f;

                if(star.size < 0.005)
                {
                    this.markDestroy();
                }
            }
        }
    }

    private class StarObj {
        public float x, y, vx, vy, size, scaleDown;
        public StarObj() {
        }
    }
}


