package com.ar.photobooth;

import com.ar.photobooth.CreateAdView;


import android.R.mipmap;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.provider.MediaStore.MediaColumns;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.LinearLayout.LayoutParams;

public class MainActivity extends Activity {



	TextView camera_btn;
	TextView photo_gallerybtn;
	TextView facebookBtn;
	TextView gallerybtn;
	Editor editor;
	SharedPreferences sharedpreferences,recievepreference;
	static final int REQUEST_TAKE_PHOTO = 1;
	public static ImageView mImageView;
	public static Bitmap bitmap2;
	LinearLayout linearl2;
	// for library images
	public static final String MyPREFERENCES = "MyPrefs" ;
	private static final int SELECT_PICTURE = 2;
	Bitmap bitmap;
	Uri fileUri;
	Intent photo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		CreateAdView.getInstance(this);
		linearl2=(LinearLayout)findViewById(R.id.linear2);
		sharedpreferences = PreferenceManager
				.getDefaultSharedPreferences(MainActivity.this);
		editor = sharedpreferences.edit();
		recievepreference = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
		EditorActivity.isuserFacebooklogin=sharedpreferences.getBoolean("fbUserStatus", false);

		camera_btn = (TextView) findViewById(R.id.camera);
		photo_gallerybtn = (TextView) findViewById(R.id.photo_gallery);
		facebookBtn = (TextView) findViewById(R.id.facebook);

		gallerybtn=(TextView)findViewById(R.id.gallery);
		mImageView = (ImageView) findViewById(R.id.image);
		try {
			Intent i = getIntent();
			boolean bool = i.getExtras().getBoolean("flag");
			if (bool == true) {
				mImageView.setImageBitmap(ImageScreenActivity.bitmap);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		photo_gallerybtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(MainActivity.this,showSavedImage.class);
				startActivity(intent);
			}
		});
		facebookBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				/*Intent i = new Intent(MainActivity.this, FacebookLogin.class);
				startActivity(i);*/

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
				Intent intent = new Intent();
				intent.setType("image/*");
				intent.setAction(Intent.ACTION_GET_CONTENT);
				intent.addCategory(Intent.CATEGORY_OPENABLE);
				startActivityForResult(intent, SELECT_PICTURE);
			//	} 
				/*else {
				    Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
				    intent.addCategory(Intent.CATEGORY_OPENABLE);
				    intent.setType("image/*");
				    startActivityForResult(intent, GALLERY_KITKAT_INTENT_CALLED);
				}*/

			}
		});

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			if (requestCode == REQUEST_TAKE_PHOTO) {

				/*
				 * try { String selectedImagePath =
				 * getAbsolutePath(data.getData()); bitmap =
				 * decodeSampledBitmapFromResource(getResources(),
				 * selectedImagePath, 320, 480);
				 * 
				 * mImageView.setImageBitmap(bitmap);
				 * 
				 * } catch (Exception ex) { ex.printStackTrace();
				 * 
				 * 
				 * }
				 */

				photo = new Intent(MainActivity.this, EditorActivity.class);
				photo.putExtras(data);
				startActivity(photo);
			} else if (requestCode == SELECT_PICTURE) {

				// Intent i=new Intent(MainActivity.this, EditorActivity.class);
				// i.putExtra("library_pic", data);
				// startActivity(i);
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
		.setIcon(android.R.drawable.ic_dialog_alert)
		.setTitle("PhotoBooth")
		.setIcon(R.drawable.icon)
		.setMessage("Are you sure you want to exit this application?")
		.setPositiveButton("Yes", new DialogInterface.OnClickListener()
		{
			@Override
			public void onClick(DialogInterface dialog, int which) {
				editor.putBoolean("fbUserStatus", EditorActivity.isuserFacebooklogin);
				editor.commit();
				System.exit(0);    
			}

		})
		.setNegativeButton("No", null)
		.show(); 

	}

	public String getAbsolutePath(Uri uri) {
		String[] projection = { MediaColumns.DATA };
		@SuppressWarnings("deprecation")
		Cursor cursor = managedQuery(uri, projection, null, null, null);
		if (cursor != null) {
			int column_index = cursor.getColumnIndexOrThrow(MediaColumns.DATA);
			cursor.moveToFirst();
			return cursor.getString(column_index);
		} else
			return null;
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
		super.onResume();
	}
	@Override  
	public boolean onOptionsItemSelected(MenuItem item) {  
		switch (item.getItemId()) {  
		case R.id.settings:  
			Intent intentsetting=new Intent(MainActivity.this,Settings.class);
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
