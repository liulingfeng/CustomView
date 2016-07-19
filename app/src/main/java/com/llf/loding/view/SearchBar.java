package com.llf.loding.view;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.llf.loding.R;

/**
 * Created by llf on 2016/7/1.
 */
public class SearchBar extends LinearLayout implements View.OnClickListener,TextWatcher{
    private ImageView delete;
    private EditText input;

    public SearchBar(Context context) {
        this(context,null);
    }

    public SearchBar(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SearchBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        View.inflate(context, R.layout.layout_searchbar,this);
        delete = (ImageView)findViewById(R.id.delete);
        input = (EditText)findViewById(R.id.input);

        delete.setOnClickListener(this);
        input.addTextChangedListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.delete:
                input.setText("");
                delete.setVisibility(View.INVISIBLE);
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        if(editable.length()!=0){
            delete.setVisibility(View.VISIBLE);
        }else{
            delete.setVisibility(View.INVISIBLE);
        }
    }
}
