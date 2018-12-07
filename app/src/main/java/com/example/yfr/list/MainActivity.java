package com.example.yfr.list;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;


import com.google.common.collect.Lists;
import com.example.yfr.mylibrary.Say;

import java.io.InputStream;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView recyclerView;
    private List<String> list;
    MyAdapter myAdapter;
    Button add,remove;
    private ImageView imageView;
    private ImageView bImageView;

    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();

        recyclerView = findViewById(R.id.recyclerView);

//        System.out.println(JSON.toJSONString(list));
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
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

    public void initData() {
        list = Lists.newArrayList();
        for (int i = 0; i < 10; i++) {
            list.add(i + "");
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
            System.out.println("list size" + list.size());
            myAdapter.setList(list);
            recyclerView.setAdapter(myAdapter);
        }else if(v==remove){

            int i = list.size();
            if(i==0){
                System.out.println("none");
            } else {
                list.remove(i - 1);
                System.out.println("list size" + list.size());
                myAdapter.setList(list);
                recyclerView.setAdapter(myAdapter);
            }
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





}
