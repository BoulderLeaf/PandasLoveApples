/**
 * 
 */
package com.pandeagames.www.gutterballredux;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Color3f;
import org.jbox2d.common.OBBViewportTransform;
import org.jbox2d.common.Vec2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

/**
 * @author Colin
 *
 */
public class SwingsSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
	class SwingsThread extends Thread{
		private ShapeDrawable shape;
		private SurfaceHolder holder;
		private Context context;
		private boolean running=false;
		private Paint mLinePaint;
		public PolygonShape groundBox;
		public PolygonShape dynamicBox;
		private AndroidDebugDraw debugDraw;
		public SwingsThread(SurfaceHolder holder,Context context){
			 mLinePaint = new Paint();
	            mLinePaint.setAntiAlias(true);
	            mLinePaint.setARGB(255, 0, 255, 0);
			this.holder=holder;
			this.context=context;
			shape = new ShapeDrawable(new OvalShape());
			shape.getPaint().setColor(0xff0000);
			shape.setBounds(10,10,30,30);
			debugDraw = new AndroidDebugDraw(new OBBViewportTransform());
			
		}
		public void run(){
			while(running){
			Canvas c =holder.lockCanvas();
			debugDraw.mCanvas=c;
			
			doDraw(c);
			debugDraw.drawPolygon(dynamicBox.getVertices(), dynamicBox.getVertexCount(), Color3f.RED);
			debugDraw.drawPolygon(groundBox.getVertices(), groundBox.getVertexCount(), Color3f.RED);
			debugDraw.drawCircle(new Vec2(300f,300f), 10f,Color3f.BLUE);
			holder.unlockCanvasAndPost(c);
			}
			
		}
		private void doDraw(Canvas c){
			shape.draw(c);
			c.drawRect(new Rect(200,600,50,50), mLinePaint);
		}
		public void setRunning(boolean value){
			running=value;
		}
	}
private SurfaceHolder holder;

	/**
	 * @param context
	 */
public SwingsThread thread;

	
	public SwingsSurfaceView(Context context) {
		super(context);
		holder=getHolder();
		holder.addCallback(this);
		thread = new SwingsThread(holder, context);
		
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public SwingsSurfaceView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public SwingsSurfaceView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}
	public void onDraw(Canvas canvas){
		super.onDraw(canvas);
		
	}

	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		Canvas canvas=arg0.lockCanvas();
		arg0.unlockCanvasAndPost(canvas);
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		FullscreenActivity context=(FullscreenActivity)this.getContext();
		thread.dynamicBox=context.thread.dynamicBox;
		thread.groundBox=context.thread.groundBox;
		thread.setRunning(true);
		thread.start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		
	}
	

}
