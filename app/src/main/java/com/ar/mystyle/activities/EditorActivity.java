package com.ar.mystyle.activities;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.ar.mystyle.Util.Constants;
import com.ar.mystyle.Util.ImageIds;
import com.ar.mystyle.Util.Utility;
import com.ar.mystyle.adapters.SelectImageAdapterHori;
import com.ar.mystyle.adapters.SelectImageAdapterVert;
import com.ar.mystyle.interfaces.ClickListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.naver.android.helloyako.imagecrop.view.ImageCropView;
import com.style.facechanger.R;

import android.app.Dialog;
import android.graphics.Matrix;
import android.media.MediaScannerConnection;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ViewSwitcher.ViewFactory;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
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
import android.widget.RelativeLayout;
import android.widget.Toast;

@SuppressWarnings("deprecation")
public class EditorActivity extends Activity implements ViewFactory,ClickListener,OnClickListener {
	private Bitmap bitmap;
	static RelativeLayout updownscroll1;
	private Bitmap bitmap1;
	private ImageView scrollLeft,scrollRight;
	private String filePath=null;
	public static Bitmap Capfground,Goggfground,MHeirfground,Lipsfground,Beardfground,WHeirfground;
	static Bitmap hideback;
	private Gallery galleryhoriz;
	static boolean isimagesaved;
	private FrameLayout frame;
	private Canvas canvasview;
	private DisplayMetrics dm;
	private SelectImageAdapterVert imageAdapter;
	public static int dHeight,dWidth;

	static boolean capdelete,googgledelete,manheirdelete,bearddelete,womanheirdelete,lipsdelete;
	public static boolean isCapDeleted,isLipsDeleted;
	public static boolean isGoggleDeleted,isManHeirDeleted;
	public static boolean isWomanHeirDeleted,isBeardDeleted;
	public static boolean isCapselected,isLipsSelected;
	public static boolean isGoggleSelected,isManHeirSelected,isSaved;
	public static boolean isWomanHeirSelected,isBeardSelected;
	public static ImageView imgSave;
	private ImageIds imgIds;
	public static boolean bagFlag = false;
	private RecyclerView recyclerView;
	private ImageView imgDelete;
	private Animation showAnim,hideAnim;
	private AdView mAdView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_editor);
