package com.ar.mystyle.adapters;




import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

import com.ar.mystyle.ImageIds;
import com.ar.mystyle.activities.EditorActivity;
import com.mystyle.R;

import java.util.ArrayList;

public class GetImageAdapterHori extends BaseAdapter {
	private Context mContext;
	ArrayList<Drawable> minageIds=new ArrayList<>();
	public GetImageAdapterHori(Context context)
	{
		mContext=context;
		ImageIds imageIds=ImageIds.getInstance(context);
		minageIds.add(imageIds.getImageIdCaps().get(10));
		minageIds.add(imageIds.getImageIdGoggles().get(14));
		minageIds.add(imageIds.getImageIsHeirs().get(2));
		minageIds.add(imageIds.getImageIsLips().get(0));
		minageIds.add(imageIds.getImageIsMouths().get(22));
		minageIds.add(imageIds.getImageIsW_Heir().get(3));

	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return minageIds.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return minageIds.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@SuppressWarnings("deprecation")
	@Override
	public View getView(int index, View view, ViewGroup viewgroup) {
		// TODO Auto-generated method stub
		ImageView i=new ImageView(mContext);
		i.setImageDrawable(minageIds.get(index));
		i.setScaleType(ImageView.ScaleType.FIT_CENTER);
		i.setLayoutParams(new Gallery.LayoutParams((EditorActivity.dWidth/5),(EditorActivity.dHeight/7)));
		return i;
	}

}
