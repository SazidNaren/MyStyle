package com.ar.mystyle.activities;
import java.io.File;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;

import com.ar.mystyle.CreateAdView;
import com.mystyle.R;

@SuppressWarnings("deprecation")
public class showSavedImage extends Activity {
	boolean isMain;
	static int heightScreen,widthScreen;
	DisplayMetrics dm;    
	String url[];
	public static String images[];
	int i,j=0,count=0;
	File file;
	PagerAdapter adapter;
	LayoutInflater inflater;
	Activity _activity;
	/*Integer pics[] = { R.drawable.b10, R.drawable.b11, R.drawable.b12, R.drawable.b13,
			R.drawable.b14};*/

	ViewPager viewpager;
	//	Gallery gallryMain;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.showsavedimage);
		if(Build.VERSION.SDK_INT>11)
		{
			getActionBar().setDisplayHomeAsUpEnabled(true);
			getActionBar().setHomeButtonEnabled(true);
		}
		_activity=showSavedImage.this;
		//gallryMain=(Gallery)findViewById(R.id.GalleryMain);
		viewpager=(ViewPager)findViewById(R.id.pager);
		adapter=new PagerAdapter() {

			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				return arg0==(View)arg1;
			}
			@Override
			public void destroyItem(View arg0, int arg1, Object arg2) {
				((ViewPager) arg0).removeView((View) arg2);
			}

			@Override
			public Object instantiateItem(ViewGroup container, int position) {
				ImageView imgDisplay;

				inflater = (LayoutInflater)_activity
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				View viewLayout = inflater.inflate(R.layout.viewpager, container,
						false);
				imgDisplay = (ImageView) viewLayout.findViewById(R.id.viewimage);
				BitmapFactory.Options options = new BitmapFactory.Options();
				options.inPreferredConfig = Bitmap.Config.ARGB_8888;
				Bitmap bitmap = BitmapFactory.decodeFile(images[position], options);
				imgDisplay.setImageBitmap(bitmap);

				// close button click event

				((ViewPager) container).addView(viewLayout);

				return viewLayout;
			}

