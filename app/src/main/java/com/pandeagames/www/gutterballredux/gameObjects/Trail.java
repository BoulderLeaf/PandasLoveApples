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
    private long[] t;
    private Paint paint;
    private int time, step, timeStep, size;
    private Path path;
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

        paint  = new Paint();
        paint.setARGB(255,255, 248, 206);

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
        if(step == size){
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
        path.moveTo(gameView.toScreenX(x[0]), gameView.toScreenY(y[0]));
        paint.setAlpha((int)(100f * (1f - (float)((float)step / (float)size))));

        for( i = 0; i < step; i++)
        {
            path.lineTo(gameView.toScreenX(x[i]+0.2), gameView.toScreenY(y[i]));
        }
        for( i = step-1; i >= 0; i--)
        {
            path.lineTo(gameView.toScreenX(x[i]-0.2), gameView.toScreenY(y[i]));
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
