package com.llf.loding;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.BounceInterpolator;

/**
 * Created by llf on 2016/6/27.
 */
public class LoadingView extends View{
    private int circleColor = Color.parseColor("#3F51B5");
    private int artColor = Color.parseColor("#FF4081");
    private boolean firstAnim = true;
    private boolean firstTime = true;
    private boolean secondAnim = false;
    private int radius;
    private Paint mPaint;

    public LoadingView(Context context) {
        this(context,null);
    }

    public LoadingView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init(){
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(circleColor);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(firstAnim){
            canvas.drawCircle(getWidth()/2,getHeight()/2,radius,mPaint);
            if(firstTime){
                doFirstAnimator();
                firstTime = false;
            }
        }else{
            Paint transparentPaint = new Paint();
            transparentPaint.setAntiAlias(true);
            transparentPaint.setColor(getResources().getColor(android.R.color.transparent));
            transparentPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
            canvas.drawCircle(getWidth() / 2, getHeight() / 2, radius, transparentPaint);
        }

        if(secondAnim){
            mPaint.setColor(artColor);
            mPaint.setStrokeWidth(8);
            mPaint.setStyle(Paint.Style.STROKE);
            canvas.drawArc(new RectF(0,0,getWidth()-8,getHeight()-8),0,90,false,mPaint);
        }
    }

    private void doFirstAnimator(){
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0,getWidth()/2);
        valueAnimator.setDuration(2000);
        valueAnimator.setInterpolator(new BounceInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                radius = (int)valueAnimator.getAnimatedValue();
                invalidate();
            }
        });
        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                firstAnim = false;
                secondAnim = true;
                invalidate();
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        valueAnimator.start();
    }
}
