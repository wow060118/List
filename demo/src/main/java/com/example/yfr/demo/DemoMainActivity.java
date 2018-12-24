package com.example.yfr.demo;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewStub;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @Author: fengrui.yang
 * @Description:
 * @Date: created in 下午3:06 2018/12/21
 * @Modified_By:
 */
public class DemoMainActivity extends AppCompatActivity {
    //提示文案 点击关闭
    private ViewStub guideViewStub;
    private TextView textView;
    private View view;
    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.demo_main_layout);


        initView();
        initEvent();

        SharedPreferences sharedPreferences = this.getSharedPreferences("share", MODE_PRIVATE);
        boolean isFirstRun = sharedPreferences.getBoolean("isFirstRun", true);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (isFirstRun) {
            Log.i("first", "第一次进入");
            // 、 进行第一次判断
            guideViewStub.inflate();
            Toast.makeText(DemoMainActivity.this, "", Toast.LENGTH_SHORT).show();
            editor.putBoolean("isFirstRun", false);
            editor.commit();
        }

    }

    //初始化事件
    private void initEvent() {
        //不知道为什么一直报空指异常。。。
//        textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                guideViewStub.setVisibility(View.INVISIBLE);
//            }
//        });
    }

    //初始化视图
    private void initView() {
        guideViewStub = findViewById(R.id.guide);
        textView=findViewById(R.id.guide_text);

    }

}
