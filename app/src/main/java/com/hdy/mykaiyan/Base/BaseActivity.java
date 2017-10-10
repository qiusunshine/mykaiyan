package com.hdy.mykaiyan.Base;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by hdy on 2017/8/12.
 */

    public abstract class BaseActivity extends AppCompatActivity {
        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            // 「必须在 Application 的 onCreate 方法中执行 BGASwipeBackManager.getInstance().init(this) 来初始化滑动返回」
            // 在 super.onCreate(savedInstanceState) 之前调用该方法
            super.onCreate(savedInstanceState);
            initLayout(savedInstanceState);
            ButterKnife.inject(this);
            initView();
            processLogic(savedInstanceState);
        }
    public void onResume() {
        super.onResume();
    }
    public void onPause() {
        super.onPause();
    }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            if (item.getItemId() == android.R.id.home) {
                return true;
            }
            return super.onOptionsItemSelected(item);
        }
    /**
     * 初始化布局
     */
    protected abstract void initLayout(Bundle savedInstanceState);
        /**
         * 初始化布局以及View控件
         */
        protected abstract void initView();

        /**
         * 处理业务逻辑，状态恢复等操作
         *
         * @param savedInstanceState
         */
        protected abstract void processLogic(Bundle savedInstanceState);
        /**
         * 查找View
         *
         * @param id   控件的id
         * @param <VT> View类型
         * @return
         */
        protected <VT extends View> VT findView(@IdRes int id) {
            return (VT) findViewById(id);
        }

        @Override
        protected void onStop() {
            super.onStop();
            Log.i(this.getClass().getSimpleName(), "onStop " + this.getClass().getSimpleName());
        }
}
