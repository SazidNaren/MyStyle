package com.ar.mystyle.activities;
import java.io.File;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.DisplayMetrics;
import android.widget.Toast;

import com.ar.mystyle.adapters.ShowImageAdapter;
import com.ar.mystyle.interfaces.ClickListener;
import com.mystyle.R;

@SuppressWarnings("deprecation")
public class ShowSavedImage extends Activity implements ClickListener{
	private DisplayMetrics dm;
	private String url[];
	private ArrayList<String> images;
	private int i,count = 0;
	private ShowImageAdapter showSavedImage;
	private RecyclerView recyclerView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.showsavedimage);
		setScreenActionBar();
		getImagesList();
		showSavedImage=new ShowImageAdapter(images,this,this);
		recyclerView=(RecyclerView)findViewById(R.id.recyclerview_show_images);

		//GridLayoutManager mLayoutManager = new GridLayoutManager(this, 4); // (Context context, int spanCount)
		/*LinearLayoutManager mLayoutManager
				= new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);*/
		StaggeredGridLayoutManager mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL); // (int spanCount, int orientation)

		recyclerView.setLayoutManager(mLayoutManager);
		recyclerView.setAdapter(showSavedImage);
	}



	private void setScreenActionBar() {
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);
		dm = new DisplayMetrics();
	}

	private void getImagesList() {
		String iconsStoragePath = Environment.getExternalStorageDirectory()
				+ "/facechanger/myImages/";
		File sdIconStorageDir = new File(iconsStoragePath);
		if (!sdIconStorageDir.exists())
			sdIconStorageDir.mkdir();
		url = sdIconStorageDir.list();
		if (url == null) {
			Toast.makeText(getApplicationContext(), "No image and directory available ", Toast.LENGTH_SHORT).show();
			return;
		} else {
			for (i = 0; i < url.length; i++) {
				if (url[i].endsWith(".png")) {
					count++;
				}
			}
		}
		if (count == 0) {
			Toast.makeText(getApplicationContext(), "No image and directory available to show..", Toast.LENGTH_SHORT).show();
			return;
		}
		images = new ArrayList<>();
		for (i = 0; i < url.length; i++) {
			if (url[i].endsWith(".png")) {
				images.add(Environment.getExternalStorageDirectory() + "/facechanger/myImages/" + url[i]);
			}
		}
	}

	@Override
	public void onItemClick(int position,int flag) {
		Uri uri = Uri.fromFile(new File(images.get(position)));
		Intent intent=new Intent(Intent.ACTION_VIEW);
		intent.setDataAndType(uri, "image/*");
		startActivity(intent);
	}

	@Override
	public void onLongItemClick(int position) {

	}
}