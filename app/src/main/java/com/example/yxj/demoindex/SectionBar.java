package com.example.yxj.demoindex;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * Created by yxj on 16/4/5.
 */
public class SectionBar extends View {

    private WindowManager windowManager;
    private View mFloatView;
    private TextView tv_letter;

    public SectionBar(Context context) {
        this(context, null);
    }

    public SectionBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SectionBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        init();
    }

    public static final char[] WORDS = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z','#'};

//    private int wordSize = 10;
//    private int preHeight = wordSize + 2;

    private int heightCenter;

    private int itemHeight;

    Paint mPaint = null;

    private boolean mTouched;

    private int selectIndex = -1;

    private void init() {
        int height = getMeasuredHeight();
        itemHeight = height / WORDS.length;

        windowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        mFloatView = LayoutInflater.from(getContext()).inflate(R.layout.float_view, null);
        tv_letter = (TextView) mFloatView.findViewById(R.id.tv_letter);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mTouched) {
            canvas.drawColor(0x30000000);
        }

        Paint mPaintSelectBg = new Paint();
        mPaintSelectBg.setColor(0x88333113);
        Rect rect = new Rect(0, (selectIndex) * itemHeight, getMeasuredWidth(), (selectIndex + 1) * itemHeight);
        canvas.drawRect(rect, mPaintSelectBg);
        mPaint = new Paint();
        mPaint.setTextSize(itemHeight);
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.setAntiAlias(true);

        for (int i = 0; i < WORDS.length; i++) {
            if (i == selectIndex) {
                mPaint.setColor(Color.WHITE);
            } else {
                mPaint.setColor(Color.GREEN);
            }
            canvas.drawText(String.valueOf(WORDS[i]), getMeasuredWidth() / 2, i * itemHeight + 1 * itemHeight, mPaint);
        }

        super.onDraw(canvas);
    }


    int lastIndex = 0;

    private OnIndexSelected mOnIndexSelected;

    public void setOnIndexSelected(OnIndexSelected onIndexSelected) {
        mOnIndexSelected = onIndexSelected;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                mTouched = true;
                int i = (int) (y / (getMeasuredHeight() * 1.0f / WORDS.length) + 0.5);
                if (i != lastIndex) {
                    Log.e("index", "当前是第" + WORDS[i]);
                    mOnIndexSelected.select(WORDS[i]);
                    selectIndex = i;
                    lastIndex = i;
                }
                break;
            case MotionEvent.ACTION_UP:
                mTouched = false;
                selectIndex = -1;
                mOnIndexSelected.up();
                break;
        }
        invalidate();
        return true;
    }

    public interface OnIndexSelected {
        public void select(char index);
        public void up();
    }

}
