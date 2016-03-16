package com.ar.mystyle.activities;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.mystyle.R;

public class SplashScreen extends Activity{
	Intent mainIntent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		mainIntent = new Intent(this, Dashboard.class);

		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {

				SplashScreen.this.startActivity(mainIntent);
				SplashScreen.this.finish(); 

			}
		}, 1000);
	}
}
