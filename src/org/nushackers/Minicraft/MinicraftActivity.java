package org.nushackers.Minicraft;

import com.MobileAnarchy.Android.Widgets.Joystick.JoystickView;
import com.mojang.ld22.Game;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Button;

public class MinicraftActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		Game game = ((GameView)findViewById(R.id.gameView)).game;
		Button btnAtk = (Button)findViewById(R.id.attack);
		Button btnMenu = (Button)findViewById(R.id.menu);
		JoystickView ctrl = (JoystickView) findViewById(R.id.joystickView);
		btnAtk.setOnTouchListener(game.getInput().atkListener);
		btnMenu.setOnTouchListener(game.getInput().menuListener);
		ctrl.setOnJostickMovedListener(game.getInput());
	}
}