package com.example.yxj.demoindex;

import android.content.Context;

/**
 * Created by yxj on 16/4/5.
 */
public class PxUtils {

    public static int px2dp(Context context,int px){
        float density =  context.getResources().getDisplayMetrics().density;
        return (int)(px/density+0.5f);
    }

    public static int dp2px(Context context,int dp){
        float density =  context.getResources().getDisplayMetrics().density;
        return (int)(density*dp+0.5f);
    }

    public static int px2sp(Context context,int px){
        float density =  context.getResources().getDisplayMetrics().scaledDensity;
        return (int)(px/density+0.5f);
    }

}
