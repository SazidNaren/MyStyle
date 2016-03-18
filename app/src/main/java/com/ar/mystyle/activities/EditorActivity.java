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
import com.imagezoom.ImageAttacher;
import com.imagezoom.ImageAttacher.OnMatrixChangedListener;
import com.imagezoom.ImageAttacher.OnPhotoTapListener;
import com.mystyle.R;

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
public class EditorActivity extends Activity implements ViewFactory {
	Bitmap bitmap;
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

	static RelativeLayout updownscroll1;
	Bitmap bitmap1;
	int galleryimageposition=0,scrollposition=0;
	static boolean isSaveCanvas;
	ImageView scrollLeft,scrollRight,scrollUp,scrollDown;
	ImageView imageView,imgDelete;
	ImageView imageView1,imgback1;
	int windowwidth;
	String filePath=null;
	int windowheight;
	Bitmap Capfground,Goggfground,MHeirfground,Lipsfground,Beardfground,WHeirfground;
	static Bitmap hideback;
	LinearLayout llayoutbtnadd;
	Gallery galleryhoriz;
	static boolean isimagesaved;
	FrameLayout frame;
	Canvas canvasview;
	ScrollView scrollview;
	DisplayMetrics dm;                                  
	private static final List<String> PERMISSIONS = Arrays.asList("publish_actions");
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
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.editor);
		if(Build.VERSION.SDK_INT>11)
			getActionBar().setDisplayHomeAsUpEnabled(true);
		hideback=BitmapFactory.decodeResource(getResources(),R.drawable.hide);
		galleryhoriz=(Gallery)findViewById(R.id.ho_gallery);
		frame = (FrameLayout) findViewById(R.id.frame);
		imageView = (ImageView) findViewById(R.id.imageset);
		imgback1 = (ImageView) findViewById(R.id.imgback);
		imgDelete=(ImageView)findViewById(R.id.delete);
		scrollview=(ScrollView)findViewById(R.id.scrollview);
		updownscroll1=(RelativeLayout)findViewById(R.id.updownscroll);
		llayoutbtnadd=(LinearLayout)findViewById(R.id.llayoutaddbtn);
		imageView1 = (ImageView) findViewById(R.id.imageset1);
		Save1 = (ImageView) findViewById(R.id.save);
		Share1 = (ImageView) findViewById(R.id.share);
		scrollDown=(ImageView)findViewById(R.id.arrowdown);
		scrollLeft=(ImageView)findViewById(R.id.arrowleft);
		scrollRight=(ImageView)findViewById(R.id.arrowright);
		scrollUp=(ImageView)findViewById(R.id.arrowup);
		dm = new DisplayMetrics();
		((Activity) this).getWindowManager().getDefaultDisplay()
		.getMetrics(dm);
		dHeight=dm.heightPixels;
		dWidth=dm.widthPixels;
		final ImageIds imgIds=new ImageIds();
		galleryhoriz.setAdapter(new GetImageAdapterHori(this) );
		galleryhoriz.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
				int i; 
				llayoutbtnadd.removeAllViews();
				switch (position) {
				case 0:
					for(i=0;i<imgIds.getImageIdCaps().length;i++)
					{
						ImageView imgview=new ImageView(getApplicationContext());
						imgview.setId(i);
						LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(Canvas.widthScreen/4,Canvas.heightScreen/5);
						imgview.setLayoutParams(layoutParams);
						imgview.setImageResource(imgIds.getImageIdCaps()[i]);
						llayoutbtnadd.addView(imgview);
						updownscroll1.setVisibility(View.VISIBLE);
						final int j=i;
						IscapDeleted=true;
						isBeardDeleted=false;
						isLipsDeleted=false;
						isGoggleDeleted=false;
						isManHeirDeleted=false;
						isWomanHeirDeleted=false;
						isBeardSelected=false;
						isLipsSelected=false;
						isGoggleSelected=false;
						isManHeirSelected=false;
						isWomanHeirSElected=false;
						imgview.setOnClickListener(new OnClickListener() {

							@Override
							public void onClick(View arg0) {
								// TODO Auto-generated method stub
								IscapDeleted=false;
								Capfground=BitmapFactory.decodeResource(getResources(), imgIds.getImageIdCaps()[j]);
								canvasview.setForeground(Capfground);
								Iscapselected=true;
								updownscroll1.setVisibility(View.INVISIBLE);
								canvasview.invalidate();
							}
						});
					}
					imgDelete.setOnClickListener(new OnClickListener()
					{
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub

							capdelete=true;

							canvasview.invalidate();
						}
					});
					break;
				case 1:
					for(i=0;i<imgIds.getImageIdGoggles().length;i++)
					{
						ImageView imgview=new ImageView(getApplicationContext());
						imgview.setImageResource(imgIds.getImageIdGoggles()[i]);
						LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(Canvas.widthScreen/4,Canvas.heightScreen/5);
						imgview.setLayoutParams(layoutParams);
						llayoutbtnadd.addView(imgview);
						updownscroll1.setVisibility(View.VISIBLE);
						final int j=i;
						IscapDeleted=false;
						isBeardDeleted=false;
						isLipsDeleted=false;
						isGoggleDeleted=true;
						isManHeirDeleted=false;
						isWomanHeirDeleted=false;
						isBeardSelected=false;
						Iscapselected=false;
						isLipsSelected=false;
						isManHeirSelected=false;
						isWomanHeirSElected=false;
						imgview.setOnClickListener(new OnClickListener() {

							@Override
							public void onClick(View arg0) {
								// TODO Auto-generated method stub
								isGoggleDeleted=false;
								Goggfground=BitmapFactory.decodeResource(getResources(), imgIds.getImageIdGoggles()[j]);
								canvasview.setForeground(Goggfground);
								canvasview.invalidate();
								isGoggleSelected=true;

								updownscroll1.setVisibility(View.INVISIBLE);
							}
						});
					}
					imgDelete.setOnClickListener(new OnClickListener()
					{
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub

							googgledelete=true;

							canvasview.invalidate();
						}
					});
					break;
				case 2:
					for(i=0;i<imgIds.getImageIsHeirs().length;i++)
					{
						ImageView imgview=new ImageView(getApplicationContext());
						imgview.setImageResource(imgIds.getImageIsHeirs()[i]);
						LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(Canvas.widthScreen/4,Canvas.heightScreen/5);
						imgview.setLayoutParams(layoutParams);
						updownscroll1.setVisibility(View.VISIBLE);
						llayoutbtnadd.addView(imgview);
						final int j=i;
						IscapDeleted=false;
						isBeardDeleted=false;
						isLipsDeleted=false;
						isGoggleDeleted=false;
						isManHeirDeleted=true;
						isWomanHeirDeleted=false;
						isBeardSelected=false;
						Iscapselected=false;
						isLipsSelected=false;
						isGoggleSelected=false;

						isWomanHeirSElected=false;
						imgview.setOnClickListener(new OnClickListener() {

							@Override
							public void onClick(View arg0) {
								// TODO Auto-generated method stub
								isManHeirDeleted=false;
								MHeirfground=BitmapFactory.decodeResource(getResources(), imgIds.getImageIsHeirs()[j]);
								canvasview.setForeground(MHeirfground);
								canvasview.invalidate();
								isManHeirSelected=true;

								updownscroll1.setVisibility(View.INVISIBLE);
							}
						});
					}
					imgDelete.setOnClickListener(new OnClickListener()
					{
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub

							manheirdelete=true;

							canvasview.invalidate();
						}
					});
					break;
				case 3:
					for(i=0;i<imgIds.getImageIsLips().length;i++)
					{
						ImageView imgview=new ImageView(getApplicationContext());
						imgview.setImageResource(imgIds.getImageIsLips()[i]);
						LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(Canvas.widthScreen/4,Canvas.heightScreen/5);
						imgview.setLayoutParams(layoutParams);
						updownscroll1.setVisibility(View.VISIBLE);
						llayoutbtnadd.addView(imgview);
						final int j=i;
						IscapDeleted=false;
						isBeardDeleted=false;
						isLipsDeleted=true;
						isGoggleDeleted=false;
						isManHeirDeleted=false;
						isWomanHeirDeleted=false;
						isBeardSelected=false;
						Iscapselected=false;

						isGoggleSelected=false;
						isManHeirSelected=false;
						isWomanHeirSElected=false;
						imgview.setOnClickListener(new OnClickListener() {

							@Override
							public void onClick(View arg0) {
								// TODO Auto-generated method stub
								isLipsDeleted=false;
								Lipsfground=BitmapFactory.decodeResource(getResources(), imgIds.getImageIsLips()[j]);
								canvasview.setForeground(Lipsfground);
								canvasview.invalidate();
								isLipsSelected=true;

								updownscroll1.setVisibility(View.INVISIBLE);
							}
						});
					}
					imgDelete.setOnClickListener(new OnClickListener()
					{
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub

							lipsdelete=true;

							canvasview.invalidate();
						}
					});
					break;
				case 4:
					for(i=0;i<imgIds.getImageIsMouths().length;i++)
					{
						ImageView imgview=new ImageView(getApplicationContext());
						imgview.setImageResource(imgIds.getImageIsMouths()[i]);
						LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(Canvas.widthScreen/4,Canvas.heightScreen/5);
						imgview.setLayoutParams(layoutParams);
						updownscroll1.setVisibility(View.VISIBLE);
						llayoutbtnadd.addView(imgview);
						final int j=i;
						IscapDeleted=false;
						isBeardDeleted=true;
						isLipsDeleted=false;
						isGoggleDeleted=false;
						isManHeirDeleted=false;
						isWomanHeirDeleted=false;
						isLipsSelected=false;
						Iscapselected=false;

						isGoggleSelected=false;
						isManHeirSelected=false;
						isWomanHeirSElected=false;
						imgview.setOnClickListener(new OnClickListener() {

							@Override
							public void onClick(View arg0) {
								// TODO Auto-generated method stub
								isBeardDeleted=false;
								Beardfground=BitmapFactory.decodeResource(getResources(), imgIds.getImageIsMouths()[j]);
								canvasview.setForeground(Beardfground);
								canvasview.invalidate();
								isBeardSelected=true;

								updownscroll1.setVisibility(View.INVISIBLE);
							}
						});
					}
					imgDelete.setOnClickListener(new OnClickListener()
					{
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							bearddelete=true;
							canvasview.invalidate();
						}
					});
					break;
				case 5:
					for(i=0;i<imgIds.getImageIsW_Heir().length;i++)
					{
						ImageView imgview=new ImageView(getApplicationContext());
						imgview.setImageResource(imgIds.getImageIsW_Heir()[i]);
						LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(Canvas.widthScreen/4,Canvas.heightScreen/5);
						imgview.setLayoutParams(layoutParams);
						updownscroll1.setVisibility(View.VISIBLE);
						llayoutbtnadd.addView(imgview);
						final int j=i;
						IscapDeleted=false;
						isBeardDeleted=false;
						isLipsDeleted=false;
						isGoggleDeleted=false;
						isManHeirDeleted=false;
						isWomanHeirDeleted=true;
						isBeardSelected=false;
						Iscapselected=false;
						isLipsSelected=false;
						isGoggleSelected=false;
						isManHeirSelected=false;
						imgview.setOnClickListener(new OnClickListener() {

							@Override
							public void onClick(View arg0) {
								// TODO Auto-generated method stub
								isWomanHeirDeleted=false;
								WHeirfground=BitmapFactory.decodeResource(getResources(), imgIds.getImageIsW_Heir()[j]);
								canvasview.setForeground(WHeirfground);
								canvasview.invalidate();
								isWomanHeirSElected=true;

								updownscroll1.setVisibility(View.INVISIBLE);
							}
						});
					}
					imgDelete.setOnClickListener(new OnClickListener()
					{
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							womanheirdelete=true;
							canvasview.invalidate();
						}
					});
					break;
				}


			}

		});

		scrollUp.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if(Build.VERSION.SDK_INT>11)
				{
					if(scrollposition>0)
						scrollposition-=scrollview.getHeight()/3;
					scrollview.setScrollY(scrollposition);
				}
			}
		});
		scrollDown.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(Build.VERSION.SDK_INT>11)
				{
					if(scrollposition!=(scrollview.getHeight()/3)*25)
						scrollposition+=scrollview.getHeight()/3;
					scrollview.setScrollY(scrollposition);// TODO Auto-generated method stub
				}
				//scrollview.setVerticalScrollbarPosition(13);

			}
		});
		scrollLeft.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(galleryhoriz.getSelectedItemPosition()!=galleryhoriz.getCount()-1)
					galleryhoriz.setSelection(galleryhoriz.getSelectedItemPosition()+1);

			}
		});
		scrollRight.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(galleryhoriz.getSelectedItemPosition()!=0)
					galleryhoriz.setSelection(galleryhoriz.getSelectedItemPosition()-1);

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

				// int resID = getResources().getIdentifier(MMessage, "assets",
				// getPackageName());

				bitmap1 = getDrawable(MMessage);
				canvasview.setForeground(bitmap1);

				// imazeZommer(imageView1);
				// imageView.setImageBitmap(bm);

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
				// canvasview.invalidate();
			}
		}
	}

	public Bitmap getDrawable(String path) {
		Drawable d = null;
		Bitmap mutableBitmap = null;
		try {
			// get input stream
			InputStream ims = getAssets().open(path);
			// load image as Drawable
			d = Drawable.createFromStream(ims, null);
			// set image to ImageView
			// mutableBitmap = Bitmap.createBitmap(d,20, 200,
			// Bitmap.Config.ARGB_8888);
			mutableBitmap = ((BitmapDrawable) d).getBitmap();
			// Canvas canvas = new Canvas(mutableBitmap);
			// drawable.setBounds(0, 0, widthPixels, heightPixels);
			// drawable.draw(canvas);

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
					// TODO Auto-generated method stub
					//Intent sharefacebookwall=new Intent(EditorActivity.this,Home.class);//sazid
					//startActivity(sharefacebookwall);


						/*Intent facebookpost=new Intent(EditorActivity.this,facebookPost.class);
						facebookpost.putExtra("image",filePath);
						startActivity(facebookpost);*/

					{

					/*	Bitmap img = BitmapFactory.decodeFile(filePath);
						Request uploadRequest = Request.newUploadPhotoRequest(
								Session.getActiveSession(), img, new Request.Callback() {
									@Override
									public void onCompleted(Response response) {
										Toast.makeText(EditorActivity.this,
												"Photo uploaded successfully",
												Toast.LENGTH_LONG).show();
									}
								});
						Bundle params = uploadRequest.getParameters();
						params.putString("message", "#PhotoBooth");
						uploadRequest.setParameters(params);
						uploadRequest.executeAsync();*/

					} 



				}
				public void postStatusMessage() {
					if (checkPermissions()) {
						/*Request request = Request.newStatusUpdateRequest(
								Session.getActiveSession(), "#PhotoBooth",
								new Request.Callback() {
									@Override
									public void onCompleted(Response response) {
										if (response.getError() == null)
											Toast.makeText(EditorActivity.this,
													"",
													Toast.LENGTH_SHORT).show();
									}
								});
						request.executeAsync();*/
					} else {
						requestPermissions();
					}
				}
				public void requestPermissions() {
//					Session s = Session.getActiveSession();
//

				}
				private boolean checkPermissions() {

//					Session s = Session.getActiveSession();
//					if (s != null) {
//						return s.getPermissions().contains("publish_actions");
					//} else
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

	private void shareimagecode() {
		// TODO Auto-generated method stub
		if(!isimagesaved)
		{
			Toast.makeText(getApplicationContext(), "Please save the image first", Toast.LENGTH_LONG).show();
			return;
		}
		/*share.setType("image/*");

	    // Make sure you put example png image named myImage.png in your
	    // directory
	    String imagePath = Environment.getExternalStorageDirectory()
	            + "/myImage.png";

	    File imageFileToShare = new File(imagePath);

	    Uri uri = Uri.fromFile(imageFileToShare);
	    share.putExtra(Intent.EXTRA_STREAM, uri);
	    startActivity(Intent.createChooser(share, "Share Image!"));*/
		///////////////////////////////
		canvasview.invalidate();
		File imageFileToShare = new File(filePath);
		Uri url1 =Uri.fromFile(imageFileToShare);
		Intent intent = new Intent(android.content.Intent.ACTION_SEND);
		intent.putExtra(android.content.Intent.EXTRA_STREAM, url1);
		intent.setType("image/png");
		startActivity(Intent.createChooser(intent, "Share via"));
		/*
				share.setType("text/plain");
				share.putExtra(Intent.EXTRA_SUBJECT, "Name of the thing to share");
				share.putExtra(android.content.Intent.EXTRA_STREAM, url1);
				share.putExtra(Intent.EXTRA_TEXT, "Text that will be shared");
				startActivity(Intent.createChooser(share, "Destroy the face"));*/

	}



	@Override
	public View makeView() {
		ImageView iView = new ImageView(this);
		iView.setScaleType(ImageView.ScaleType.FIT_CENTER);
		iView.setLayoutParams(new ImageSwitcher.LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		iView.setBackgroundColor(0xFF000000);
		return iView;
		// TODO Auto-generated method stub

	}

}
