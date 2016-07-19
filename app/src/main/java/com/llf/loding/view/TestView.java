package com.llf.loding.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by llf on 2016/7/5.
 */
public class TestView extends View{
    public TestView(Context context) {
        this(context,null);
    }

    public TestView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TestView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);

        int resultWidth = 100;//设置一个默认宽度
        if(modeWidth == MeasureSpec.EXACTLY){
            resultWidth = sizeWidth;
        }else{
            resultWidth = Math.min(resultWidth,sizeWidth);
        }

        int resultHeight = 100;//设置一个默认高度
        if(modeHeight == MeasureSpec.EXACTLY){
            resultHeight = sizeHeight;
        }else{
            resultHeight = Math.min(resultHeight,sizeHeight);
        }

        //此句必须要写
        setMeasuredDimension(resultWidth,resultHeight);
    }
}
