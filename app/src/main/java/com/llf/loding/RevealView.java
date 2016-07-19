package com.llf.loding;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.animation.AccelerateInterpolator;
import android.widget.RelativeLayout;

public class RevealView extends RelativeLayout {
	private Paint paint;
	private int radius;
	private Callback callback;

	public RevealView(Context context) {
		super(context);
		init();
	}

	public RevealView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	public RevealView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	private void init() {
		paint = new Paint();
		paint.setColor(Color.RED);
		// 使用抗锯齿功能
		paint.setAntiAlias(true);
		// 使用抗锯齿功能
		paint.setDither(true);
		// 设置画笔的样式
		paint.setStyle(Paint.Style.FILL);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		canvas.drawCircle(getWidth() / 2, getHeight() / 2, radius, paint);
	}

	public void startReveal() {
		// 这里我计算了斜对角线的长度,这样可以保证画的圆能够保证覆盖整个view
		int maxRadius = (int) (Math.sqrt(Math.pow(getHeight(), 2)
				+ Math.pow(getWidth(), 2)));
		ObjectAnimator revealAnimator = ObjectAnimator.ofInt(this, "radius", 0,
				maxRadius).setDuration(600);
		revealAnimator.setInterpolator(new AccelerateInterpolator());
		revealAnimator.addListener(new AnimatorListener() {

			@Override
			public void onAnimationStart(Animator animation) {

			}

			@Override
			public void onAnimationRepeat(Animator animation) {

			}

			@Override
			public void onAnimationEnd(Animator animation) {
				if (callback != null){
					callback.onRevealEnd();
				}
			}

			@Override
			public void onAnimationCancel(Animator animation) {

			}
		});
		revealAnimator.start();
	}

	public void setRadius(int radius) {
		this.radius = radius;
		invalidate();
	}

	public void setCallback(Callback callback) {
		this.callback = callback;
	}

	public interface Callback {
		void onRevealEnd();
	}
}
