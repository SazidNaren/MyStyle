package com.ar.mystyle.activities;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import com.ar.mystyle.Util.CreateAdView;
import com.ar.mystyle.ImageIds;
import com.ar.mystyle.adapters.GetImageAdapterHori;
import com.ar.mystyle.adapters.SelectImageAdapter;
import com.ar.mystyle.interfaces.ClickListener;
import com.imagezoom.ImageAttacher;
import com.imagezoom.ImageAttacher.OnMatrixChangedListener;
import com.imagezoom.ImageAttacher.OnPhotoTapListener;
import com.mystyle.R;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ViewSwitcher.ViewFactory;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.Gallery;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

@SuppressWarnings("deprecation")
public class EditorActivity extends Activity implements ViewFactory,ClickListener {
	Bitmap bitmap;
	static RelativeLayout updownscroll1;
	Bitmap bitmap1;
	ImageView scrollLeft,scrollRight;
	ImageView imageView,imgDelete;
	ImageView imageView1,imgback1;
	String filePath=null;
	Bitmap Capfground,Goggfground,MHeirfground,Lipsfground,Beardfground,WHeirfground;
	static Bitmap hideback;
	//LinearLayout llayoutbtnadd;
	Gallery galleryhoriz;
	static boolean isimagesaved;
	FrameLayout frame;
	Canvas canvasview;
	DisplayMetrics dm;
	SelectImageAdapter imageAdapter;
	public static int dHeight,dWidth;
	ImageView Share1;
	static boolean capdelete,googgledelete,manheirdelete,bearddelete,womanheirdelete,lipsdelete;
	public static boolean IscapDeleted,isLipsDeleted;
	public static boolean isGoggleDeleted,isManHeirDeleted;
	public static boolean isWomanHeirDeleted,isBeardDeleted;
	public static boolean isFirstImage,Iscapselected,isLipsSelected;
	public static boolean isGoggleSelected,isManHeirSelected,isSaved;
	public static boolean isWomanHeirSElected,isBeardSelected;
	public static ImageView Save1;
	public static boolean bagFlag = false;
	private RecyclerView recyclerView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.editor);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		hideback=BitmapFactory.decodeResource(getResources(),R.drawable.hide);
		galleryhoriz=(Gallery)findViewById(R.id.ho_gallery);
		frame = (FrameLayout) findViewById(R.id.frame);
		imageView = (ImageView) findViewById(R.id.imageset);
		imgback1 = (ImageView) findViewById(R.id.imgback);
		imgDelete=(ImageView)findViewById(R.id.delete);
		updownscroll1=(RelativeLayout)findViewById(R.id.updownscroll);
		imageView1 = (ImageView) findViewById(R.id.imageset1);
		Save1 = (ImageView) findViewById(R.id.save);
		Share1 = (ImageView) findViewById(R.id.share);
		scrollLeft=(ImageView)findViewById(R.id.arrowleft);
		scrollRight=(ImageView)findViewById(R.id.arrowright);
		recyclerView=(RecyclerView)findViewById(R.id.recyclerview_select_images);
		GridLayoutManager mLayoutManager = new GridLayoutManager(this,4);
		recyclerView.setLayoutManager(mLayoutManager);
		dm = new DisplayMetrics();
		((Activity) this).getWindowManager().getDefaultDisplay()
				.getMetrics(dm);
		dHeight=dm.heightPixels;
		dWidth=dm.widthPixels;
		final ImageIds imgIds=ImageIds.getInstance(this);
		galleryhoriz.setAdapter(new GetImageAdapterHori(this));
		galleryhoriz.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
				recyclerView.setVisibility(View.VISIBLE);
				int i;
				switch (position) {
					case 0:
						for (i = 0; i < imgIds.getImageIdCaps().size(); i++) {
							imageAdapter=new SelectImageAdapter(0,imgIds.getImageIdCaps(),EditorActivity.this,EditorActivity.this);
							recyclerView.setAdapter(imageAdapter);
							recyclerView.setVisibility(View.VISIBLE);

							ImageView imgview = new ImageView(getApplicationContext());
							imgview.setId(i);
							LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(Canvas.widthScreen / 4, Canvas.heightScreen / 5);
							imgview.setLayoutParams(layoutParams);
							imgview.setImageDrawable(imgIds.getImageIdCaps().get(i));
						//	llayoutbtnadd.addView(imgview);
							updownscroll1.setVisibility(View.VISIBLE);
							final int j = i;
							IscapDeleted = true;
							setAllSelectedFalse();
							imgview.setOnClickListener(new OnClickListener() {
								@Override
								public void onClick(View arg0) {
									// TODO Auto-generated method stub
									IscapDeleted = false;
									Capfground =  ((BitmapDrawable)imgIds.getImageIdCaps().get(j)).getBitmap();
									canvasview.setForeground(Capfground);
									Iscapselected = true;
									updownscroll1.setVisibility(View.INVISIBLE);
									canvasview.invalidate();
								}
							});
						}
						imgDelete.setOnClickListener(new OnClickListener() {
							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								capdelete = true;
								canvasview.invalidate();
							}
						});
						break;
					case 1:
						imageAdapter=new SelectImageAdapter(1,imgIds.getImageIdGoggles(),EditorActivity.this,EditorActivity.this);
						recyclerView.setAdapter(imageAdapter);
						recyclerView.setVisibility(View.VISIBLE);

						for (i = 0; i < imgIds.getImageIdGoggles().size(); i++) {
							ImageView imgview = new ImageView(getApplicationContext());
							imgview.setImageDrawable(imgIds.getImageIdGoggles().get(i));
							LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(Canvas.widthScreen / 4, Canvas.heightScreen / 5);
							imgview.setLayoutParams(layoutParams);
						//	llayoutbtnadd.addView(imgview);
							updownscroll1.setVisibility(View.VISIBLE);
							final int j = i;
							setAllSelectedFalse();
							isGoggleDeleted = true;
							imgview.setOnClickListener(new OnClickListener() {

								@Override
								public void onClick(View arg0) {
									// TODO Auto-generated method stub
									isGoggleDeleted = false;
									Goggfground = 	MHeirfground = ((BitmapDrawable)imgIds.getImageIdGoggles().get(j)).getBitmap();
									canvasview.setForeground(Goggfground);
									canvasview.invalidate();
									isGoggleSelected = true;

									updownscroll1.setVisibility(View.INVISIBLE);
								}
							});
						}
						imgDelete.setOnClickListener(new OnClickListener() {
							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub

								googgledelete = true;

								canvasview.invalidate();
							}
						});
						break;
					case 2:
						imageAdapter=new SelectImageAdapter(1,imgIds.getImageIsHeirs(),EditorActivity.this,EditorActivity.this);
						recyclerView.setAdapter(imageAdapter);
						recyclerView.setVisibility(View.VISIBLE);

						for (i = 0; i < imgIds.getImageIsHeirs().size(); i++) {
							ImageView imgview = new ImageView(getApplicationContext());
							imgview.setImageDrawable(imgIds.getImageIsHeirs().get(i));
							LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(Canvas.widthScreen / 4, Canvas.heightScreen / 5);
							imgview.setLayoutParams(layoutParams);
							updownscroll1.setVisibility(View.VISIBLE);
						//	llayoutbtnadd.addView(imgview);
							final int j = i;
							setAllSelectedFalse();
							isManHeirDeleted = true;
							imgview.setOnClickListener(new OnClickListener() {

								@Override
								public void onClick(View arg0) {
									// TODO Auto-generated method stub
									isManHeirDeleted = false;
									MHeirfground = ((BitmapDrawable)imgIds.getImageIsHeirs().get(j)).getBitmap();
									canvasview.setForeground(MHeirfground);
									canvasview.invalidate();
									isManHeirSelected = true;

									updownscroll1.setVisibility(View.INVISIBLE);
								}
							});
						}
						imgDelete.setOnClickListener(new OnClickListener() {
							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub

								manheirdelete = true;

								canvasview.invalidate();
							}
						});
						break;
					case 3:
						for (i = 0; i < imgIds.getImageIsLips().size(); i++) {
							ImageView imgview = new ImageView(getApplicationContext());
							imgview.setImageDrawable(imgIds.getImageIsLips().get(i));
							LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(Canvas.widthScreen / 4, Canvas.heightScreen / 5);
							imgview.setLayoutParams(layoutParams);
							updownscroll1.setVisibility(View.VISIBLE);
						//	llayoutbtnadd.addView(imgview);
							final int j = i;
							setAllSelectedFalse();
							isLipsDeleted = true;
							imgview.setOnClickListener(new OnClickListener() {

								@Override
								public void onClick(View arg0) {
									// TODO Auto-generated method stub
									isLipsDeleted = false;
									Lipsfground = ((BitmapDrawable)imgIds.getImageIsLips().get(j)).getBitmap();
									canvasview.setForeground(Lipsfground);
									canvasview.invalidate();
									isLipsSelected = true;

									updownscroll1.setVisibility(View.INVISIBLE);
								}
							});
						}
						imgDelete.setOnClickListener(new OnClickListener() {
							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub

								lipsdelete = true;

								canvasview.invalidate();
							}
						});
						break;
					case 4:
						for (i = 0; i < imgIds.getImageIsMouths().size(); i++) {
							ImageView imgview = new ImageView(getApplicationContext());
							imgview.setImageDrawable(imgIds.getImageIsMouths().get(i));
							LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(Canvas.widthScreen / 4, Canvas.heightScreen / 5);
							imgview.setLayoutParams(layoutParams);
							updownscroll1.setVisibility(View.VISIBLE);
						//	llayoutbtnadd.addView(imgview);
							final int j = i;
							setAllSelectedFalse();
							isBeardDeleted = true;
							imgview.setOnClickListener(new OnClickListener() {

								@Override
								public void onClick(View arg0) {
									// TODO Auto-generated method stub
									isBeardDeleted = false;
									Beardfground = ((BitmapDrawable)(imgIds.getImageIsMouths().get(j))).getBitmap();
									canvasview.setForeground(Beardfground);
									canvasview.invalidate();
									isBeardSelected = true;

									updownscroll1.setVisibility(View.INVISIBLE);
								}
							});
						}
						imgDelete.setOnClickListener(new OnClickListener() {
							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								bearddelete = true;
								canvasview.invalidate();
							}
						});
						break;
					case 5:
						for (i = 0; i < imgIds.getImageIsW_Heir().size(); i++) {
							ImageView imgview = new ImageView(getApplicationContext());
							imgview.setImageDrawable(imgIds.getImageIsW_Heir().get(i));
							LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(Canvas.widthScreen / 4, Canvas.heightScreen / 5);
							imgview.setLayoutParams(layoutParams);
							updownscroll1.setVisibility(View.VISIBLE);
							//llayoutbtnadd.addView(imgview);
							final int j = i;
							setAllSelectedFalse();
							isWomanHeirDeleted = true;
							imgview.setOnClickListener(new OnClickListener() {

								@Override
								public void onClick(View arg0) {
									// TODO Auto-generated method stub
									isWomanHeirDeleted = false;
									WHeirfground =  ((BitmapDrawable)(imgIds.getImageIsW_Heir().get(j))).getBitmap();
									canvasview.setForeground(WHeirfground);
									canvasview.invalidate();
									isWomanHeirSElected = true;

									updownscroll1.setVisibility(View.INVISIBLE);
								}
							});
						}
						imgDelete.setOnClickListener(new OnClickListener() {
							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								womanheirdelete = true;
								canvasview.invalidate();
							}
						});
						break;
				}
			}
		});

		scrollLeft.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (galleryhoriz.getSelectedItemPosition() != galleryhoriz.getCount() - 1)
					galleryhoriz.setSelection(galleryhoriz.getSelectedItemPosition() + 1);

			}
		});
		scrollRight.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (galleryhoriz.getSelectedItemPosition() != 0)
					galleryhoriz.setSelection(galleryhoriz.getSelectedItemPosition() - 1);

			}
		});
		canvasview = new Canvas(EditorActivity.this);
		frame.addView(canvasview);
		if (getIntent().hasExtra("data")) {
			bitmap = (Bitmap) getIntent().getExtras().get("data");
			isFirstImage=true;
			canvasview.setBackground(bitmap);
		}
		else if (getIntent().hasExtra("path")) {
			bitmap = decodeSampledBitmapFromResource(getResources(),
					getIntent().getStringExtra("path"), dWidth/2, dHeight/2);
			if(!(getIntent().getStringExtra("path").endsWith(".jpg")||getIntent().getStringExtra("path").endsWith(".jpeg")||getIntent().getStringExtra("path").endsWith(".gif")||getIntent().getStringExtra("path").endsWith(".png")))
			{
				Toast.makeText(getApplicationContext(), "please select an image", Toast.LENGTH_SHORT).show();
				return;
			}
			isFirstImage=true;
			canvasview.setBackground(bitmap);
		}
		else if (getIntent().getExtras().getBoolean("album")) {
			isFirstImage=true;
			canvasview.setBackground(bitmap);
		}
	}


	void setAllSelectedFalse()
	{
		IscapDeleted=false;
		isBeardDeleted=false;
		isLipsDeleted=false;
		isGoggleDeleted=false;
		isManHeirDeleted=false;
		isWomanHeirDeleted=false;
		isBeardSelected=false;
		Iscapselected=false;
		isLipsSelected=false;
		isGoggleSelected=false;
		isManHeirSelected=false;
		isWomanHeirSElected=false;
	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		Canvas.Beardfground=null;
		Canvas.Capfground=null;
		Canvas.Goggfground=null;
		Canvas.Lipsfground=null;
		Canvas.MHeirfground=null;
		Canvas.WHeirfground=null;
		super.onBackPressed();
	}

	@Override
	protected void onResume() {
		CreateAdView.getInstance().setSinglaotonAdview(this);
		// TODO Auto-generated method stub
		super.onResume();
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.editor_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}



	private boolean storeImage(Bitmap imageData, String filename) {
		// get path to external storage (SD card)
		String iconsStoragePath = Environment.getExternalStorageDirectory()
				+ "/facechanger/myImages/";
		File sdIconStorageDir = new File(iconsStoragePath);

		// create storage directories, if they don't exist
		sdIconStorageDir.mkdirs();

		try {
			filePath = sdIconStorageDir.toString() + filename;
			FileOutputStream fileOutputStream = new FileOutputStream(filePath);

			BufferedOutputStream bos = new BufferedOutputStream(
					fileOutputStream);

			// choose another format if PNG doesn't suit you
			imageData.compress(CompressFormat.PNG, 100, bos);

			bos.flush();
			bos.close();

		} catch (FileNotFoundException e) {
			Log.w("TAG", "Error saving image file: " + e.getMessage());
			return false;
		} catch (IOException e) {
			Log.w("TAG", "Error saving image file: " + e.getMessage());
			return false;
		}

		return true;
	}

	public static Bitmap decodeSampledBitmapFromResource(Resources res,
														 String respath, int reqWidth, int reqHeight) {
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(respath, options);
		options.inSampleSize = calculateInSampleSize(options, reqWidth,
				reqHeight);
		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeFile(respath, options);
	}

	public static int calculateInSampleSize(BitmapFactory.Options options,
											int reqWidth, int reqHeight) {
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;
		if (height > reqHeight || width > reqWidth) {
			final int halfHeight = height / 2;
			final int halfWidth = width / 2;
			while ((halfHeight / inSampleSize) > reqHeight
					&& (halfWidth / inSampleSize) > reqWidth) {
				inSampleSize *= 2;
			}
		}

		return inSampleSize;
	}

	public void imazeZommer(ImageView imgViw) {
		ImageAttacher at = new ImageAttacher(imgViw);
		ImageAttacher.MAX_ZOOM = 2.0f;
		ImageAttacher.MIN_ZOOM = .5f;

		at.setOnMatrixChangeListener(new MatrixChangeListener());
		at.setOnPhotoTapListener(new PhotoTapListener());
	}

	private class PhotoTapListener implements OnPhotoTapListener {

		@Override
		public void onPhotoTap(View arg0, float arg1, float arg2) {
			// TODO Auto-generated method stub

		}

	}

	private class MatrixChangeListener implements OnMatrixChangedListener {

		@Override
		public void onMatrixChanged(RectF arg0) {
			// TODO Auto-generated method stub

		}

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);

		if (resultCode == RESULT_OK) {
			if (requestCode == 1) {
				Bundle MBuddle = data.getExtras();
				String MMessage = MBuddle.getString("bun");
				bitmap1 = getDrawable(MMessage);
				canvasview.setForeground(bitmap1);
			}
			if (requestCode == 2) {
				try {

					bagFlag = true;
					byte[] byteArray = data.getByteArrayExtra("image");
					Bitmap backgroundimage = BitmapFactory.decodeByteArray(
							byteArray, 0, byteArray.length);
					canvasview.setBackground(backgroundimage);

				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	public Bitmap getDrawable(String path) {
		Drawable d = null;
		Bitmap mutableBitmap = null;
		try {
			InputStream ims = getAssets().open(path);
			d = Drawable.createFromStream(ims, null);
			mutableBitmap = ((BitmapDrawable) d).getBitmap();
		} catch (IOException ex) {
			return null;
		}
		return mutableBitmap;

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
			case R.id.save:
				isSaved=true;
				canvasview.invalidate();
				canvasview.setBackgroundResource(R.drawable.background);
				canvasview.setDrawingCacheEnabled(true);
				canvasview.buildDrawingCache(true);
				// Dashboard.bitmap2=canvasview.getDrawingCache(true);
				Bitmap imgData = Bitmap.createBitmap(canvasview
						.getDrawingCache(true));
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				Calendar cal = Calendar.getInstance();

				storeImage(imgData, "/"
						+ cal.getTime().toString().replace(':', '_') + ".png");
				Toast.makeText(EditorActivity.this, "Image Saved...",
						Toast.LENGTH_SHORT).show();
				isSaved=false;
				isimagesaved=true;
				new AlertDialog.Builder(this)
						.setIcon(android.R.drawable.ic_dialog_alert)
						.setTitle("Photo Booth")
						.setIcon(R.drawable.icon)
						.setMessage("Do you want to post image on facebook..")
						.setPositiveButton("Yes", new DialogInterface.OnClickListener()
						{
							@Override
							public void onClick(DialogInterface dialog, int which) {
								{
								}
							}
							public void postStatusMessage() {
								if (checkPermissions()) {
								} else {
									requestPermissions();
								}
							}
							public void requestPermissions() {
							}
							private boolean checkPermissions() {
								return false;
							}

						})
						.setNegativeButton("No", null)
						.show();

				return true;

			case R.id.share:
				shareimagecode();

				return true;

			default:
				return super.onOptionsItemSelected(item);
		}

	}

	@Override
	public void onItemClick(int position) {

	}

	@Override
	public void onLongItemClick(int position) {

	}

	private void shareimagecode() {
		// TODO Auto-generated method stub
		if(!isimagesaved)
		{
			Toast.makeText(getApplicationContext(), "Please save the image first", Toast.LENGTH_LONG).show();
			return;
		}
		canvasview.invalidate();
		File imageFileToShare = new File(filePath);
		Uri url1 =Uri.fromFile(imageFileToShare);
		Intent intent = new Intent(android.content.Intent.ACTION_SEND);
		intent.putExtra(android.content.Intent.EXTRA_STREAM, url1);
		intent.setType("image/png");
		startActivity(Intent.createChooser(intent, "Share via"));
	}

	@Override
	public View makeView() {
		ImageView iView = new ImageView(this);
		iView.setScaleType(ImageView.ScaleType.FIT_CENTER);
		iView.setLayoutParams(new ImageSwitcher.LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		iView.setBackgroundColor(0xFF000000);
		return iView;
	}
}
