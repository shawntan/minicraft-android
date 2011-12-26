package org.nushackers.Minicraft;

import com.mojang.ld22.Game;
import com.mojang.ld22.State;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;
public class MinicraftActivity extends Activity {
	/** Called when the activity is first created. */
	
	Game game;
	private PowerManager.WakeLock wakelock = null;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		/*
		
		getWindow().setFlags(
				WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN
		);*/


		setContentView(R.layout.main);

		GameView gv = (GameView)findViewById(R.id.gameView);

		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		gv.setDimensions(metrics.widthPixels,metrics.heightPixels);
		
		game = gv.game;

		ControlPad pad = (ControlPad)findViewById(R.id.controlPad);
		
		
		pad.setJoystickListener(game.getInput());
		pad.setButton1Listener(game.getInput().atkListener);
		pad.setButton2Listener(game.getInput().menuListener);
		

		PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
		wakelock = pm.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK, "Minicraft");
		wakelock.acquire();
		game.start();
	


	}
	@Override
	protected void onPause() {
		super.onPause();
		game.stop();
		if(wakelock != null && wakelock.isHeld())
			wakelock.release();
	}
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		game.stop();
		outState.putSerializable("save",game.getState());
		game.start();
	}
}