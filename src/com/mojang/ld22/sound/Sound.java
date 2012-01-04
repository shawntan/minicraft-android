package com.mojang.ld22.sound;

import org.nushackers.Minicraft.GameView;
import org.nushackers.Minicraft.R;

import android.media.MediaPlayer;



public class Sound {
	public static final Sound playerHurt = new Sound(R.raw.playerhurt);
	public static final Sound playerDeath = new Sound(R.raw.death);
	public static final Sound monsterHurt = new Sound(R.raw.monsterhurt);
	public static final Sound test = new Sound(R.raw.test);
	public static final Sound pickup = new Sound(R.raw.pickup);
	public static final Sound bossdeath = new Sound(R.raw.bossdeath);
	public static final Sound craft = new Sound(R.raw.craft);

	//private AudioClip clip;
	private MediaPlayer player;
	private Sound(int id) {
		/*
		try {
			clip = Applet.newAudioClip(Sound.class.getResource(name));
		} catch (Throwable e) {
			e.printStackTrace();
		}
		*/
		player = MediaPlayer.create(GameView.context,id);
	}

	public void play() {
		player.start();
		/*
		try {
			new Thread() {
				public void run() {
					clip.play();
				}
			}.start();
		} catch (Throwable e) {
			e.printStackTrace();
		}*/
	}
}