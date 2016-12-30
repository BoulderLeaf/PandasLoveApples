package com.pandeagames.www.gutterballredux.Drawing;

import com.pandeagames.www.gutterballredux.gameControllers.levelManagment.AppleLevelManager;

import java.util.ArrayList;
import java.util.List;

import com.pandeagames.www.gutterballredux.droidControllers.GutterBallApp;
import com.pandeagames.www.gutterballredux.droidControllers.SwingActivity;

import com.pandeagames.www.gutterballredux.threads.SwingsThread;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;

public class UnlockingScrollView extends ScrollView {
	private List<PhotoButton> photoButtonList;
	public UnlockingScrollView(Context context) {
		super(context);
		photoButtonList=new ArrayList<PhotoButton>();
		// TODO Auto-generated constructor stub
	}
	protected void onAttachedToWindow(){
		super.onAttachedToWindow();
		searchForPhotoButtons(this);
	}
	private void searchForPhotoButtons(ViewGroup vg){
		for(int i=0; i < vg.getChildCount(); i++)
		{
		    View v =vg.getChildAt(i);
		    if (v instanceof PhotoButton) {
		    	  photoButtonList.add((PhotoButton)v);
		    }else if(v instanceof ViewGroup){
		    	searchForPhotoButtons((ViewGroup)v);
		    }
		}
	}
	public UnlockingScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		photoButtonList=new ArrayList<PhotoButton>();
	}
	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt){
		super.onScrollChanged(l, t, oldl, oldt);
		for(PhotoButton pb : photoButtonList){
			int top = (int)((ViewGroup)pb.getParent()).getY();
			int bottom = pb.getBottom();
			String text = pb.getText().toString();
			
			int h = this.getHeight();
			
			int unlcockAt = top+bottom/2;
			int scrollBottom = t+h;
			
			if(pb.getMarkForUnlock()){
				if(t+h>top+bottom/2){
				
					pb.doUnlockAnim();
					
					((AppleLevelManager)((GutterBallApp)getContext().getApplicationContext()).getLevelManager()).markForUnlock(pb.levelDef.getId(), false);
				}
			}
		}
	}
}
