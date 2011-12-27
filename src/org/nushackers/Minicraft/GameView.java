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
	private long lastUpdate = 0;
	private long sleepTime=0;
	public Game game;
	private SurfaceHolder surfaceHolder;
	public Context context;
	private Paint drawPaint;
	private int ww, hh;
	private float sx, sy;
	private int xo, yo;
	
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
		this.context = context;
		init();
	}
	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		init();
	}


	private Runnable doInvalidate = new Runnable() {		
		public void run() {
			invalidate();
		}
	};
	public void repaint() {
		post(doInvalidate);
	}

	private void recalculateScale(Canvas c)	{
		int canvasHeight = c.getHeight();
		int canvasWidth = c.getWidth();
		if (canvasHeight * Game.WIDTH / Game.HEIGHT > canvasWidth)
		{
			ww = canvasWidth;
			hh = canvasWidth * Game.HEIGHT / Game.WIDTH;
		}
		else
		{
			ww = canvasHeight * Game.WIDTH / Game.HEIGHT;
			hh = canvasHeight;
		}
		sx = (float)ww / Game.WIDTH;
		sy = (float)hh / Game.HEIGHT;
		xo = (canvasWidth - ww) / 2;
		yo = (canvasHeight - hh) / 2;
		
		
	}
	
	@Override
	protected void onDraw(Canvas c) {
		recalculateScale(c);
		c.scale(sx,sy,0,0);
		c.drawBitmap(pixels,0,Game.WIDTH,xo/sx,yo/sy,Game.WIDTH,Game.HEIGHT,false, drawPaint);
	}

}
