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
    private static String SCRIPTS_PACKAGE = "com.pandeagames.www.gutterballredux.gameObjects.scripts";

    public static AbstractGameComponent parseGameObjectFromID(Game game, String id){
        AbstractGameComponent object = null;

        try{
            Class<?> clazz = Class.forName(SCRIPTS_PACKAGE + "." + id);
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
            Class<?> clazz = Class.forName(SCRIPTS_PACKAGE + "." + id);
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
            x = (float)objectJSON.getDouble("x");
            y =(float) objectJSON.getDouble("y");
            width = (float)objectJSON.getDouble("width");
            height = (float)objectJSON.getDouble("height");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        gameObject.setPos(x + width / 2, y+ height / 2);
        gameObject.setDimensions(width, height);
    }
}
