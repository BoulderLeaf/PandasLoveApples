package com.pandeagames.www.gutterballredux.gameControllers;


import com.pandeagames.R;

import android.content.Context;
import android.content.Loader;
import android.content.Loader.OnLoadCompleteListener;
import android.media.AudioManager;
import android.media.SoundPool;

public class GameSoundPool extends SoundPool  {
	private Pool pool;
	private Context context;
	public GameSoundPool(Context context, int streamType, int srcQuality) {
		super(12, streamType, srcQuality);
		pool= new Pool();
		this.setVolume(AudioManager.STREAM_MUSIC, .99f, 0.99f);
		this.context=context;
		loadSounds();
	}
	public Pool getPool(){
		return pool;
	}
	private void loadSounds(){
		pool.squish=this.load(context, R.raw.squish, 1);
		pool.die1=this.load(context, R.raw.die1, 1);
		pool.die2=this.load(context, R.raw.die2, 1);
		pool.die3=this.load(context, R.raw.die3, 1);
		pool.die4=this.load(context, R.raw.die3, 1);
		pool.hit1=this.load(context, R.raw.hit1, 1);
		pool.hit2=this.load(context, R.raw.hit2, 1);
		pool.hit3=this.load(context, R.raw.hit3, 1);
		pool.hit4=this.load(context, R.raw.hit4, 1);
		pool.swing1=this.load(context, R.raw.swing1, 1);
		pool.swing2=this.load(context, R.raw.swing2, 1);
		pool.rope1=this.load(context, R.raw.rope1, 1);
	}
	public class Pool{
		public int squish;
		public int die1;
		public int die2;
		public int die3;
		public int die4;
		public int hit1;
		public int hit2;
		public int hit3;
		public int hit4;
		public int swing1;
		public int swing2;
		public int rope1;
		public Pool(){
			
		}
	}
	public void playRandom(int... soundIds){
		int ran = (int)Math.random()*soundIds.length;
		this.play(soundIds[ran], 0.99f, 0.99f, 1, 0, 1.0f);
	}

}
