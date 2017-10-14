package com.hdy.mykaiyan.Feed;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
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
    private onFeedItemClickListener onFeedItemClickListener;
    BindViewHolderHelperFeed(Context context, RecyclerView.ViewHolder viewHolder, int position, adapter_feed myadapter_feed,onFeedItemClickListener onFeedItemClickListener){
        this.context=context;
        this.viewHolder=viewHolder;
        this.position=position;
        this.myadapter_feed=myadapter_feed;
        this.onFeedItemClickListener=onFeedItemClickListener;
    }
    void DoBindViewHolder(){
        //Glide加载参数初始化
         options= new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.loading)
                .error(R.drawable.loading);
        if(viewHolder instanceof VhFeedFollowCard){
            bindVhFeedFollowCard();
        }
    }
    private void bindVhFeedFollowCard(){
            List<DataCard> jsonArray=myadapter_feed.getFollowCardArray();
            final DataCard databean=jsonArray.get(position);//注意之前有banner，需要减1
            final VhFeedFollowCard holder=(VhFeedFollowCard)viewHolder;
            holder.title.setText(databean.getTitle());
            holder.desc.setText(databean.getDesc());
            Glide.with(context).asBitmap().load(databean.getCover()).apply(options)
                    .into(holder.img);
            Glide.with(context).asBitmap().load(databean.getIcon()).apply(options)
                .into(new BitmapImageViewTarget(holder.icon){
                    @Override
                    protected void setResource(Bitmap resource) {
                        if(databean.getIconType().equals("square")){ //根据iconType为icon设置不同的形状
                            holder.icon.setImageBitmap(resource);
                        }
                        else {
                            RoundedBitmapDrawable roundedBitmapDrawable= RoundedBitmapDrawableFactory.create(context.getResources(),resource);
                            roundedBitmapDrawable.setCircular(true);
                            holder.icon.setImageDrawable(roundedBitmapDrawable);
                        }
                    }
                });
            holder.img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onFeedItemClickListener.goToPlay(position);
                }
            });
    }

}
