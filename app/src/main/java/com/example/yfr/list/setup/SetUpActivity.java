package com.example.yfr.list.setup;

import android.app.AppOpsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.Settings;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yfr.list.MainActivity;
import com.example.yfr.list.R;
import com.example.yfr.list.entity.EventBusMsg;
import com.example.yfr.list.entity.SetUpEntity;
import com.example.yfr.list.util.CacheUtil;
import com.google.common.collect.Lists;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

public class SetUpActivity extends AppCompatActivity {

    private List<SetUpEntity> setUpEntities;
    private RecyclerView recyclerView;
    private SetUpAdapter setUpAdapter;
    private Switch notifiSwitch;
    private boolean notifiIsEnabled;
    public static final String SETTINGS_ACTION = "android.settings.APPLICATION_DETAILS_SETTINGS";
    private NotificationManagerCompat notification;
    private TextView logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_setup);
        initData();

        NotificationManagerCompat notification = NotificationManagerCompat.from(this);
        boolean isEnabled = notification.areNotificationsEnabled();

        recyclerView = findViewById(R.id.setup_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        //设置分割线
//        recyclerView.addItemDecoration(new DividerItemDecoration(SetUpActivity.this,DividerItemDecoration.VERTICAL));
        //设置布局管理器
        recyclerView.setLayoutManager(layoutManager);
        //设置为垂直布局，这也是默认的
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        //设置Adapter
        recyclerView.setAdapter(setUpAdapter = new SetUpAdapter(setUpEntities,getApplicationContext()));

        notifiSwitch = findViewById(R.id.notifi_switch);
        notifiSwitch.setChecked(notifiIsEnabled);
        notifiSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                //系统设置
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.BASE) {
//                    Intent intent = new Intent(Settings.ACTION_SETTINGS);
//                    startActivity(intent);
//                    return;
//                } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {// 运行系统在5.x环境使用
//                    Intent intent = new Intent(Settings.ACTION_SETTINGS);
//                    startActivity(intent);
//                    return;
//                }


                //应用设置
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.BASE) {
                    Intent intent = new Intent()
                            .setAction(SETTINGS_ACTION)
                            .setData(Uri.fromParts("package",
                                    getApplicationContext().getPackageName(), null));
                    startActivity(intent);
                } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Intent intent = new Intent()
                            .setAction(SETTINGS_ACTION)
                            .setData(Uri.fromParts("package",
                                    getApplicationContext().getPackageName(), null));
                    startActivity(intent);
                }

//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        boolean notifiNow = notification.areNotificationsEnabled();
//                        Log.i("notifi", notifiIsEnabled + "");
//                        while (true) {
//                            if (notifiNow != notifiIsEnabled) {
//                                notifiIsEnabled = notifiNow;
//                                runOnUiThread(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        notifiSwitch.setChecked(notifiNow);
//                                    }
//                                });
//                                break;
//                            } else {
//                                runOnUiThread(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        notifiSwitch.setChecked(notifiIsEnabled);
//                                    }
//                                });
//                            }
//                        }
//                    }
//                }).start();
            }
        });
        logout= findViewById(R.id.logout);
        logout.setOnClickListener(v ->{
           Intent i =new Intent(SetUpActivity.this,MainActivity.class);
           i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
           startActivity(i);

        });
    }


    private void initData() {
        setUpEntities = Lists.newArrayList();
        SetUpEntity setUpEntity1 = new SetUpEntity();
        setUpEntity1.setTitle("隐私协议");
        setUpEntity1.setCache(false);
        setUpEntities.add(setUpEntity1);

        SetUpEntity setUpEntity2 = new SetUpEntity();
        setUpEntity2.setTitle("用户协议");
        setUpEntity2.setCache(false);
        setUpEntities.add(setUpEntity2);

        SetUpEntity setUpEntity3 = new SetUpEntity();
        setUpEntity3.setTitle("清理缓存");
        setUpEntity3.setCache(true);
        try {
            setUpEntity3.setCache(CacheUtil.getTotalCacheSize(SetUpActivity.this));
        } catch (Exception e) {
            e.printStackTrace();
        }
        setUpEntities.add(setUpEntity3);

        notification = NotificationManagerCompat.from(this);
        notifiIsEnabled = notification.areNotificationsEnabled();

        EventBus.getDefault().register(this);
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        notifiIsEnabled = notification.areNotificationsEnabled();
        notifiSwitch.setChecked(notifiIsEnabled);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveMsg(EventBusMsg eventBusMsg){
        System.out.println("receive");
        Toast.makeText(SetUpActivity.this, eventBusMsg.getMsg(), Toast.LENGTH_LONG).show();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解除注册
        EventBus.getDefault().unregister(this);
    }

}