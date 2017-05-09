package com.gkpoter.maptt.util;

import android.util.Log;

/**
 * Created by dy on 2017/2/20.
 */

public class L {
    private L(){}
    private final static boolean debug = true;
    public static void e(String TAG, String info){
        if(debug){
            Log.i(TAG,info);
        }
    }
}
