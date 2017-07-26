package com.pandeagames.www.gutterballredux.gameObjects.scripts;

import com.pandeagames.www.gutterballredux.gameControllers.Game;
import com.pandeagames.www.gutterballredux.gameObjects.AppleType;
import com.pandeagames.www.gutterballredux.gameObjects.Portal;

import org.json.JSONObject;

/**
 * Created by ccove on 7/8/2017.
 */

public class golden_apple extends Portal
{
    public golden_apple(Game game, JSONObject objectJSON)
    {
        super(game, AppleType.GOLDEN);
    }
}
