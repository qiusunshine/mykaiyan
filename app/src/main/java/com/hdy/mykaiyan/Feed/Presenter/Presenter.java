package com.hdy.mykaiyan.Feed.Presenter;

import com.hdy.mykaiyan.Feed.DataCard;
import com.hdy.mykaiyan.Feed.Moudle.LoadCallback;
import com.hdy.mykaiyan.Feed.Moudle.NetTask;
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
    public void onSuccess(List<DataCard> list) {
        view.showInitSuccess(list);
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
