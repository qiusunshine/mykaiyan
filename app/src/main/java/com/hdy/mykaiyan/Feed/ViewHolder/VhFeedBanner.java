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

public class VhFeedBanner extends RecyclerView.ViewHolder {
    public ImageView search;
    public TextView title,desc;
    public ViewPager viewPager;
    public VhFeedBanner(View itemView) {
        super(itemView);
        search=itemView.findViewById(R.id.home_feed_banner_search);
        title=itemView.findViewById(R.id.home_feed_banner_title);
        desc=itemView.findViewById(R.id.home_feed_banner_desc);
        viewPager=itemView.findViewById(R.id.home_feed_banner_vp);
    }
}
