package com.ar.mystyle.activities;

import com.ar.mystyle.Util.Utility;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.sromku.simple.fb.Permission;
import com.sromku.simple.fb.SimpleFacebook;
import com.sromku.simple.fb.listeners.OnLoginListener;
import com.style.facechanger.R;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends Activity implements OnClickListener {

	private LinearLayout camera_btn;
	private LinearLayout photo_gallerybtn,facebookBtn,gallerybtn;
	private Editor editor;
	private SharedPreferences sharedpreferences,recievepreference;
	static final int REQUEST_TAKE_PHOTO = 1;
//	public static ImageView mImageView;
	private LinearLayout linearl2;
	private SimpleFacebook simpleFacebook;
	private final String MyPREFERENCES = "MyPrefs" ;
	private static final int SELECT_PICTURE = 2;
	private Intent photo;
	private AdView mAdView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		FacebookSdk.sdkInitialize(getApplicationContext());
		AppEventsLogger.activateApp(this);
		linearl2=(LinearLayout)findViewById(R.id.linear2);
		sharedpreferences = PreferenceManager
				.getDefaultSharedPreferences(MainActivity.this);
		editor = sharedpreferences.edit();
		recievepreference = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
		mAdView = (AdView) findViewById(R.id.adView);
		camera_btn = (LinearLayout) findViewById(R.id.ll_camera);
		photo_gallerybtn = (LinearLayout) findViewById(R.id.ll_my_collection);
		facebookBtn = (LinearLayout) findViewById(R.id.ll_facebook);
		gallerybtn=(LinearLayout)findViewById(R.id.ll_gallery);
		camera_btn.setOnClickListener(this);
		photo_gallerybtn.setOnClickListener(this);
		facebookBtn.setOnClickListener(this);
		gallerybtn.setOnClickListener(this);
		AdRequest adRequest = new AdRequest.Builder()
				.build();
		mAdView.loadAd(adRequest);
		checkPermissionInMarshMallow();
	}

	private void checkPermissionInMarshMallow() {
		if (Build.VERSION.SDK_INT >= 23) {
			if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {

			}
			else {
				ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1002);
			}
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		simpleFacebook.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			if (requestCode == REQUEST_TAKE_PHOTO) {
				photo = new Intent(MainActivity.this, EditorActivity.class);
				photo.putExtras(data);
				startActivity(photo);
			} else if (requestCode == SELECT_PICTURE) {
				Uri selectedImage = data.getData();
				String[] filePathColumn = { MediaStore.Images.Media.DATA };

				Cursor cursor = getContentResolver().query(selectedImage,
						filePathColumn, null, null, null);
				cursor.moveToFirst();

				int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
				String filePath = cursor.getString(columnIndex);
				if(filePath==null)
				{
				Toast.makeText(getApplicationContext(), "Please select image from gallery..", Toast.LENGTH_SHORT).show();	
				return ;
			
				}
				cursor.close();
				photo = new Intent(MainActivity.this, EditorActivity.class);
				if (filePath.startsWith("http"))
					photo.putExtra("url", filePath);
				else
					photo.putExtra("path", filePath);

				startActivity(photo);
			}
		}
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub

		new AlertDialog.Builder(this)
		.setIcon(R.mipmap.ic_launcher		)
		.setTitle("PhotoBooth")
	//	.setIcon(R.drawable.icon)
		.setMessage("Are you sure you want to exit this application?")
		.setPositiveButton("Yes", new DialogInterface.OnClickListener()
		{
			@Override
			public void onClick(DialogInterface dialog, int which) {
				editor.commit();
				finish();
			}

		})
		.setNegativeButton("No", null)
		.show(); 

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}
	@Override  
	public boolean onOptionsItemSelected(MenuItem item) {  
		switch (item.getItemId()) {  
		case R.id.settings:  
			Intent intentsetting=new Intent(MainActivity.this,SettingsActivity.class);
			startActivity(intentsetting);
			return true;     

		default:  
			return super.onOptionsItemSelected(item);  
		}  
	}
	@Override
	public void onPause() {
		if (mAdView != null) {
			mAdView.pause();
		}
		super.onPause();
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		Utility.isNetworkConnected(mAdView,this);
		simpleFacebook=SimpleFacebook.getInstance(MainActivity.this);
		if (mAdView != null) {
			mAdView.resume();
		}
		super.onResume();
	}


	@Override
	public void onDestroy() {
		if (mAdView != null) {
			mAdView.destroy();
		}
		super.onDestroy();
	}

	@Override
	public void onClick(View v) {

		int id=v.getId();
		switch (id) {
			case R.id.ll_gallery:
				Intent  intent_photp_gallery = new Intent(
						Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				intent_photp_gallery.setType("image/*");
				startActivityForResult(intent_photp_gallery, SELECT_PICTURE);
				break;

			case R.id.ll_facebook:
				getFacebookLogin();
				break;

			case R.id.ll_camera:
				Intent intent2 = new Intent(
						android.provider.MediaStore.ACTION_IMAGE_CAPTURE);

				startActivityForResult(intent2, REQUEST_TAKE_PHOTO);
				break;

			case R.id.ll_my_collection:
				Intent intent = new Intent(MainActivity.this, MyCollectionActivity.class);
				startActivity(intent);
				overridePendingTransition(R.anim.bottom_to_top, 0);
				break;
		}
	}

	private void getFacebookLogin()
	{
		if(!Utility.isNetworkConnected(mAdView,MainActivity.this)) {
			Snackbar snackbar = Snackbar
					.make(findViewById(R.id.mainlayout), "Internet Connection not available.", Snackbar.LENGTH_LONG);
			snackbar.setActionTextColor(Color.RED);
			snackbar.show();
			return;
		}OnLoginListener onLoginListener = new OnLoginListener() {

		@Override
		public void onLogin(String accessToken, List<Permission> acceptedPermissions, List<Permission> declinedPermissions) {
			// change the state of the button or do whatever you want
			Log.i("MyStyle", "Logged in");
			startActivity(new Intent(MainActivity.this,FacebookAlbumsActivity.class));
			overridePendingTransition(R.anim.bottom_to_top, 0);
		}

		@Override
		public void onCancel() {
			// user canceled the dialog
			Log.i("MyStyle", "Logged in");
		}

		@Override
		public void onFail(String reason) {
			// failed to login
			Log.i("MyStyle", "Logged in");
		}

		@Override
		public void onException(Throwable throwable) {
			// exception from facebook

			Log.i("MyStyle", "Logged in");
		}

	};
		simpleFacebook.login(onLoginListener);
	}
}
