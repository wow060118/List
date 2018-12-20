package com.example.yfr.list;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.view.ViewStub;
import android.widget.TextView;

/**
 * @Author: fengrui.yang
 * @Description:
 * @Date: created in 下午8:07 2018/12/19
 * @Modified_By:
 */
public class ScrollerActivity extends AppCompatActivity {
    ViewStub mViewStub;
    TextView textView;
    View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.merge_main);
//        mViewStub=findViewById(R.id.view_stub);
//        mViewStub.inflate();
//        textView=findViewById(R.id.view_stub_text);
//        findViewById(R.id.view_stub_text).setOnClickListener(v->{
//            mViewStub.setVisibility(View.INVISIBLE);
//            Log.i("mview","    "+mViewStub);
//            Log.i("layout","    "+view);
//        });

    }
}