			@Override
			public int getCount() {

				return images.length;
			}
		};
		dm = new DisplayMetrics();
		((Activity) this).getWindowManager().getDefaultDisplay()
		.getMetrics(dm);
		heightScreen=dm.heightPixels;
		widthScreen=dm.widthPixels;

		/*iSwitcher = (ImageSwitcher) findViewById(R.id.ImageSwitcher01);
		iSwitcher.setFactory(this);
		iSwitcher.setInAnimation(AnimationUtils.loadAnimation(this,
				android.R.anim.fade_in));
		iSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this,
				android.R.anim.fade_out));*/
		String iconsStoragePath = Environment.getExternalStorageDirectory()
				+ "/facechanger/myImages/";
		File sdIconStorageDir = new File(iconsStoragePath);
		if(!sdIconStorageDir.exists())
			sdIconStorageDir.mkdir();
		url=sdIconStorageDir.list();
		if(url==null)
		{
			/*iSwitcher.setImageResource(R.drawable.onimageavailable);*/
			Toast.makeText(getApplicationContext(), "No image and directory available ", Toast.LENGTH_SHORT).show();
			return;
		}
		else
		{
			for(i=0;i<url.length;i++)
			{
				if(url[i].endsWith(".png"))
				{	count++;
				}
			}
		}
		if(count==0)
		{
			Toast.makeText(getApplicationContext(), "No image and directory available to show..", Toast.LENGTH_SHORT).show();
			return;
		}
		images=new String[count]; 
		for(i=0;i<url.length;i++)
		{
			if(url[i].endsWith(".png"))
			{
				images[j++]=Environment.getExternalStorageDirectory()+"/facechanger/myImages/"+url[i];
			}
		}
		int j=0;


		/*if(j>=1)
			iSwitcher.setImageURI(Uri.parse(images[0]));
		else
			iSwitcher.setImageResource(R.drawable.onimageavailable);
		 */
		final Gallery gallery = (Gallery) findViewById(R.id.Gallery01);
		viewpager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				gallery.setSelection(arg0);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				//gallery.setSelection(arg0);
			}
		});
		gallery.setAdapter(new ImageAdapter(this));
		//gallryMain.setAdapter(new ImageAdapterMain(this));
		viewpager.setAdapter(adapter);


		gallery.setOnItemClickListener(new OnItemClickListener() 
		{

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				viewpager.setCurrentItem(arg2);
			}
		});
	}
	/*	gallryMain.setOnItemSelectedListener( new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> arg0, View arg1, int pos, long arg3) {
				gallery.setSelection(pos);
			}

			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
	}*/
	@Override
	protected void onResume() {
		CreateAdView.getInstance().setSinglaotonAdview(this);
		// TODO Auto-generated method stub
		super.onResume();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.savedimagemenu, menu);
		return super.onCreateOptionsMenu(menu);
	}
	@Override  
	public boolean onOptionsItemSelected(MenuItem item) {  
		if (android.R.id.home==item.getItemId())
			finish();
		switch (item.getItemId()) {  
		case R.id.showshare: 
			if(viewpager.getChildCount()==0)
			{
				Toast.makeText(getApplicationContext(), "No image to share...", Toast.LENGTH_SHORT).show();
				break;
			}
			String filePath=images[viewpager.getCurrentItem()];		 
			File imageFileToShare = new File(filePath);
			Uri url1 =Uri.fromFile(imageFileToShare);
			Intent intent = new Intent(android.content.Intent.ACTION_SEND);
			intent.putExtra(android.content.Intent.EXTRA_STREAM, url1);
			intent.setType("image/png");
			startActivity(Intent.createChooser(intent, "Share via"));
			break;
		}

		return true;}
	class ImageAdapter extends BaseAdapter
	{

		private Context ctx;

		public ImageAdapter(Context c) {
			ctx = c;
		}

		@Override
		public int getCount() {

			return images.length;
		}

		@Override
		public Object getItem(int arg0) {

			return arg0;
		}

		@Override
		public long getItemId(int arg0) {

			return arg0;
		}

		@SuppressWarnings("deprecation")
		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {

			ImageView iView = new ImageView(ctx);
			Bitmap bmp = BitmapFactory.decodeFile(images[arg0]);
			iView.setImageBitmap(bmp);
			iView.setScaleType(ImageView.ScaleType.FIT_XY);
			iView.setLayoutParams(new Gallery.LayoutParams(widthScreen/4,heightScreen/5));
			return iView;
		}

	}

	/*@Override
	public View makeView() {
		ImageView iView = new ImageView(this);
		iView.setScaleType(ImageView.ScaleType.FIT_CENTER);
		iView.setLayoutParams(new ImageSwitcher.LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		iView.setBackgroundColor(0xFF000000);
		return iView;
	}*/

}
class ImageAdapterMain extends BaseAdapter
{

	private Context ctx;

	public ImageAdapterMain(Context c) {
		ctx = c;
	}

	@Override
	public int getCount() {

		return showSavedImage.images.length;
	}

	@Override
	public Object getItem(int arg0) {

		return arg0;
	}

	@Override
	public long getItemId(int arg0) {

		return arg0;
	}

	@SuppressWarnings("deprecation")
	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {

		ImageView iView = new ImageView(ctx);
		Bitmap bmp = BitmapFactory.decodeFile(showSavedImage.images[arg0]);
		iView.setImageBitmap(bmp);
		iView.setScaleType(ImageView.ScaleType.FIT_XY);
		iView.setLayoutParams(new Gallery.LayoutParams(showSavedImage.widthScreen,showSavedImage.heightScreen-(showSavedImage.heightScreen/5+10)));
		return iView;
	}

}

