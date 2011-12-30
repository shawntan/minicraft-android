package org.nushackers.Minicraft;

import java.io.IOException;

import android.content.Context;
import android.util.Log;

import com.mojang.ld22.InputHandler;
import com.zeemote.zc.Configuration;
import com.zeemote.zc.Controller;
import com.zeemote.zc.event.BatteryEvent;
import com.zeemote.zc.event.ButtonEvent;
import com.zeemote.zc.event.ControllerEvent;
import com.zeemote.zc.event.DisconnectEvent;
import com.zeemote.zc.event.IButtonListener;
import com.zeemote.zc.event.IJoystickListener;
import com.zeemote.zc.event.IStatusListener;
import com.zeemote.zc.event.JoystickEvent;
import com.zeemote.zc.ui.android.ControllerAndroidUi;

public class ZMControlPad implements IButtonListener, IJoystickListener, IStatusListener {
	private Controller controller;
	private ControllerAndroidUi controllerUi;
	private boolean controllerConnected = false;
	private InputHandler inputHandler;

	public ZMControlPad(InputHandler handler) {
		inputHandler = handler;
		controller = new Controller(1);
		controller.addStatusListener(this);
		controller.addJoystickListener(this);
		controller.addButtonListener(this);
	}

	public boolean isControllerConnected() {
		return controllerConnected;
	}
	
	public void connectController(Context ctx) {
		controllerUi = new ControllerAndroidUi(ctx, controller);
		controllerUi.startConnectionProcess();
	}
	
	public void disconnectController() {
		try {
			controller.disconnect();
		} catch (IOException e) {
		}
	}
	
	@Override
	public void joystickMoved(JoystickEvent e) {
		int x = e.getScaledX(-6, 6);
		int y = e.getScaledY(-6, 6);

		inputHandler.OnMoved(x, y);
	}

	@Override
	public void buttonPressed(ButtonEvent e) {
		switch(e.getButtonGameAction()) {
		case Controller.GAME_FIRE:
			inputHandler.attack.toggle(true);
			break;
		case Controller.GAME_A:
			inputHandler.menu.toggle(true);
			break;
		}
	}

	@Override
	public void buttonReleased(ButtonEvent e) {
		switch(e.getButtonID()) {
		case Controller.GAME_A:
			inputHandler.attack.toggle(false);
			break;
		case Controller.GAME_B:
			inputHandler.menu.toggle(false);
			break;
		}
	}
	
	public void batteryUpdate(BatteryEvent event) {
		Log.d("Zeemote", "Battery Update: cur=" + event.getCurrentLevel() + ", max="
				+ event.getMaximumLevel() + ", warn=" + event.getWarningLevel()
				+ ", min=" + event.getMinimumLevel());
	}

	public void connected(ControllerEvent event) {
		controllerConnected = true;
		Configuration config = event.getController().getConfiguration();
		Log.i("Zeemote", "Connected to controller: "+
			"Num Buttons=" + config.getButtonCount()+
			", Num Joysticks=" + config.getJoystickCount());
	}

	public void disconnected(DisconnectEvent arg0) {
		controllerConnected = false;
	}
}
