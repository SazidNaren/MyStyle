package com.ar.mystyle.view;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created by SAZID ALI on 21/08/2016.
 */
public class MyButton extends Button{
    public MyButton(Context context) {
        super(context);
        setFontStyle(context);
    }

    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        setFontStyle(context);
    }

    public MyButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setFontStyle(context);
    }

    public MyButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setFontStyle(context);
    }
    private void setFontStyle(Context context)
    {
        Typeface type = Typeface.createFromAsset(context.getAssets(),"fonts/times_roman.ttf");
        setTypeface(type);
    }
}
