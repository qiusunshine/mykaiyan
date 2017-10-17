package com.hdy.mykaiyan.Feed.JavaBean;

/**
 * 作者：By hdy
 * 日期：On 2017/10/12
 * 时间：At 19:53
 */

public class DataCard {
    private String title;
    private String desc,Description;
    private String icon;
    private String iconType;
    private String cover;
    private playinfo myplayinfo;
    private int collectionCount;
    private int shareCount;
    private int replyCount;
    private String type;
    private String id;
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDesc() {
        return desc;
    }
    public void setDescription(String Description) {
        this.Description = Description;
    }
    public String getDescription() {
        return Description;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    public String getIcon() {
        return icon;
    }
    public void setIcon(String icon) {
        this.icon = icon;
    }
    public String getIconType() {
        return iconType;
    }
    public void setIconType(String iconType) {
        this.iconType = iconType;
    }
    public String getCover() {
        return cover;
    }
    public void setCover(String cover) {
        this.cover = cover;
    }
    public playinfo getplayinfo() {
        return myplayinfo;
    }
    public void setplayinfo(playinfo myplayinfo) {
        this.myplayinfo = myplayinfo;
    }
    public int getCollectionCount() {
        return collectionCount;
    }
    public void setCollectionCount(int collectionCount) {
        this.collectionCount = collectionCount;
    }
    public int getShareCount() {
        return shareCount;
    }
    public void setShareCount(int shareCount) {
        this.shareCount = shareCount;
    }
    public int getReplyCount() {
        return replyCount;
    }
    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
    }

    public static class playinfo{
        private String low,normal,high;
        public String getLow() {
            return low;
        }
        public void setLow(String low) {
            this.low =low;
        }
        public String getHigh() {
            return high;
        }
        public void setHigh(String high) {
            this.high =high;
        }
        public String getNormal() {
            return normal;
        }
        public void setNormal(String normal) {
            this.normal=normal;
        }
    }
}
