package com.pandeagames.www.gutterballredux.gameObjects;

import android.accessibilityservice.GestureDescription;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;

import com.pandeagames.www.gutterballredux.Components.DrawableGameComponent;
import com.pandeagames.www.gutterballredux.gameControllers.Game;
import com.pandeagames.www.gutterballredux.gameControllers.Simulation;
import com.pandeagames.www.gutterballredux.infoHolders.DrawInfo;
import com.pandeagames.www.gutterballredux.infoHolders.UpdateInfo;

/**
 * Created by ccove on 10/3/2017.
 */

public class ComboDisplay extends DrawableGameComponent {

    private Paint textPaint;
    private Paint strokeTextPaint;
    private Typeface _typeFace;
    private int padding = 1;

    private float fontSize = 3;
    private float strokeSize = 0.5f;
    private float scale = 1;
    private float scaleVelocity = 0;
    private float scaleForce = -0.001f;
    private int combo;
    private String text;

    private Rect dest;

    public ComboDisplay(Game game, float x, float y, int combo) {
        super(game);

        textPaint=new Paint();
        textPaint.setARGB(255, 255, 52, 34);
        _typeFace = Typeface.createFromAsset(game.getAssets(), "typefaces/LondrinaSolid-Regular.otf");
        textPaint.setTypeface(_typeFace);

        strokeTextPaint=new Paint();
        strokeTextPaint.setARGB(255,159, 184, 58);
        strokeTextPaint.setTypeface(_typeFace);
        strokeTextPaint.setStrokeWidth(gameView.toScreen(0.5f));
        strokeTextPaint.setStrokeWidth(20);
        strokeTextPaint.setStyle(Paint.Style.STROKE);


        dest = new Rect();

        text =  "x"+String.valueOf(combo+1);
        textPaint.setTextSize(gameView.toScreen(fontSize));
        textPaint.getTextBounds(text,0, text.length(), dest);

        dest.inset(dest.left*-1, dest.top*-1);

        //dest.set
        dest.set((int)(gameView.toScreen(x)- dest.width() / 2),
                (int)(gameView.toScreen(y) + dest.height() / 2),
                (int)(gameView.toScreen(x) + dest.width() / 2),
                (int)(gameView.toScreen(y) - dest.height() / 2));

        if(dest.left < gameView.toScreen(padding)) {
            dest.offsetTo((int)gameView.toScreen(padding), dest.top);
        }

        if(dest.right > gameView.toScreen(Simulation.SIMULATION_WIDTH) - gameView.toScreen(padding)) {
            dest.offsetTo((int)((gameView.toScreen(Simulation.SIMULATION_WIDTH) - dest.width()) - gameView.toScreen(padding)), dest.top);
        }

        if(dest.top < gameView.toScreen(padding)) {
            dest.offsetTo(dest.left, (int)gameView.toScreen(padding));
        }

        if(dest.bottom > gameView.toScreen(Simulation.SIMULATION_HEIGHT) - gameView.toScreen(padding)) {
            dest.offsetTo(dest.left, (int)((gameView.toScreen(Simulation.SIMULATION_HEIGHT) - dest.height()) - gameView.toScreen(padding)));
        }

        this.setX(gameView.toWorld(dest.left + dest.width() / 2));
        this.setY(gameView.toWorld(dest.top + dest.height() / 2));
    }

    public void update(UpdateInfo info){
        super.update(info);

        scaleVelocity += scaleForce;
        scale += scaleVelocity;

        if(scale < 0)
        {
            this.markDestroy();
        }
    }

    public void draw(DrawInfo info) {
        super.draw(info);

        strokeTextPaint.setTextSize(gameView.toScreen(fontSize * scale));
        strokeTextPaint.setStrokeWidth(gameView.toScreen(strokeSize * scale));
        textPaint.setTextSize(gameView.toScreen(fontSize * scale));
        textPaint.getTextBounds(text,0, text.length(), dest);

        info.getCanvas().drawText(
                text,
                (int)gameView.toScreenX(getX()) - dest.width() / 2,
                (int)gameView.toScreenY(getY()) - dest.height() / 2,
                strokeTextPaint);

        info.getCanvas().drawText(
                text,
                (int)gameView.toScreenX(getX()) - dest.width() / 2,
                (int)gameView.toScreenY(getY()) - dest.height() / 2,
                textPaint);
    }
}
