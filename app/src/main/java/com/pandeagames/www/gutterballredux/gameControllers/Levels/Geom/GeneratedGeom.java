package com.pandeagames.www.gutterballredux.gameControllers.Levels.Geom;

import com.pandeagames.www.gutterballredux.Components.AbstractGameComponent;
import com.pandeagames.www.gutterballredux.gameControllers.Game;
import com.pandeagames.www.gutterballredux.gameControllers.Levels.LevelDef;
import com.pandeagames.www.gutterballredux.gameObjects.FabricObjectTypes;
import com.pandeagames.www.gutterballredux.gameObjects.GameObjectUtils;
import com.pandeagames.www.gutterballredux.gameObjects.ObjectGroup;
import com.pandeagames.www.gutterballredux.utils.JSON;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ccove on 7/9/2017.
 */

public class GeneratedGeom extends LevelWalls {

    private LevelDef _levelDef;

    public GeneratedGeom(Game game, LevelDef levelDef) {
        super(game);

        this._levelDef = levelDef;
    }
    @Override
    public void createBody(){
        createBody(world.createBody(bodyDef));
    }
    @Override
    public void createBody(Body body){
        PolygonShape groundBox = null;

        try {
            JSONObject levelJSON = new JSONObject(JSON.loadJSONFromAsset(game.getAssets(),"levels/" + this._levelDef.getId()+".json"));

            JSONArray objects = levelJSON.getJSONArray("objects");
            JSONObject instance = null;

            JSONObject objectsDefs = new JSONObject(JSON.loadJSONFromAsset(game.getAssets(),"creatorObjects.json"));
            JSONObject prefab, params, group, fabricObjectType;

            float x, y, width, height;

            for(int i = 0; i<objects.length(); i++)
            {
                instance= (JSONObject)objects.get(i);
                prefab = objectsDefs.getJSONObject(instance.getString("objectId"));
                params = prefab.getJSONObject("params");
                group = params.getJSONObject("group");
                fabricObjectType = params.getJSONObject("fabricObjectType");

                x = instance.getInt("x");
                y = instance.getInt("y");
                width = instance.getInt("width");
                height = instance.getInt("height");

                if(group.getInt("value") != ObjectGroup.GEOM.value()) {continue;}

                switch(FabricObjectTypes.values()[fabricObjectType.getInt("value")])
                {
                    case LINE:
                    case CIRCLE:
                    case TRIANGLE:
                    case ELLIPSE:
                        break;
                    case RECT:

                        groundBox = new PolygonShape();
                        groundBox.setAsBox(width / 2, height / 2, new Vec2(x, y), 0.0f);
                        body.createFixture(groundBox, 0.0f);

                        break;
                    case POLYLINE:
                    case POLYGON:
                    case GROUP:
                    case TEXT:
                    case IMAGE:
                    case PATH:
                        break;
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        super.createBody(body);
    }
}
