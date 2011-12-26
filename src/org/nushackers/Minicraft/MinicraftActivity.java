package org.nushackers.Minicraft;

import com.mojang.ld22.Game;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;
public class MinicraftActivity extends Activity {
	/** Called when the activity is first created. */
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Game game = ((GameView)findViewById(R.id.gameView)).game;
		ControlPad pad = (ControlPad)findViewById(R.id.controlPad);
		
		
		pad.setJoystickListener(game.getInput());
		pad.setButton1Listener(game.getInput().atkListener);
		pad.setButton2Listener(game.getInput().menuListener);
		
		getWindow().setFlags(
				WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN
		);
	}
}