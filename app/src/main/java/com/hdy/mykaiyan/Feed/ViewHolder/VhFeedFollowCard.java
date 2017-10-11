package com.hdy.mykaiyan.Feed.ViewHolder;

import android.support.v4.view.ViewPager;
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

public class VhFeedFollowCard extends RecyclerView.ViewHolder {
    ImageView img,icon,more;
    TextView title,desc;
    public VhFeedFollowCard(View itemView) {
        super(itemView);
        img=itemView.findViewById(R.id.home_feed_followcard_img);
        icon=itemView.findViewById(R.id.home_feed_followcard_icon);
        more=itemView.findViewById(R.id.home_feed_followcard_more);
        title=itemView.findViewById(R.id.home_feed_followcard_title);
        desc=itemView.findViewById(R.id.home_feed_followcard_desc);
    }
}
