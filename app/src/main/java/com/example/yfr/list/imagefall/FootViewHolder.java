package com.example.yfr.list.imagefall;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.example.yfr.list.R;

public class FootViewHolder extends RecyclerView.ViewHolder {


    ProgressBar footViewProgressbar;

    public FootViewHolder(@NonNull View itemView) {
        super(itemView);
        footViewProgressbar=itemView.findViewById(R.id.foot_fresh_item);
    }
}
