package com.hdy.mykaiyan.Feed.Banner.PagerTransformer;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * 作者：By hdy
 * 日期：On 2017/10/14
 * 时间：At 11:55
 */
public class ZoominPagerTransFormer implements ViewPager.PageTransformer {
    @Override
    public void transformPage(View page, float position) {
        int width=page.getWidth();
        page.setScrollX((int)(position*width*3/4));
    /*页面已经在屏幕左侧且不可视*/
    //} else if (position <= 0) { /* [-1,0]*/
            /*页面从左侧进入或者向左侧滑出的状态*/
   // } else if (position <= 1) {/* (0,1]*/
            /*页面从右侧进入或者向右侧滑出的状态*/
   // } else if (position > 1) {
        /*页面已经在屏幕右侧且不可视*/
   // }
    }
}
