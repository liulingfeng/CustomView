package com.llf.anjuke;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.LinearInterpolator;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Activity implements MyScrollView.OnScrollListener {
    private RelativeLayout parent;
    private MyScrollView mScrollView;
    private RelativeLayout head;
    private LinearLayout scroll_head;
    private TextView title;
    private EditText edt_real, edt_pseudo;
    private float orgin = 1;
    private ViewGroup.MarginLayoutParams lp;

    int height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        parent = (RelativeLayout) findViewById(R.id.parent);
        mScrollView = (MyScrollView) findViewById(R.id.scrollView);
        head = (RelativeLayout) findViewById(R.id.head);
        scroll_head = (LinearLayout) findViewById(R.id.scroll_head);
        title = (TextView) findViewById(R.id.title);
        edt_real = (EditText) findViewById(R.id.edt_real);
        edt_pseudo = (EditText) findViewById(R.id.edt_pseudo);
        lp = (ViewGroup.MarginLayoutParams) edt_pseudo.getLayoutParams();
        head.setBackgroundColor(Color.argb(0, 0xff, 0x40, 0x81));
        mScrollView.setOnScrollListener(this);
        ViewTreeObserver vto = parent.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                height = scroll_head.getHeight() / 2;
                onScroll(mScrollView.getScrollY());
            }
        });
    }

    @Override
    public void onScroll(int scrollY) {
        float scale = (float) scrollY / height;
        float alpha = 255 * scale;
        if (scale > 1) {
            alpha = 255;
        }

        head.setBackgroundColor(Color.argb((int) alpha, 0xff, 0x40, 0x81));
        title.setAlpha(255 - alpha);

        int position = Math.max(scrollY, edt_real.getTop());
        edt_pseudo.layout(edt_real.getLeft(), edt_real.getTop(), edt_real.getRight(), edt_real.getBottom());

        if (scale < 0.6 && scrollY != 0) {
            ObjectAnimator animator = ObjectAnimator.ofFloat(edt_real, "scaleX", orgin, 1 - scale/2);
            animator.setDuration(250);
            animator.setInterpolator(new LinearInterpolator());
            animator.start();
            orgin = 1 - scale/2;
        }
    }
}
