package com.example.yfr.list.viewpage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.yfr.list.AppManager;

/**
 * @Author: fengrui.yang
 * @Description:
 * @Date: created in 下午2:39 2019/2/15
 * @Modified_By:
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //将Activity实例添加到AppManager的堆栈
        AppManager.getAppManager().addActivity(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //将Activity实例从AppManager的堆栈中移除
        AppManager.getAppManager().finishActivity(this);
    }

}
