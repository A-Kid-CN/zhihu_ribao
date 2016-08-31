package com.ditange.zhihu_ribao.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import com.ditange.zhihu_ribao.R;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LRULimitedMemoryCache;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.utils.DiskCacheUtils;
import com.nostra13.universalimageloader.utils.MemoryCacheUtils;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;

/***
 * ImageLoader工具类
 *
 * @author qpf
 */
public class ImageLoaderUtils {
    public static final int IMAGE_DEFAULT = 100;
    public static final int IMAGE_CORNER = 101;
    public static final int IMAGE_ROUND = 102;

    public static void initImageConf(Context context) {
        File cacheDir = StorageUtils.getOwnCacheDirectory(context,
                "wanwucloud/imageloader/Cache");

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                context)
                .memoryCacheExtraOptions(480, 800)
                // max width, max height，即保存的每个缓存文件的最大长宽
                .threadPoolSize(1)
                // 线程池内加载的数量
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new UsingFreqLimitedMemoryCache(8 * 1024 * 1024))
                .memoryCache(new LRULimitedMemoryCache(8 * 1024 * 1024))
                .memoryCache(new WeakMemoryCache())
                // LruMemoryCache
                // .memoryCache([new LruMemoryCache(1)])
                // You can pass your own memory cache
                // implementation/你可以通过自己的内存缓存实现
                .memoryCacheSize(8 * 1024 * 1024)
                .diskCacheSize(50 * 1024 * 1024)
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                // 将保存的时候的URI名称用MD5 加密
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .diskCacheFileCount(50)
                // 缓存的文件数量
                .diskCache(new UnlimitedDiscCache(cacheDir))
                // 自定义缓存路径
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple())
                .imageDownloader(
                        new BaseImageDownloader(context, 5 * 1000, 30 * 1000)) // connectTimeout
                // (5
                // s),
                // readTimeout
                // (30
                // s)超时时间
                .writeDebugLogs() // Remove for release app
                .build();// 开始构建
        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config);// 全局初始化此配置
    }

    /***
     * ImageLoader圆形头像配置
     *
     * @return
     */
    private static DisplayImageOptions getRoundOption() {
        DisplayImageOptions options;
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.user) // 设置图片在下载期间显示的图片
                .showImageForEmptyUri(R.drawable.user)// 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.drawable.user) // 设置图片加载/解码过程中错误时候显示的图片
                .cacheInMemory(false)// 设置下载的图片是否缓存在内存中
                .cacheOnDisk(true)// 设置下载的图片是否缓存在SD卡中
                .considerExifParams(true) // 是否考虑JPEG图像EXIF参数（旋转，翻转）
                // 设置图片以如何的编码方式显示
                .bitmapConfig(Bitmap.Config.RGB_565)// 设置图片的解码类型//
                .resetViewBeforeLoading(true)
                // 设置图片在下载前是否重置，复位
                // .displayer(new FadeInBitmapDisplayer(1000))
                // 是否图片加载好后渐入的动画时间
                .displayer(new RoundedBitmapDisplayer(120))// 是否设置为圆角，弧度为多少
                .build();// 构建完成

        return options;
    }

    /***
     * 四角圆
     *
     * @return
     */
    private static DisplayImageOptions getPhotoCornerOption() {
        DisplayImageOptions options;
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.default_img) // 设置图片在下载期间显示的图片
                .showImageForEmptyUri(R.drawable.default_img)//
                // 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.drawable.default_img) //
                // 设置图片加载/解码过程中错误时候显示的图片
                .cacheInMemory(false)// 设置下载的图片是否缓存在内存中
                .cacheOnDisk(true)// 设置下载的图片是否缓存在SD卡中
                .considerExifParams(true) // 是否考虑JPEG图像EXIF参数（旋转，翻转）
                .imageScaleType(ImageScaleType.EXACTLY)// 设置图片以如何的编码方式显示
                .bitmapConfig(Bitmap.Config.RGB_565)// 设置图片的解码类型//
                // .decodingOptions(android.graphics.BitmapFactory.Options
                // decodingOptions)//设置图片的解码配置
                // .delayBeforeLoading(int delayInMillis)//int
                // delayInMillis为你设置的下载前的延迟时间
                // 设置图片加入缓存前，对bitmap进行设置
                // .preProcessor(BitmapProcessor preProcessor)
                .resetViewBeforeLoading(true)// 设置图片在下载前是否重置，复位
                .displayer(new RoundedBitmapDisplayer(8))// 是否设置为圆角，弧度为多少
                // .displayer(new FadeInBitmapDisplayer(1000))// 是否图片加载好后渐入的动画时间
                .build();// 构建完成
        return options;
    }

    /***
     * 默认
     *
     * @return
     */
    private static DisplayImageOptions getPhotoOption() {
        DisplayImageOptions options;
        options = new DisplayImageOptions.Builder()
//		 .showImageOnLoading(R.drawable.default_img) // 设置图片在下载期间显示的图片
                .showImageForEmptyUri(R.drawable.default_img)//
                // 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.drawable.default_img) //
                // 设置图片加载/解码过程中错误时候显示的图片
                .cacheInMemory(false)// 设置下载的图片是否缓存在内存中
                .cacheOnDisk(true)// 设置下载的图片是否缓存在SD卡中
                .considerExifParams(true) // 是否考虑JPEG图像EXIF参数（旋转，翻转）
                .imageScaleType(ImageScaleType.EXACTLY)// 设置图片以如何的编码方式显示
                .bitmapConfig(Bitmap.Config.RGB_565)// 设置图片的解码类型//
                // .decodingOptions(android.graphics.BitmapFactory.Options
                // decodingOptions)//设置图片的解码配置
                // .delayBeforeLoading(int delayInMillis)//int
                // delayInMillis为你设置的下载前的延迟时间
                // 设置图片加入缓存前，对bitmap进行设置
                // .preProcessor(BitmapProcessor preProcessor)
//				.resetViewBeforeLoading(true)// 设置图片在下载前是否重置，复位
                // .displayer(new RoundedBitmapDisplayer(8))// 是否设置为圆角，弧度为多少
                // .displayer(new FadeInBitmapDisplayer(1000))// 是否图片加载好后渐入的动画时间
                .build();// 构建完成
        return options;
    }

    /***
     * 设置图片
     *
     * @param url
     * @param view
     * @param type
     */
    public static void displayImage(String url, final ImageView view, int type) {

        DisplayImageOptions options = getPhotoOption();
        switch (type) {
            case IMAGE_CORNER:
                options = getPhotoCornerOption();
                break;

            case IMAGE_DEFAULT:
                options = getPhotoOption();
                break;
            case IMAGE_ROUND:
                options = getRoundOption();
                break;
        }

        ImageLoader.getInstance().displayImage(url, view, options,
                new ImageLoadingListener() {
                    @Override
                    public void onLoadingStarted(String arg0, View arg1) {
                        ((ImageView) view)
                                .setScaleType(ImageView.ScaleType.CENTER_CROP);
                    }

                    @Override
                    public void onLoadingFailed(String arg0, View arg1,
                                                FailReason arg2) {
                        // TODO 默认图
                        ((ImageView) view)
                                .setScaleType(ImageView.ScaleType.CENTER_CROP);
                    }

                    @SuppressLint("NewApi")
                    @Override
                    public void onLoadingComplete(String arg0, View arg1,
                                                  Bitmap bitmap) {
                        ((ImageView) view)
                                .setScaleType(ImageView.ScaleType.CENTER_CROP);
                    }

                    @Override
                    public void onLoadingCancelled(String arg0, View arg1) {
                        ((ImageView) view)
                                .setScaleType(ImageView.ScaleType.CENTER_CROP);
                    }
                });
    }

    /**
     * 根据URL移除内存和磁盘缓存的图片
     *
     * @param uri
     * @return
     */
    public static void removeAllCacheByUri(String uri) {
        if (null == uri || "".equals(uri)) {
            return;
        }

        MemoryCacheUtils.removeFromCache(uri, ImageLoader.getInstance()
                .getMemoryCache());
        DiskCacheUtils.removeFromCache(uri, ImageLoader.getInstance()
                .getDiskCache());

    }

    /**
     * 根据URL移除内存缓存的图片
     *
     * @param uri
     * @return
     */
    public static void removeMemoryCacheByUri(String uri) {
        if (null == uri || "".equals(uri)) {
            return;
        }

        MemoryCacheUtils.removeFromCache(uri, ImageLoader.getInstance()
                .getMemoryCache());
    }
}
