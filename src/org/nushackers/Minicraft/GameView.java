package org.nushackers.Minicraft;

import com.mojang.ld22.Game;

import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
	long lastUpdate = 0;
	long sleepTime=0;
	
	Game game;
	SurfaceHolder surfaceHolder;
	Context context;
	
	private Thread thread;
	
	void init(){
		SurfaceHolder holder = getHolder();
		holder.addCallback(this);
		
		game = new Game(holder, context.getResources());
		
		//thread = new Thread(game);
		setFocusable(true);
		setOnKeyListener(game.getInput());
	}
	

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

	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {}

	@Override
	public void surfaceCreated(SurfaceHolder sh) {	
		System.out.println("Surface created");
		game.setSurfaceHolder(sh);
       // if(!game.isRunning()){
            //When game is opened again in the Android O
		game.start();
            
       // }else{
            //creating the game Thread for the first time
        //    thread.start();
        //}

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
		boolean retry = true;
		game.setRunning(false);
		while (retry) {
			try {
				thread.join();
				retry = false;
			} catch (InterruptedException e) {
			}
		}
	}
	

}
