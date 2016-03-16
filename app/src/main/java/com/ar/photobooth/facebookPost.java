/*
package com.ar.photobooth;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.List;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.android.AsyncFacebookRunner;
import com.facebook.android.Facebook;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;
import com.facebook.widget.LoginButton.UserInfoChangedCallback;

public class facebookPost extends FragmentActivity {

	private LoginButton loginBtn;


	private TextView userName;

	private UiLifecycleHelper uiHelper;

	private static final List<String> PERMISSIONS = Arrays.asList("publish_actions");

	private static String message = "#PhotoBooth";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		uiHelper = new UiLifecycleHelper(this, statusCallback);
		uiHelper.onCreate(savedInstanceState);

		setContentView(R.layout.activity_facebook);

		userName = (TextView) findViewById(R.id.user_name);
		loginBtn = (LoginButton) findViewById(R.id.fb_login_button);
		loginBtn.setUserInfoChangedCallback(new UserInfoChangedCallback() {
			@Override
			public void onUserInfoFetched(GraphUser user) {
				if (user != null) {
					userName.setText("Hello, " + user.getName());
					postImage();
				
				} else {
					userName.setText("You are not logged");
				}
			}
		});
	}


	private Session.StatusCallback statusCallback = new Session.StatusCallback() {
		@Override
		public void call(Session session, SessionState state,
				Exception exception) {
			if (state.isOpened()) {

				Log.d("FacebookSampleActivity", "Facebook session opened");
			} else if (state.isClosed()) {

				Log.d("FacebookSampleActivity", "Facebook session closed");
			}
		}
	};



	@SuppressWarnings("deprecation")
	public void postImage() {
		*/
/*String pathName=getIntent().getExtras().getString("image");
		Bitmap img = BitmapFactory.decodeFile(pathName);*//*

		*/
/*ByteArrayOutputStream stream = new ByteArrayOutputStream();
		img.compress(Bitmap.CompressFormat.PNG, 100, stream);
		byte[] byteArray = stream.toByteArray();
		Bundle params = new Bundle();              
		params.putString(Facebook.TOKEN, getAccessToken());              
		params.putString("method", "photos.upload");              
		params.putByteArray("picture", byteArray); 
		AsyncFacebookRunner mAsyncRunner = new AsyncFacebookRunner(facebook);              
		mAsyncRunner.request(null, params, "POST", new SampleUploadListener(), null);*//*

		String pathName=getIntent().getExtras().getString("image");
			Bitmap img = BitmapFactory.decodeFile(pathName);
			Request uploadRequest = Request.newUploadPhotoRequest(
					Session.getActiveSession(), img, new Request.Callback() {
						@Override
						public void onCompleted(Response response) {
							Toast.makeText(facebookPost.this,
									"Photo uploaded successfully",
									Toast.LENGTH_LONG).show();
								finish();
						}
					});
			Bundle params = uploadRequest.getParameters();
			params.putString("message", "#PhotoBooth");
			uploadRequest.setParameters(params);
			uploadRequest.executeAsync();
		 
	}

	public void postStatusMessage() {
		if (checkPermissions()) {
			Request request = Request.newStatusUpdateRequest(
					Session.getActiveSession(), message,
					new Request.Callback() {
						@Override
						public void onCompleted(Response response) {
							if (response.getError() == null)
								Toast.makeText(facebookPost.this,
										"Status updated successfully",
										Toast.LENGTH_LONG).show();
						}
					});
			request.executeAsync();
		} else {
			requestPermissions();
		}
	}

	public boolean checkPermissions() {
		Session s = Session.getActiveSession();
		if (s != null) {
			return s.getPermissions().contains("publish_actions");
		} else
			return false;
	}

	public void requestPermissions() {
		Session s = Session.getActiveSession();
		if (s != null)
			s.requestNewPublishPermissions(new Session.NewPermissionsRequest(
					this, PERMISSIONS));
	}

	@Override
	public void onResume() {
		Session session=Session.getActiveSession();
		if (session != null && session.isOpened()) {
			// if the session is already open, try to show the Videos Activity
			Log.d("Inside session : session opened() returns",
					session.isOpened() + "");
			//	showVideoPage();
			//this.finish();
			EditorActivity.isuserFacebooklogin=true;
			finish();
		}
		super.onResume();
		uiHelper.onResume();

	}

	@Override
	public void onPause() {
		super.onPause();
		uiHelper.onPause();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		uiHelper.onDestroy();
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		uiHelper.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void onSaveInstanceState(Bundle savedState) {
		super.onSaveInstanceState(savedState);
		uiHelper.onSaveInstanceState(savedState);
	}

}*/
