package com.pandeagames.www.gutterballredux.gameControllers.Levels;

import com.pandeagames.www.gutterballredux.Components.AbstractGameComponent;
import com.pandeagames.www.gutterballredux.Components.BodyComponent;
import com.pandeagames.www.gutterballredux.gameControllers.Game;
import com.pandeagames.www.gutterballredux.gameObjects.Background;
import com.pandeagames.www.gutterballredux.gameObjects.GameObjectUtils;
import com.pandeagames.www.gutterballredux.gameObjects.ObjectGroup;
import com.pandeagames.www.gutterballredux.utils.JSON;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by ccove on 7/6/2017.
 */

public class GeneratedLevel extends AbstractGameComponent {

    private JSONObject _levelJSON;
    private ArrayList<AbstractGameComponent> levelObjects;
    protected LevelDef levelDef;

    public GeneratedLevel(Game game, LevelDef levelDef){
        super(game);

        this.levelDef = levelDef;
        levelObjects = new ArrayList<AbstractGameComponent>();

        this.init();
    }

    protected void init(){
        try {
            this._levelJSON = new JSONObject(JSON.loadJSONFromAsset(game.getAssets(),"levels/" + levelDef.getId()+".json"));

            JSONArray objects = this._levelJSON.getJSONArray("objects");
            JSONObject instance = null;
            AbstractGameComponent gameComponent = null;

            JSONObject objectsDefs = new JSONObject(JSON.loadJSONFromAsset(game.getAssets(),"creatorObjects.json"));
            JSONObject prefab, params, group;

            for(int i = 0; i<objects.length(); i++)
            {
                instance= (JSONObject)objects.get(i);
                prefab = objectsDefs.getJSONObject(instance.getString("objectId"));
                params = prefab.getJSONObject("params");
                group = params.getJSONObject("group");

                if(group.getInt("value") == ObjectGroup.OBJECT.value()) {
                    gameComponent = GameObjectUtils.parseGameObjectFromJSON(game, instance);
                    this.initializeGameComponent(gameComponent, instance);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    protected void initializeGameComponent(AbstractGameComponent gameComponent,JSONObject object)
    {
        this.levelObjects.add(gameComponent);
        GameObjectUtils.syncGameObjectFromJSON(gameComponent, object);
    }

    @Override
    public void destroy(){
        super.destroy();

        for(AbstractGameComponent gameComponent:levelObjects) {
            gameComponent.markDestroy();
        }

        levelObjects.clear();
        levelObjects = null;
    }
}
