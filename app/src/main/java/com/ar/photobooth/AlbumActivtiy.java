package com.ar.photobooth;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class AlbumActivtiy extends Activity {

	ListView gridView;
	String id;
	String res;
	ArrayList<FacebookAlbumPojo> arraylist;
	//MyAdapter adapter;
	public static Bitmap bmap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.album);
		gridView = (ListView) findViewById(R.id.grid_view);
		arraylist = new ArrayList<FacebookAlbumPojo>();

		if (getIntent().getExtras().getBoolean("facebook")) {
			id = getIntent().getExtras().getString("id");

			if (FacebookPojo.checkInternetConnection(getApplicationContext())) {
			//	new AlbumTask().execute();
			} else {
				Toast.makeText(getApplicationContext(),
						"check your internet connection", Toast.LENGTH_SHORT)
						.show();
			}
		}
		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				String src = arraylist.get(arg2).albumName;
			//	// bmap = getBitmapFromURL(src);
			//	Intent in = new Intent(AlbumActivtiy.this, FacebookPhoto.class);
			//	in.putExtra("photo", true);
			//	in.putExtra("albumID", src);
			//	startActivity(in);
			}
		});
		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				String src = arraylist.get(arg2).albumID;

				Intent i = new Intent(AlbumActivtiy.this, AlbumPhoto.class);
				i.putExtra("facebookAlBumID", true);
				i.putExtra("albumID", src);
				startActivity(i);

			}
		});
	}

	

	/*public class AlbumTask extends AsyncTask<Void, Void, Void> {
		ProgressDialog progressDialog;
		String height, width, sourceURL;
		String albumName;
		String albumID;

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			progressDialog = new ProgressDialog(AlbumActivtiy.this);
			progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			progressDialog.setMessage(getResources()
					.getString(R.string.loading));
			progressDialog.setIndeterminate(true);
			progressDialog.setCancelable(false);
			progressDialog.show();
		}

		@Override
		protected Void doInBackground(Void... params) {
		*//*	try {
				Session session = Session.getActiveSession();
				String token = session.getAccessToken();
				HttpClient httpclient = new DefaultHttpClient();
				// HttpGet post = new HttpGet("https://graph.facebook.com/" + id
				// + "?fields=photos.fields(id,images)&access_token="
				// + token);
				HttpGet post = new HttpGet("https://graph.facebook.com/" + id
						+ "?fields=albums.fields(name,id)&access_token="
						+ token);

				// HttpGet post = new HttpGet("https://graph.facebook.com/" + id
				// + "/picture?type=album&access_token=" + token);
				// httpclient.execute(post);

				HttpResponse response = httpclient.execute(post);
				if (response.getStatusLine().getStatusCode() == 200) {
					res = EntityUtils.toString(response.getEntity(), "UTF-8");
				}

				if (res != null) {
					JSONObject jsonObj = new JSONObject(res);
					JSONObject ob1 = jsonObj.getJSONObject("albums");
					JSONArray ob2 = ob1.getJSONArray("data");
					for (int i = 0; i < ob2.length(); i++) {

						JSONObject ob3 = ob2.getJSONObject(i);
						albumName = ob3.getString("name");
						albumID = ob3.getString("id");

						arraylist
								.add(new FacebookAlbumPojo(albumName, albumID));
					}

				}
			} catch (IOException ex) {
				ex.printStackTrace();
			} catch (Exception ex) {
				ex.printStackTrace();
			}*//*
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			try {
				if (arraylist != null && !(arraylist.isEmpty())) {

					adapter = new MyAdapter(getApplicationContext(), 1,
							arraylist);
					gridView.setAdapter(adapter);
				}
				if (progressDialog.isShowing()) {
					progressDialog.cancel();
					progressDialog = null;
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}*/

	/*public class MyAdapter extends ArrayAdapter<FacebookAlbumPojo> {

		Context context;

		TextView textView;
		List<FacebookAlbumPojo> list;

		public MyAdapter(Context context, int textViewResourceId,
				List<FacebookAlbumPojo> list) {
			//super(context, 1], 1, list);
			// TODO Auto-generated constructor stub
			this.context = context;
			this.list = list;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			View view = convertView;

			if (view == null) {
				LayoutInflater inflater = AlbumActivtiy.this
						.getLayoutInflater();
				view = inflater.inflate(R.layout.album_adapter, null);
			}

			textView = (TextView) view.findViewById(R.id.textview_album);
			textView.setText(list.get(position).albumName);

			return view;
		}

	}*/
}
