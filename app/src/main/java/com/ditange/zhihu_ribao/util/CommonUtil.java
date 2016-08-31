package com.ditange.zhihu_ribao.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by ditange on 2016/8/27.
 */
public class CommonUtil {
    /**
     * 检查是否有网络
     */
    public static boolean isNetWorkAvailable(Context context){
        NetworkInfo info = getNetworkInfo(context);
        if(info !=null){
            return info.isAvailable();
        }
        return false;
    }

    private static NetworkInfo getNetworkInfo(Context context){
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return manager.getActiveNetworkInfo();
    }
}
