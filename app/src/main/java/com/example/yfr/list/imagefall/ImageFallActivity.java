package com.example.yfr.list.imagefall;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.ImageView;

import com.example.yfr.list.MainActivity;
import com.example.yfr.list.R;
import com.google.common.collect.Lists;

import java.util.LinkedList;
import java.util.List;

public class ImageFallActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FallAdapter fallAdapter;
    private LinkedList pics = Lists.newLinkedList();
    private SwipeRefreshLayout swipeRefreshLayout;
    private int mLastVisibleItemPosition;
    @Override
    protected void onCreate(Bundle startInstance) {
        super.onCreate(startInstance);
        setContentView(R.layout.recycleview);
        init();
        recyclerView = findViewById(R.id.fall_recycleview);

        StaggeredGridLayoutManager staggeredGridLayoutManager=new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        staggeredGridLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        recyclerView.addItemDecoration(new StaggeredDividerItemDecoration(ImageFallActivity.this, 3));
        //设置布局管理器
        recyclerView.setLayoutManager(staggeredGridLayoutManager);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                staggeredGridLayoutManager.invalidateSpanAssignments();


                    mLastVisibleItemPosition = pics.size();
                if (fallAdapter != null) {
                    if (newState == RecyclerView.SCROLL_STATE_IDLE
                            && mLastVisibleItemPosition + 1 == fallAdapter.getItemCount()) {
                        //发送网络请求获取更多数据
                        footAdd();
                    }
                }

            }

        });
        //设置为垂直布局，这也是默认的
        recyclerView.setHasFixedSize(true);
        //设置Adapter
        recyclerView.setAdapter(fallAdapter=new FallAdapter(this,pics));


        swipeRefreshLayout=findViewById(R.id.swiperefreshlayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //发送网络请求
                headAdd();
                //停止刷新
                swipeRefreshLayout.setRefreshing(false);
                //刷新RecycleView
                fallAdapter.notifyDataSetChanged();
            }
        });

    }

    public void init() {
        pics.add(new PicEntity(R.mipmap.firstfall));
        pics.add(new PicEntity(R.mipmap.secondfall));
        pics.add(new PicEntity(R.mipmap.firstfall));
        pics.add(new PicEntity(R.mipmap.secondfall));
        pics.add(new PicEntity(R.mipmap.firstfall));
        pics.add(new PicEntity(R.mipmap.secondfall));
        pics.add(new PicEntity(R.mipmap.firstfall));
        pics.add(new PicEntity(R.mipmap.secondfall));
        pics.add(new PicEntity(R.mipmap.firstfall));
        pics.add(new PicEntity(R.mipmap.secondfall));
        pics.add(new PicEntity(R.mipmap.firstfall));
        pics.add(new PicEntity(R.mipmap.secondfall));
        pics.add(new PicEntity(R.mipmap.firstfall));
        pics.add(new PicEntity(R.mipmap.secondfall));
        pics.add(new PicEntity(R.mipmap.firstfall));
        pics.add(new PicEntity(R.mipmap.secondfall));
        pics.add(new PicEntity(R.mipmap.firstfall));
        pics.add(new PicEntity(R.mipmap.secondfall));
        pics.add(new PicEntity(R.mipmap.firstfall));
        pics.add(new PicEntity(R.mipmap.secondfall));
        pics.add(new PicEntity(R.mipmap.firstfall));
        pics.add(new PicEntity(R.mipmap.secondfall));
    }
    public void headAdd(){
        pics.addFirst(new PicEntity(R.mipmap.common_icon_black_back));
        pics.addFirst(new PicEntity(R.mipmap.common_icon_right_arrow));
        pics.addFirst(new PicEntity(R.mipmap.next_icon));
    }

    private void footAdd() {
        pics.addLast(new PicEntity(R.mipmap.common_icon_black_back));
        pics.addLast(new PicEntity(R.mipmap.common_icon_right_arrow));
        pics.addLast(new PicEntity(R.mipmap.next_icon));
    }
}
