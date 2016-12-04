package com.pandeagames.www.gutterballredux.gameObjects.launcher;

import java.util.ArrayList;
import java.util.List;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.animation.Animation;

import com.pandeagames.www.gutterballredux.gameControllers.Game;
import com.pandeagames.www.gutterballredux.infoHolders.DrawInfo;
import com.pandeagames.www.gutterballredux.infoHolders.UpdateInfo;
import com.pandeagames.www.gutterballredux.droidControllers.SwingActivity;
import com.pandeagames.www.gutterballredux.Components.DrawableComponent;
import com.pandeagames.www.gutterballredux.Components.DrawableGameComponent;

public class LauncherTouchCircle extends DrawableGameComponent implements ILauncherListener, AnimatorListener {
	public static final int IDLE=0, PULLING=1, DISABLED=2, APPEARING=3; 
	private Launcher launcher;
	private float radius;
	private int alpha=100;
	private int[] alphaFadeAnim = {0};
	private int alphaFadeAnimIndex=0;
	private int STATE=IDLE;
	//Drawing
	private Paint paint;
	
	private float radiusVel=0f;
	private float radiusStaticOffset=0.2f;
	private float radiusOffset=radiusStaticOffset;
	
	public LauncherTouchCircle(Game game, Launcher launcher) {
		super(game);
		this.launcher=launcher;
		launcher.addLauncherListener(this);
		paint=new Paint();
		paint.setARGB(100, 255, 248, 206);
	}
	@Override
	public int drawOrder(){
		return 1;
	}
	@Override
	public void update(UpdateInfo updateInfo){
		super.update(updateInfo);
		//State controlled animations
		if(STATE!=IDLE){
			
		}
		if(STATE==IDLE){
			alpha=100;
			
				if(radiusOffset>0){
				radiusVel-=0.007f;
			}else if(radiusOffset<0){
				radiusVel+=0.007f;
			}
			radiusOffset+=radiusVel;
			alpha=(int)(100+radiusOffset*100);
		}else if(STATE==PULLING){
			/*if(alpha>0){		
				alpha-=50;
			}
			if(alpha<0){
				alpha=0;
			}*/
			if(alphaFadeAnimIndex<alphaFadeAnim.length)
			{
				alpha=alphaFadeAnim[alphaFadeAnimIndex++];
			}
		}else if(STATE==DISABLED){
					
		}else if(STATE==APPEARING){
			if(alpha<100){		
				alpha+=10;
			}else{
				STATE=IDLE;
				alpha=100;
			}
			radiusOffset=radiusStaticOffset;
			radiusVel=0;
		}
	}
	@Override
	public void draw(DrawInfo drawInfo){	
		super.draw(drawInfo);
		paint.setAlpha(alpha);
		drawInfo.getCanvas().drawCircle(
				gameView.toScreenX(launcher.getX()), 
				gameView.toScreenY(launcher.getY()), 
				gameView.toScreen(launcher.getRadius()+radiusOffset),
				paint);
		
		
		
	}
	
////////////////////////////Animation Values///////////////////////////
	public void setRadius(float newValue){
		this.radius=newValue;
	}
	public void setAlpha(int newValue){
		this.alpha=newValue;
	}
	
	@Override
	public void enableLauncher(Launcher launcher) {
		// TODO Auto-generated method stub
		STATE=APPEARING;
	}

	@Override
	public void disableLauncher(Launcher launcher) {
		// TODO Auto-generated method stub
		STATE=DISABLED;
	}

	@Override
	public void launch(Launcher launcher, float x, float y) {
		// TODO Auto-generated method stub
		alpha=0;
	}

