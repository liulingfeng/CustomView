package com.llf.loding.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ViewFlipper;

/**
 * Created by Administrator on 2016/7/1.
 */
public class NotifiableViewFlipper extends ViewFlipper {

    private OnFlipListener onFlipListener;

    public static interface OnFlipListener {
        public void onShowPrevious(NotifiableViewFlipper flipper);

        public void onShowNext(NotifiableViewFlipper flipper);
    }

    public void setOnFlipListener(
            OnFlipListener onFlipListener) {
        this.onFlipListener = onFlipListener;
    }

    public NotifiableViewFlipper(Context context) {
        super(context);
    }

    public NotifiableViewFlipper(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void showPrevious() {
        super.showPrevious();
        if (hasFlipListener()) {
            onFlipListener.onShowPrevious(this);
        }
    }

    @Override
    public void showNext() {
        super.showNext();
        if (hasFlipListener()) {
            onFlipListener.onShowNext(this);
        }
    }

    private boolean hasFlipListener() {
        return onFlipListener != null;
    }
}
