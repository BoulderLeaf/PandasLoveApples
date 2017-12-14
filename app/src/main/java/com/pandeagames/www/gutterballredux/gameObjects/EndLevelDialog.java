package com.pandeagames.www.gutterballredux.gameObjects;

import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;

import com.pandeagames.R;
import com.pandeagames.www.gutterballredux.Components.DrawableGameComponent;
import com.pandeagames.www.gutterballredux.droidControllers.GutterBallApp;
import com.pandeagames.www.gutterballredux.gameControllers.BitmapPool;
import com.pandeagames.www.gutterballredux.gameControllers.Button;
import com.pandeagames.www.gutterballredux.gameControllers.Game;
import com.pandeagames.www.gutterballredux.gameControllers.IButtonPressListener;
import com.pandeagames.www.gutterballredux.gameControllers.Simulation;
import com.pandeagames.www.gutterballredux.gameControllers.levelManagment.LevelManager;
import com.pandeagames.www.gutterballredux.infoHolders.DrawInfo;
import com.pandeagames.www.gutterballredux.infoHolders.StageScore;
import com.pandeagames.www.gutterballredux.infoHolders.UpdateInfo;

/**
 * Created by ccove on 9/4/2017.
 */

public class EndLevelDialog extends DrawableGameComponent implements IButtonPressListener {

    private Button levelSelectButton, nextlevelButton, retryButton;
    private Paint paint, textPaint;
    private float padding = 1f;
    private float paddingMinor = 0.8f;
    private BitmapDrawable panda;
    private int pandas, par, basePoints, totalPoints, bonus;
    private Rect dest;
    private Typeface _typeFace;
    private Button buttonPressed = null;

    public EndLevelDialog(Game game)
    {
        super(game);

        paint=new Paint();
        paint.setARGB(210,255, 248, 255);

        textPaint=new Paint();
        textPaint.setARGB(255,159, 184, 58);
        _typeFace = Typeface.createFromAsset(game.getAssets(), "typefaces/LondrinaSolid-Regular.otf");
        textPaint.setTypeface(_typeFace);

        this.levelSelectButton = new Button(game, "Level Select");
        this.levelSelectButton.onButtonPress(this);
        this.levelSelectButton.fontSize = 1.5f;
        this.levelSelectButton.setPos(-20, -20);
        this.levelSelectButton.setButtonColor(255,159, 184, 58);
        this.levelSelectButton.setButtonTextColor(255, 255, 242, 255);
        this.levelSelectButton.setRadius(0.5f);

        this.retryButton = new Button(game, "Retry");
        this.retryButton.onButtonPress(this);
        this.retryButton.fontSize = 1.5f;
        this.retryButton.setPos(-20, -20);
        this.retryButton.setButtonColor(255,159, 184, 58);
        this.retryButton.setButtonTextColor(255, 255, 242, 255);
        this.retryButton.setRadius(0.5f);

        this.nextlevelButton = new Button(game, "Next Stage!");
        this.nextlevelButton.onButtonPress(this);
        this.nextlevelButton.fontSize = 1.5f;
        this.nextlevelButton.setPos(-20, -20);
        this.nextlevelButton.setButtonColor(255, 255, 52, 34);
        this.nextlevelButton.setButtonTextColor(255, 255, 242, 242);
        this.nextlevelButton.setRadius(0.5f);

        dest = new Rect();

        panda = BitmapPool.getBitmapDrawable(game.getResources(), R.drawable.panda_green);
    }
    public void onButtonPress(Button button) {
        buttonPressed = button;
    }

    public void update(UpdateInfo info)
    {
        super.update(info);

        float totalHeight = this.levelSelectButton.getHeight();
        totalHeight += padding;
        totalHeight += this.nextlevelButton.getHeight();

        this.nextlevelButton.setY(Simulation.SIMULATION_HEIGHT / 2 - totalHeight/  2);
        this.levelSelectButton.setY(this.nextlevelButton.getY()+this.nextlevelButton.getHeight() + padding);
        this.retryButton.setY(this.levelSelectButton.getY()+this.levelSelectButton.getHeight() + padding);

        this.levelSelectButton.setX(Simulation.SIMULATION_WIDTH / 2 - this.levelSelectButton.getWidth() / 2);
        this.nextlevelButton.setX(Simulation.SIMULATION_WIDTH / 2 - this.nextlevelButton.getWidth() / 2);
        this.retryButton.setX(Simulation.SIMULATION_WIDTH / 2 - this.retryButton.getWidth() / 2);

        if(buttonPressed == this.levelSelectButton) {
            game.finish();
        }else if(buttonPressed == this.retryButton){
            game.loadLevel(game.getLevelId());
            //Intent game = new Intent(this, Game.class);
            //game.putExtra("level", selectedLevel);
        }else if(buttonPressed == nextlevelButton){
            LevelManager levelManager = ((GutterBallApp)game.getApplicationContext()).getLevelManager();
            game.loadLevel(levelManager.getNextLevel(game.getLevelId()).getId());
        }

        buttonPressed = null;
    }