	@Override
	public void touchLauncher(Launcher launcher, float x, float y) {
		// TODO Auto-generated method stub
		STATE=PULLING;
		alphaFadeAnimIndex=0;
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
	@Override
	public void pullingLauncher(Launcher launcher, float x, float y) {
		// TODO Auto-generated method stub
		
	}
	
}
class IdleAnim extends AnimatorWrapper{
	private LauncherTouchCircle launcherTouchCircle;
	public IdleAnim(LauncherTouchCircle launcherTouchCircle){
		super();
		this.launcherTouchCircle=launcherTouchCircle;
		createAnimation();
	}
	public void createAnimation(){
		ObjectAnimator rAnim = ObjectAnimator.ofFloat(launcherTouchCircle, "radius", 1f, 10f);
		ObjectAnimator aAnim = ObjectAnimator.ofInt(launcherTouchCircle, "alpha", 50, 255);
		playTogether(rAnim, aAnim);
		
		setRepeat(true);
		setDuration(6000);
		
	}
}
class AppearAnim extends AnimatorWrapper{
	private LauncherTouchCircle launcherTouchCircle;
	public AppearAnim(LauncherTouchCircle launcherTouchCircle){
		super();
		this.launcherTouchCircle=launcherTouchCircle;
		createAnimation();
	}
	public void createAnimation(){
		ObjectAnimator rAnim = ObjectAnimator.ofFloat(launcherTouchCircle, "radius", 0f, 1f);
		ObjectAnimator aAnim = ObjectAnimator.ofInt(launcherTouchCircle, "alpha", 0, 255);
		playTogether(rAnim, aAnim);
		setDuration(500);
	}
}
class TouchAnim extends AnimatorWrapper{
	private LauncherTouchCircle launcherTouchCircle;
	public TouchAnim(LauncherTouchCircle launcherTouchCircle){
		super();
		this.launcherTouchCircle=launcherTouchCircle;
		createAnimation();
	}
	public void createAnimation(){
		//ObjectAnimator rAnim = ObjectAnimator.ofFloat(launcherTouchCircle, "radius", 1f, 3f);
		ObjectAnimator aAnim = ObjectAnimator.ofInt(launcherTouchCircle, "alpha", 255, 0);
		play(aAnim);
		
		setDuration(300);
	}
}
class AnimationMaster{
	public static void doRepeat(AnimatorWrapper anim){
		anim.cancel();
		anim.removeAllListeners();
		anim.end();
		anim.resetAnimatorSet();
		anim.createAnimation();
		anim.start();
	}
	public static void startAnimation(AnimatorWrapper anim){
		anim.createAnimation();
	}
}
class AnimatorWrapper implements AnimatorListener {
	private boolean repeat;
	private AnimatorSet animatorSet;
	private boolean canceling=false;
	public AnimatorWrapper(){
		this.animatorSet=new AnimatorSet();
		animatorSet.addListener(this);
		//AnimationMaster.startAnimation(this);
	}
	public void createAnimation(){
		
	}
	public void resetAnimatorSet()
	{
		this.animatorSet=new AnimatorSet();
		animatorSet.addListener(this);
	}
	public void addListener(AnimatorListener listener){
		animatorSet.addListener(listener);
	}
	public void removeListener(AnimatorListener listener){
		animatorSet.removeListener(listener);
	}
	public void removeAllListeners(){
		animatorSet.removeAllListeners();
	}
	public AnimatorSet getAnimatorSet(){
		return animatorSet;
	}
	public void start(){
		canceling=false;
		animatorSet.start();
	}
	public void end(){
		animatorSet.end();
	}
	public void setTarget(Object target){
		animatorSet.setTarget(target);
	}
	public void playSequentially(List<Animator> items){
		animatorSet.playSequentially(items);
	}
	public void play(Animator anim){
		animatorSet.play(anim);
	}
	public void cancel(){
		canceling=true;
		animatorSet.cancel();
	}
	public void playTogether(Animator... items){
		animatorSet.playTogether(items);
	}
	public void setDuration(long duration){
		animatorSet.setDuration(duration);
		
	}
	public void setRepeat(boolean value){
		repeat=value;
	}
	public boolean getRepeat(){
		return repeat;
	}
	@Override
	public void onAnimationCancel(Animator animation) {
		// TODO Auto-generated method stub
		canceling=true;
	}
	@Override
	public void onAnimationEnd(Animator animation) {
		// TODO Auto-generated method stub
		/*if(repeat && !canceling){
			//animation.
			
			animatorSet.removeAllListeners();
			AnimatorSet tmpAnimatorSet=(AnimatorSet)animation.clone();
			
			animatorSet.play(null);
			animatorSet.cancel();
			animatorSet.end();
			
		
			
			animatorSet=tmpAnimatorSet;
			
			animatorSet.addListener(this);
			animatorSet.start();
		}*/
		if(repeat && !canceling){
			AnimationMaster.doRepeat(this);
		}
	}

	@Override
	public void onAnimationStart(Animator animation) {
		// TODO Auto-generated method stub
		canceling=false;
	}
	@Override
	public void onAnimationRepeat(Animator animation) {
		// TODO Auto-generated method stub
		
	}
}

class VirtualList<E> extends ArrayList<E>{
	@Override
	public int size(){
		return Integer.MAX_VALUE;
	}
	public int getRealIndex(int index){
		if(index==0)return 0;
		if(index<super.size())return index;
		int one = super.size()%index;
		int two = super.size();
		int three = two-one;
		return three;
	}
	@Override
	public E set(int index,E object)
	{
		return super.set(getRealIndex(index), object);
	}
	public E get(int index){
		return super.get(getRealIndex(index));
	}
}