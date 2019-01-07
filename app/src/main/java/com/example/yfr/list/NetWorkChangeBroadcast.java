package com.example.yfr.list;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * @Author: fengrui.yang
 * @Description:
 * @Date: created in 上午11:51 2019/1/7
 * @Modified_By:
 */

public class NetWorkChangeBroadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("change","接收消息");
        Toast.makeText(context,"接收消息",Toast.LENGTH_SHORT).show();
    }
}
