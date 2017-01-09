package com.pandeagames.www.gutterballredux.gameObjects;

import android.graphics.Paint;
import android.graphics.Path;
import android.util.Log;

import com.pandeagames.www.gutterballredux.Components.AbstractGameComponent;
import com.pandeagames.www.gutterballredux.Components.BodyComponent;
import com.pandeagames.www.gutterballredux.Components.DrawableGameComponent;
import com.pandeagames.www.gutterballredux.Components.interfaces.IBodyCreationListener;
import com.pandeagames.www.gutterballredux.gameControllers.Game;
import com.pandeagames.www.gutterballredux.infoHolders.DrawInfo;
import com.pandeagames.www.gutterballredux.infoHolders.UpdateInfo;

import org.jbox2d.dynamics.Body;

/**
 * Created by ccove on 1/8/2017.
 */

public class Trail extends DrawableGameComponent implements IBodyCreationListener {

    private BodyComponent attached;
    private float[] x, y;
    private int[] a;
    private float lineWidth;
    private long[] t;
    private Paint paint;
    private int time, step, timeStep, size, lifetime;
    private Path path;
    private long currentTime;
    private boolean bodyCreated;


    public Trail(Game game, BodyComponent attached){
        this(game, attached, 1500);
    }
    public Trail(Game game, BodyComponent attached, int time){
        this(game, attached, 1500, 5);
    }
    public Trail(Game game, BodyComponent attached, int time, int timeStep){
        super(game);
        this.attached = attached;
        this.time = time;
        this.size = time / timeStep;

        x = new float[size];
        y = new float[size];
        a = new int[size];
        t = new long[size];

        lifetime = 2500;
        lineWidth = 0.3f;

        paint  = new Paint();
        paint.setARGB(255,255, 248, 255);

        path = new Path();

        attached.addBodyCreationListener(this);
    }
    @Override
    public void update(UpdateInfo info){
        super.update(info);

        if(!bodyCreated){
            return;
        }

        if(step<size){
            x[step] = attached.getX();
            y[step] = attached.getY();
            t[step] = info.getTime();
            a[step] = 100* (1 - (step / size));

            step++;

           // path.lineTo(gameView.toScreenX(attached.getX()), gameView.toScreenY(attached.getY()));
        }

        currentTime = info.getTime();

        if(info.getTime() - t[step-1] > lifetime){
            this.markDestroy();
        }
    }
    @Override
    public void draw(DrawInfo info){
        super.draw(info);
        path.reset();
        if(step<2){
            return;
        }
        int i = 0;
        float dx, dy, r, dt;
        double a, cos, sin;
        boolean moved = false;

        for( i = 1; i < step; i++)
        {
            r = (1 - (float)(currentTime - t[i]) / (float) lifetime) * lineWidth;

            if(r<0.05)
            {
                continue;
            }else if(!moved){
                path.moveTo(gameView.toScreenX(x[i]), gameView.toScreenY(y[i]));
                moved = true;
                continue;
            }

            dx = x[i] - x[i-1];
            dy = y[i] - y[i-1];
            a = Math.atan2(dy, dx) - Math.PI / 2;
            cos = Math.cos(a) * r;
            sin = Math.sin(a) * r;

            path.lineTo(gameView.toScreenX(x[i]+cos), gameView.toScreenY(y[i]+sin));
        }

        for( i = step -1; i > 0; i--)
        {
            r = (1 - (float)(currentTime - t[i]) / (float) lifetime) * lineWidth;

            if(r<0.05) continue;

            dx = x[i] - x[i-1];
            dy = y[i] - y[i-1];
            a = Math.atan2(dy, dx) + Math.PI / 2;
            cos = Math.cos(a) * r;
            sin = Math.sin(a) * r;

            path.lineTo(gameView.toScreenX(x[i]+cos), gameView.toScreenY(y[i]+sin));
        }

        info.getCanvas().drawPath(path, paint);
    }
    @Override
    public void bodyCreated(Body body)
    {
        // TODO Auto-generated method stub
        bodyCreated = true;
        //body.applyForce(force, point);
    }
}
