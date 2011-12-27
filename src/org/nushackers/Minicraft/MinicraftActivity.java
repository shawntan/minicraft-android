package org.nushackers.Minicraft;

import com.mojang.ld22.Game;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
public class MinicraftActivity extends Activity {
	/** Called when the activity is first created. */
	
	Game game;
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
		
		//dims bottom bar for Android tablets
		gv.setSystemUiVisibility(View.STATUS_BAR_HIDDEN);
		
		game = gv.game;
		ControlPad pad = (ControlPad)findViewById(R.id.controlPad);
		
		
		pad.setJoystickListener(game.getInput());
		pad.setButton1Listener(game.getInput().atkListener);
		pad.setButton2Listener(game.getInput().menuListener);
		game.start();

	}
	@Override
	protected void onPause() {
		super.onPause();
		game.stop();
	}
}