package com.ar.mystyle;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

import com.ar.mystyle.activities.EditorActivity;
import com.mystyle.R;

public class AlbumPhoto extends Activity {
	GridView gridView;
	String albumID;
	ArrayList<PhotoPojo> photoList;
	//MyAdapter adapter;
	public static Bitmap bmap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.photos);
		gridView = (GridView) findViewById(R.id.photo_gridview);
		photoList = new ArrayList<PhotoPojo>();

		if (getIntent().getExtras().getBoolean("facebookAlBumID")) {
			albumID = getIntent().getExtras().getString("albumID");

			if (FacebookPojo.checkInternetConnection(getApplicationContext())) {
			//	new PhotoTask().execute();
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

				Intent i=new Intent(AlbumPhoto.this,EditorActivity.class);
				i.putExtra("album", true);
				String url=photoList.get(arg2).url;
				bmap=getBitmapFromURL(url);
				startActivity(i);
			}
		});
	}
	public static Bitmap getBitmapFromURL(String src) {
		try {
			URL url = new URL(src);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setDoInput(true);
			connection.connect();
			InputStream input = connection.getInputStream();
			Bitmap myBitmap = BitmapFactory.decodeStream(input);
			return myBitmap;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	String res;

	/*public class PhotoTask extends AsyncTask<Void, Void, Void> {
		ProgressDialog progressDialog;

		String photoID, photoURL;

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			progressDialog = new ProgressDialog(AlbumPhoto.this);
			progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			progressDialog.setMessage(getResources()
					.getString(R.string.loading));
			progressDialog.setIndeterminate(true);
			progressDialog.setCancelable(false);
			progressDialog.show();
		}

		@Override
		protected Void doInBackground(Void... params) {
			try {
				Session session = Session.getActiveSession();
				String token = session.getAccessToken();
				HttpClient httpclient = new DefaultHttpClient();
				// HttpGet post = new HttpGet("https://graph.facebook.com/" + id
				// + "?fields=photos.fields(id,images)&access_token="
				// + token);
				// HttpGet post = new HttpGet("https://graph.facebook.com/"
				// + albumID
				// + "?fields=photos.fields(source)&access_token=" + token);

				HttpGet post = new HttpGet("https://graph.facebook.com/"
						+ albumID
						+ "?fields=photos.fields(source)&access_token=" + token);
				// HttpGet post = new HttpGet(res1);

				// HttpGet post = new HttpGet("https://graph.facebook.com/" + id
				// + "/picture?type=album&access_token=" + token);
				// httpclient.execute(post);

				HttpResponse response = httpclient.execute(post);
				if (response.getStatusLine().getStatusCode() == 200) {
					res = EntityUtils.toString(response.getEntity(), "UTF-8");
				}

				if (res != null) {
					JSONObject jsonObj = new JSONObject(res);
					JSONObject ob1 = jsonObj.getJSONObject("photos");
					JSONArray ob2 = ob1.getJSONArray("data");
					for (int i = 0; i < ob2.length(); i++) {

						JSONObject ob3 = ob2.getJSONObject(i);
						photoURL = ob3.getString("source");
						photoID = ob3.getString("id");

						photoList.add(new PhotoPojo(photoID, photoURL));
					}

				}
			} catch (IOException ex) {
				ex.printStackTrace();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			try {
				if (photoList != null && !(photoList.isEmpty())) {

					adapter = new MyAdapter(getApplicationContext(), 1,
							photoList);
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
	}

	public class MyAdapter extends ArrayAdapter<PhotoPojo> {

		Context context;

		ImageView imageView;
		List<PhotoPojo> photoList;

		public MyAdapter(Context context, int textViewResourceId,
				List<PhotoPojo> photoList) {
			super(context, 1, 1, photoList);
			// TODO Auto-generated constructor stub
			this.context = context;
			this.photoList = photoList;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			View view = convertView;

			if (view == null) {
				LayoutInflater inflater = AlbumPhoto.this.getLayoutInflater();
				view = inflater.inflate(R.layout.photo_adapter, null);
			}

			// textView = (TextView) view.findViewById(R.id.textview_album);
			// textView.setText(photoList.get(position).albumName);
			imageView = (ImageView) findViewById(R.id.photo_imageview);
			AQuery aq = new AQuery(view);

			File file = aq.getCachedFile(photoList.get(position).url);

			if (file != null) {
				aq.id(R.id.photo_imageview).image(file, 25);
			} else {
				aq.id(R.id.photo_imageview).image(photoList.get(position).url, false,
						true, 25, R.drawable.ic_launcher);
			}

			return view;
		}

	}*/
}
