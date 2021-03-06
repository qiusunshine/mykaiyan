package com.hdy.mykaiyan.Feed.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hdy.mykaiyan.R;

/**
 * 作者：By hdy
 * 日期：On 2017/10/11
 * 时间：At 20:24
 */

public class VhFeedVideoCollection extends RecyclerView.ViewHolder {
    public ImageView img,icon,more;
    public TextView title,desc;
    public VhFeedVideoCollection(View itemView) {
        super(itemView);
        img=itemView.findViewById(R.id.home_feed_followcard_img);
        icon=itemView.findViewById(R.id.home_feed_followcard_icon);
        more=itemView.findViewById(R.id.home_feed_followcard_more);
        title=itemView.findViewById(R.id.home_feed_followcard_title);
        desc=itemView.findViewById(R.id.home_feed_followcard_desc);
    }
}
