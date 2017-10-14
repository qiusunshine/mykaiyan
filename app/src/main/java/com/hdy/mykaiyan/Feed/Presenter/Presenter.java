package com.hdy.mykaiyan.Feed.Presenter;

import android.content.Context;
import com.hdy.mykaiyan.Feed.DataCard;
import com.hdy.mykaiyan.Feed.MyObservable;
import com.hdy.mykaiyan.Util.Constants;
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
 * 时间：At 20:37
 */

public class Presenter implements PresenterContract.Presenter {
    private PresenterContract.View view;
    private Context context;
    public Presenter (Context context,PresenterContract.View view){
        this.context=context;
        this.view=view;
    }
    @Override
    public void initData() {
        view.showLoading();
        HttpRequest.get(Constants.Feed_Api_Url,new JsonHttpRequestCallback(){
            @Override
            protected void onSuccess(final com.alibaba.fastjson.JSONObject jsonObject) {
                super.onSuccess(jsonObject);
                Observable<List<DataCard>> observable=new MyObservable().getObservable(jsonObject);
                Observer<List<DataCard>> observer=new Observer<List<DataCard>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {}
                    @Override
                    public void onNext(@NonNull List<DataCard> dataCards) {
                        if(dataCards==null){
                           view.showLoadFail();
                        }
                        else {
                            view.showInitSuccess(dataCards);
                        }

                    }
                    @Override
                    public void onError(@NonNull Throwable e) {}
                    @Override
                    public void onComplete() {}
                };
                observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                        .subscribe(observer);
            }
            @Override
            public void onFailure(int errorCode, String msg) {
                super.onFailure(errorCode, msg);
                view.showLoadFail();
            }
        });
    }
    @Override
    public void loadmore() {
       view.showLoading();
    }
}
