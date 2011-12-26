package org.nushackers.Minicraft;

import org.nushackers.Minicraft.MultiButton.MultiTouchListener;

import com.MobileAnarchy.Android.Widgets.Joystick.JoystickMovedListener;
import com.MobileAnarchy.Android.Widgets.Joystick.JoystickView;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class ControlPad extends LinearLayout {
	
	private JoystickView joystick;
	private MultiButton button1;
	private MultiButton button2;
	
	private View pad;

	public ControlPad(Context context, AttributeSet attrs) {
		super(context, attrs);
		joystick = new JoystickView(context, attrs);
		button1 = new MultiButton(context, attrs);
		button2 = new MultiButton(context, attrs);
		init();
	}
	public ControlPad(Context context) {
		super(context);
		joystick = new JoystickView(context);
		button1 = new MultiButton(context);
		button2 = new MultiButton(context);
		init();
	}
	
	
	private void init() {
		setOrientation(LinearLayout.HORIZONTAL);
		pad = new View(getContext());
	}
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		removeView(joystick);
		removeView(button1);
		removeView(button2);

		int padW = getMeasuredWidth()-(getMeasuredHeight()*3);
		int joyWidth = ((getMeasuredWidth()-padW)/3);
		LayoutParams joyLParams = new LayoutParams(joyWidth,getMeasuredHeight());
		joystick.setLayoutParams(joyLParams);
		button1.setLayoutParams(joyLParams);
		button2.setLayoutParams(joyLParams);
		button1.TAG = "L";
		button2.TAG = "R";
		addView(button1);
		addView(button2);
		
		ViewGroup.LayoutParams padLParams = new ViewGroup.LayoutParams(padW,getMeasuredHeight());
		removeView(pad);
		pad.setLayoutParams(padLParams);
		addView(pad);
		
		addView(joystick);
	}
	
	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {
		super.onLayout(changed, left, top, right, bottom);
		joystick.setTouchOffset(joystick.getLeft(), joystick.getTop());
		button1.setTouchOffset(button1.getLeft(), button1.getTop());
		button2.setTouchOffset(button2.getLeft(), button2.getTop());
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
	}
	
	@Override
	protected void dispatchDraw(Canvas canvas) {
		super.dispatchDraw(canvas);
	}
	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		return	button1.dispatchTouchEvent(event) |
				button2.dispatchTouchEvent(event) |
				joystick.dispatchTouchEvent(event);
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return button1.onTouchEvent(event) |
				button2.onTouchEvent(event) |
				joystick.onTouchEvent(event);
	}
	
	public void setJoystickListener(JoystickMovedListener l){
		joystick.setOnJoystickMovedListener(l);
	}
	
	public void setButton1Listener(MultiTouchListener l) {
		button1.setTouchEventListener(l);
	}
	public void setButton2Listener(MultiTouchListener l) {
		button2.setTouchEventListener(l);
	}

}
