package com.hdy.mykaiyan.View;

/**
 * 作者：By hdy
 * 日期：On 2017/9/6
 * 时间：At 20:18
 */
import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.WindowManager;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by junweiliu on 16/12/29.
 */
public class PrinterTextView extends android.support.v7.widget.AppCompatTextView {
    /**
     * TAG
     */
    private static final String TAG = "PrinterTextView";
    /**
     * 默认打字字符
     */
    private final String DEFAULT_INTERVAL_CHAR = "";
    /**
     * 默认打字间隔时间
     */
    private final int DEFAULT_TIME_DELAY = 30;
    /**
     * 计时器
     */
    private Timer mTimer;
    /**
     * 需要打字的文字
     */
    private String mPrintStr;
    /**
     * 间隔时间
     */
    private int intervalTime = DEFAULT_TIME_DELAY;

    /**
     * 打字进度
     */
    private int printProgress = 0;


    public PrinterTextView(Context context) {
        super(context);
    }

    public PrinterTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PrinterTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    /**
     * 设置要打字的文字
     *
     * @param str
     */
    public PrinterTextView setPrintText(String str) {
        setPrintText(str, DEFAULT_TIME_DELAY);
        return this;
    }

    /**
     * 设置需要打字的文字及打字间隔
     *
     * @param str  打字文字
     * @param time 打字间隔(ms)
     */

    /**
     * 设置需要打字的文字,打字间隔,间隔符号
     *
     * @param str          打字文字
     * @param time         打字间隔(ms)
     */
    public PrinterTextView setPrintText(String str, int time) {
        if (strIsEmpty(str) || 0 == time) {
            return this;
        }
        WindowManager wm=(WindowManager)getContext().getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        ViewGroup.MarginLayoutParams layoutParams=(ViewGroup.MarginLayoutParams)getLayoutParams();
        int thestart=width-(int)(this.getPaint().measureText(str));
        if(thestart<0)thestart=0;
        layoutParams.setMarginStart(thestart/2);
        this.setLayoutParams(layoutParams);
        this.mPrintStr = str;
        this.intervalTime = time;
        return this;
    }

    /**
     * 开始打字
     */
    public void startPrint() {
        // 判空处理
        if (strIsEmpty(mPrintStr)) {
            if (!strIsEmpty(getText().toString())) {
                this.mPrintStr = getText().toString();
            } else {
                return;
            }
        }
        // 重置相关信息
        setText("");
        stopPrint();
        printProgress = 0;
        mTimer = new Timer();
        mTimer.schedule(new PrinterTimeTask(), intervalTime, intervalTime);
    }

    /**
     * 停止打字
     */
    public void stopPrint() {
        if (null != mTimer) {
            mTimer.cancel();
            mTimer = null;
        }
    }

    /**
     * 判断str是否为空
     *
     * @param str
     * @return
     */
    private boolean strIsEmpty(String str) {
        if (null != str && !"".equals(str)) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 打字计时器任务
     */
    private class PrinterTimeTask extends TimerTask {

        @Override
        public void run() {
            // 需要刷新页面,必须在UI线程,使用post方法
            post(new Runnable() {
                @Override
                public void run() {
                    // 如果未显示完,继续显示
                    if (printProgress < mPrintStr.length()) {
                        printProgress++;
                        // (printProgress & 1) == 1 等价于printProgress%2!=0
                        setText(mPrintStr.substring(0, printProgress));
                    } else {
                        // 如果完成打字,显示完整文字
                        setText(mPrintStr);
                        stopPrint();
                    }
                }
            });
        }
    }
}
