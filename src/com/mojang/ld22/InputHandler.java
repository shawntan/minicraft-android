package com.mojang.ld22;

import java.util.ArrayList;
import java.util.List;

import org.nushackers.Minicraft.MultiButton.MultiTouchListener;

import com.MobileAnarchy.Android.Widgets.Joystick.JoystickMovedListener;

import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;

public class InputHandler implements OnKeyListener, JoystickMovedListener{
	public class Key {
		public int presses, absorbs;
		public boolean down, clicked;

		public Key() {
			keys.add(this);
		}

		public void toggle(boolean pressed) {
			/*if (pressed != down)*/ down = pressed;
			if (pressed) presses++;
		}

		public void tick() {
			if (absorbs < presses) {
				absorbs++;
				clicked = true;
			} else {
				clicked = false;
			}
		}
	}

	public List<Key> keys = new ArrayList<Key>();

	public Key up = new Key();
	public Key down = new Key();
	public Key left = new Key();
	public Key right = new Key();
	public Key attack = new Key();
	public Key menu = new Key();
	
	public MultiTouchListener atkListener;
	public MultiTouchListener menuListener;


	public void releaseAll() {
		for (int i = 0; i < keys.size(); i++) {
			keys.get(i).down = false;
		}
	}

	public void tick() {
		for (int i = 0; i < keys.size(); i++) {
			keys.get(i).tick();
		}
	}

	public InputHandler(Game game) {
		menuListener = new MultiTouchListener() {
			
			@Override
			public void onTouchEvent(boolean pressed) {
				menu.toggle(pressed);
			}
		};
		atkListener =  new MultiTouchListener() {
			
			@Override
			public void onTouchEvent(boolean pressed) {
				attack.toggle(pressed);
			}
		};
		//game.addKeyListener(this);
	}
/*
	public void keyPressed(KeyEvent ke) {
		toggle(ke, true);
	}

	public void keyReleased(KeyEvent ke) {
		toggle(ke, false);
	}
*/

	private void toggle(KeyEvent ke, boolean pressed) {
		//directions
		if (ke.getKeyCode() == KeyEvent.KEYCODE_DPAD_UP) up.toggle(pressed);
		if (ke.getKeyCode() == KeyEvent.KEYCODE_DPAD_DOWN) down.toggle(pressed);
		if (ke.getKeyCode() == KeyEvent.KEYCODE_DPAD_LEFT) left.toggle(pressed);
		if (ke.getKeyCode() == KeyEvent.KEYCODE_DPAD_RIGHT) right.toggle(pressed);
		/*
		if (ke.getKeyCode() == KeyEvent.VK_W) up.toggle(pressed);
		if (ke.getKeyCode() == KeyEvent.VK_S) down.toggle(pressed);
		if (ke.getKeyCode() == KeyEvent.VK_A) left.toggle(pressed);
		if (ke.getKeyCode() == KeyEvent.VK_D) right.toggle(pressed);
		if (ke.getKeyCode() == KeyEvent.VK_UP) up.toggle(pressed);
		if (ke.getKeyCode() == KeyEvent.VK_DOWN) down.toggle(pressed);
		if (ke.getKeyCode() == KeyEvent.VK_LEFT) left.toggle(pressed);
		if (ke.getKeyCode() == KeyEvent.VK_RIGHT) right.toggle(pressed);*/
		//menu & attack
		/*
		if (ke.getKeyCode() == KeyEvent.VK_TAB) menu.toggle(pressed);
		if (ke.getKeyCode() == KeyEvent.VK_ALT) menu.toggle(pressed);
		if (ke.getKeyCode() == KeyEvent.VK_ALT_GRAPH) menu.toggle(pressed);
		if (ke.getKeyCode() == KeyEvent.VK_SPACE) attack.toggle(pressed);
		if (ke.getKeyCode() == KeyEvent.VK_CONTROL) attack.toggle(pressed);
		if (ke.getKeyCode() == KeyEvent.VK_NUMPAD0) attack.toggle(pressed);
		if (ke.getKeyCode() == KeyEvent.VK_INSERT) attack.toggle(pressed);
		if (ke.getKeyCode() == KeyEvent.VK_ENTER) menu.toggle(pressed);
		 */
		if (ke.getKeyCode() == KeyEvent.KEYCODE_Q) menu.toggle(pressed);
		if (ke.getKeyCode() == KeyEvent.KEYCODE_W) attack.toggle(pressed);
		
	}


	@Override
	public boolean onKey(View v, int keyCode, KeyEvent event) {
		toggle(event,event.getAction()==KeyEvent.ACTION_DOWN);
		return true;
	}

	@Override
	public void OnMoved(int pan, int tilt) {
		if(tilt < -5) up.toggle(true); 
		else if(tilt > 5) down.toggle(true);
		else {
			up.toggle(false);
			down.toggle(false);
		}
		if (pan < -5) left.toggle(true);
		else if(pan > 5) right.toggle(true);
		else {
			left.toggle(false);
			right.toggle(false);
		}
	}

	@Override
	public void OnReleased() {}

	@Override
	public void OnReturnedToCenter() {
		releaseAll();
	}


}
