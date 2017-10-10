package com.hdy.mykaiyan.Home;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.hdy.mykaiyan.Base.BaseActivity;
import com.hdy.mykaiyan.R;
import com.hdy.mykaiyan.Util.FragmentController;

import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    private int TextViewSelect=1;
    private FragmentController controller;
    @InjectView(R.id.ac_main_container)
    FrameLayout acMainContainer;
    @InjectView(R.id.ac_main_bot_feed)
    TextView acMainBotFeed;
    @InjectView(R.id.ac_main_bot_follow)
    TextView acMainBotFollow;
    @InjectView(R.id.ac_main_bot_category)
    TextView acMainBotCategory;
    @InjectView(R.id.ac_main_bot_profile)
    TextView acMainBotProfile;
    @InjectView(R.id.ac_main_lay)
    LinearLayout acMainLay;

    @Override
    protected void initLayout(Bundle savedInstanceState) {
        setTheme(R.style.AppThemeNoActionbar);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initView() {
        controller= FragmentController.getInstance(this,R.id.ac_main_container);
        //添加fragment
        controller.addFragment(new FeedFragment())
                .addFragment(new FollowFragment())
                .addFragment(new CategoryFragment())
                .addFragment(new ProfileFragment());
        //初始化fragment
        controller.showFragment(0);
    }
    @Override
    protected void processLogic(Bundle savedInstanceState) {

    }

    @OnClick({R.id.ac_main_bot_feed, R.id.ac_main_bot_follow, R.id.ac_main_bot_category, R.id.ac_main_bot_profile})
    public void onViewClicked(View view) {
        TextView SelectTextView;
        Drawable resid;
        //判断之前在哪个版块
        switch (TextViewSelect){
            case 1:
                SelectTextView=acMainBotFeed;
                resid=getResources().getDrawable(R.drawable.ic_tab_strip_icon_feed);
                break;
            case 2:
                SelectTextView=acMainBotFollow;
                resid=getResources().getDrawable(R.drawable.ic_tab_strip_icon_follow);
                break;
            case 3:
                SelectTextView=acMainBotCategory;
                resid=getResources().getDrawable(R.drawable.ic_tab_strip_icon_category);
                break;
            case 4:
                SelectTextView=acMainBotProfile;
                resid=getResources().getDrawable(R.drawable.ic_tab_strip_icon_profile);
                break;
            default:
                SelectTextView=acMainBotFeed;
                resid=getResources().getDrawable(R.drawable.ic_tab_strip_icon_feed);
                break;
        }
        resid.setBounds( 0, 0,resid.getMinimumWidth(),resid.getMinimumHeight());//不设置会出问题，透明不可见
        //对之前所在版块底部置相应的drawable
        SelectTextView.setCompoundDrawables(null,resid,null,null);
        SelectTextView.setTextColor(getResources().getColor(R.color.tertiary_text_light));
        //重置选择的底部TextView的号数
        Drawable dra;
        switch (view.getId()) {
            case R.id.ac_main_bot_feed:
                TextViewSelect=1;
                dra=getResources().getDrawable(R.drawable.ic_tab_strip_icon_feed_selected);
                dra.setBounds( 0, 0, dra.getMinimumWidth(),dra.getMinimumHeight());
                acMainBotFeed.setCompoundDrawables(null,dra,null,null);
                controller.showFragment(0);
                break;
            case R.id.ac_main_bot_follow:
                TextViewSelect=2;
                dra=getResources().getDrawable(R.drawable.ic_tab_strip_icon_follow_selected);
                dra.setBounds( 0, 0, dra.getMinimumWidth(),dra.getMinimumHeight());
                acMainBotFollow.setCompoundDrawables(null,dra,null,null);
                controller.showFragment(1);
                break;
            case R.id.ac_main_bot_category:
                TextViewSelect=3;
                dra=getResources().getDrawable(R.drawable.ic_tab_strip_icon_category_selected);
                dra.setBounds( 0, 0, dra.getMinimumWidth(),dra.getMinimumHeight());
                acMainBotCategory.setCompoundDrawables(null,dra,null,null);
                controller.showFragment(2);
                break;
            case R.id.ac_main_bot_profile:
                TextViewSelect=4;
                dra=getResources().getDrawable(R.drawable.ic_tab_strip_icon_profile_selected);
                dra.setBounds( 0, 0, dra.getMinimumWidth(),dra.getMinimumHeight());
                acMainBotProfile.setCompoundDrawables(null,dra,null,null);
                controller.showFragment(3);
                break;
        }
        //
        TextView t=(TextView)view;
        t.setTextColor(getResources().getColor(R.color.text_select_dark));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        FragmentController.destoryController();
    }
}