    public void draw(DrawInfo drawInfo) {
        super.draw(drawInfo);

        float midX = Simulation.SIMULATION_WIDTH / 2;

        dest.set(
                (int)gameView.toScreenX(midX - (12 * 0.66) / 2),
                (int)gameView.toScreenY(Simulation.SIMULATION_HEIGHT  - 10),
                (int)gameView.toScreenX(midX  + (12 * 0.66) / 2),
                (int)gameView.toScreenY(Simulation.SIMULATION_HEIGHT + 2)
        );


        drawInfo.getCanvas().drawRect(
                0,
                0,
                gameView.getRight(),
                gameView.getBottom(),
                paint
        );

        panda.setBounds(dest);
        panda.draw(drawInfo.getCanvas());

        String text = "Pandas";
        int bottom = 0;

        textPaint.setTextSize(gameView.toScreen(1.25f));
        textPaint.getTextBounds(text,0, text.length(), dest);
        bottom = (int)gameView.toScreenY(3) + dest.height();

        drawInfo.getCanvas().drawText(
                text,
                (int)gameView.toScreenX(midX) - dest.width() / 2,
                (int)gameView.toScreenY(3),
                textPaint);

        text = String.valueOf(pandas);

        textPaint.setTextSize(gameView.toScreen(2f));
        textPaint.getTextBounds(text,0, text.length(), dest);
        bottom = (int)(bottom + gameView.toScreen(paddingMinor) + dest.height());

        drawInfo.getCanvas().drawText(
                text,
                (int)gameView.toScreenX(midX) - dest.width() / 2,
                bottom - dest.height(),
                textPaint);

        text = "Bonus ( Par "+Integer.toString(par)+" )";

        textPaint.setTextSize(gameView.toScreen(1.25f));
        textPaint.getTextBounds(text,0, text.length(), dest);
        bottom = (int)(bottom + gameView.toScreen(padding) + dest.height());

        drawInfo.getCanvas().drawText(
                text,
                (int)gameView.toScreenX(midX) - dest.width() / 2,
                bottom - dest.height(),
                textPaint);

        text = String.valueOf(bonus);

        textPaint.setTextSize(gameView.toScreen(2f));
        textPaint.getTextBounds(text,0, text.length(), dest);
        bottom = (int)(bottom + gameView.toScreen(paddingMinor) + dest.height());

        drawInfo.getCanvas().drawText(
                text,
                (int)gameView.toScreenX(midX) - dest.width() / 2,
                bottom - dest.height(),
                textPaint);

        text = "Total";

        textPaint.setTextSize(gameView.toScreen(1.25f));
        textPaint.getTextBounds(text,0, text.length(), dest);
        bottom = (int)(bottom + gameView.toScreen(padding) + dest.height());

        drawInfo.getCanvas().drawText(
                text,
                (int)gameView.toScreenX(midX) - dest.width() / 2,
                bottom - dest.height(),
                textPaint);

        text = String.valueOf(totalPoints);

        textPaint.setTextSize(gameView.toScreen(2f));
        textPaint.getTextBounds(text,0, text.length(), dest);
        bottom = (int)(bottom + gameView.toScreen(paddingMinor) + dest.height());

        drawInfo.getCanvas().drawText(
                text,
                (int)gameView.toScreenX(midX) - dest.width() / 2,
                bottom - dest.height(),
                textPaint);
    };

    public void displayDialog(int pandas, int par, int basePoints, int totalPoints, int bonus) {
        this.pandas = pandas;
        this.par = par;
        this.basePoints = basePoints;
        this.totalPoints = totalPoints;
        this.bonus = bonus;
    }

    public void displayDialog(StageScore score) {
        this.displayDialog(score.getTokenCount(), score.getPar(), score.getBasePoints(), score.getTotalPoints(), score.getParBonus());
    }

    @Override
    public int drawOrder() {
        return 203;
    }

    @Override
    public void destroy() {
        if(destroyed) {return;}
        levelSelectButton.destroy();
        nextlevelButton.destroy();
        retryButton.destroy();

        dest = null;
        paint = null;
        textPaint = null;
        levelSelectButton = null;
        nextlevelButton = null;
        retryButton = null;

        super.destroy();
    }
}
