package com.example.yfr.demo.followpic;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Button;


import com.example.yfr.demo.R;
import com.google.common.collect.Lists;

import java.util.Collections;
import java.util.LinkedList;

public class FollowPicActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FallAdapter fallAdapter;
    private LinkedList pics = Lists.newLinkedList();
    private SwipeRefreshLayout swipeRefreshLayout;
    private int mLastVisibleItemPosition;
    private ItemTouchHelper mItemTouchHelper;
    private Button button;

    @Override
    protected void onCreate(Bundle startInstance) {
        super.onCreate(startInstance);
        setContentView(R.layout.demo_follow_pic);
        init();
        recyclerView = findViewById(R.id.fall_recycleview);

        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        staggeredGridLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);

//        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4, OrientationHelper.VERTICAL, false);
//        recyclerView.addItemDecoration(new StaggeredDividerItemDecoration(FollowPicActivity.this, 3));
        //设置布局管理器
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        recyclerView.addItemDecoration(new MDGridRvDividerDecoration(this));

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
//                staggeredGridLayoutManager.invalidateSpanAssignments();


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
        recyclerView.setAdapter(fallAdapter = new FallAdapter(this, pics,true));



        swipeRefreshLayout = findViewById(R.id.swiperefreshlayout);
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

        button=findViewById(R.id.recycle_edit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(button.getText().equals("编辑")) {
                    button.setText("完成");
                    fallAdapter.setHide(false);
                    fallAdapter.notifyDataSetChanged();



                    mItemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {


                        @Override
                        public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
                            final int dragFlags;
                            final int swipeFlags;
                            if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
                                dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
                                swipeFlags = 0;
                            } else {
                                dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                                swipeFlags = 0;
                            }
                            return makeMovementFlags(dragFlags, swipeFlags);
                        }

                        @Override
                        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                            //得到当拖拽的viewHolder的Position
                            int fromPosition = viewHolder.getAdapterPosition();
                            //拿到当前拖拽到的item的viewHolder
                            int toPosition = target.getAdapterPosition();
                            if (fromPosition < toPosition) {
                                for (int i = fromPosition; i < toPosition; i++) {
                                    Collections.swap(pics, i, i + 1);
                                }
                            } else {
                                for (int i = fromPosition; i > toPosition; i--) {
                                    Collections.swap(pics, i, i - 1);
                                }
                            }
                            fallAdapter.notifyItemMoved(fromPosition, toPosition);
                            return true;
                        }

                        @Override
                        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

                        }

                        @Override
                        public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
                            if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
                                viewHolder.itemView.setBackgroundColor(Color.LTGRAY);
                            }
                            super.onSelectedChanged(viewHolder, actionState);
                        }

                        @Override
                        public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                            super.clearView(recyclerView, viewHolder);
                            viewHolder.itemView.setBackgroundColor(0);
                        }

                    });
                    mItemTouchHelper.attachToRecyclerView(recyclerView);
                } else {
                    button.setText("编辑");
                    fallAdapter.setHide(true);
                    fallAdapter.notifyDataSetChanged();
                    mItemTouchHelper.attachToRecyclerView(null);
                }

            }
        });

    }

    public void init() {

    }

    public void headAdd() {

    }

    private void footAdd() {

    }

    private void removeItem(int position){
        pics.remove(position);
    }
}
