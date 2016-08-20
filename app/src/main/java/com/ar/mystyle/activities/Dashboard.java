package com.ar.mystyle.activities;

import com.ar.mystyle.Util.CreateAdView;
import com.mystyle.R;
import com.sromku.simple.fb.Permission;
import com.sromku.simple.fb.SimpleFacebook;
import com.sromku.simple.fb.listeners.OnLoginListener;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class Dashboard extends Activity {

	TextView camera_btn;
	TextView photo_gallerybtn;
	TextView facebookBtn;
	TextView gallerybtn;
	Editor editor;
	SharedPreferences sharedpreferences,recievepreference;
	static final int REQUEST_TAKE_PHOTO = 1;
//	public static ImageView mImageView;
	LinearLayout linearl2;
	SimpleFacebook simpleFacebook;
	public static final String MyPREFERENCES = "MyPrefs" ;
	private static final int SELECT_PICTURE = 2;
	Intent photo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
/*


		try {
			PackageInfo info = getPackageManager().getPackageInfo(
					"com.mystyle",
					PackageManager.GET_SIGNATURES);
			for (Signature signature : info.signatures) {
				MessageDigest md = MessageDigest.getInstance("SHA");
				md.update(signature.toByteArray());
				Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
			}
		} catch (PackageManager.NameNotFoundException e) {

		} catch (NoSuchAlgorithmException e) {

		}

*/

		CreateAdView.getInstance(this);
		linearl2=(LinearLayout)findViewById(R.id.linear2);
		sharedpreferences = PreferenceManager
				.getDefaultSharedPreferences(Dashboard.this);
		editor = sharedpreferences.edit();
		recievepreference = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);

		camera_btn = (TextView) findViewById(R.id.camera);
		photo_gallerybtn = (TextView) findViewById(R.id.photo_gallery);
		facebookBtn = (TextView) findViewById(R.id.facebook);

		gallerybtn=(TextView)findViewById(R.id.gallery);
		//mImageView = (ImageView) findViewById(R.id.image);
		photo_gallerybtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(Dashboard.this,ShowSavedImage.class);
				startActivity(intent);
			}
		});
		facebookBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				OnLoginListener onLoginListener = new OnLoginListener() {

					@Override
					public void onLogin(String accessToken, List<Permission> acceptedPermissions, List<Permission> declinedPermissions) {
						// change the state of the button or do whatever you want
						Log.i("MyStyle", "Logged in");
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
		});
		camera_btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				/*
				 * Intent takePictureIntent = new Intent(
				 * MediaStore.ACTION_IMAGE_CAPTURE);
				 */
				// if (takePictureIntent.resolveActivity(getPackageManager()) !=
				// null) {
				// File photoFile = null;
				// try {
				// photoFile = createImageFile();
				// } catch (IOException ex) {
				// // Error occurred while creating the File
				//
				// }
				// // Continue only if the File was successfully created
				// if (photoFile != null) {
				// takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
				// Uri.fromFile(photoFile));
				// // mImageUri = Uri.fromFile(photoFile);
				// startActivityForResult(takePictureIntent,
				// REQUEST_TAKE_PHOTO);
				// }
				Intent intent = new Intent(
						android.provider.MediaStore.ACTION_IMAGE_CAPTURE);

				startActivityForResult(intent, REQUEST_TAKE_PHOTO);
				// }

			}
		});
		gallerybtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			//	if (Build.VERSION.SDK_INT <19){
				Intent  intent = new Intent(
						Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				intent.setType("image/*");
				startActivityForResult(intent, SELECT_PICTURE);
			}
		});

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		simpleFacebook.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			if (requestCode == REQUEST_TAKE_PHOTO) {
				photo = new Intent(Dashboard.this, EditorActivity.class);
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
				photo = new Intent(Dashboard.this, EditorActivity.class);
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
		.setIcon(android.R.drawable.ic_dialog_alert)
		.setTitle("PhotoBooth")
		.setIcon(R.drawable.icon)
		.setMessage("Are you sure you want to exit this application?")
		.setPositiveButton("Yes", new DialogInterface.OnClickListener()
		{
			@Override
			public void onClick(DialogInterface dialog, int which) {
				editor.commit();
				System.exit(0);    
			}

		})
		.setNegativeButton("No", null)
		.show(); 

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return super.onCreateOptionsMenu(menu);
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		CreateAdView.getInstance().setSinglaotonAdview(this);
		simpleFacebook=SimpleFacebook.getInstance(Dashboard.this);
		super.onResume();
	}
	@Override  
	public boolean onOptionsItemSelected(MenuItem item) {  
		switch (item.getItemId()) {  
		case R.id.settings:  
			Intent intentsetting=new Intent(Dashboard.this,Settings.class);
			startActivity(intentsetting);
			return true;     

		default:  
			return super.onOptionsItemSelected(item);  
		}  
	}  
	@Override
	protected void onDestroy() {

		// TODO Auto-generated method stub
		super.onDestroy();
	}
}
