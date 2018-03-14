package com.android.lopscoopnew.manager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.android.lopscoopnew.activity.MainActivity;

/**
 * Created by wangchao on 18-3-14.
 */

public class ActivityManager {
    private  static  ActivityManager activityManager;
    private ActivityManager() {
    }

    public static ActivityManager getInstance(){
        if (activityManager != null){
            synchronized (ActivityManager.class){
                if (activityManager != null){
                    activityManager = new ActivityManager();
                }
            }
        }
        return activityManager;
    }

}
