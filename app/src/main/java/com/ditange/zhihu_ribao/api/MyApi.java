package com.ditange.zhihu_ribao.api;

/**
 * Created by ditange on 2016/8/27.
 */
public class MyApi {
    public static final String host = "http://news-at.zhihu.com/api/4/";
    /**
     * 启动界面图像获取
     */
    public static final String startImg = "http://newxs-at.zhihu.com/api/4/start-image/1080*1776";
    /**
     * 最新消息获取
     */
    public static final String todayInfo = "http://news-at.zhihu.com/api/4/news/latest";
    /**
     * 消息内容获取与离线下载
     * 后面拼上要获取的消息id
     */
    public static final String getNewInfo = "http://news-at.zhihu.com/api/4/news/";

    /**
     * 后面拼接新闻的ID，获取对应新闻的额外信息，如评论数量，所获的『赞』的数量。
     */
    public static final String getExtraNewInfo = "http://news-at.zhihu.com/api/4/story-extra/";
    /**
     * 新闻对应长评论查看首部
     * （中间拼接id）
     */
    public static final String longcomments1 = "http://news-at.zhihu.com/api/4/story/";
    /**
     * 新闻对应长评论查看尾部
     */
    public static final String longcomments2 = "/long-comments";
    /**
     * 新闻对应短评论查看首部
     * （中间拼接id）
     */
    public static final String shortcomments1 = "http://news-at.zhihu.com/api/4/story/";
    /**
     * 新闻对应短评论查看尾部
     */
    public static final String shortcomments2 = "/short-comments";
    /**
     * 主题日报列表查看（左菜单内容）
     */
    public static final String leftMenuList = "http://news-at.zhihu.com/api/4/themes";

}
