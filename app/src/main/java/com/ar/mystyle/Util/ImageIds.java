package com.ar.mystyle.Util;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import java.io.InputStream;
import java.util.ArrayList;

public class ImageIds {

	public static ImageIds imageIds;
	private ImageIds(Context context)
	{
		getImages(context);
	}
	ArrayList<Drawable> ImageIsW_Heir=new ArrayList<>();
	ArrayList<Drawable> ImageIsMouths=new ArrayList<>();
	ArrayList<Drawable> ImageIsLips=new ArrayList<>();
	ArrayList<Drawable> ImageIsHeirs=new ArrayList<>();
	ArrayList<Drawable> ImageIdGoggles=new ArrayList<>();
	ArrayList<Drawable> imageIdCaps=new ArrayList<>();

	public ArrayList<Drawable> getImageIsW_Heir() {return ImageIsW_Heir;}

	public ArrayList<Drawable> getImageIsMouths() {
		return ImageIsMouths;
	}

	public ArrayList<Drawable> getImageIsLips() {
		return ImageIsLips;
	}

	public ArrayList<Drawable> getImageIsHeirs() {
		return ImageIsHeirs;
	}

	public ArrayList<Drawable> getImageIdGoggles() {
		return ImageIdGoggles;
	}
	public ArrayList<Drawable> getImageIdCaps() {
		return imageIdCaps;
	}


	void getImages(Context context) {
		AssetManager assetManager = context.getAssets();
		try {
			String[] files = assetManager.list("icons");
			for(int i=0;i<files.length;i++)
			{
				String[] item_lists=assetManager.list("icons/"+files[i]);
				for(int j=0;j<item_lists.length;j++)
				{
					InputStream stream=assetManager.open("icons/"+files[i]+"/"+item_lists[j]);
					Drawable drawable=Drawable.createFromStream(stream,"");
					switch (files[i])
					{
						case "caps":
							imageIdCaps.add(drawable);
							break;
						case "lips":
							ImageIsLips.add(drawable);
							break;
						//ImageIsW_Heir,ImageIsMouths,ImageIsLips,ImageIsHeirs,ImageIdGoggles,imageIdCaps
						case "men_hair":
							ImageIsHeirs.add(drawable);
							break;
						case "moustache":
							ImageIsMouths.add(drawable);
							break;
						case "specs":
							ImageIdGoggles.add(drawable);
							break;
						case "women_hair":
							ImageIsW_Heir.add(drawable);
							break;
					}
				}
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
	public static ImageIds getInstance(Context context)
	{
		if(imageIds==null)
			imageIds=new ImageIds(context);
		return imageIds;
	}
}
