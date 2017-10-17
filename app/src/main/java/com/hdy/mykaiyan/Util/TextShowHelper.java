package com.hdy.mykaiyan.Util;

import android.widget.TextView;

/**
 * 作者：By hdy
 * 日期：On 2017/10/16
 * 时间：At 10:47
 */

public class TextShowHelper {
    private TextView titleV;
    private String title;
    private int nn=0;
    private long time=700;
    public TextShowHelper(){
    }
    public TextShowHelper setTextView(TextView titleV){
        this.titleV=titleV;
        return this;
    }
    public TextShowHelper setString(String title){
        this.title=title;
        return this;
    }
    public void show(final int n){
        final int length=title.length();
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            final String til = title.substring(0, n);//截取要填充的字符串
                            titleV.post(new Runnable() {
                                @Override
                                public void run() {
                                    titleV.setText(til);
                                }
                            });
                            Thread.sleep(time / length);//休息片刻
                            nn = n + 1;//n+1；多截取一个
                            if (nn <= length) {//如果还有汉字，那么继续开启线程，相当于递归的感觉
                                show(nn);
                            }

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

        ).start();
    }

}
