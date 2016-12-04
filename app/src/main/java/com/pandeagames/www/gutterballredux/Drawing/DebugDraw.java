package com.pandeagames.www.gutterballredux.Drawing;

import java.util.Vector;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.ShapeType;
import org.jbox2d.common.Transform;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.Fixture;

import com.pandeagames.www.gutterballredux.threads.BufferedList;

import android.graphics.Paint;

import com.pandeagames.www.gutterballredux.gameControllers.Game;
import com.pandeagames.www.gutterballredux.infoHolders.DrawInfo;
import com.pandeagames.www.gutterballredux.infoHolders.UpdateInfo;
import com.pandeagames.www.gutterballredux.droidControllers.SwingActivity;
import com.pandeagames.www.gutterballredux.Components.BodyComponent;
import com.pandeagames.www.gutterballredux.Components.DrawableComponent;
import com.pandeagames.www.gutterballredux.Components.DrawableGameComponent;

public class DebugDraw extends DrawableGameComponent {
	private BufferedList<BodyComponent> list;
	private Paint paint;
	private Paint textPaint;
	private boolean overrideDrawBody;
	private float fps;
	private float[] fpsList;
	private int fpsListSize;
	private long lastUpdate;
	public DebugDraw(Game activity, BufferedList<BodyComponent> list) 
	{
		super(activity);
		// TODO Auto-generated constructor stub
		this.list = list;
		paint = new Paint();
		paint.setARGB(255, 255, 0, 0);
		overrideDrawBody=false;
		
		
		textPaint = new Paint();
		textPaint.set(paint);
		textPaint.setTextSize(30);
		fpsListSize=5;
		fpsList=new float[fpsListSize];
		lastUpdate=0;
		
	}
	public DebugDraw(Game activity, BufferedList<BodyComponent> list, boolean overrideDrawBody) {
		this(activity, list);
		// TODO Auto-generated constructor stub
		this.overrideDrawBody=overrideDrawBody;
	}
	
	@Override
	public void update(UpdateInfo updateInfo) {
		super.update(updateInfo);
		
		for(int i=1;i<fpsListSize;i++){
			fpsList[i] = fpsList[i-1];
		}
		fpsList[0] = updateInfo.getTime()-lastUpdate;
		fps=0f;
		for(int i=0;i<fpsListSize;i++){
			fps+=fpsList[i];
		}
		fps = 1000/(fps/fpsListSize);
		
		
		lastUpdate=updateInfo.getTime();
		
	}

	public void draw(DrawInfo info) {
		super.draw(info);
		for (BodyComponent bodyComp : list) {
			if(bodyComp.getDrawBody()){
			Body body = bodyComp.getBody();
			
			if(body==null)return;
			Fixture fix = body.getFixtureList();
			Vec2 center = body.getWorldCenter();
			while (fix != null) {
				if (fix.getType() == ShapeType.CIRCLE) {
					CircleShape shape = (CircleShape) fix.getShape();
					info.getCanvas().drawCircle(gameView.toScreenX(shape.m_p.x),
							gameView.toScreenY(shape.m_p.y),
							gameView.toScreen(shape.m_radius), paint);
				} else if (fix.getType() == ShapeType.POLYGON) {
					info.getCanvas().save();
					PolygonShape shape = (PolygonShape) fix.getShape();
					Vec2[] vecs = shape.getVertices();
					
					info.getCanvas().rotate((float)(
							body.getAngle()/(Math.PI/180)),
							gameView.toScreenX(center.x),
							gameView.toScreenY(center.y));
					
					int count = shape.getVertexCount();
					for (int i = 0; i < count - 1; i++) {
						info.getCanvas().drawLine(
								gameView.toScreenX(vecs[i].x + center.x),
								gameView.toScreenY(vecs[i].y + center.y),
								gameView.toScreenX(vecs[i + 1].x + center.x),
								gameView.toScreenY(vecs[i + 1].y + center.y),
								paint);
					}
					info.getCanvas().drawLine(
							gameView.toScreenX(vecs[0].x + center.x),
							gameView.toScreenY(vecs[0].y + center.y),
							gameView.toScreenX(vecs[count - 1].x + center.x),
							gameView.toScreenY(vecs[count - 1].y + center.y),
							paint);
					info.getCanvas().restore();
				}
				fix = fix.getNext();
			}
		}
		}
		info.getCanvas().drawText("fps: "+Float.toString(fps), 100, 100, textPaint);
	}

}
