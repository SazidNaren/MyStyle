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

import com.ar.mystyle.Util.CreateAdView;
import com.ar.mystyle.ImageIds;
import com.ar.mystyle.adapters.GetImageAdapterHori;
import com.ar.mystyle.adapters.SelectImageAdapter;
import com.ar.mystyle.interfaces.ClickListener;
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
public class EditorActivity extends Activity implements ViewFactory,ClickListener {
	private Bitmap bitmap;
	static RelativeLayout updownscroll1;
	private Bitmap bitmap1;
	private ImageView scrollLeft,scrollRight;
	private String filePath=null;
	private Bitmap Capfground,Goggfground,MHeirfground,Lipsfground,Beardfground,WHeirfground;
	static Bitmap hideback;
	private Gallery galleryhoriz;
	static boolean isimagesaved;
	private FrameLayout frame;
	private Canvas canvasview;
	private DisplayMetrics dm;
	private SelectImageAdapter imageAdapter;
	public static int dHeight,dWidth;
	ImageView Share1;

	static boolean capdelete,googgledelete,manheirdelete,bearddelete,womanheirdelete,lipsdelete;
	public static boolean isCapDeleted,isLipsDeleted;
	public static boolean isGoggleDeleted,isManHeirDeleted;
	public static boolean isWomanHeirDeleted,isBeardDeleted;
	public static boolean isFirstImage,Iscapselected,isLipsSelected;
	public static boolean isGoggleSelected,isManHeirSelected,isSaved;
	public static boolean isWomanHeirSElected,isBeardSelected;
	public static ImageView Save1;
	private ImageIds imgIds;
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
		updownscroll1=(RelativeLayout)findViewById(R.id.updownscroll);
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
		imgIds=ImageIds.getInstance(this);
		galleryhoriz.setAdapter(new GetImageAdapterHori(this));
		galleryhoriz.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
				recyclerView.setVisibility(View.VISIBLE);
				int i;
				setAllSelectedFalse();
				switch (position) {
					case 0:
						imageAdapter=new SelectImageAdapter(0,imgIds.getImageIdCaps(),EditorActivity.this,EditorActivity.this);
						recyclerView.setAdapter(imageAdapter);
						recyclerView.setVisibility(View.VISIBLE);
						updownscroll1.setVisibility(View.VISIBLE);
						isCapDeleted =true;
						break;

					case 1:
						imageAdapter=new SelectImageAdapter(1,imgIds.getImageIdGoggles(),EditorActivity.this,EditorActivity.this);
						recyclerView.setAdapter(imageAdapter);
						recyclerView.setVisibility(View.VISIBLE);
						updownscroll1.setVisibility(View.VISIBLE);
						isGoggleDeleted=true;
					break;
					case 2:
						imageAdapter=new SelectImageAdapter(2,imgIds.getImageIsHeirs(),EditorActivity.this,EditorActivity.this);
						recyclerView.setAdapter(imageAdapter);
						recyclerView.setVisibility(View.VISIBLE);
						updownscroll1.setVisibility(View.VISIBLE);
						isManHeirDeleted=true;
						break;
					case 3:
						imageAdapter=new SelectImageAdapter(3,imgIds.getImageIsLips(),EditorActivity.this,EditorActivity.this);
						recyclerView.setAdapter(imageAdapter);
						recyclerView.setVisibility(View.VISIBLE);
        				updownscroll1.setVisibility(View.VISIBLE);
						isLipsDeleted=true;
						break;
					case 4:
						imageAdapter=new SelectImageAdapter(4,imgIds.getImageIsMouths(),EditorActivity.this,EditorActivity.this);
						recyclerView.setAdapter(imageAdapter);
						recyclerView.setVisibility(View.VISIBLE);
						updownscroll1.setVisibility(View.VISIBLE);
						isBeardDeleted=true;
						break;

					case 5:
						imageAdapter=new SelectImageAdapter(5,imgIds.getImageIsW_Heir(),EditorActivity.this,EditorActivity.this);
						recyclerView.setAdapter(imageAdapter);
						recyclerView.setVisibility(View.VISIBLE);
						updownscroll1.setVisibility(View.VISIBLE);
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
		isCapDeleted =false;
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
	public void onItemClick(int type,int position) {

		switch (type)
		{
			case 0:
				isCapDeleted = false;
				Capfground =  ((BitmapDrawable)imgIds.getImageIdCaps().get(position)).getBitmap();
				canvasview.setForeground(Capfground);
				Iscapselected = true;
				updownscroll1.setVisibility(View.INVISIBLE);
				canvasview.invalidate();
				break;
			case 1:
				isGoggleDeleted = false;
				Goggfground = 	MHeirfground = ((BitmapDrawable)imgIds.getImageIdGoggles().get(position)).getBitmap();
				canvasview.setForeground(Goggfground);
				canvasview.invalidate();
				isGoggleSelected = true;
				updownscroll1.setVisibility(View.INVISIBLE);
				break;
			case 2:
				isManHeirDeleted = false;
				MHeirfground = ((BitmapDrawable)imgIds.getImageIsHeirs().get(position)).getBitmap();
				canvasview.setForeground(MHeirfground);
				canvasview.invalidate();
				isManHeirSelected = true;
				updownscroll1.setVisibility(View.INVISIBLE);
				break;
		case 3:
				isLipsDeleted = false;
				Lipsfground = ((BitmapDrawable)imgIds.getImageIsLips().get(position)).getBitmap();
				canvasview.setForeground(Lipsfground);
				canvasview.invalidate();
				isLipsSelected = true;
				updownscroll1.setVisibility(View.INVISIBLE);

				break;
			case 4:
				isBeardDeleted = false;
				Beardfground = ((BitmapDrawable)(imgIds.getImageIsMouths().get(position))).getBitmap();
				canvasview.setForeground(Beardfground);
				canvasview.invalidate();
				isBeardSelected = true;
				updownscroll1.setVisibility(View.INVISIBLE);
				break;
			case 5:
				isWomanHeirDeleted = false;
				WHeirfground =  ((BitmapDrawable)(imgIds.getImageIsW_Heir().get(position))).getBitmap();
				canvasview.setForeground(WHeirfground);
				canvasview.invalidate();
				isWomanHeirSElected = true;
				updownscroll1.setVisibility(View.INVISIBLE);
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
