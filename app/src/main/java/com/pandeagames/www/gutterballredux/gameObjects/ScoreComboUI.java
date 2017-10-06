package com.pandeagames.www.gutterballredux.gameObjects;

import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;

import com.pandeagames.www.gutterballredux.Components.DrawableGameComponent;
import com.pandeagames.www.gutterballredux.gameControllers.Game;
import com.pandeagames.www.gutterballredux.gameControllers.Simulation;
import com.pandeagames.www.gutterballredux.infoHolders.DrawInfo;
import com.pandeagames.www.gutterballredux.infoHolders.IScoreChangeListener;
import com.pandeagames.www.gutterballredux.infoHolders.StageScore;
import com.pandeagames.www.gutterballredux.infoHolders.UpdateInfo;

/**
 * Created by ccove on 10/3/2017.
 */

public class ScoreComboUI extends DrawableGameComponent implements IScoreChangeListener{

    private Paint textPaint;

    private StageScore _score;

    private int _displayScore;
    private int _targetScore;

    private float comboScaleVelocity = 0;
    private float comboScaleGravity = 0.05f;
    private float comboGroundScale = 1;
    private float comboScale = comboGroundScale;

    private float comboSize = 2;
    private float scoreSize = 2;

    private Typeface _typeFace;

    private int padding = 1;

    private Rect dest;

    public ScoreComboUI(Game game, StageScore score) {
        super(game);

        _score = score;

        textPaint=new Paint();
        textPaint.setARGB(255,159, 184, 58);
        _typeFace = Typeface.createFromAsset(game.getAssets(), "typefaces/LondrinaSolid-Regular.otf");
        textPaint.setTypeface(_typeFace);

        score.addScoreChangeListener(this);

        dest = new Rect();
    }

    public void scoreChange(int pandas, int basePoints, int totalPoints, int apples, int combo){
        comboScaleVelocity = 1;
        _targetScore = basePoints;
    }

    public void update(UpdateInfo info){
        super.update(info);

        comboScaleVelocity = comboScaleVelocity - comboScaleGravity;
        comboScale+=comboScaleVelocity;

        if(comboScale < comboGroundScale) {
            comboScale = comboGroundScale;
            comboScaleVelocity = 0;
        }

        _displayScore = _displayScore + (_targetScore - _displayScore) / 5;
    }

    public void draw(DrawInfo info){
        super.draw(info);

        String text = String.valueOf(_displayScore);

        textPaint.setTextSize(gameView.toScreen(scoreSize));
        textPaint.getTextBounds(text,0, text.length(), dest);

        info.getCanvas().drawText(
                text,
                gameView.toScreenX(padding),
                gameView.toScreenY(Simulation.SIMULATION_HEIGHT - padding) - dest.height(),
                textPaint);

       /* if(comboScale > comboGroundScale) {
            text = String.valueOf(_);

            textPaint.setTextSize(gameView.toScreen(scoreSize));
            textPaint.getTextBounds(text,0, text.length(), dest);

            info.getCanvas().drawText(
                    text,
                    gameView.toScreenX(padding),
                    gameView.toScreenY(Simulation.SIMULATION_HEIGHT - padding) - dest.height(),
                    textPaint);
        }*/
    }
}
