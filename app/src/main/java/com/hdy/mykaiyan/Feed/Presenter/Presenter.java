package com.hdy.mykaiyan.Feed.Presenter;

import com.hdy.mykaiyan.Feed.JavaBean.DataCard;
import com.hdy.mykaiyan.Feed.Moudle.LoadCallback;
import com.hdy.mykaiyan.Feed.Moudle.NetTask;
import com.hdy.mykaiyan.Util.Constants;

import java.util.List;
/**
 * 作者：By hdy
 * 日期：On 2017/10/14
 * 时间：At 20:37
 */

public class Presenter implements PresenterContract.Presenter,LoadCallback {
    private PresenterContract.View view;
    private NetTask netTask;
    public Presenter (PresenterContract.View view, NetTask netTask){
        this.view=view;
        this.netTask=netTask;
    }
    @Override
    public void getData(String data) {

        netTask.execute(data,this);
    }

    @Override
    public void onSuccess(String url,List<DataCard> topIssues, List<DataCard> dataCards) {
        if(url.equals(Constants.Feed_Api_Url))
        view.showInitSuccess(topIssues,dataCards);
        else view.showLoadSuccess(dataCards);
    }
    @Override
    public void onStart() {
        view.showLoading();
    }

    @Override
    public void onFailed() {
        view.showLoadFail();
    }
}
