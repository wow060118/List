package com.example.yfr.list.setup;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;

import com.example.yfr.list.MainActivity;
import com.example.yfr.list.R;
import com.example.yfr.list.entity.SetUpEntity;
import com.example.yfr.list.util.CacheUtil;
import com.google.common.collect.Lists;

import java.util.List;

public class SetUpActivity extends AppCompatActivity {

    private List<SetUpEntity> setUpEntities;
    private RecyclerView recyclerView;
    private SetUpAdapter setUpAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_setup);
        initData();


        recyclerView = findViewById(R.id.setup_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        //设置分割线
//        recyclerView.addItemDecoration(new DividerItemDecoration(SetUpActivity.this,DividerItemDecoration.VERTICAL));
        //设置布局管理器
        recyclerView.setLayoutManager(layoutManager);
        //设置为垂直布局，这也是默认的
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        //设置Adapter
        recyclerView.setAdapter(setUpAdapter = new SetUpAdapter(setUpEntities));
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



    }


}
