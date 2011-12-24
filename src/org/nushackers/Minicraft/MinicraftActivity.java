package org.nushackers.Minicraft;

import android.app.Activity;
import android.os.Bundle;

public class MinicraftActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	System.out.println("Activity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
}