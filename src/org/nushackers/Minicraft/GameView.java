package org.nushackers.Minicraft;

import com.mojang.ld22.Game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

public class GameView extends View {
	long lastUpdate = 0;
	long sleepTime=0;
	Game game;
	SurfaceHolder surfaceHolder;
	public static Context context;
	Paint drawPaint;
	
	public int[] pixels;
	void init(){
		game = new Game(this);
		drawPaint = new Paint();
        drawPaint.setAntiAlias(false);
        
        pixels = new int[Game.HEIGHT*Game.WIDTH];
		//thread = new Thread(game);
        
		setFocusable(true);
		
		setOnKeyListener(game.getInput());

	}
	
	//public Runnable paintJob = 

	public GameView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		GameView.context = context;
		init();
	}
	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs);
		GameView.context = context;
		init();
	}


	//private boolean first = true;
	private float scale;
	private float ww,hh;
	int xo,yo;
	public void setDimensions(int width, int height) {
		scale = Math.min(
				height/(float)Game.HEIGHT,
				width/(float)Game.WIDTH
				);
		ww = Game.WIDTH * this.scale;
		hh = Game.HEIGHT * this.scale;
		xo = (int)(width - ww) / 2;
		yo = (int)(height - hh) / 2;
		System.out.println(width+"x"+height+":"+scale);
	}

	
	private Runnable doInvalidate = new Runnable() {		
		public void run() {
			invalidate();
		}
	};
	public void repaint() {
		post(doInvalidate);

	}
	

	@Override
	protected void onDraw(Canvas c) {
		c.scale(scale,scale,xo,yo);
		c.drawBitmap(pixels,0,Game.WIDTH,xo,yo,Game.WIDTH,Game.HEIGHT,false, drawPaint);
	}

}
