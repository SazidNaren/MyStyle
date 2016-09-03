package com.ar.mystyle.activities;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import com.ar.mystyle.Util.CreateAdView;
import com.style.facechanger.R;

public class SettingsActivity extends Activity{
	ImageButton fblogout;
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		if(Build.VERSION.SDK_INT>11)
			getActionBar().setDisplayHomeAsUpEnabled(true);
		fblogout=(ImageButton)findViewById(R.id.facebooklogout);
		fblogout.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Toast.makeText(getApplicationContext(), "you are successfully logout..", Toast.LENGTH_SHORT).show();
			}
		});
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		CreateAdView.getInstance().setSinglaotonAdview(this);
		super.onResume();
	}


}