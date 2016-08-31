package com.ditange.zhihu_ribao.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ditange.zhihu_ribao.Bean.Story;
import com.ditange.zhihu_ribao.R;
import com.ditange.zhihu_ribao.util.ImageLoaderUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ditange on 2016/8/30.
 */
public class homeRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Story> storyArrayList;
    private final int TYPE_CONTENT = 0;
    private final int TYPE_HEADER = 1;
    private ArrayList<String> titleList;
    private ArrayList<List<String>> imageList;
    private int headerCount = 1;


    public homeRecyclerViewAdapter(ArrayList<Story> storyArrayList) {
        titleList = new ArrayList<>();
        imageList = new ArrayList<>();

        this.storyArrayList = storyArrayList;
        for (Story story : storyArrayList) {
            titleList.add(story.getTitle());
            imageList.add(story.getImage());
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (headerCount > 0 && position < headerCount) {
            return TYPE_HEADER;
        } else {
            return TYPE_CONTENT;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {
            View headView = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_carousel, null, false);
            HeaderViewHolder viewHolder = new HeaderViewHolder(headView);
            return viewHolder;
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_recyclerview_item, null, false);
            contentViewHolder viewHolder = new contentViewHolder(view);

            return viewHolder;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof HeaderViewHolder) {

        } else if (viewHolder instanceof contentViewHolder) {
            ((contentViewHolder) viewHolder).item.setText(titleList.get(position - headerCount));
            ImageLoaderUtils.displayImage(imageList.get(position - headerCount).get(0),
                    ((contentViewHolder) viewHolder).imageView,
                    ImageLoaderUtils.IMAGE_DEFAULT);
        }
    }

    @Override
    public int getItemCount() {
        return titleList.size() + headerCount;
    }

    public class contentViewHolder extends RecyclerView.ViewHolder {
        TextView item;
        ImageView imageView;

        public contentViewHolder(View itemView) {
            super(itemView);
            item = (TextView) itemView.findViewById(R.id.item_text);
            imageView = (ImageView) itemView.findViewById(R.id.item_img);
        }
    }

    public class HeaderViewHolder extends RecyclerView.ViewHolder {
        public HeaderViewHolder(View itemView) {
            super(itemView);
        }
    }


}
