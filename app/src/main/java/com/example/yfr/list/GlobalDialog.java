package com.example.yfr.list;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.example.yfr.list.viewpage.BaseActivity;

/**
 * @Author: fengrui.yang
 * @Description:
 * @Date: created in 下午2:26 2019/2/15
 * @Modified_By:
 */
public class GlobalDialog extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
//        intent.setData(Uri.parse("package:" + getPackageName()));
//        startActivityForResult(intent,100);


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("开启推送？");
        builder.setPositiveButton("确定", (dialog, which) -> {

        }).setNegativeButton("取消", ((dialog, which) -> {

        }));
        AlertDialog dialog = builder.create();
//        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
//            dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY);
//        }else {
//            dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);//将弹出框设置为全局
//        }
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

}
