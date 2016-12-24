package com.pandeagames.www.gutterballredux.gameObjects;

import com.pandeagames.www.gutterballredux.gameControllers.Game;
import com.pandeagames.www.gutterballredux.gameControllers.Simulation;
import com.pandeagames.www.gutterballredux.infoHolders.DrawInfo;

import com.pandeagames.R;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import com.pandeagames.www.gutterballredux.droidControllers.SwingActivity;
import com.pandeagames.www.gutterballredux.Components.DrawableComponent;
import com.pandeagames.www.gutterballredux.Components.DrawableGameComponent;

public class Background extends DrawableGameComponent {
	private Rect bgBounds;
	private Paint paint;
	private Game game;
	private boolean fillCanvas=true;
	private Bitmap bitmap;
	private int id =0;
	public Background(Game activity) {
		this(activity, R.drawable.background);
	}
	public Background(Game activity, int id) {
		this(activity,id, -1);
	}
	public Background(Game activity, int id, boolean fillCanvas) {
		this(activity,id, -1, fillCanvas);
	}
	public Background(Game activity, int id, int drawOrder) {
		this(activity, id, drawOrder, true);
	}
	public Background(Game activity, int id, int drawOrder, boolean fillCanvas) {
		super(activity, drawOrder);
		// TODO Auto-generated constructor stub
		paint=new Paint();
		paint.setARGB(255, 255, 0, 0);
	this.game=activity;
	this.fillCanvas=fillCanvas;
	
	Options options = new Options();
	options.inDither = false;
	options.inJustDecodeBounds = false;
	options.inSampleSize = 1;
	options.mCancel = false;
	options.inPreferredConfig = Config.RGB_565;
	bitmap = BitmapFactory.decodeResource(game.getResources(), id, options);
	
	options.mCancel = false;
	
	}
	
	
	public void draw(DrawInfo info){
		super.draw(info);
		
		if(bitmap==null){
		
		}
		
		if(fillCanvas)	info.getCanvas().drawARGB(255, 159, 184, 58);
		
		Rect des = new Rect();
		des.set((int)gameView.toScreenX(0.0f), 
				(int)gameView.toScreenY(0.0f),
				(int)gameView.toScreenX(0.0f)+(int)gameView.toScreen(Simulation.SIMULATION_WIDTH),
				(int)gameView.toScreenY(0.0f)+(int)gameView.toScreen(Simulation.SIMULATION_HEIGHT));
		Rect src = new Rect();
		src.set(0, 
				0,
				bitmap.getWidth(),
				bitmap.getHeight());
		
		
		info.getCanvas().drawBitmap(bitmap, src, des, paint);
	}
	@Override
	public int drawOrder() {
		// TODO Auto-generated method stub
		return -1;
	}
	public void destroy(){
		super.destroy();
		paint=null;
		bitmap=null;
	}
}
