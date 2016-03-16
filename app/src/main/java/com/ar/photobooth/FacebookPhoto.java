/*
package com.ar.photobooth;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import com.androidquery.AQuery;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class FacebookPhoto extends Activity {
	String res;
	JSONArray jsonarray = null;
	String id, name, url;
	ArrayList<FacebookPojo> list;
	ListView listView;
	MyAdapter adapter;
	public static Bitmap bmap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.facebook_photo);
		listView = (ListView) findViewById(R.id.list_view);
      EditorActivity.isuserFacebooklogin=true;
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();

		StrictMode.setThreadPolicy(policy);
		list = new ArrayList<FacebookPojo>();
		try {

			if (FacebookPojo.checkInternetConnection(getApplicationContext())) {

				new TaskPerformer().execute();
			} else {
				Toast.makeText(getApplicationContext(),
						"check your internet connection", Toast.LENGTH_SHORT)
						.show();
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				String src = list.get(arg2).id;
				// bmap = getBitmapFromURL(src);
				// new Canvas(FacebookPhoto.this).setBackground(bmap);
				Intent i = new Intent(FacebookPhoto.this, AlbumActivtiy.class);
				i.putExtra("facebook", true);
				i.putExtra("id", src);
				startActivity(i);

			}

		});
	}

	public class MyAdapter extends ArrayAdapter<FacebookPojo> {
		Context context;
		TextView textView;
		ImageView imageView;
		List<FacebookPojo> list;

		public MyAdapter(Context context, int textViewResourceId,
				List<FacebookPojo> list) {
			super(context, 1, 1, list);
			// TODO Auto-generated constructor stub
			this.context = context;
			this.list = list;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			View view = convertView;

			if (view == null) {
				LayoutInflater inflater = FacebookPhoto.this
						.getLayoutInflater();
				view = inflater.inflate(R.layout.my_adapter, null);
			}
			textView = (TextView) view.findViewById(R.id.textview);
			imageView = (ImageView) view.findViewById(R.id.imageview);

			textView.setText(list.get(position).name);

			AQuery aq = new AQuery(view);

			File file = aq.getCachedFile(list.get(position).url);

			if (file != null) {
				aq.id(R.id.imageview).image(file, 25);
			} else {
				aq.id(R.id.imageview).image(list.get(position).url, false,
						true, 25, R.drawable.ic_launcher);
			}

			return view;
		}

	}

	public class TaskPerformer extends AsyncTask<Void, Void, Void> {
		ProgressDialog progressDialog;

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();

			progressDialog = new ProgressDialog(FacebookPhoto.this);
			progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			progressDialog.setMessage(getResources()
					.getString(R.string.loading));
			progressDialog.setIndeterminate(true);
			progressDialog.setCancelable(false);
			progressDialog.show();
		}

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			try {
				Session session = Session.getActiveSession();
				String token = session.getAccessToken();
				HttpClient httpclient = new DefaultHttpClient();
				HttpGet post = new HttpGet(
						"https://graph.facebook.com/me?fields=friends.fields(picture,name)&access_token="
								+ token);
				// httpclient.execute(post);
				HttpResponse response = httpclient.execute(post);
				if (response.getStatusLine().getStatusCode() == 200) {
					res = EntityUtils.toString(response.getEntity(), "UTF-8");
				}

				if (res != null) {
					JSONObject jsonObj = new JSONObject(res);
					JSONObject businessObject = jsonObj
							.getJSONObject("friends");

					jsonarray = businessObject.getJSONArray("data");

					for (int i = 0; i < jsonarray.length(); i++) {
						JSONObject c = jsonarray.getJSONObject(i);
						if (c.has("id")) {
							id = c.getString("id");
						}
						if (c.has("name")) {
							name = c.getString("name");
						}
						if (c.has("picture")) {

							JSONObject ob1 = c.getJSONObject("picture");
							JSONObject ob2 = ob1.getJSONObject("data");
							url = ob2.getString("url");
						}
						list.add(new FacebookPojo(id, name, url));
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
				if (list != null && !(list.isEmpty())) {

					adapter = new MyAdapter(getApplicationContext(), 1, list);
					listView.setAdapter(adapter);
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
}
*/
