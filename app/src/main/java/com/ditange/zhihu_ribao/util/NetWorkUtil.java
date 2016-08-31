package com.ditange.zhihu_ribao.util;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

/**
 * Created by ditange on 2016/8/27.
 */
public class NetWorkUtil {
    private static AsyncHttpClient client = new AsyncHttpClient();//实例化对象；

    public static AsyncHttpClient getClient() {
        return client;
    }

    static {
        client.setTimeout(3000);
    }

    /**
     * 不带参数， 返回json对象或者数组
     * @param context
     * @param url
     * @param responseHandler
     */
    public static void get(Context context, String url, JsonHttpResponseHandler responseHandler) {
        if (CommonUtil.isNetWorkAvailable(context)) {
            client.get(url, responseHandler);
        } else {
            responseHandler.onFailure(-1, null, "网络不可用", null);
        }
    }

}
