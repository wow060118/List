package com.example.yfr.list;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;


import com.alibaba.fastjson.JSON;
import com.example.yfr.list.advice.AdviceActivity;
import com.example.yfr.list.db.DaoMaster;
import com.example.yfr.list.db.DaoSession;
import com.example.yfr.list.db.Entity;
import com.example.yfr.list.db.EntityDao;
import com.example.yfr.list.util.LongClickUtils;
import com.example.yfr.list.util.SystemUtil;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import org.greenrobot.greendao.database.Database;

import java.io.InputStream;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView recyclerView;
    private List<String> list;
    MyAdapter myAdapter;
    Button add,remove,databtn,readBtn,download,advice;
    private ImageView imageView;
    private ImageView bImageView;

    private Dialog dialog;

    private DaoSession daoSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDataBase();
        initData();

//        String TAG = "系统参数：";
//        Log.e(TAG, "手机厂商：" + SystemUtil.getDeviceBrand());
//        Log.e(TAG, "手机型号：" + SystemUtil.getSystemModel());
//        Log.e(TAG, "手机当前系统语言：" + SystemUtil.getSystemLanguage());
//        Log.e(TAG, "Android系统版本号：" + SystemUtil.getSystemVersion());



        recyclerView = findViewById(R.id.recyclerView);

//        System.out.println(JSON.toJSONString(list));
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(MainActivity.this,DividerItemDecoration.VERTICAL));
        //设置布局管理器
        recyclerView.setLayoutManager(layoutManager);
        //设置为垂直布局，这也是默认的
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        //设置Adapter
        recyclerView.setAdapter(myAdapter = new MyAdapter(list));

//        //设置分隔线
//        recyclerView.addItemDecoration( new DividerGridItemDecoration(this ));
//        //设置增加或删除条目的动画
//        recyclerView.setItemAnimator( new DefaultItemAnimator());
        add = findViewById(R.id.btn);
        remove = findViewById(R.id.remove);
        databtn = findViewById(R.id.dataBaseButton);
        readBtn = findViewById(R.id.dataBaseButtonRead);
        download =findViewById(R.id.download);
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,Download.class);
                startActivity(i);

            }
        });

        advice =findViewById(R.id.advice);
        advice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(MainActivity.this,AdviceActivity.class);
                startActivity(i);
            }
        });

        imageView = findViewById(R.id.img);

        bImageView = getBIimageView();

        dialog = new Dialog(MainActivity.this);

        dialog.setContentView(bImageView);
        bImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        bImageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //弹出的“保存图片”的Dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setItems(new String[]{"长事件"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "这是一个长点击事件", Toast.LENGTH_LONG).show();
                    }
                });
                builder.show();
                return true;
            }
        });


        LongClickUtils.setLongClick(new Handler(), imageView, 2000, new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //todo:补充长按事件的处理逻辑
                Toast.makeText(MainActivity.this, "这是一个长点击事件", Toast.LENGTH_LONG).show();
                return true;
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
                Toast.makeText(MainActivity.this, "这是一个短点击事件", Toast.LENGTH_LONG).show();
            }
        });

    }

    private void initDataBase() {
        createDataBase(MainActivity.this);
    }

    public void initData() {
        list = Lists.newArrayList();
        for (int i = 0; i < 10; i++) {
            list.add(i + "");
        }


        Intent intent = getIntent();
            //获取传递的值
            String str = intent.getStringExtra("data");
            if(!Strings.isNullOrEmpty(str)) {
                //设置值
                Toast.makeText(MainActivity.this, str, Toast.LENGTH_LONG).show();
            }

    }

    public ImageView getBIimageView(){
        ImageView iv = new ImageView(this);
        //宽高
        iv.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        //设置Padding
        iv.setPadding(20,20,20,20);
        //imageView设置图片
        InputStream is = getResources().openRawResource(R.mipmap.img);
        Drawable drawable = BitmapDrawable.createFromStream(is, null);
        iv.setImageDrawable(drawable);
        return iv;
    }

    @Override
    public void onClick(View v) {
        if(v==add) {
            int i = list.size();
            list.add(i + "");
            Log.i("add size",list.size()+"");
            myAdapter.setList(list);
            recyclerView.setAdapter(myAdapter);
        }else if(v==remove){

            int i = list.size();
            if(i==0){
                Log.i("size none",list.size()+"");
            } else {
                list.remove(i - 1);
                Log.i("del size",list.size()+"");
                myAdapter.setList(list);
                recyclerView.setAdapter(myAdapter);
            }
        } else if(v==databtn){

            Entity entity = new Entity();
            entity.setAge("10");
            entity.setName("demo");
            try {
                daoSession.getEntityDao().insert(entity);
            }catch (Exception e){
                Log.i("data error",e.getMessage());
            }
            Toast.makeText(MainActivity.this, "增加一条数据", Toast.LENGTH_LONG).show();
        } else if(v==readBtn){

            try {
                List<Entity> list = daoSession.getEntityDao().queryBuilder()
                        .where(EntityDao.Properties.Age.le(10))
                        .build().list();
                Log.i("queryList is", JSON.toJSONString(list));
            }catch (Exception e){
                Log.i("data error",e.getMessage());
            }
            Toast.makeText(MainActivity.this, "查询数据", Toast.LENGTH_LONG).show();
        }

    }
    class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
        private List<String> list;

        public MyAdapter(List<String> list) {
            this.list = list;

        }

        public void setList(List<String> list){
            this.list = list;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false));

            return holder;
        }

        @Override
        public void onBindViewHolder(final MyViewHolder myViewHolder, int i) {
//        System.out.println("iiii"+list.get(i));
            myViewHolder.textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, myViewHolder.textView.getText(), Toast.LENGTH_LONG).show();
                }
            });
            myViewHolder.textView.setText(list.get(i));
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }


    private void createDataBase(Context context){
        DaoMaster.DevOpenHelper devOpenHelper=new DaoMaster.DevOpenHelper(context ,"demo.db");
        Database database=devOpenHelper.getWritableDb();
        DaoMaster daoMaster =new DaoMaster(database);
        daoSession = daoMaster.newSession();
        EntityDao entityDao = daoSession.getEntityDao();
        entityDao.createTable(database,true);
    }

    public DaoSession getDaoSession(){
        return daoSession;
    }

}
