package com.example.yfr.list.setup;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yfr.list.R;
import com.example.yfr.list.entity.EventBusMsg;
import com.example.yfr.list.entity.SetUpEntity;
import com.example.yfr.list.util.CacheUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class SetUpAdapter extends RecyclerView.Adapter<SetUpAdapter.SetUpViewHolder> {
    private List<SetUpEntity> entityList;
    private Context context;

    public SetUpAdapter(){

    }

    public SetUpAdapter(List<SetUpEntity> entityList,Context context) {
        this.entityList = entityList;
        this.context= context;
    }

    public List<SetUpEntity> getEntityList() {
        return entityList;
    }

    public void setEntityList(List<SetUpEntity> entityList) {
        this.entityList = entityList;
    }

    @NonNull
    @Override
    public SetUpViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        SetUpViewHolder setUpViewHolder =new SetUpViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.setup_recyclerview, viewGroup, false));
        return setUpViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SetUpViewHolder setUpViewHolder, int i) {
        SetUpEntity s=entityList.get(i);
        setUpViewHolder.title.setText(s.getTitle());
        if(s.isCache()){
            System.out.println(1);
            setUpViewHolder.cache.setText(s.getCache());
            setUpViewHolder.cache.setVisibility(View.VISIBLE);
        }
        setUpViewHolder.title.setOnClickListener(v -> {
            EventBusMsg eventBusMsg=new EventBusMsg();
            eventBusMsg.setType(1);
            eventBusMsg.setMsg(s.getTitle());
            EventBus.getDefault().post(eventBusMsg);
            if(s.isCache()){
                CacheUtil.clearAllCache(context);
                try {
                    setUpViewHolder.cache.setText(CacheUtil.getTotalCacheSize(context));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return entityList.size();
    }



    class SetUpViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView cache;
        ImageView icon;

        public SetUpViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.setup_title);
            cache=itemView.findViewById(R.id.setup_cache);
            icon=itemView.findViewById(R.id.setup_icon);

        }

//        public SetUpViewHolder getSetUpHolder(Context context){
//
//
//        }
    }
}
