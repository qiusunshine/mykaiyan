package com.hdy.mykaiyan.Feed;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.util.ArrayList;
import java.util.List;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;

/**
 * 作者：By hdy
 * 日期：On 2017/10/13
 * 时间：At 16:01
 */

public class MyObservable {
    public Observable<List<DataCard>> getObservable(final com.alibaba.fastjson.JSONObject jsonObject){
        Observable<List<DataCard>> myobservable=Observable.create(new ObservableOnSubscribe<List<DataCard>>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<List<DataCard>> e) throws Exception {
                try {
                    List<DataCard> listbean= new ArrayList<>();
                    com.alibaba.fastjson.JSONArray jsonArray=jsonObject.getJSONArray("itemList");
                    for (int i=0;i<jsonArray.size();i++) {
                        com.alibaba.fastjson.JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        if (jsonObject1.getString("type").equals("followCard")) {
                            com.alibaba.fastjson.JSONObject header = jsonObject1.getJSONObject("data").getJSONObject("header");
                            com.alibaba.fastjson.JSONObject content = jsonObject1.getJSONObject("data")
                                    .getJSONObject("content").getJSONObject("data");
                            DataCard databean=new DataCard();
                            databean.setTitle(header.getString("title"));
                            databean.setCover(content.getJSONObject("cover").getString("feed"));
                            databean.setDesc(header.getString("description"));
                            databean.setIcon(header.getString("icon"));
                            databean.setIconType(header.getString("iconType"));
                            databean.setId(header.getString("id"));
                            databean.setDescription(content.getString("description"));
                            databean.setReplyCount(Integer.parseInt(content.getJSONObject("consumption").getString("replyCount")));
                            databean.setShareCount(Integer.parseInt(content.getJSONObject("consumption").getString("shareCount")));
                            databean.setCollectionCount(Integer.parseInt(content.getJSONObject("consumption").getString("collectionCount")));
                            DataCard.playinfo playinfo=new DataCard.playinfo();
                            JSONArray pijson=content.getJSONArray("playInfo");
                            for(int j=0;j<pijson.size();j++){
                                JSONObject jsonObject2=pijson.getJSONObject(j);
                                String playlist=jsonObject2.getString("urlList");
                                switch (jsonObject2.getString("name")){
                                    case "流畅":
                                        playinfo.setLow(playlist);
                                        break;
                                    case "标清":
                                        playinfo.setNormal(playlist);
                                        break;
                                    default:
                                        playinfo.setHigh(playlist);
                                        break;
                                }
                            }
                            databean.setplayinfo(playinfo);
                            listbean.add(databean);
                            //TODO card的图片右下角“开眼精选”标识 布局都还没有完善
                        }
                    }
                    e.onNext(listbean);
                } catch (Exception e1) {
                    e1.printStackTrace();
                    e.onNext(null);
                }
            }
        });
        return myobservable;
    }


}
