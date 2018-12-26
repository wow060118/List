package com.example.yfr.demo.channel;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yfr.demo.R;
import com.example.yfr.demo.follow.FallHolder;

import java.util.List;

public class ChipsAdapter extends  RecyclerView.Adapter<ChipsAdapter.ViewHolder> {

    private List<String> chipsEntities;
    //是否隐藏
    public boolean hide;

    public ChipsAdapter(List<String> chipsEntities) {
        this.chipsEntities = chipsEntities;
    }

    public ChipsAdapter(List<String> chipsEntities, boolean hide) {
        this.hide=hide;
        this.chipsEntities = chipsEntities;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.demo_item_chip, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindItem(chipsEntities.get(position));
        if(hide){
            ((ViewHolder) holder).imageButton.setVisibility(View.INVISIBLE);
        }else{
            ((ViewHolder) holder).imageButton.setVisibility(View.VISIBLE);
        }
        ((ViewHolder) holder).imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeItem(position);
            }
        });
    }

    public void removeItem(int i) {
        chipsEntities.remove(i);
        notifyDataSetChanged();
    }

    public boolean isHide() {
        return hide;
    }

    public void setHide(boolean hide) {
        this.hide = hide;
    }

    @Override
    public int getItemCount() {
        return chipsEntities.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvName;
        private ImageButton imageButton;
        ViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            imageButton = itemView.findViewById(R.id.ibClose);
        }

        void bindItem(String text) {

            tvName.setText(text);

        }
    }

}
