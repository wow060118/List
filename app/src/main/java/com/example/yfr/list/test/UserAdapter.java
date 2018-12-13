package com.example.yfr.list.test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yfr.list.R;

import java.util.LinkedList;
import java.util.List;

// 自定义一个adapter 这样可以点击自己的事件。
public class UserAdapter extends BaseAdapter {
    private List<User> mData;
    private Context mContext;

    public UserAdapter(List<User> mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            convertView =LayoutInflater.from(mContext).inflate(R.layout.simple_adapter_layout,parent,false);

            viewHolder=new ViewHolder();
            viewHolder.icon=convertView.findViewById(R.id.imgtou);
            viewHolder.username = convertView.findViewById(R.id.name);
            viewHolder.say = convertView.findViewById(R.id.says);
            convertView.setTag(viewHolder);
        }else{
            viewHolder=(ViewHolder) convertView.getTag();
        }

        viewHolder.icon.setBackgroundResource(mData.get(position).getImg());
        viewHolder.username.setText(mData.get(position).getName());
        viewHolder.say.setText(mData.get(position).getSay());
        return convertView;
    }

    public ViewHolder getHolder(View convertView,ViewGroup parent){

        return null;
    }
    static class ViewHolder{
        ImageView icon;
        TextView username;
        TextView say;
    }
}
