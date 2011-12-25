package org.nushackers.Minicraft;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class MultiButton extends View{
	String TAG = "Multi-touch Button";

	private Paint btnPaint;
	private Paint hitPaint;

	private int diameter;
	private int radius;
	private int innerPadding;
	private int highlightThickness;

	private boolean touched;

	private int offsetX;

	private int offsetY;
	public MultiButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		init();
	}
	public MultiButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init();
	}
	public MultiButton(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init();
	}
	private void init() {
		innerPadding = 10;
		highlightThickness = innerPadding;

		btnPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		btnPaint.setColor(Color.argb(100, 255,255,255));
		btnPaint.setStrokeWidth(1);
		btnPaint.setStyle(Style.FILL_AND_STROKE);

		hitPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		hitPaint.setColor(Color.argb(100, 255,0,0));
		hitPaint.setStrokeWidth(highlightThickness);
		hitPaint.setStyle(Style.STROKE);


		touched = false;
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int measuredWidth = measure(widthMeasureSpec);
		int measuredHeight = measure(heightMeasureSpec);
		setMeasuredDimension(measuredWidth, measuredHeight);
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right,int bottom) {
		super.onLayout(changed, left, top, right, bottom);
		diameter = Math.min(getMeasuredWidth(), getMeasuredHeight());
		radius = diameter/2 - innerPadding;
	}
	@Override
	protected void onDraw(Canvas canvas) {
		canvas.save();
		canvas.drawCircle(radius,radius,radius-innerPadding, btnPaint);
		if(touched)	canvas.drawCircle(radius,radius,radius-highlightThickness, hitPaint);
		canvas.restore();
	}
	public static final int INVALID_POINTER_ID = -1;
	private int pointerId = INVALID_POINTER_ID;
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		final int action = event.getAction();
		switch (action & MotionEvent.ACTION_MASK) {
			case MotionEvent.ACTION_MOVE: {if(pointerId!= INVALID_POINTER_ID) return true;}
			case MotionEvent.ACTION_CANCEL:
			case MotionEvent.ACTION_UP: {
				if(pointerId != INVALID_POINTER_ID) release();
				break;
			}
			case MotionEvent.ACTION_POINTER_UP:{
				if(pointerId != INVALID_POINTER_ID){
			        final int p = (action & MotionEvent.ACTION_POINTER_INDEX_MASK) >> MotionEvent.ACTION_POINTER_INDEX_SHIFT;
			        final int pointerId = event.getPointerId(p);
			        if(pointerId == this.pointerId){
			        	release();
				        return true;
			        }
				}
				break;
			}
			case MotionEvent.ACTION_DOWN: {
				if(pointerId == INVALID_POINTER_ID) {
					int x = (int) event.getX();
					if(x >= offsetX && x < (offsetX + diameter))  {
						press(event.getPointerId(0));
						return true;
					}
				}
				break;
			}
			case MotionEvent.ACTION_POINTER_DOWN: {
		    	if ( pointerId == INVALID_POINTER_ID ) {
			        final int pointerIndex = (action & MotionEvent.ACTION_POINTER_INDEX_MASK) >> MotionEvent.ACTION_POINTER_INDEX_SHIFT;
			        final int pointerId = event.getPointerId(pointerIndex);
			        int x = (int) event.getX(pointerId);
			        if(x >= offsetX && x < (offsetX + diameter)){
			        	press(event.getPointerId(0));
			        	return true;
			        }
		    	}
		    	break;
			}
		}

		return false;
	}
	
	private void release() {
		Log.v(TAG,"release");
		touched = false;
		invalidate();
		this.pointerId = INVALID_POINTER_ID;
	}
	private void press(int pointerId) {
		Log.v(TAG,"press");
		touched = true;
		invalidate();
		this.pointerId = pointerId;
	}

	private int measure(int measureSpec) {
		int result = 0;
		// Decode the measurement specifications.
		int specMode = MeasureSpec.getMode(measureSpec);
		int specSize = MeasureSpec.getSize(measureSpec);
		if (specMode == MeasureSpec.UNSPECIFIED) {
			// Return a default size of 200 if no bounds are specified.
			result = 200;
		} else {
			// As you want to fill the available space
			// always return the full available bounds.
			result = specSize;
		}
		return result;
	}
	public void setTouchOffset(int x, int y) {
		offsetX = x;
		offsetY = y;
	}

}
