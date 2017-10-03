package com.pandeagames.www.gutterballredux.gameObjects;

import android.graphics.Paint;
import android.graphics.Rect;

import com.pandeagames.www.gutterballredux.gameControllers.Button;
import com.pandeagames.www.gutterballredux.gameControllers.Game;
import com.pandeagames.www.gutterballredux.gameControllers.Levels.CullShaftRenderer;
import com.pandeagames.www.gutterballredux.infoHolders.DrawInfo;

/**
 * Created by ccove on 9/4/2017.
 */

public class LevelSelectButton extends Button {

    public LevelSelectButton(Game game) {
        super(game, "Level Select");
    }

    public void draw(DrawInfo drawInfo)
    {
        super.draw(drawInfo);
    }
    @Override
    public int drawOrder() {
        // TODO Auto-generated method stub
        return 100;
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
