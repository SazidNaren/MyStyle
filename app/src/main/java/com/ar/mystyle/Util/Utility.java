package com.ar.mystyle.Util;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by sajid on 18/3/16.
 */
public class Utility {

    public static int dpToPx(int dp,Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }
}
