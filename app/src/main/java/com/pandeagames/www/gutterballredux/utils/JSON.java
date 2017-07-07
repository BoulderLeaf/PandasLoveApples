package com.pandeagames.www.gutterballredux.utils;

import android.content.res.AssetManager;
import android.content.res.Resources;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by ccove on 12/28/2016.
 */

public class JSON {
    public static String loadJSONFromResource(Resources resources, int asset) {
        String json = null;
        try {
            InputStream is = resources.openRawResource(asset);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
    public static String loadJSONFromAsset(AssetManager assets, String asset) {
        String json;
        try {
            InputStream is = assets.open(asset);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }

        return json;
    }
}
