package com.llf.loding.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.llf.loding.R;
import java.util.List;

/**
 * Created by llf on 2016/7/1.
 */
public class AdvertisementLayout extends LinearLayout implements NotifiableViewFlipper.OnFlipListener{
    private Context mContext;
    private NotifiableViewFlipper viewFlipper;
    private LinearLayout indicator;
    private List<Integer> datas;
    private ImageView[] images;
    private int currentItem;

    public AdvertisementLayout(Context context) {
        this(context,null);
    }

    public AdvertisementLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public AdvertisementLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mContext = context;
        init();
    }

    private void init(){
        View.inflate(mContext, R.layout.layout_advertisement,this);
        viewFlipper = (NotifiableViewFlipper)findViewById(R.id.viewflipper);
        indicator = (LinearLayout)findViewById(R.id.indicator);
        viewFlipper.setOnFlipListener(this);
    }

    public void setData(List<Integer> data){
        this.datas = data;

        instantiation();
    }

    private void instantiation(){
        images = new ImageView[datas.size()];
        for (int i=0;i<datas.size();i++){
            ImageView imageView = new ImageView(mContext);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,(int)mContext.getResources().getDimension(R.dimen.iv_height)));
            imageView.setImageResource(datas.get(i));
            imageView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(mContext,viewFlipper.getDisplayedChild()+"被点击了",Toast.LENGTH_SHORT).show();
                }
            });
            viewFlipper.addView(imageView);
        }

        for (int i=0;i<datas.size();i++){
            ImageView imageView = new ImageView(mContext);
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(30,30);
            layoutParams.setMargins(10,2,10,2);
            imageView.setLayoutParams(layoutParams);
            imageView.setBackgroundResource(R.drawable.indicator_iv);
            imageView.setEnabled(false);
            indicator.addView(imageView);
            images[i] = imageView;
        }
        images[0].setEnabled(true);
    }

    @Override
    public void onShowPrevious(NotifiableViewFlipper flipper) {
        images[currentItem].setEnabled(false);
        images[flipper.getDisplayedChild()].setEnabled(true);
        currentItem = flipper.getDisplayedChild();
    }

    @Override
    public void onShowNext(NotifiableViewFlipper flipper) {
        images[currentItem].setEnabled(false);
        images[flipper.getDisplayedChild()].setEnabled(true);
        currentItem = flipper.getDisplayedChild();
    }
}
