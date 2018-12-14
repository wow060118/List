package com.example.yfr.list.imagefall;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.ViewGroup;

import com.example.yfr.list.R;

import java.util.LinkedList;

public class FallAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context fContext;
    private LinkedList<PicEntity> pics;

    public static final int view_Foot = 2;
    //主要布局
    public static final int view_Normal = 1;
    //是否隐藏
    public boolean isLoadMore = false;


    public FallAdapter(Context fContext, LinkedList pics) {
        this.fContext = fContext;
        this.pics = pics;
    }

    public FallAdapter() {
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        RecyclerView.ViewHolder viewHolder = null;
        if (i == view_Normal) {
            viewHolder = new FallHolder(viewGroup.inflate(fContext, R.layout.waterfall, null));
        } else if(i==view_Foot){
            viewHolder = new FootViewHolder(viewGroup.inflate(fContext, R.layout.foot_fresh, null));
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof FallHolder) {
            bindFallView((FallHolder) viewHolder,i);
        }
//        else if(viewHolder instanceof FootViewHolder){
//            bindFootView((FootViewHolder) viewHolder,i);
//        }

    }

//    private void bindFootView(FootViewHolder holder,int i) {
//        FootViewHolder footViewHolder=holder;
//        footViewHolder.textView.setText("上拉刷新");
//    }


    private void bindFallView(FallHolder holder, int position) {
        FallHolder fallHolder = holder;
        fallHolder.pic.setImageResource(pics.get(position).getPic());
    }



    @Override
    public int getItemCount() {
        return pics.size()+1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount()) {//这是为了添加一个footerview
            return view_Foot;
        } else {
            return view_Normal;
        }
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        if (isStaggeredGridLayout(holder)) {
            handleLayoutIfStaggeredGridLayout(holder, holder.getLayoutPosition());
        }
    }

    private boolean isStaggeredGridLayout(RecyclerView.ViewHolder holder) {
        ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
        if (layoutParams != null && layoutParams instanceof StaggeredGridLayoutManager.LayoutParams) {
            return true;
        }
        return false;
    }

    protected void handleLayoutIfStaggeredGridLayout(RecyclerView.ViewHolder holder, int position) {
        if (position+1 ==getItemCount()){

            StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) holder.itemView.getLayoutParams();
            p.setFullSpan(true);
        }
    }
}
