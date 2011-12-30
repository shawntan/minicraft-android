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
	ZMControlPad zmPad;
	
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
		pad.setVisibility(View.GONE);
		
		/*pad.setJoystickListener(game.getInput());
		pad.setButton1Listener(game.getInput().atkListener);
		pad.setButton2Listener(game.getInput().menuListener);*/
		zmPad = new ZMControlPad(game.getInput());
	}

	@Override
	protected void onResume() {
		super.onResume();
		if(!zmPad.isControllerConnected())
			zmPad.connectController(this);
		else
			game.start();
	}

	@Override
	protected void onPause() {
		super.onPause();
		if(game != null && game.isRunning())
			game.stop();
		if(zmPad.isControllerConnected())
			zmPad.disconnectController();
	}
}