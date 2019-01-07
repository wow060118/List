package com.example.yfr.demo.channel;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.beloo.widget.chipslayoutmanager.ChipsLayoutManager;
import com.beloo.widget.chipslayoutmanager.gravity.IChildGravityResolver;
import com.example.yfr.demo.R;
import com.example.yfr.demo.follow.FallAdapter;
import com.google.common.collect.Lists;

import java.util.Collections;
import java.util.LinkedList;

public class ChannelManagementActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FallAdapter fallAdapter;
    private ChipsAdapter chipsAdapter;
    private LinkedList pics = Lists.newLinkedList();
    private SwipeRefreshLayout swipeRefreshLayout;
    private int mLastVisibleItemPosition;
    private ItemTouchHelper mItemTouchHelper;
    private TextView button;

    @Override
    public void onCreate(Bundle startInstanceState) {

        super.onCreate(startInstanceState);
        setContentView(R.layout.demo_channel_mannager);
        init();
        recyclerView = findViewById(R.id.channel_recycleview);
        ChipsLayoutManager spanLayoutManager = ChipsLayoutManager.newBuilder(getApplicationContext())
                .setOrientation(ChipsLayoutManager.HORIZONTAL)
                .setMaxViewsInRow(4)
                .build();
        recyclerView.setLayoutManager(spanLayoutManager);


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                mLastVisibleItemPosition = pics.size();
                if (chipsAdapter != null) {
                    if (newState == RecyclerView.SCROLL_STATE_IDLE
                            && mLastVisibleItemPosition + 1 == chipsAdapter.getItemCount()) {
                        //发送网络请求获取更多数据
//                        footAdd();
                    }
                }

            }

        });
        recyclerView.addItemDecoration(new SpacesItemDecoration(20));

        //设置Adapter
        recyclerView.setAdapter(chipsAdapter = new ChipsAdapter(pics, true));


//        swipeRefreshLayout = findViewById(R.id.swiperefreshlayout);
//        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                //发送网络请求
//                headAdd();
//                //停止刷新
//                swipeRefreshLayout.setRefreshing(false);
//                //刷新RecycleView
//                chipsAdapter.notifyDataSetChanged();
//            }
//        });

        button = findViewById(R.id.follow_channel_edit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (button.getText().equals("编辑")) {
                    button.setText("完成");
                    chipsAdapter.setHide(false);
                    chipsAdapter.notifyDataSetChanged();


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
                            chipsAdapter.notifyItemMoved(fromPosition, toPosition);
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
                    chipsAdapter.setHide(true);
                    chipsAdapter.notifyDataSetChanged();
                    mItemTouchHelper.attachToRecyclerView(null);
                }

            }
        });
    }

    public void init() {
        pics.add("动物世界");
        pics.add("人与自然");
        pics.add("测试炒鸡肠炒鸡肠炒鸡肠测试炒鸡肠炒鸡肠炒鸡肠");
        pics.add("长度要长一点再试试");
        pics.add("1111");
        pics.add("11111111");
        pics.add("11111111");
        pics.add("11111111");
        pics.add("11111111");
        pics.add("11111111");
        pics.add("11111111");
        pics.add("11111111");
        pics.add("11111111");


    }


}
