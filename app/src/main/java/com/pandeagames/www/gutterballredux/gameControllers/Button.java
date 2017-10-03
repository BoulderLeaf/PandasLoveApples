package com.pandeagames.www.gutterballredux.gameControllers;

import android.content.DialogInterface;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

import com.pandeagames.BuildConfig;
import com.pandeagames.www.gutterballredux.Components.DrawableGameComponent;
import com.pandeagames.www.gutterballredux.Components.interfaces.IUserInputComponent;
import com.pandeagames.www.gutterballredux.Drawing.DebugDraw;
import com.pandeagames.www.gutterballredux.infoHolders.DrawInfo;

/**
 * Created by ccove on 9/4/2017.
 */

public class Button extends DrawableGameComponent implements IUserInputComponent {
    private IButtonPressListener listener;
    private Paint paint;
    protected String text;
    protected Paint textPaint;
    public float padding = 0.5f;
    public float fontSize = 0.5f;
    private float _r = 0;
    private Typeface _typeFace;
    private Boolean _isPressed;

    public Button(Game game) {
        this(game, "Button");
    }

    public Button(Game game, String text) {
        super(game);
        activity.addInputComponent(this);

        this.text = text;

        paint=new Paint();
        paint.setARGB(255, 235,235, 235);

        textPaint=new Paint();
        textPaint.setARGB(255, 0,0, 0);

        _typeFace = Typeface.createFromAsset(game.getAssets(), "typefaces/LondrinaSolid-Regular.otf");
        textPaint.setTypeface(_typeFace);
    }

    @Override
    public void destroy() {
        super.destroy();

        this.textPaint = null;
        this.paint = null;
        this.listener = null;
    }

    public void onButtonPress(IButtonPressListener listener)
    {
        this.listener = listener;
    }

    protected void onButtonAction()
    {
        if(this.listener != null)
        {
            this.listener.onButtonPress(this);
        }
    }

    public void draw(DrawInfo drawInfo) {
        super.draw(drawInfo);

        textPaint.setTextSize(gameView.toScreenX(fontSize));

        float screenFontSize  = gameView.toScreenX(fontSize);

        Rect bounds = new Rect();

        textPaint.getTextBounds(text,0, text.length(), bounds);

        this.setWidth(gameView.toWorldX(bounds.width()) + padding * 2);
        this.setHeight(gameView.toWorld(bounds.height())+ padding * 2);



            drawInfo.getCanvas().drawRoundRect(
                    gameView.toScreenX(this.getX()),
                    gameView.toScreenY(this.getY()),
                    gameView.toScreenX(this.getX() + this.getWidth()),
                    gameView.toScreenY(this.getY() + this.getHeight()),
                    gameView.toScreen(_r),
                    gameView.toScreen(_r),
                    paint
            );

           /* drawInfo.getCanvas().drawText(
                    text,
                    (int)gameView.toScreenX(this.getX() + padding),
                    (int)gameView.toScreenY(this.getY() + this.getHeight() + padding),
                    textPaint);*/

        drawInfo.getCanvas().drawText(
                text,
                (int)gameView.toScreenX(this.getX() + padding) + bounds.left,
                (int)gameView.toScreenY(this.getY() + padding) - bounds.top,
                textPaint);

    };

    public void onTouch(View v, MotionEvent event) {

        if(event.getAction() != MotionEvent.ACTION_UP)
        {
            return;
        }

        double x =  event.getRawX();
        double y =  event.getRawY();

        if(x < gameView.toScreenX(this.getX()) + gameView.toScreenX(this.getWidth()) &&
                x > gameView.toScreenX(this.getX())&&
                y < gameView.toScreenY(this.getY())+ gameView.toScreenY(this.getHeight()) &&
                y > gameView.toScreenY(this.getY()))
        {
            this.onButtonAction();
        }
    }

    @Override
    public void onLongClick(View v) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        // TODO Auto-generated method stub

    }
    @Override
    public void onCreateContextMenu() {
        // TODO Auto-generated method stub

    }
    @Override
    public void onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub

    }

    @Override
    public int drawOrder() {
        // TODO Auto-generated method stub
        return 204;
    }

    public void setButtonColor(int a, int r, int g, int b) {
        this.paint.setARGB(a, r, g, b);
    }

    public void setButtonTextColor(int a, int r, int g, int b) {
        this.textPaint.setARGB(a, r, g, b);
    }
    public void setRadius(float r){
        this._r = r;
    }
}
