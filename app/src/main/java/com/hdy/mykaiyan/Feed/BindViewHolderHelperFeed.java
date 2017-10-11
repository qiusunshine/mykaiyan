package com.hdy.mykaiyan.Feed;

import android.support.v7.widget.RecyclerView;
import com.hdy.mykaiyan.Feed.ViewHolder.VhFeedBanner;
import com.hdy.mykaiyan.Feed.ViewHolder.VhFeedFollowCard;
import org.json.JSONArray;

/**
 * 作者：By hdy
 * 日期：On 2017/10/11
 * 时间：At 21:17
 */

public class BindViewHolderHelperFeed {
    private RecyclerView.ViewHolder viewHolder;
    private adapter_feed myadapter_feed;
    int position;
    public BindViewHolderHelperFeed(RecyclerView.ViewHolder viewHolder,int position,adapter_feed myadapter_feed){
        this.viewHolder=viewHolder;
        this.position=position;
        this.myadapter_feed=myadapter_feed;
    }
    public void DoBindViewHolder(){
        if(viewHolder instanceof VhFeedBanner){
            bindVhFeedBanner();
        }
        else if(viewHolder instanceof VhFeedFollowCard){
            bindVhFeedFollowCard();
        }
    }
    public void bindVhFeedBanner(){
        JSONArray jsonArray=myadapter_feed.getBannerArray();
        //TODO  将数据绑定到view
    }
    public void bindVhFeedFollowCard(){
        JSONArray jsonArray=myadapter_feed.getFollowCardArray();
        //TODO  将数据绑定到view
    }

}
