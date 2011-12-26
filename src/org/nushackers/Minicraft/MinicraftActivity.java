package org.nushackers.Minicraft;

import com.mojang.ld22.Game;
import com.mojang.ld22.State;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;
public class MinicraftActivity extends Activity {
	/** Called when the activity is first created. */
	Game game;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		game = ((GameView)findViewById(R.id.gameView)).game;
		ControlPad pad = (ControlPad)findViewById(R.id.controlPad);
		
		
		pad.setJoystickListener(game.getInput());
		pad.setButton1Listener(game.getInput().atkListener);
		pad.setButton2Listener(game.getInput().menuListener);
		
		getWindow().setFlags(
				WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN
		);
		if (savedInstanceState != null) {
			game.loadState((State)savedInstanceState.get("save"));
		}
	}
	@Override
	protected void onPause() {
		super.onPause();
		game.stop();
	}
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		game.stop();
		outState.putSerializable("save",game.getState());
		game.start();
	}
}