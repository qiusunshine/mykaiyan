package com.hdy.mykaiyan.Home;

import android.support.design.widget.Snackbar;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.hdy.mykaiyan.Base.BaseFragment;
import com.hdy.mykaiyan.Feed.Banner.Xcircleindicator;
import com.hdy.mykaiyan.Feed.DataCard;
import com.hdy.mykaiyan.Feed.Banner.PagerTransformer.ZoominPagerTransFormer;
import com.hdy.mykaiyan.Feed.Presenter.Presenter;
import com.hdy.mykaiyan.Feed.adapter_feed;
import com.hdy.mykaiyan.Feed.Presenter.PresenterContract;
import com.hdy.mykaiyan.Feed.onFeedItemClickListener;
import com.hdy.mykaiyan.R;
import com.jingewenku.abrahamcaijin.commonutil.AppToastMgr;
import com.lzy.widget.PullZoomView;
import com.lzy.widget.manager.ExpandLinearLayoutManager;
import java.util.List;

/**
 * 作者：By hdy
 * 日期：On 2017/10/10
 * 时间：At 18:42
 */

public class FeedFragment extends BaseFragment implements PresenterContract.View{
    private RecyclerView recyclerView;
    private Xcircleindicator xcircleindicator;
    private PresenterContract.Presenter presenter;
    private int[] imageIds = new int[]{R.drawable.author_account_bg,R.drawable.cover_default,R.drawable.author_account_bg};
    @Override
    protected int getLayoutId() {
        return R.layout.home_feed_fragment;
    }

    @Override
    protected void initView() {
        recyclerView=findView(R.id.home_feed_recyclerv);
        recyclerView.setLayoutManager(new ExpandLinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        PullZoomView pullZoomView = findView(R.id.home_feed_PullZoomView);
        pullZoomView.setIsParallax(false);
        pullZoomView.setOnPullZoomListener(new PullZoomView.OnPullZoomListener() {
            @Override
            public void onZoomFinish() {
                super.onZoomFinish();
            }
        });
        xcircleindicator=findView(R.id.Xcircleindicator);
        xcircleindicator.initData(imageIds.length,0);
        xcircleindicator.setCurrentPage(0);
        ViewPager viewPager = findView(R.id.home_feed_banner_vp);
        viewPager.setPageTransformer(true, new ZoominPagerTransFormer()); //设置页面切换过渡动画
        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }
            @Override
            public int getCount() {
                return imageIds.length;
            }
            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ImageView imageView = new ImageView(getContext());
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setImageResource(imageIds[position]);
                container.addView(imageView);
                return imageView;
            }
            @Override
            public void destroyItem(ViewGroup container, int position,
                                    Object object) {
                container.removeView((View)object);
            }
        });
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
               xcircleindicator.setCurrentPage(position);
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        // TODO: 2017/10/14 完善viewpager
        initData();
    }
    @Override
    protected void initData() {
        presenter=new Presenter(getContext(),this);
        presenter.initData();
    }
    @Override
    public void showInitSuccess(List<DataCard> dataCards) {
        adapter_feed adapter_feed=new adapter_feed(getContext(),dataCards);
        recyclerView.setAdapter(adapter_feed);
        initClick(adapter_feed);
    }
    @Override
    public void showLoading() {

    }
    @Override
    public void showLoadSuccess() {

    }

    @Override
    public void showLoadFail() {
        loadFail();
    }

    public void initClick(adapter_feed adapterFeed){
        adapterFeed.setOnItemClickListener(new onFeedItemClickListener() {
            @Override
            public void goToPlay(int position) {
                //position--;
                AppToastMgr.ToastShortCenter(getContext(),"点击了FollowCard的第"+position+"项");
                // TODO: 2017/10/13 完善点击事件
            }
        });
    }
    public void loadFail(){
        Snackbar.make(recyclerView,"加载失败，是否重新加载？",Snackbar.LENGTH_SHORT)
                .setAction("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        presenter.initData();
                    }
                }).show();
    }
}
