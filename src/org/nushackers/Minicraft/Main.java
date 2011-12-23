package org.nushackers.Minicraft;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

public class Main extends Activity {
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		View v = (View) findViewById(R.id.gamedisplay);
		Bitmap b = Bitmap.createBitmap(200, 200, Bitmap.Config.ARGB_8888);
		Canvas cv = new Canvas(b);
		
		
	}
	
}
