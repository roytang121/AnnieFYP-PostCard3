package com.example.user.anniefyppostcard;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;

/**
 * Created by roytang on 16/3/2016.
 */
public class Dp {
    public static float toPx(int dp, Context context) {
        Resources r = context.getResources();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics());
    }
}
