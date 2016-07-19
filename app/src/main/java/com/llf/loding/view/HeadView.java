package com.llf.loding.view;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.llf.loding.R;

/**
 * Created by llf on 2016/6/29.
 */
public class HeadView extends RelativeLayout implements View.OnClickListener{
    private TextView tv_head;
    private ImageView iv_back;
    private Context mContext;

    public HeadView(Context context) {
        this(context,null);
    }

    public HeadView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public HeadView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mContext = context;
        //第一个参数:布局的资源id 第二个参数:填充的根视图 第三个参数是否将载入的视图绑定到根视图中
        LayoutInflater.from(context).inflate(R.layout.layout_head,this,true);
        tv_head = (TextView)findViewById(R.id.tv_head);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(this);
    }

    public void setTitle(String headTv){
        tv_head.setText(headTv);
    }

    @Override
    public void onClick(View view) {
        if(view == iv_back){
            ((Activity)mContext).finish();
        }
    }
}
