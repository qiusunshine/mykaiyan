package com.hdy.mykaiyan.Feed.Banner;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

/**
 * 作者：By hdy
 * 日期：On 2017/10/14
 * 时间：At 19:16
 */

public class Indicator extends View {
    private Paint paint;
    public Indicator(Context context) {
        super(context);
        paint=new Paint();
        paint.setAntiAlias(true);
        setSelect(false);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(getMeasuredWidth()/2,getMeasuredHeight()/2);
        canvas.drawCircle(0f, 0f, getMeasuredWidth()/ 2, paint);
    }
    public void setSelect(Boolean select){
        if(select){
            paint.setColor(0xffffffff);
        }
        else {
            paint.setColor(0x88ffffff);
        }
        invalidate();
    }

}
