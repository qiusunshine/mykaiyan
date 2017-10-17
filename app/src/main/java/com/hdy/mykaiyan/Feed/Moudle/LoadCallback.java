package com.hdy.mykaiyan.Feed.Moudle;

import com.hdy.mykaiyan.Feed.JavaBean.DataCard;

import java.util.List;

/**
 * 作者：By hdy
 * 日期：On 2017/10/14
 * 时间：At 22:00
 */

public interface LoadCallback{
    void onSuccess(String url,List<DataCard> topIssues,List<DataCard> dataCards);
    void onStart();
    void onFailed();
}
