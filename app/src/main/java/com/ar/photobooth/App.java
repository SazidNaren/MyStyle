package com.ar.photobooth;

import java.util.List;




import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

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
		super.onCreate();
	}

	
}
