package com.pandeagames.www.gutterballredux.Components;

import com.pandeagames.www.gutterballredux.droidControllers.SwingActivity;
import com.pandeagames.www.gutterballredux.infoHolders.DrawInfo;
import com.pandeagames.www.gutterballredux.Components.interfaces.IDrawableComponent;
import android.app.Activity;
import android.content.res.Resources;

public class DrawableComponent extends AbstractComponent implements IDrawableComponent {
protected Resources resources;
protected int drawOrder=0;
	public DrawableComponent(SwingActivity activity) {
		super(activity);
		// TODO Auto-generated constructor stub
		activity.addDrawableComponent(this);
		resources=activity.getResources();
	}

	@Override
	public void draw(DrawInfo info) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		activity.removeDrawableComponent(this);
		super.destroy();
		
	}

	@Override
	public int drawOrder() {
		// TODO Auto-generated method stub
		return drawOrder;
	}

}
