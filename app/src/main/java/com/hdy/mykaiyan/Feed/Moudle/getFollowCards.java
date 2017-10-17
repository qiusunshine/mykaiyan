package com.hdy.mykaiyan.Feed.Moudle;

import com.hdy.mykaiyan.Feed.JavaBean.DataCard;
import com.hdy.mykaiyan.Feed.MyObservable;

import java.util.ArrayList;
import java.util.List;
import cn.finalteam.okhttpfinal.HttpRequest;
import cn.finalteam.okhttpfinal.JsonHttpRequestCallback;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 作者：By hdy
 * 日期：On 2017/10/14
 * 时间：At 22:08
 */

public class getFollowCards implements NetTask<String> {

    public getFollowCards(){
    }
    @Override
    public void execute(final String data, final LoadCallback callback) {
        callback.onStart();
        HttpRequest.get(data,new JsonHttpRequestCallback(){
            @Override
            protected void onSuccess(final com.alibaba.fastjson.JSONObject jsonObject) {
                super.onSuccess(jsonObject);
                Observable<List<DataCard>> observable=new MyObservable(data).getObservable(jsonObject);
                Observer<List<DataCard>> observer=new Observer<List<DataCard>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {}
                    @Override
                    public void onNext(@NonNull List<DataCard> dataCards) {
                        if(dataCards==null){
                            callback.onFailed();
                        }
                        else {
                            List<DataCard> dataCard1= new ArrayList<>();
                            List<DataCard> dataCard2=dataCards;
                            for (int i=0;i<dataCards.size();i++) {
                                if(dataCards.get(i).getType().equals("HorizontalScrollCard")){
                                    dataCard1.add(dataCards.get(i));
                                    dataCard2.remove(dataCards.get(i));
                                }
                            }
                            callback.onSuccess(data,dataCard1,dataCard2);
                        }

                    }
                    @Override
                    public void onError(@NonNull Throwable e) {
                        callback.onFailed();
                    }
                    @Override
                    public void onComplete() {}
                };
                observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                        .subscribe(observer);
            }
            @Override
            public void onFailure(int errorCode, String msg) {
                super.onFailure(errorCode, msg);
                callback.onFailed();
            }
        });
    }
}
