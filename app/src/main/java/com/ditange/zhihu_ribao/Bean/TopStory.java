package com.ditange.zhihu_ribao.Bean;

/**
 * 轮播内容
 * Created by ditange on 2016/8/30.
 */
public class TopStory {
    private String image;
    private String type;
    private String id;
    private String ga_prefix;
    private String title;

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
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
