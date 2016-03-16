package com.ar.photobooth;

import java.io.ByteArrayOutputStream;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Im;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class ImageScreenActivity extends Activity {
	public static ImageView iv;
	ImageView imgButton;
	ImageView backButton;
	public static Bitmap bitmap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.imagescreen);
		iv = (ImageView) findViewById(R.id.imagefill);

		iv.setImageBitmap(bitmap);

		imgButton = (ImageView) findViewById(R.id.button_check);
		backButton = (ImageView) findViewById(R.id.button_back);
		imgButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(ImageScreenActivity.this,
						MainActivity.class);
			
				i.putExtra("flag", true);
				// MainActivity.mImageView.setImageBitmap(bitmap);
				startActivity(i);
			}
		});
		backButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// Bitmap backgroundimage = Bitmap.createBitmap(iv.getWidth(),
				// iv.getHeight(), Bitmap.Config.RGB_565);
				iv.buildDrawingCache();
				Bitmap backgroundimage = iv.getDrawingCache();
				ByteArrayOutputStream stream = new ByteArrayOutputStream();
				backgroundimage
						.compress(Bitmap.CompressFormat.PNG, 100, stream);
				byte[] byteArray = stream.toByteArray();

				Intent in1 = getIntent();
				in1.putExtra("image", byteArray);
				ImageScreenActivity.this.setResult(RESULT_OK, in1);
				finish();
			}
		});

	}
}
