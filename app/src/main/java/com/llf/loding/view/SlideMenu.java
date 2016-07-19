package com.llf.loding.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import com.nineoldandroids.view.ViewHelper;

/**
 * Created by Administrator on 2016/7/4.
 */
public class SlideMenu extends HorizontalScrollView{
    private int screenWidth;
    private DisplayMetrics outMetrics;
    private int margenRight, menuWidth;

    private LinearLayout mWapper;
    private ViewGroup mMenu;
    private ViewGroup mContent;

    private boolean first = true;

    public SlideMenu(Context context, AttributeSet attrs) {
        super(context, attrs);

        WindowManager manager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        screenWidth = outMetrics.widthPixels;
        margenRight = 100;
    }

    /*
     * 设置子view的宽和高 设置viewGroup的宽和高。目的是设置左边布局的一个偏移量
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (first) {
            mWapper = (LinearLayout) getChildAt(0);
            mMenu = (ViewGroup) mWapper.getChildAt(0);
            mContent = (ViewGroup) mWapper.getChildAt(1);
            menuWidth = mMenu.getLayoutParams().width = screenWidth
                    - margenRight;
            mContent.getLayoutParams().width = screenWidth;
            first = false;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    //一开始向左滚动screenWidth - margenRight
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (changed) {
            this.scrollTo(screenWidth - margenRight, 0);
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_UP:
                int scrollX = getScrollX();
                //当滑动的距离大于menu宽度的一半自动滚动menuWidth，否则滚回起始点
                if (scrollX >= menuWidth / 2) {
                    //smoothScrollTo方法滚动平缓
                    this.smoothScrollTo(menuWidth, 0);
                } else {
                    this.smoothScrollTo(0, 0);
                }
                return true;
        }
        return super.onTouchEvent(ev);
    }

    /**
     * 滚动发生时
     */
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        //由于刚开始时向左边滚动了menuWidth，所以l的起始值为menuWidth
        float scale = l * 1.0f / menuWidth; // 1 ~ 0
        float rightScale = 0.7f + 0.3f * scale;
        float leftScale = 1.0f - scale * 0.3f;
        float leftAlpha = 0.6f + 0.4f * (1 - scale);

        // 调用属性动画，设置TranslationX
        ViewHelper.setTranslationX(mMenu, menuWidth * scale* 0.8f);
        ViewHelper.setPivotX(mContent, 0);
        ViewHelper.setPivotY(mContent, getHeight() / 2);
        ViewHelper.setScaleX(mContent, rightScale);
        ViewHelper.setScaleY(mContent, rightScale);

        ViewHelper.setScaleX(mMenu, leftScale);
        ViewHelper.setScaleY(mMenu, leftScale);
        ViewHelper.setAlpha(mMenu, leftAlpha);
    }
}
