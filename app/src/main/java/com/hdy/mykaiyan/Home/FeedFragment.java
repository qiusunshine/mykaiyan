package com.hdy.mykaiyan.Home;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.hdy.mykaiyan.Base.BaseFragment;
import com.hdy.mykaiyan.Feed.DataCard;
import com.hdy.mykaiyan.Feed.adapter_feed;
import com.hdy.mykaiyan.R;
import com.hdy.mykaiyan.Util.Constants;
import java.util.ArrayList;
import java.util.List;
import cn.finalteam.okhttpfinal.HttpRequest;
import cn.finalteam.okhttpfinal.JsonHttpRequestCallback;

/**
 * 作者：By hdy
 * 日期：On 2017/10/10
 * 时间：At 18:42
 */

public class FeedFragment extends BaseFragment {
    private RecyclerView recyclerView;
    @Override
    protected int getLayoutId() {
        return R.layout.home_feed_fragment;
    }

    @Override
    protected void initView() {
        recyclerView=findView(R.id.home_feed_recyclerv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        initData();
    }

    @Override
    protected void initData() {
                HttpRequest.get(Constants.Feed_Api_Url,new JsonHttpRequestCallback(){
                @Override
                protected void onSuccess(com.alibaba.fastjson.JSONObject jsonObject) {
                    super.onSuccess(jsonObject);
                    try {
                        List<DataCard> listbean= new ArrayList<>();
                        com.alibaba.fastjson.JSONArray jsonArray=jsonObject.getJSONArray("itemList");
                        for (int i=0;i<jsonArray.size();i++) {
                            com.alibaba.fastjson.JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            if (jsonObject1.getString("type").equals("followCard")) {
                                com.alibaba.fastjson.JSONObject header = jsonObject1.getJSONObject("data").getJSONObject("header");
                                com.alibaba.fastjson.JSONObject content = jsonObject1.getJSONObject("data")
                                        .getJSONObject("content").getJSONObject("data").getJSONObject("cover");
                                DataCard databean=new DataCard();
                                databean.setTitle(header.getString("title"));
                                databean.setCover(content.getString("feed"));
                                databean.setDesc(header.getString("description"));
                                databean.setIcon(header.getString("icon"));
                                databean.setIconType(header.getString("iconType"));
                                listbean.add(databean);
                                //TODO 解析更多数据到Databean ,card的图片右下角“开眼精选”标识 布局都还没有完善
                            }
                        }
                        adapter_feed adapter_feed=new adapter_feed(getContext(),listbean);
                        recyclerView.setAdapter(adapter_feed);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

    }
}
