package com.llf.loding;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;

/**
 * Created by llf on 2016/6/28.
 */
public class RevealActivity extends Activity{
    private RevealView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reveal);

        init();
    }


    private void init(){
        view = (RevealView) findViewById(R.id.view);
        view.setCallback(new RevealView.Callback() {

            @Override
            public void onRevealEnd() {
				view.setVisibility(View.GONE);
            }
        });
        view.setRadius(10);
        view.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {

            @Override
            public boolean onPreDraw() {
                //首先移除OnPreDrawListener，否则在动画过程中会多次调用，导致死循环
                view.getViewTreeObserver().removeOnPreDrawListener(this);
                view.startReveal();
                return true;
            }
        });
    }
}
