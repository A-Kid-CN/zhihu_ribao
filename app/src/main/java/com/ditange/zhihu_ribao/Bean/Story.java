package com.ditange.zhihu_ribao.Bean;

import java.util.List;

/**
 * Created by ditange on 2016/8/30.
 */
public class Story {
    private List<String> images;
    private String type;
    private String id;
    private String ga_prefix;
    private String title;

    public void setImage(List<String> images) {
        this.images = images;
    }

    public List<String> getImage() {
        return images;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setGa_prefix(String ga_prefix) {
        this.ga_prefix = ga_prefix;
    }

    public String getGa_prefix() {
        return ga_prefix;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
