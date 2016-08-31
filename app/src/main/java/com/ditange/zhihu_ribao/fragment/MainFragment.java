package com.ditange.zhihu_ribao.fragment;

import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ditange.zhihu_ribao.Bean.Story;
import com.ditange.zhihu_ribao.R;
import com.ditange.zhihu_ribao.activity.MainActivity;
import com.ditange.zhihu_ribao.activity.lunBoFrameLayout;
import com.ditange.zhihu_ribao.adapter.homeRecyclerViewAdapter;

import java.util.ArrayList;

import static android.support.v7.widget.RecyclerView.ItemDecoration;
import static android.support.v7.widget.RecyclerView.State;

/**
 * Created by ditange on 2016/8/29.
 */
public class MainFragment extends BaseFragment {
    private RecyclerView recyclerView;
    private lunBoFrameLayout lunBoFrameLayout;
    private LinearLayoutManager mLayoutManager;
    public homeRecyclerViewAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ArrayList<Story> storyArrayList;
    private int firstVisibleItem;
    private int lastVisibleItem;

    private Handler uiHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            swipeRefreshLayout.setRefreshing(false);
        }
    };

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.home_recyclerview, null);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        storyArrayList = MainActivity.storyArrayList;

        mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(mLayoutManager);
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.space);
        recyclerView.addItemDecoration(new SpaceItemDecoration(spacingInPixels));//设置边距

        recyclerView.setHasFixedSize(true);//设置这个，当高固定时，可以提高性能

        if (storyArrayList != null) {
            adapter = new homeRecyclerViewAdapter(storyArrayList);
            recyclerView.setAdapter(adapter);
            //为recyclerView设置滑动监听
            recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                    if (newState == recyclerView.SCROLL_STATE_IDLE && firstVisibleItem == 0) {
                        swipeRefreshLayout.setEnabled(true);
                        uiHandler.sendEmptyMessageDelayed(0, 3000);
                    } else {
                        swipeRefreshLayout.setEnabled(false);
                    }
                }

                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    firstVisibleItem = mLayoutManager.findFirstVisibleItemPosition();
                }
            });
        } else {
            recyclerView.setAdapter(null);
        }
        Log.d("TAG", "实例1:" + swipeRefreshLayout);
        return view;
    }


    /**
     * ItemDecoration装饰item，这里用于设置边距
     */
    public class SpaceItemDecoration extends ItemDecoration {

        private int space;

        public SpaceItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, State state) {

            if (parent.getChildPosition(view) != 0)
                outRect.top = space;
        }
    }


    public void setSwipeRefreshLayout(final SwipeRefreshLayout swipeRefreshLayout) {
        this.swipeRefreshLayout = swipeRefreshLayout;

        Log.d("TAG", "实例2:" + swipeRefreshLayout + "实例2:" + recyclerView);
    }
}
