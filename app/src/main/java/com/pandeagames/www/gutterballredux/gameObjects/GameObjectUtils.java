package com.pandeagames.www.gutterballredux.gameObjects;

import android.util.Log;

import com.pandeagames.www.gutterballredux.Components.AbstractGameComponent;
import com.pandeagames.www.gutterballredux.gameControllers.Game;
import com.pandeagames.www.gutterballredux.gameControllers.Levels.Level;
import com.pandeagames.www.gutterballredux.gameControllers.Levels.LevelDef;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by ccove on 7/8/2017.
 */

public class GameObjectUtils
{
    public static AbstractGameComponent parseGameObjectFromID(Game game, String id){
        AbstractGameComponent object = null;

        try{
            Class<?> clazz = Class.forName("com.pandeagames.www.gutterballredux.gameObjects."+id);
            Constructor<?> constructor = clazz.getConstructor(Game.class, JSONObject.class);
            object = (AbstractGameComponent) constructor.newInstance(game);
        }catch(IllegalAccessException ex){
            Log.e("PANDAS LOVE APPLES", "Unable to generate game object", ex);
            return null;
        }catch(ClassNotFoundException ex){
            Log.e("PANDAS LOVE APPLES", "Unable to generate game object", ex);
            return null;
        }catch(InstantiationException ex){
            Log.e("PANDAS LOVE APPLES", "Unable to generate game object", ex);
            return null;
        }catch(NoSuchMethodException ex){
            Log.e("PANDAS LOVE APPLES", "Unable to generate game object", ex);
            return null;
        }catch(InvocationTargetException ex){
            Log.e("PANDAS LOVE APPLES", "Unable to generate game object", ex);
            return null;
        }

        return object;
    }


    public static AbstractGameComponent parseGameObjectFromJSON(Game game, JSONObject objectJSON)
    {
        AbstractGameComponent object = null;

        String id = null;

        try{
            id = objectJSON.getString("objectId");
        }
        catch(JSONException ex)
        {
            Log.e("PANDAS LOVE APPLES", "Unable to parse game object JSON", ex);
        }

        try{
            Class<?> clazz = Class.forName("com.pandeagames.www.gutterballredux.gameObjects."+id);
            Constructor<?> constructor = clazz.getConstructor(Game.class, JSONObject.class);
            object = (AbstractGameComponent) constructor.newInstance(game, objectJSON);
        }catch(IllegalAccessException ex){
            Log.e("PANDAS LOVE APPLES", "Unable to generate game object", ex);
            return null;
        }catch(ClassNotFoundException ex){
            Log.e("PANDAS LOVE APPLES", "Unable to generate game object", ex);
            return null;
        }catch(InstantiationException ex){
            Log.e("PANDAS LOVE APPLES", "Unable to generate game object", ex);
            return null;
        }catch(NoSuchMethodException ex){
            Log.e("PANDAS LOVE APPLES", "Unable to generate game object", ex);
            return null;
        }catch(InvocationTargetException ex){
            Log.e("PANDAS LOVE APPLES", "Unable to generate game object", ex);
            return null;
        }

        return object;
    }

    public static void syncGameObjectFromJSON(AbstractGameComponent gameObject, JSONObject objectJSON)
    {
        float x = 0, y = 0, width = 0, height = 0;

        try {
            x = objectJSON.getInt("x");
            y = objectJSON.getInt("y");
            width = objectJSON.getInt("width");
            height = objectJSON.getInt("height");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        gameObject.setPos(x, y);
        gameObject.setDimensions(width, height);
    }
}
