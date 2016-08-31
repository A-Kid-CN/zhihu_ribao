package com.ditange.zhihu_ribao.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.ditange.zhihu_ribao.Bean.LeftMenu;
import com.ditange.zhihu_ribao.R;
import com.ditange.zhihu_ribao.adapter.leftRecyclerViewAdapter;
import com.ditange.zhihu_ribao.api.MyApi;
import com.ditange.zhihu_ribao.util.NetWorkUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ditange on 2016/8/26.
 */
public class MenuFragment extends BaseFragment {

    private LinearLayout mLinearLayout;
    private RecyclerView mRecyclerView;
    private List<LeftMenu> LeftMenuList;

    /**
     * 布局管理器
     */
    private LinearLayoutManager mLayoutManager;

    Handler uiHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (LeftMenuList.size() != 0) {
                leftRecyclerViewAdapter adapter = new leftRecyclerViewAdapter(LeftMenuList);
                mRecyclerView.setAdapter(adapter);
            }
        }
    };

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        mLinearLayout = (LinearLayout) view.findViewById(R.id.home);
        LeftMenuList = new ArrayList<>();

        mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);

        getLeftMenuList();

        return view;
    }


    public void getLeftMenuList() {
        NetWorkUtil.get(getActivity(), MyApi.leftMenuList, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Gson gson = new Gson();
                try {
                    LeftMenuList = gson.fromJson(response.getJSONArray("others").toString(),
                            new TypeToken<List<LeftMenu>>() {
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
}
