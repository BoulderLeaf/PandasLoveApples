package com.pandeagames.www.gutterballredux.Drawing;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.GridLayout;

public class LinearStretchGridLayout extends GridLayout {

	public LinearStretchGridLayout(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public LinearStretchGridLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	
	}

	public LinearStretchGridLayout(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}
	@SuppressLint("NewApi")
	@Override
	
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		//super.onLayout(changed, l, t, r, b);
		float ratio=1.0f;
		int colCount=getColumnCount();
		
	     int itemWidth = (r-l)/colCount;
	     for(int i=0; i< this.getChildCount(); i++){
	    	 
	         View v = getChildAt(i);
	         ratio=(float)v.getMeasuredWidth()/(float)v.getMeasuredHeight();
	       int height= v.getHeight();
	       int minHeight= v.getMinimumHeight();
	      int measuredH= v.getMeasuredHeight();
	      int measuredW= v.getMeasuredWidth();
	     // v.get
	         int remainder= i%colCount;
	         int dev=i/colCount;
	         int left=remainder*itemWidth;
	         int right = remainder*itemWidth+itemWidth;
	         int top = dev*(int)(itemWidth/ratio);
	         int bottom = dev*(int)(itemWidth/ratio)+(int)(itemWidth/ratio);
	         v.layout(left, top, right, bottom);
		//v.layout(itemWidth, t, (i+1)*itemWidth, b);
	         //v.layout(i%getRowCount()*itemWidth, itemWidth, r, b)
	     }
	     
	}

}