//		getActionBar().setDisplayHomeAsUpEnabled(true);
		hideback=BitmapFactory.decodeResource(getResources(),R.drawable.hide);
		galleryhoriz=(Gallery)findViewById(R.id.ho_gallery);
		frame = (FrameLayout) findViewById(R.id.frame);
		updownscroll1=(RelativeLayout)findViewById(R.id.updownscroll);
		imgSave= (ImageView) findViewById(R.id.img_save);
		imgSave.setOnClickListener(this);

		scrollLeft=(ImageView)findViewById(R.id.arrowleft);
		scrollRight=(ImageView)findViewById(R.id.arrowright);
		recyclerView=(RecyclerView)findViewById(R.id.recyclerview_select_images);
		imgDelete=(ImageView)findViewById(R.id.delete);
		mAdView = (AdView) findViewById(R.id.adView);
		GridLayoutManager mLayoutManager = new GridLayoutManager(this,4);
		recyclerView.setLayoutManager(mLayoutManager);
		dm = new DisplayMetrics();
		((Activity) this).getWindowManager().getDefaultDisplay()
				.getMetrics(dm);
		dHeight=dm.heightPixels;
		dWidth=dm.widthPixels;
		imgIds=ImageIds.getInstance(this);
		showAnim= AnimationUtils.loadAnimation(this,R.anim.bottom_to_top);
		hideAnim= AnimationUtils.loadAnimation(this,R.anim.top_to_bottom);
		galleryhoriz.setAdapter(new SelectImageAdapterHori(this));
		galleryhoriz.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
				recyclerView.setVisibility(View.VISIBLE);
				int i;
				setAllSelectedFalse();
				switch (position) {
					case 0:
						imageAdapter=new SelectImageAdapterVert(0,imgIds.getImageIdCaps(),EditorActivity.this,EditorActivity.this);
						recyclerView.setAdapter(imageAdapter);
						recyclerView.setVisibility(View.VISIBLE);
						updownscroll1.setVisibility(View.VISIBLE);
						updownscroll1.startAnimation(showAnim);
						isCapDeleted =true;
						break;

					case 1:
						imageAdapter=new SelectImageAdapterVert(1,imgIds.getImageIdGoggles(),EditorActivity.this,EditorActivity.this);
						recyclerView.setAdapter(imageAdapter);
						recyclerView.setVisibility(View.VISIBLE);
						updownscroll1.setVisibility(View.VISIBLE);
						updownscroll1.startAnimation(showAnim);
						isGoggleDeleted=true;
						break;
					case 2:
						imageAdapter=new SelectImageAdapterVert(2,imgIds.getImageIsHeirs(),EditorActivity.this,EditorActivity.this);
						recyclerView.setAdapter(imageAdapter);
						recyclerView.setVisibility(View.VISIBLE);
						updownscroll1.setVisibility(View.VISIBLE);
						updownscroll1.startAnimation(showAnim);
						isManHeirDeleted=true;
						break;
					case 3:
						imageAdapter=new SelectImageAdapterVert(3,imgIds.getImageIsLips(),EditorActivity.this,EditorActivity.this);
						recyclerView.setAdapter(imageAdapter);
						recyclerView.setVisibility(View.VISIBLE);
						updownscroll1.setVisibility(View.VISIBLE);
						updownscroll1.startAnimation(showAnim);
						isLipsDeleted=true;
						break;
					case 4:
						imageAdapter=new SelectImageAdapterVert(4,imgIds.getImageIsMouths(),EditorActivity.this,EditorActivity.this);
						recyclerView.setAdapter(imageAdapter);
						recyclerView.setVisibility(View.VISIBLE);
						updownscroll1.setVisibility(View.VISIBLE);
						updownscroll1.startAnimation(showAnim);
						isBeardDeleted=true;
						break;

					case 5:
						imageAdapter=new SelectImageAdapterVert(5,imgIds.getImageIsW_Heir(),EditorActivity.this,EditorActivity.this);
						recyclerView.setAdapter(imageAdapter);
						recyclerView.setVisibility(View.VISIBLE);
						updownscroll1.setVisibility(View.VISIBLE);
						updownscroll1.startAnimation(showAnim);
						isWomanHeirDeleted = true;
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
		AdRequest adRequest = new AdRequest.Builder()
				.build();
		mAdView.loadAd(adRequest);
		if (getIntent().hasExtra("data")) {
			bitmap = (Bitmap) getIntent().getExtras().get("data");
			openResizableDialog();
		}
		else if (getIntent().hasExtra("path")) {
			bitmap = decodeSampledBitmapFromResource(getResources(),
					getIntent().getStringExtra("path"), dWidth/2, dHeight/2);
			String imgUrl=getIntent().getStringExtra("path");
			if(!(imgUrl.endsWith(".jpg")||imgUrl.endsWith(".jpeg")||imgUrl.endsWith(".gif")||imgUrl.endsWith(".png")) || bitmap==null)
			{
				Toast.makeText(getApplicationContext(), "Please select an image..", Toast.LENGTH_SHORT).show();
				super.onBackPressed();
				overridePendingTransition(0, R.anim.top_to_bottom);
				return;
			}
			//isFirstImage=true;
			//	canvasview.setBackground(bitmap);
			openResizableDialog();
		}
		else if (getIntent().getExtras().containsKey("fbphoto")) {
			String photoUrl=getIntent().getExtras().getString("fbphoto");
			//bitmap= Utility.getBitmapFromURL(photoUrl);
			new MyBitmap(photoUrl).execute();
		}
		imgDelete.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				googgledelete=true;
				canvasview.invalidate();
			}
		});
	}


	void openResizableDialog()
	{

		final Dialog dialog = new Dialog(this,R.style.DialogTheme);
		dialog.setCanceledOnTouchOutside(false);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.dialog_crop_zoom_image);
		//dialog.setTitle("");
		// set the custom dialog components - text, image and button
		final ImageCropView image = (ImageCropView) dialog.findViewById(R.id.imageset);
		image.setAspectRatio(12,16);
		image.setImageBitmap(bitmap);
		Button btnDone = (Button) dialog.findViewById(R.id.btn_done);
		Button btnCancel = (Button) dialog.findViewById(R.id.btn_cancel);
		ImageView btnRotate=(ImageView) dialog.findViewById(R.id.img_rotate);
		// if button is clicked, close the custom dialog
		btnRotate.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Matrix matrix = new Matrix();
				matrix.postRotate(90);
				bitmap = Bitmap.createBitmap(bitmap, 0, 0,
						bitmap.getWidth(), bitmap.getHeight(), matrix, true);
				image.setImageBitmap(bitmap);
			}
		});
		btnDone.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
				bitmap=image.getCroppedImage();
				canvasview.setBackground(bitmap);
			}
		});

		btnCancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
				finish();
				overridePendingTransition(0,R.anim.top_to_bottom);
			}
		});
		dialog.setOnKeyListener(new Dialog.OnKeyListener() {
			@Override
			public boolean onKey(DialogInterface arg0, int keyCode,
								 KeyEvent event) {
				// TODO Auto-generated method stub
				if (keyCode == KeyEvent.KEYCODE_BACK) {
					dialog.dismiss();
					finish();
					overridePendingTransition(0,R.anim.top_to_bottom);
				}
				return true;
			}
		});

		dialog.show();
	}

	void setAllSelectedFalse()
	{
		isCapDeleted =false;
		isBeardDeleted=false;
		isLipsDeleted=false;
		isGoggleDeleted=false;
		isManHeirDeleted=false;
		isWomanHeirDeleted=false;
		isBeardSelected=false;
		isCapselected =false;
		isLipsSelected=false;
		isGoggleSelected=false;
		isManHeirSelected=false;
		isWomanHeirSelected =false;
	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		if(updownscroll1.getVisibility()==View.VISIBLE)
		{
			updownscroll1.setVisibility(View.INVISIBLE);
			updownscroll1.startAnimation(hideAnim);
		}
		else {
			Canvas.Beardfground = null;
			Canvas.Capfground = null;
			Canvas.Goggfground = null;
			Canvas.Lipsfground = null;
			Canvas.MHeirfground = null;
			Canvas.WHeirfground = null;
			super.onBackPressed();
			overridePendingTransition(0, R.anim.top_to_bottom);
		}
	}

	@Override
	protected void onResume() {
		//CreateAdView.getInstance().setSinglaotonAdview(this);
		Utility.isNetworkConnected(mAdView,this);
		if (mAdView != null) {
			mAdView.resume();
		}
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
				+ Constants.imageLocation;
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
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
				Intent mediaScanIntent = new Intent(
						Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
				File file=new File(filePath);
				Uri contentUri = Uri.fromFile(file);
				//out is your output file
				mediaScanIntent.setData(contentUri);
				this.sendBroadcast(mediaScanIntent);
			} else {
				sendBroadcast(new Intent(
						Intent.ACTION_MEDIA_MOUNTED,
						Uri.parse("file://"
								+ Environment.getExternalStorageDirectory())));
			}
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
				saveImageCode();
				return true;

			case R.id.share:
				shareimagecode();

				return true;

			default:
				return super.onOptionsItemSelected(item);
		}

	}

	@Override
	public void onItemClick(int type,int position) {

		switch (type)
		{
			case 0:
				isCapDeleted = false;
				Capfground =  ((BitmapDrawable)imgIds.getImageIdCaps().get(position)).getBitmap();
				canvasview.setForeground(Capfground);
				isCapselected = true;
				updownscroll1.setVisibility(View.INVISIBLE);
				updownscroll1.startAnimation(hideAnim);
				canvasview.invalidate();
				break;
			case 1:
				isGoggleDeleted = false;
				Goggfground = 	MHeirfground = ((BitmapDrawable)imgIds.getImageIdGoggles().get(position)).getBitmap();
				canvasview.setForeground(Goggfground);
				canvasview.invalidate();
				isGoggleSelected = true;
				updownscroll1.setVisibility(View.INVISIBLE);
				updownscroll1.startAnimation(hideAnim);
				break;
			case 2:
				isManHeirDeleted = false;
				MHeirfground = ((BitmapDrawable)imgIds.getImageIsHeirs().get(position)).getBitmap();
				canvasview.setForeground(MHeirfground);
				canvasview.invalidate();
				isManHeirSelected = true;
				updownscroll1.setVisibility(View.INVISIBLE);
				updownscroll1.startAnimation(hideAnim);
				break;
			case 3:
				isLipsDeleted = false;
				Lipsfground = ((BitmapDrawable)imgIds.getImageIsLips().get(position)).getBitmap();
				canvasview.setForeground(Lipsfground);
				canvasview.invalidate();
				isLipsSelected = true;
				updownscroll1.setVisibility(View.INVISIBLE);
				updownscroll1.startAnimation(hideAnim);
				break;
			case 4:
				isBeardDeleted = false;
				Beardfground = ((BitmapDrawable)(imgIds.getImageIsMouths().get(position))).getBitmap();
				canvasview.setForeground(Beardfground);
				canvasview.invalidate();
				isBeardSelected = true;
				updownscroll1.setVisibility(View.INVISIBLE);
				updownscroll1.startAnimation(hideAnim);
				break;
			case 5:
				isWomanHeirDeleted = false;
				WHeirfground =  ((BitmapDrawable)(imgIds.getImageIsW_Heir().get(position))).getBitmap();
				canvasview.setForeground(WHeirfground);
				canvasview.invalidate();
				isWomanHeirSelected = true;
				updownscroll1.setVisibility(View.INVISIBLE);
				updownscroll1.startAnimation(hideAnim);
				break;
		}
	}

	@Override
	public void onLongItemClick(int position) {
		Toast.makeText(getApplicationContext(), "Please save the image first", Toast.LENGTH_LONG).show();
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

	private void saveImageCode()
	{
		isSaved=true;
		canvasview.invalidate();
		canvasview.setBackgroundColor(getResources().getColor(R.color.cardview_shadow_start_color));
		canvasview.setDrawingCacheEnabled(true);
		canvasview.buildDrawingCache(true);
		Bitmap imgData = Bitmap.createBitmap(canvasview
				.getDrawingCache(true));
		Calendar cal = Calendar.getInstance();
		storeImage(imgData, "/"
				+ cal.getTime().toString().replace(':', '_') + ".png");
		Toast.makeText(EditorActivity.this, "Your image Saved...",
				Toast.LENGTH_SHORT).show();
		isSaved=false;
		isimagesaved=true;
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

	@Override
	public void onClick(View v) {
		int id=v.getId();
		switch (id)
		{
			case R.id.img_save:
				saveImageCode();
				break;
			case R.id.img_share:
				shareimagecode();
				break;
		}
	}
	class MyBitmap extends AsyncTask
	{
		String url;
		public MyBitmap(String url)
		{
			this.url=url;
		}
		@Override
		protected Object doInBackground(Object[] params) {
			bitmap=Utility.getBitmapFromURL(url);
			return null;
		}

		@Override
		protected void onPostExecute(Object o) {
			super.onPostExecute(o);
			openResizableDialog();
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
	public void onDestroy() {
		if (mAdView != null) {
			mAdView.destroy();
		}
		super.onDestroy();
	}
}
