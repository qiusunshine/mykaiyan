package com.hdy.mykaiyan.Home;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;
import com.hdy.mykaiyan.Base.BaseActivity;
import com.hdy.mykaiyan.Feed.DataCard;
import com.hdy.mykaiyan.Feed.MyObservable;
import com.hdy.mykaiyan.R;
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

public class SplashActivity extends BaseActivity {
private List<DataCard> dataCardList;
    @Override
    protected void initLayout(Bundle savedInstanceState) {
        setTheme(R.style.AppThemeNoActionbar);
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void initView() {
        VideoView videoView=findView(R.id.splash_videoview);
        String uri = "android.resource://" + getPackageName() + "/" + R.raw.landing;
        videoView.setVideoURI(Uri.parse(uri));
        videoView.setMediaController(null);
        videoView.start();
        //监听视频播放完的代码
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mPlayer) {
                mPlayer.start();
                mPlayer.setLooping(true);
            }
        });
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        HttpRequest.get(Constants.Feed_Api_Url, new JsonHttpRequestCallback() {
            @Override
            protected void onSuccess(final com.alibaba.fastjson.JSONObject jsonObject) {
                super.onSuccess(jsonObject);
                Observable<List<DataCard>> observable = new MyObservable().getObservable(jsonObject);
                Observer<List<DataCard>> observer = new Observer<List<DataCard>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }

                    @Override
                    public void onNext(@NonNull List<DataCard> dataCards) {
                        dataCardList=dataCards;
                        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                };
                observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                        .subscribe(observer);
            }
        });
    }
    public List<DataCard> getList(){
        SplashActivity.this.finish();
        return dataCardList;
    }
}
