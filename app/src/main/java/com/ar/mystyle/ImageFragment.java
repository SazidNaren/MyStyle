package com.ar.mystyle;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ar.mystyle.custom.TouchImageView;
import com.style.facechanger.R;

/**
 * Created by SAZID ALI on 30/08/2016.
 */
public class ImageFragment extends Fragment{
TouchImageView zoomView;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.image_fragment,container,false);
        String url=getArguments().getString("url");
        zoomView=(TouchImageView)view.findViewById(R.id.my_zoom_view);
        Drawable drawable=Drawable.createFromPath(url);
        zoomView.setImageBitmap(((BitmapDrawable)drawable).getBitmap());
        return view;
    }
}
