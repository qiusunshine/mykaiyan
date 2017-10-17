package com.hdy.mykaiyan.Home;

import android.support.design.widget.Snackbar;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.hdy.mykaiyan.Base.BaseFragment;
import com.hdy.mykaiyan.Feed.JavaBean.DataCard;
import com.hdy.mykaiyan.Feed.Banner.PagerTransformer.ZoominPagerTransFormer;
import com.hdy.mykaiyan.Feed.Moudle.getFollowCards;
import com.hdy.mykaiyan.Feed.Presenter.Presenter;
import com.hdy.mykaiyan.Feed.adapter_feed;
import com.hdy.mykaiyan.Feed.Presenter.PresenterContract;
import com.hdy.mykaiyan.Feed.onFeedItemClickListener;
import com.hdy.mykaiyan.R;
import com.hdy.mykaiyan.Util.Constants;
import com.hdy.mykaiyan.View.IndicatorView;
import com.hdy.mykaiyan.View.PrinterTextView;
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
    private PresenterContract.Presenter presenter;
    private PrinterTextView bannerTitle,bannerDesc;
    private ViewPager viewPager;
    private adapter_feed adapter_feed;
    private boolean loading=true;
    private int currentPage=0;
    private IndicatorView indicatorView;
    @Override
    protected int getLayoutId() {
        return R.layout.home_feed_fragment;
    }

    @Override
    protected void initView() {
        recyclerView=findView(R.id.home_feed_recyclerv);
        bannerTitle=findView(R.id.home_feed_banner_title);
        bannerDesc=findView(R.id.home_feed_banner_desc);
        ExpandLinearLayoutManager linearLayoutManager = new ExpandLinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        PullZoomView pullZoomView = findView(R.id.home_feed_PullZoomView);
        pullZoomView.setOnPullZoomListener(new PullZoomView.OnPullZoomListener() {
            @Override
            public void onZoomFinish() {
                super.onZoomFinish();
                //presenter.getData(Constants.Feed_Api_Url);
            }
        });
        pullZoomView.setOnScrollListener(new PullZoomView.OnScrollListener() {
            @Override
            public void scrollToBottom(int l, int t, int oldl, int oldt) {
                super.scrollToBottom(l, t, oldl, oldt);
                if (!loading) {
                    loading = true;
                    currentPage=currentPage+2;
                    presenter.getData(Constants.Feed_Api_Url_More+currentPage);
                    AppToastMgr.ToastShortCenter(getContext(),"正在加载中");
                }
            }

        });
        viewPager= findView(R.id.home_feed_banner_vp);
        viewPager.setPageTransformer(true, new ZoominPagerTransFormer()); //设置页面切换过渡动画
        indicatorView=findView(R.id.home_feed_banner_btm_indicator);
        // TODO: 2017/10/14 完善viewpager
        initData();
    }
    @Override
    protected void initData() {
        presenter=new Presenter(this,new getFollowCards());
        presenter.getData(Constants.Feed_Api_Url);
    }

    @Override
    public void showInitSuccess(List<DataCard> topIssues, List<DataCard> dataCards) {
        loading=false;
        adapter_feed=new adapter_feed(getContext(),dataCards);
        recyclerView.setAdapter(adapter_feed);
        initRecyclerView(adapter_feed);
        bannerTitle.setPrintText(topIssues.get(0).getTitle()).startPrint();
        bannerDesc.setPrintText(topIssues.get(0).getDesc()).startPrint();
        initViewPager(topIssues);
    }
    @Override
    public void showLoading() {

    }
    @Override
    public void showLoadSuccess(List<DataCard> dataCards) {
        loading=false;
        adapter_feed.addItem(dataCards);
    }

    @Override
    public void showLoadFail() {
        loadFail();
    }

    public void initRecyclerView(adapter_feed adapterFeed){
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
                        presenter.getData(Constants.Feed_Api_Url);
                    }
                }).show();
    }
    public void initViewPager(final List<DataCard> topIssues){
        indicatorView.setCount(topIssues.size());
        indicatorView.setmCurSelectedPosition(0);
        indicatorView.setVisibility(View.VISIBLE);//解决不显示的问题
        final RequestOptions options= new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.loading)
                .error(R.drawable.loading);
        viewPager.setAdapter(new PagerAdapter() {

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }
            @Override
            public int getCount() {
                return topIssues.size();
            }
            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ImageView imageView = new ImageView(getContext());
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                Glide.with(getContext()).load(topIssues.get(position).getCover()).apply(options).into(imageView);
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
                bannerTitle.setPrintText(topIssues.get(position).getTitle()).startPrint();
                bannerDesc.setPrintText(topIssues.get(position).getDesc()).startPrint();
                indicatorView.setmCurSelectedPosition(position);
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }
}
