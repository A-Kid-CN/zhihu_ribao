package com.ditange.zhihu_ribao.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.ditange.zhihu_ribao.Bean.Story;
import com.ditange.zhihu_ribao.Bean.TopStory;
import com.ditange.zhihu_ribao.R;
import com.ditange.zhihu_ribao.api.MyApi;
import com.ditange.zhihu_ribao.fragment.MainFragment;
import com.ditange.zhihu_ribao.util.NetWorkUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private Toolbar toolbar;
    private MainFragment mainFragment;
    public static ArrayList<Story> storyArrayList;
    public static ArrayList<TopStory> topStoryArrayList;

    private Handler uiHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            initView();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        getTodayInfo();
    }

    private void initView() {
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        mainFragment = new MainFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, mainFragment).commit();
        mainFragment.setSwipeRefreshLayout(mSwipeRefreshLayout);

        //  mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar.inflateMenu(R.menu.toolbar_menu);
        toolbar.setNavigationIcon(R.mipmap.home);
        toolbar.setTitle(R.string.app_name);
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));


    }


    /**
     * 获取主菜单内容
     */
    public void getTodayInfo() {
        NetWorkUtil.get(getApplicationContext(), MyApi.todayInfo, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);

                Gson gson = new Gson();
                storyArrayList = new ArrayList<>();
                topStoryArrayList = new ArrayList<>();

                try {
                    storyArrayList = gson.fromJson(response.getJSONArray("stories").toString(),
                            new TypeToken<ArrayList<Story>>() {
                            }.getType());

                    topStoryArrayList = gson.fromJson(response.getJSONArray("top_stories").toString(),
                            new TypeToken<ArrayList<TopStory>>() {
                            }.getType());

                    uiHandler.sendEmptyMessage(0);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });
    }


    @Override
    public void onRefresh() {

    }
}
