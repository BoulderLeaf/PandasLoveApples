package com.pandeagames.www.gutterballredux.gameControllers;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.content.res.ResourcesCompat;

import com.pandeagames.R;

import java.util.Dictionary;
import java.util.HashMap;

/**
 * Created by ccove on 12/13/2017.
 */

public class BitmapPool {

    private static HashMap<Integer, BitmapDrawable> pool = new HashMap<Integer, BitmapDrawable>();
    private static HashMap<Integer, Bitmap> bitmapPool = new HashMap<Integer, Bitmap>();
    public static BitmapDrawable getBitmapDrawable(Resources r, int resId)
    {
        if(pool.containsKey(resId))
        {
            return pool.get(resId);
        }

        BitmapDrawable bitmap = (BitmapDrawable) ResourcesCompat.getDrawable(r, resId, null);
        pool.put(resId, bitmap);
        return bitmap;
    }

    public static Bitmap getBitmap(Resources r, int resId)
    {
        return getBitmap(r, resId, null);
    }

    public static Bitmap getBitmap(Resources r, int resId, BitmapFactory.Options options)
    {
        if(bitmapPool.containsKey(resId))
        {
            return bitmapPool.get(resId);
        }

        Bitmap bitmap = BitmapFactory.decodeResource(r, resId, options);
        bitmapPool.put(resId, bitmap);
        return bitmap;
    }
}
