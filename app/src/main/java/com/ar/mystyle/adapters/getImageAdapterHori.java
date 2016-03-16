package com.ar.mystyle.adapters;




import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

import com.ar.mystyle.activities.EditorActivity;
import com.mystyle.R;

public class getImageAdapterHori extends BaseAdapter {
private Context mContext;
public static Integer[] minageIds=
{
		R.drawable.c14,
		R.drawable.g6,
		R.drawable.h8,
		R.drawable.l6,
		R.drawable.m13,
		R.drawable.w8,
		
};
public getImageAdapterHori(Context context)
{
mContext=context;
}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return minageIds.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return minageIds[position];
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
		i.setImageResource(minageIds[index]);
		i.setScaleType(ImageView.ScaleType.FIT_XY);
		i.setLayoutParams(new Gallery.LayoutParams((EditorActivity.dWidth/5),(EditorActivity.dHeight/7)));
		return i;
	}

}
