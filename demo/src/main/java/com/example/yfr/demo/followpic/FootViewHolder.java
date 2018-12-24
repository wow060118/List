package com.example.yfr.demo.followpic;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.example.yfr.demo.R;


public class FootViewHolder extends RecyclerView.ViewHolder {


    ProgressBar footViewProgressbar;

    public FootViewHolder(@NonNull View itemView) {
        super(itemView);
        footViewProgressbar=itemView.findViewById(R.id.foot_fresh_item);
    }
}
