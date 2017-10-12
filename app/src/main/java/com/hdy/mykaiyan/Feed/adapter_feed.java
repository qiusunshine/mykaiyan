package com.hdy.mykaiyan.Feed;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.hdy.mykaiyan.Feed.ViewHolder.VhFeedBanner;
import com.hdy.mykaiyan.Feed.ViewHolder.VhFeedFollowCard;
import com.hdy.mykaiyan.R;
import java.util.List;

/**
 * 作者：By hdy
 * 日期：On 2017/9/10
 * 时间：At 17:26
 */

public class adapter_feed extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private static final int TYPE_banner = 1;
    private static final int TYPE_follow_card = 2;
    private List<DataCard> followCardArray;
    public adapter_feed(Context context, List<DataCard> followCardArray){
        this.context=context;
        //TODO this.bannerArray=bannerArray;
        this.followCardArray=followCardArray;
    }

    @Override
    public int getItemViewType(int position) {
        if(position==0){
            return TYPE_banner;
        }
        else {
            return TYPE_follow_card;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case TYPE_banner:
                return new VhFeedBanner(LayoutInflater.from(context).inflate(R.layout.home_feed_item_banner,parent,false));
            case TYPE_follow_card:
                return new VhFeedFollowCard(LayoutInflater.from(context).inflate(R.layout.home_feed_item_followcard,parent,false));
            default:return new VhFeedFollowCard(LayoutInflater.from(context).inflate(R.layout.home_feed_item_followcard,parent,false));
        }
    }
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, int position) {
        BindViewHolderHelperFeed bindViewHolderHelperFeed=new BindViewHolderHelperFeed(context,viewHolder,position,this);
        bindViewHolderHelperFeed.DoBindViewHolder();
    }

    @Override
    public int getItemCount() {
        return followCardArray.size()+1;
    }

    public List<DataCard> getFollowCardArray(){
        return followCardArray;
    }
}
