package com.hdy.mykaiyan.Feed.Presenter;

import com.hdy.mykaiyan.Feed.DataCard;
import java.util.List;

/**
 * 作者：By hdy
 * 日期：On 2017/10/14
 * 时间：At 20:32
 */

public interface PresenterContract {
    interface Presenter{
        void getData(String data);
    }
    interface View{
        void showInitSuccess(List<DataCard> dataCards);
        void showLoading();
        void showLoadSuccess();
        void showLoadFail();
    }
}
