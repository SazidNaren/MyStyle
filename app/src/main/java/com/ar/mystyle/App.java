package com.ar.mystyle;


import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.mystyle.R;
import com.sromku.simple.fb.Permission;
import com.sromku.simple.fb.SimpleFacebook;
import com.sromku.simple.fb.SimpleFacebookConfiguration;

public class App extends Application
{
	private static SharedPreferences		preferences;
	private static SharedPreferences.Editor	editor;
	private static Context					context;

	public static Context getContext()
	{
		return App.context;
	}

	public static SharedPreferences getPreferences()
	{
		return App.preferences;
	}

	public static SharedPreferences.Editor getEditor()
	{
		return App.editor;
	}

	@Override
	public void onCreate()
	{
		App.context = getApplicationContext();
		App.preferences=getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
		App.editor=App.preferences.edit();
		Permission[] permissions = new Permission[] {
				Permission.USER_PHOTOS,
				Permission.EMAIL,
				Permission.PUBLISH_ACTION
		};
		SimpleFacebookConfiguration configuration = new SimpleFacebookConfiguration.Builder()
				.setAppId(getString(R.string.fb_app_id))
						.setNamespace(getString(R.string.app_name))
						.setPermissions(permissions)
						.build();
		SimpleFacebook.setConfiguration(configuration);
		super.onCreate();
	}

	
}
