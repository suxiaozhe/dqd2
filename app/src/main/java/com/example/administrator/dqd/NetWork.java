package com.example.administrator.dqd;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetWork {
    public static boolean isNetSystemUsable(Context context){
        boolean isNetUsable = false;
        ConnectivityManager manager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
//            NetworkCapabilities networkCapabilities =
//                    manager.getNetworkCapabilities(manager.getActiveNetwork());
//            isNetUsable = networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED);
            NetworkInfo networkInfo = manager.getActiveNetworkInfo();
            if(networkInfo != null&&networkInfo.isAvailable()){

                isNetUsable = true;
            }
        }
        return isNetUsable;
    }

}
