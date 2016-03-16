package com.ar.mystyle;

import java.io.IOException;
import java.io.InputStream;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.mystyle.R;

public class ImagesList extends Activity {
	AssetManager assetManager;
	String str[];
	GridView listview;
	MyAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.imageslist);
		listview = (GridView) findViewById(R.id.image_list);
		assetManager = getAssets();

		try {
			str = assetManager.list("images");
		} catch (IOException e) {
			e.printStackTrace();
		}
		// ArrayAdapter<String> adapter = new Arrayx
		// Adapter<String>(this,
		// android.R.layout.simple_gallery_item, str);
		//
		// listview.setAdapter(adapter);
		adapter = new MyAdapter(ImagesList.this, 1, str);
		listview.setAdapter(adapter);

		listview.setOnItemClickListener(new OnItemClickListener() {
			Bitmap bitmap[];

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub

				String s1 = listview.getItemAtPosition(arg2).toString();
				Uri imageUri = Uri.parse("images/" + s1);
				
				Intent intent = getIntent();
				intent.putExtra("bun", imageUri.toString());
				ImagesList.this.setResult(RESULT_OK, intent);

				finish();

				// Uri imageUri = Uri.parse("images/" +
				// s1);

			}

		});

	}

	public class MyAdapter extends ArrayAdapter<String> {
		String arr[];
		Context context;
		ImageView imageview;

		public MyAdapter(Context context, int textViewResourceId, String[] arr) {
			super(context, textViewResourceId, arr);
			// TODO Auto-generated constructor stub
			this.arr = arr;
			this.context = context;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			View view = convertView;

			if (view == null) {
				LayoutInflater inflater = getLayoutInflater();
				view = inflater.inflate(R.layout.row, null);

			}
			imageview = (ImageView) view.findViewById(R.id.icon);
			imageview.setImageDrawable(getDrawable("images/" + str[position]));
			return view;

		}

	}

	public Drawable getDrawable(String path) {
		Drawable d = null;
		try {
			// get input stream
			InputStream ims = getAssets().open(path);
			// load image as Drawable
			d = Drawable.createFromStream(ims, null);
			// set image to ImageView

		} catch (IOException ex) {
			return null;
		}
		return d;

	}
}
