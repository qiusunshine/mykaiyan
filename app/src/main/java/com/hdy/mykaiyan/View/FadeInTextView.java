package com.hdy.mykaiyan.View;

/**
 * 作者：By hdy
 * 日期：On 2017/10/16
 * 时间：At 10:21
 */
import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.text.Layout;
import android.util.AttributeSet;
import android.widget.TextView;

public class FadeInTextView extends android.support.v7.widget.AppCompatTextView {
    private static final int DEFAULT_UNIT_LENGTH = 2;
    private static final int DEFAULT_DURATION = 3820;
    private List<Rect> lineRects = new ArrayList<Rect>();
    private int duration = DEFAULT_DURATION;
    private int moveUnitLength;
    private int fromLine = 0;
    private int moveCount;
    private int fromStart;
    private int maskColor;
    private Paint paint;
    private FadeInListener fadeInListener;

    private volatile boolean fadeInFinished = false;

    private boolean hasCallListener = false;

    public FadeInTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public FadeInTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FadeInTextView(Context context) {
        super(context);
    }

    public boolean isFadeInFinished() {
        return fadeInFinished;
    }

    public void setFadeInDuration(int duration) {
        this.duration = duration;
    }

    public int getFadeInDuration() {
        return duration;
    }

    public void setMoveUnitLength(int moveUnitLength) {
        this.moveUnitLength = moveUnitLength;
    }

    public void setFadeInMaskColor(int maskColor) {
        this.maskColor = maskColor;
    }

    public void setFadeInListener(FadeInListener fadeInListener) {
        this.fadeInListener = fadeInListener;
    }
    public void activateFadeInListener() {
        this.hasCallListener = false;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (lineRects.isEmpty()) {
            getLineRects();
            if (fadeInListener != null && !hasCallListener) {
                fadeInListener.onFadeInStart(this);
            }
        }
        if (paint == null) {
            paint = new Paint();
            paint.setColor(maskColor);
            paint.setAntiAlias(true);
            paint.setStyle(Style.FILL);
        }
        for (int i = fromLine; i < lineRects.size(); i++) {
            Rect rect = new Rect(lineRects.get(i));
            if (i == fromLine) {
                rect.left = fromStart;
            }
            canvas.drawRect(rect, paint);
        }

        if (!computeNextFrame()) {
            fadeInFinished = true;
            if (fadeInListener != null && !hasCallListener) {
                fadeInListener.onFadeInEnd(this);
                hasCallListener = true;
            }
            return;
        }
        postDelayed(new Runnable() {
            @Override
            public void run() {
                invalidate();
            }
        }, duration / moveCount);
    }

    private boolean computeNextFrame() {
        if (lineRects.size() <= fromLine) {
            return false;
        }
        Rect currentLineRect = lineRects.get(fromLine);
        if (fromStart >= currentLineRect.right) {
            return false;
        }
        fromStart = fromStart + moveUnitLength;
        if (fromStart >= currentLineRect.right) {
            fromLine++;
            if (lineRects.size() > fromLine) {
                fromStart = lineRects.get(fromLine).left;
            } else {
                fromStart = 0;
            }
        }
        return true;

    }

    private void getLineRects() {
        Layout layout = getLayout();
        if (layout == null) {
            return;
        }
        int lineCount = layout.getLineCount();
        int totalLength = 0;
        for (int i = 0; i < lineCount; i++) {
            Rect rect = new Rect();
            rect.left = (int) layout.getLineLeft(i);
            rect.right = (int) layout.getLineRight(i);
            rect.top = layout.getLineTop(i);
            rect.bottom = layout.getLineBottom(i);
            if (i == 0) {
                fromStart = rect.left;
            }
            lineRects.add(rect);
            totalLength = totalLength + rect.width();
        }
        if (moveUnitLength <= 0) {
            moveUnitLength = DEFAULT_UNIT_LENGTH;
        }
        moveCount = totalLength / moveUnitLength;
        if (moveCount <= 0) {
            moveCount = 1;
        }
    }

    public static interface FadeInListener {
        public void onFadeInStart(TextView tv);

        public void onFadeInEnd(TextView tv);
    }
}