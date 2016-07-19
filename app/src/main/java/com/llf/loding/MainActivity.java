package com.llf.loding;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
/**
 * 探索5.0的一些炫酷的效果 1.Activity的切换效果
 *
 * @author llf
 *
 */
public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void start(View v) {
        Intent intent = new Intent(MainActivity.this, RevealActivity.class);
        startActivity(intent);
        overridePendingTransition(0, 0);
    }


}

