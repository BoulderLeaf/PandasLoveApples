package com.pandeagames.www.gutterballredux.Drawing;

import java.util.ArrayList;
import java.util.List;

import org.jbox2d.common.Vec2;

import com.pandeagames.R;
import com.pandeagames.www.gutterballredux.gameControllers.BitmapPool;
import com.pandeagames.www.gutterballredux.gameControllers.Levels.LevelDef;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Notification.Style;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;

public class PhotoButton extends Button implements AnimatorListener {
	private int photo = -1;
	private int disabledPhoto = -1;
	private int disabledIcon = -1;
	private int unlockedPhoto = -1;
	private BitmapDrawable bitmapDisplayed;
	private int resDisplayed = -1;
	private int paddingBottom;
	private int paddingTop;
	private int paddingRight;
	private int paddingLeft;
	private float heightRatio;
	private boolean photoOnDisabled=true;
	private boolean markForUnlock=false;
	public boolean levelBeaten=false;
	public LevelDef levelDef;
	
	private Paint textPaint;
	private Paint coverPaint;
	
	public PhotoButton(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public PhotoButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		handleAttrs(context,attrs);
		constructPaint();
	}

	public PhotoButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		handleAttrs(context,attrs);
		constructPaint();
	}
	public PhotoButton(Context context, int photoId, int photoDisabledId, int iconDisabledId, int unlockedIconId, float heightRatio) {
		super(context);

		photo = photoId;
		unlockedPhoto = unlockedIconId;
		disabledPhoto = photoDisabledId;
		disabledIcon = iconDisabledId;

		photoOnDisabled = true;
		this.heightRatio = heightRatio;

		constructPaint();
	}

	private BitmapDrawable getDrawableRes()
	{
		int resToDisplay = -1;

		if(photo!=-1){
			resToDisplay = photo;
		}

		if(!this.isEnabled() || markForUnlock){
			if(disabledPhoto!=-1)
			{
				resToDisplay = disabledPhoto;
			}
			if(this.disabledIcon!=-1){
				resToDisplay = disabledIcon;
			}
		}

		if(resToDisplay == resDisplayed)
		{
			return bitmapDisplayed;
		}

		resDisplayed = resToDisplay;
		bitmapDisplayed = BitmapPool.getBitmapDrawable(this.getResources(), resToDisplay);

		return bitmapDisplayed;
	}

	private void constructPaint(){
		textPaint = new Paint();
		textPaint.setTextSize(30);
		textPaint.setColor(Color.WHITE);
		textPaint.setStyle(android.graphics.Paint.Style.FILL);
		
		coverPaint = new Paint();
		coverPaint.setARGB(0, 255,225, 100);
		
	}
	private void handleAttrs(Context context,AttributeSet attrs){
		TypedArray a =  context.obtainStyledAttributes(attrs, R.styleable.PhotoButton);
		try{
			int photoRes=a.getResourceId(R.styleable.PhotoButton_photo, -1);
			int disabledPhotoRes=a.getResourceId(R.styleable.PhotoButton_disabledPhoto, -1);
			int disabledIconRes=a.getResourceId(R.styleable.PhotoButton_disabledIcon, -1);
			int unlockedIconRes=a.getResourceId(R.styleable.PhotoButton_unlockedPhoto, -1);
			photoOnDisabled=a.getBoolean(R.styleable.PhotoButton_photoOnDisabled, true);
			heightRatio = a.getFloat(R.styleable.PhotoButton_heightRatio, 1.0f);
			setEnabled(!a.getBoolean(R.styleable.PhotoButton_disabled, false));
			setPhotoRes(photoRes);
			setUnlockedRes(unlockedIconRes);
			setDisabledPhotoRes(disabledPhotoRes);
			setDisabledIcon(disabledIconRes);
		} finally{
			a.recycle();
		}
	}
	public void draw(Canvas c){
		super.draw(c);
		Rect des = new Rect();

		paddingBottom=0;
		paddingTop=0;
		paddingRight=0;
		paddingLeft=0;

		BitmapDrawable toDraw = getDrawableRes();

		if(photo!=-1){
			if(!this.isEnabled() && !photoOnDisabled){
			}else{
				des.set(paddingLeft,paddingTop,getWidth()-paddingRight,getHeight()-paddingBottom);
				toDraw.setBounds(des);
				toDraw.draw(c);
			}
		}
		Rect coverDes = new Rect();
		coverDes.set(des);
		if(!this.isEnabled() || markForUnlock){
			if(disabledPhoto!=-1){
				des.set(paddingLeft,paddingTop,getWidth()-paddingRight,getHeight()-paddingBottom);
				toDraw.setBounds(des);
				toDraw.draw(c);
			}
			if(this.disabledIcon!=-1){
				float scale=1f;
				int left, right, top, bottom;
				
				if(getWidth()<toDraw.getIntrinsicWidth()){
					scale=(float)getWidth()/(float)toDraw.getIntrinsicWidth();
				}
				if(getHeight()<toDraw.getIntrinsicHeight()){
					if(scale>(float)getHeight()/(float)toDraw.getIntrinsicHeight()){
						scale=(float)getHeight()/(float)toDraw.getIntrinsicHeight();
					}
				}
				left=(int)(getWidth()/2-(toDraw.getIntrinsicWidth()*scale)/2);
				right=(int)(getWidth()/2+(toDraw.getIntrinsicWidth()*scale)/2);
				top=(int)(getHeight()/2-(toDraw.getIntrinsicHeight()*scale)/2);
				bottom=(int)(getHeight()/2+(toDraw.getIntrinsicHeight()*scale)/2);
				des.set(left,top,right,bottom);
				toDraw.setBounds(des);
				toDraw.draw(c);

				c.drawText(this.getText().toString(), des.centerX()-this.getText().length()*7, des.bottom+40, textPaint);
			}
		}
		c.drawRect(coverDes, coverPaint);
	}
	
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int widthSize = MeasureSpec.getSize(widthMeasureSpec);
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		int heightSize = MeasureSpec.getSize(heightMeasureSpec);

		int desiredWidth = widthSize - getPaddingLeft() - getPaddingRight();
		int desiredHeight = (int)(widthSize * heightRatio);

		int width =getMeasuredWidth();
		int height = (int)((width - getCompoundPaddingRight() - getCompoundPaddingLeft()) * heightRatio);

		setMeasuredDimension(width, height);
	}
	public void setDisabledPhotoRes(int res){
		if(res==-1)return;
		disabledPhoto=res;
	}
	public void setMarkForUnlock(boolean value){
		markForUnlock=value;
	}
	public void doUnlockAnim(){
		setMarkForUnlock(false);
		setText("Unlocked!");
		AnimatorSet animSet = new AnimatorSet();
		animSet.addListener(this);
		//dummy animation used to update the view
		ObjectAnimator anim1 = ObjectAnimator.ofInt(this, "dummyValue", 255,0);

		ObjectAnimator anim2 = ObjectAnimator.ofInt(coverPaint, "alpha", 255,0);
		anim1.setInterpolator(new DecelerateInterpolator());
		anim2.setInterpolator(new DecelerateInterpolator());
		anim1.setDuration(2500);
		anim2.setDuration(2500);

		animSet.setDuration(2000);
		animSet.playTogether(anim1, anim2);
		animSet.setInterpolator(new DecelerateInterpolator());
		animSet.start();
		
		
		
	}
	public boolean getMarkForUnlock(){
		return markForUnlock;
	}
	private int dummyValue;
	public void setDummyValue(int dummyInt){
		dummyValue = dummyInt;
		this.invalidate();
	}
	public int getDummyValue(){
		return dummyValue;
	}
	public int getDisabledPhoto(){
		return disabledPhoto;
	}
	public void setPhotoRes(int res){
		if(res==-1)return;
		photo = res;
	}
	public void setPhotoRes(Drawable drawable){
		if(drawable==null)return;
		setPhotoRes((BitmapDrawable) drawable);
	}

	public int getPhoto(){
		return photo;
	}
	public void setUnlockedRes(int res){
		if(res==-1)return;
		unlockedPhoto = res;
	}
	public int getUnlockedPhoto(){
		return unlockedPhoto;
	}

	public void setDisabledIcon(int res){
		disabledIcon=res;
		this.invalidate();
	}

	@Override
	public void onAnimationCancel(Animator arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAnimationEnd(Animator arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAnimationRepeat(Animator arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAnimationStart(Animator arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
