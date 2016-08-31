package com.ditange.zhihu_ribao.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ditange.zhihu_ribao.Bean.LeftMenu;
import com.ditange.zhihu_ribao.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ditange on 2016/8/28.
 */
public class leftRecyclerViewAdapter extends RecyclerView.Adapter<leftRecyclerViewAdapter.ViewHolder> {
    private List<LeftMenu> LeftMenuList;
    private List<String> nameList;

    public leftRecyclerViewAdapter(List<LeftMenu> LeftMenuList) {
        nameList = new ArrayList<>();
        this.LeftMenuList = LeftMenuList;
        for (LeftMenu menu : LeftMenuList) {
            nameList.add(menu.getName());
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.left_menu_item, null, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.item.setText(nameList.get(position));
    }

    @Override
    public int getItemCount() {
        return nameList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView item;

        public ViewHolder(View itemView) {
            super(itemView);
            item = (TextView) itemView.findViewById(R.id.item);
        }
    }
}
