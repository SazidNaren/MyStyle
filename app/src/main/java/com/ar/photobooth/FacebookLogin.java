/*
package com.ar.photobooth;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;


import com.facebook.AppEventsLogger;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.android.AsyncFacebookRunner;
import com.facebook.android.AsyncFacebookRunner.RequestListener;
import com.facebook.android.DialogError;
import com.facebook.android.Facebook;
import com.facebook.android.Facebook.DialogListener;
import com.facebook.android.FacebookError;
import com.facebook.widget.LoginButton;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class FacebookLogin extends Activity{
	private boolean isResumed = false;
	private UiLifecycleHelper uiHelper;
	private Session.StatusCallback callback = new Session.StatusCallback() {
		@Override
		public void call(Session session, SessionState state,
				Exception exception) {
			*/
/*
			 * Session.OpenRequest openRequest = null;
			 * 
			 * 
			 * openRequest = new Session.OpenRequest(LoginPage.this); if
			 * (openRequest != null) {
			 * openRequest.setPermissions(session.getPermissions
			 * ().add("user_groups")); session.openForRead(openRequest); }
			 *//*

			onSessionStateChange(session, state, exception);
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		
		if(Build.VERSION.SDK_INT>11)
			getActionBar().setDisplayHomeAsUpEnabled(true);
		
		try {
			//findViewById(R.id.login_button);
			com.facebook.widget.LoginButton loginButton =(LoginButton) findViewById(R.id.login_button);
			loginButton
					.setReadPermissions(Arrays.asList("user_location",
							"user_photo_video_tags", "user_likes", "user_groups","friend_list",
							"friends_videos","user_videos","friends_photos"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		uiHelper = new UiLifecycleHelper(this, callback);
		uiHelper.onCreate(savedInstanceState);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		uiHelper.onResume();
		isResumed = true;
		
			CreateAdView.getInstance().setSinglaotonAdview(this);
		

		// Call the 'activateApp' method to log an app event for use in
		// analytics and advertising reporting. Do so in
		// the onResume methods of the primary Activities that an app may be
		// launched into.
		AppEventsLogger.activateApp(this);
		
		try {
			Session session=Session.getActiveSession();
			SessionState sessionState= session.getState();
			session.getAccessToken();
			//session.closeAndClearTokenInformation();
			//session= Session.getActiveSession();
			Log.d("Inside resume :: session object ", session + "");
			if(sessionState == SessionState.CLOSED_LOGIN_FAILED)
			{
				*/
/*Toast.makeText(FacebookLogin.this, "Please check network connection. !!",
						Toast.LENGTH_SHORT).show();*//*

				Toast.makeText(FacebookLogin.this, "Some error ocured !!",
						Toast.LENGTH_SHORT).show();
			}
			if (session != null && session.isOpened()) {
				// if the session is already open, try to show the Videos Activity
				Log.d("Inside session : session opened() returns",
						session.isOpened() + "");
			//	showVideoPage();
				//this.finish();
				Intent intent=new Intent(FacebookLogin.this, FacebookPhoto.class);
				startActivity(intent);
				finish();
			} else if (session.isClosed()) {
				// otherwise present the splash screen and ask the user to login,
				// unless the user explicitly skipped.
*/
/*//*
/				Toast.makeText(FacebookLogin.this, "Log In to Proceed!!",
//						Toast.LENGTH_LONG).show();
*//*
			} else {
				*/
/*Toast.makeText(LoginPage.this, "Internet Problem Occured!!",
						Toast.LENGTH_LONG).show();
*//*
		}
		} catch (Exception e) {
			Log.d("Unable to resume activity","inside Login Page");
		}
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		uiHelper.onPause();
		isResumed = false;
	
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		uiHelper.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		uiHelper.onDestroy();
	}

	private void onSessionStateChange(Session session, SessionState state,
			Exception exception) {
		if (isResumed) {

			// check for the OPENED state instead of session.isOpened() since
			// for the
			// OPENED_TOKEN_UPDATED state, the selection fragment should already
			// be showing.
//			try{
//			if (state.equals(SessionState.OPENED)) {
//				showVideoPage();
//			}
//			}catch(NullPointerException e)
//			{
//				Log.d("Null Pointer exception","Login Page");
//			}
		}
	}

//	private void showVideoPage() {
//		// TODO Auto-generated method stub
//		Intent toVideos = new Intent(FacebookLogin.this, VideosPageActivity.class);
//		getSharedPreferences("Login", 0).edit().putString("login", "loggedIn")
//				.commit();
//
//		startActivity(toVideos);
//		// this.finish();
//
//	}
	
}
*/
