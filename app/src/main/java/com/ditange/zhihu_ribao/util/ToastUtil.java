package com.ditange.zhihu_ribao.util;

import android.util.Log;
import android.widget.Toast;

import com.ditange.zhihu_ribao.MyApplication;

/**
 * Created by ditange on 2016/8/26.
 */
public class ToastUtil {

    public static void shortShow(String content) {
        Toast.makeText(MyApplication.getInstance(), content, Toast.LENGTH_SHORT).show();
    }

    public static void longShow(String content) {
        Toast.makeText(MyApplication.getInstance(), content, Toast.LENGTH_LONG).show();
    }

    public static void showLog(String content) {
        Log.d("TAG", content);
    }
}
