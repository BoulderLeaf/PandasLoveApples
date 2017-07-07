package com.pandeagames.www.gutterballredux.gameControllers.Levels;

import com.pandeagames.www.gutterballredux.Components.AbstractGameComponent;
import com.pandeagames.www.gutterballredux.Components.BodyComponent;
import com.pandeagames.www.gutterballredux.gameControllers.Game;
import com.pandeagames.www.gutterballredux.gameObjects.Background;
import com.pandeagames.www.gutterballredux.utils.JSON;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ccove on 7/6/2017.
 */

public class GeneratedLevel extends AbstractGameComponent {

    private JSONObject _levelJSON;

    protected LevelDef levelDef;

    public GeneratedLevel(Game game, LevelDef levelDef){
        super(game);

        this.levelDef = levelDef;

        this.init();
    }

    private void init(){
        try {
            this._levelJSON = new JSONObject(JSON.loadJSONFromAsset(game.getAssets(),"levels/" + levelDef.getId()+".json"));

            JSONArray objects = this._levelJSON.getJSONArray("objects");

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
