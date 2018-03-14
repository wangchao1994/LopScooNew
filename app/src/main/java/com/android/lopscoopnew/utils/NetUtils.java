package com.android.lopscoopnew.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetUtils {
	public static boolean hasNetWork(Context context){
		ConnectivityManager con = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo workinfo = con.getActiveNetworkInfo();
		if(workinfo == null || !workinfo.isAvailable())
		{
			return false;
		}
		return true;
	}

}