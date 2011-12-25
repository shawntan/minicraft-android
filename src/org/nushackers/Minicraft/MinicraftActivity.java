package org.nushackers.Minicraft;

import com.MobileAnarchy.Android.Widgets.Joystick.JoystickView;
import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
public class MinicraftActivity extends Activity {
	/** Called when the activity is first created. */
	
	private Button btnAtk;
	private Button btnMenu;
	private JoystickView ctrl;
	private TextView textView1;
	private Rect screen;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		//Game game = ((GameView)findViewById(R.id.gameView)).game;
		//View main = (View) findViewById(R.id.relativeLayout1);
		/*
		//btnAtk = (Button)findViewById(R.id.attack);
		//btnMenu = (Button)findViewById(R.id.menu);
		ctrl = (JoystickView) findViewById(R.id.joystick);
		textView1 = (TextView) findViewById(R.id.textView);
		View.OnTouchListener listener = new View.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				textView1.setText(((Button)v).getText());
				return true;
			}
		};
		btnAtk.setOnTouchListener(listener);
		btnMenu.setOnTouchListener(listener);	
		btnAtk.setOnTouchListener(game.getInput().atkListener);
		btnMenu.setOnTouchListener(game.getInput().menuListener);
		ctrl.setOnJostickMovedListener(game.getInput());
		*/
	
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);		
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		//textView1.setText(event.getX(0) + "," + event.getY(0)+"\n"+event.getX(1)+","+event.getY(1));
		//ctrl.dispatchTouchEvent(event);
		//btnAtk.dispatchTouchEvent(event);
		//btnMenu.dispatchTouchEvent(event);
		
		return false;
	}
}