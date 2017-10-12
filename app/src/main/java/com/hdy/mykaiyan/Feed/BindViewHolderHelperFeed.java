package com.hdy.mykaiyan.Feed;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.hdy.mykaiyan.Feed.ViewHolder.VhFeedBanner;
import com.hdy.mykaiyan.Feed.ViewHolder.VhFeedFollowCard;
import com.hdy.mykaiyan.R;
import java.util.List;

/**
 * 作者：By hdy
 * 日期：On 2017/10/11
 * 时间：At 21:17
 */

class BindViewHolderHelperFeed {
    private RecyclerView.ViewHolder viewHolder;
    private adapter_feed myadapter_feed;
    private Context context;
    private int position;
    private RequestOptions options;
    BindViewHolderHelperFeed(Context context, RecyclerView.ViewHolder viewHolder, int position, adapter_feed myadapter_feed){
        this.context=context;
        this.viewHolder=viewHolder;
        this.position=position;
        this.myadapter_feed=myadapter_feed;
    }
    void DoBindViewHolder(){
        //Glide加载参数初始化
         options= new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.loading)
                .error(R.drawable.loading);
        if(viewHolder instanceof VhFeedBanner){
            bindVhFeedBanner();
        }
        else if(viewHolder instanceof VhFeedFollowCard){
            bindVhFeedFollowCard();
        }
    }
    private void bindVhFeedBanner(){
        //TODO  将数据绑定到Bannerview
    }
    private void bindVhFeedFollowCard(){
        //TODO 根据iconType为icon设置不同的形状
            List<DataCard> jsonArray=myadapter_feed.getFollowCardArray();
            DataCard databean=jsonArray.get(position-1);//注意之前有banner，需要减1
            VhFeedFollowCard holder=(VhFeedFollowCard)viewHolder;
            holder.title.setText(databean.getTitle());
            holder.desc.setText(databean.getDesc());
            Glide.with(context).asBitmap().load(databean.getIcon()).apply(options)
                    .into(holder.icon);
            Glide.with(context).asBitmap().load(databean.getCover()).apply(options)
                    .into(holder.img);
    }

}
