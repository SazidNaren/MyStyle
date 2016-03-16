package com.ar.photobooth;

import android.content.Context;
import android.net.ConnectivityManager;

public class FacebookPojo {

	String id, name, url;

	public FacebookPojo(String id, String name, String url) {
		// TODO Auto-generated constructor stub
		this.id = id;
		this.name = name;
		this.url = url;
	}

	public static boolean checkInternetConnection(final Context ctx) {
		final ConnectivityManager conMgr = (ConnectivityManager) ctx
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		if (conMgr.getActiveNetworkInfo() != null
				&& conMgr.getActiveNetworkInfo().isAvailable()
				&& conMgr.getActiveNetworkInfo().isConnected()) {
			return true;
		}

		return false;
	}
}
