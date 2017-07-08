package com.pandeagames.www.gutterballredux.gameObjects;

import com.pandeagames.www.gutterballredux.gameControllers.Game;

import org.json.JSONObject;

/**
 * Created by ccove on 7/8/2017.
 */

public class apple extends Portal
{
    public apple(Game game, JSONObject objectJSON)
    {
        super(game, AppleType.NORMAL);
    }
}
