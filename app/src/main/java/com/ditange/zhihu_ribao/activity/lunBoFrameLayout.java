package com.ditange.zhihu_ribao.activity;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ditange.zhihu_ribao.Bean.TopStory;
import com.ditange.zhihu_ribao.R;
import com.ditange.zhihu_ribao.adapter.lunboAdapter;
import com.ditange.zhihu_ribao.util.ImageLoaderUtils;

import java.util.ArrayList;


/**
 * Created by ditange on 2016/8/30.
 */
public class lunBoFrameLayout extends FrameLayout {

    private ViewPager mViewPager;
    private LinearLayout mLinearLayout;
    private ImageView imageView;
    private TextView textView;

    private ArrayList<View> viewList;
    private ArrayList<TopStory> topStoryArrayList;
    private Handler handler = new Handler();
    private int current_item;
    private boolean isAutoPlay;
    private Context context;
    private int length;

//    private Handler uiHandler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            initData();
//        }
//    };


    public lunBoFrameLayout(Context context) {
        super(context, null);
    }

    public lunBoFrameLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public lunBoFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView();
//        getNewInfo();
        initData();
    }


    private void initView() {
        View view = LayoutInflater.from(context).inflate(R.layout.lunbo_item, this, true);

        mViewPager = (ViewPager) view.findViewById(R.id.view_pager);
        //封装轮播下面的指示点
        mLinearLayout = (LinearLayout) view.findViewById(R.id.point);
    }


    private void initData() {
        viewList = new ArrayList<View>();
        topStoryArrayList = MainActivity.topStoryArrayList;

        length = topStoryArrayList.size();

        ImageLoaderUtils.initImageConf(context);

        for (int i = 0; i <= length; i++) {
            //viewpager的item
            View view = LayoutInflater.from(context).inflate(R.layout.lunbo_viewpager_item, null);
            imageView = (ImageView) view.findViewById(R.id.imageview);
            textView = (TextView) view.findViewById(R.id.text);

            if (i == length) {
                TopStory story = topStoryArrayList.get(0);
                ImageLoaderUtils.displayImage(story.getImage(), imageView, ImageLoaderUtils.IMAGE_DEFAULT);
            } else {
                TopStory story = topStoryArrayList.get(i);
                ImageLoaderUtils.displayImage(story.getImage(), imageView, ImageLoaderUtils.IMAGE_DEFAULT);
                textView.setText(story.getTitle());
            }
            viewList.add(view);
        }

        lunboAdapter myAdapter = new lunboAdapter(viewList);
        mViewPager.setAdapter(myAdapter);
        mViewPager.setCurrentItem(0);
        mViewPager.addOnPageChangeListener(new MyOnPageChangeListener());
        current_item = 0;

        //开始轮播
        startPlay();
    }

    private void startPlay() {
        isAutoPlay = true;
        handler.postDelayed(task, 1500);
    }

    private Runnable task = new Runnable() {
        @Override
        public void run() {
            current_item = current_item % (length + 1) + 1;
            if (isAutoPlay) {
                if (current_item == 0) {
                    mViewPager.setCurrentItem(current_item, false);
                    handler.postDelayed(task, 1500);
                } else {
                    mViewPager.setCurrentItem(current_item);
                    handler.postDelayed(task, 1500);
                }
            } else {
                handler.postDelayed(task, 1500);
            }
        }
    };


    class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        /**
         * arg0这个参数有三种状态（0，1，2）。arg0 ==1--默示正在滑动，arg0==2--默示滑动完毕了，arg0==0--默示什么都没做。
         *
         * @param arg0
         */
        @Override
        public void onPageScrollStateChanged(int arg0) {
            switch (arg0) {
                case 1:
                    isAutoPlay = false;
                    break;
                case 2:
                    isAutoPlay = true;
                    break;
                case 0:
                    if (mViewPager.getCurrentItem() == length) {
                        /**
                         * false表示无动画效果调到指定页。
                         * 由于最后一页和第一页的图片是一样的，无动画跳转给人的感觉就是已经是第一页
                         */
                        mViewPager.setCurrentItem(0, false);
                    }
                    current_item = mViewPager.getCurrentItem();
                    isAutoPlay = true;
                    break;
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageSelected(int arg0) {
        }
    }

//    public void getNewInfo() {
//        NetWorkUtil.get(context, MyApi.todayInfo, new JsonHttpResponseHandler() {
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//                super.onSuccess(statusCode, headers, response);
//                Gson gson = new Gson();
//                topStoryArrayList = new ArrayList<>();
//                try {
//                    topStoryArrayList = gson.fromJson(response.getJSONArray("top_stories").toString(),
//                            new TypeToken<ArrayList<TopStory>>() {
//                            }.getType());
//                    uiHandler.sendEmptyMessage(0);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
//                super.onFailure(statusCode, headers, responseString, throwable);
//            }
//
//            @Override
//            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
//                super.onFailure(statusCode, headers, throwable, errorResponse);
//            }
//        });
//    }
}
