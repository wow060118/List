package com.example.yfr.list.imagefall;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.yfr.list.R;

public class FallHolder extends RecyclerView.ViewHolder {
    ImageView pic;
    public FallHolder(@NonNull View itemView) {
        super(itemView);
        pic=itemView.findViewById(R.id.pic);
        int width = ((Activity) pic.getContext()).getWindowManager().getDefaultDisplay().getWidth();
        ViewGroup.LayoutParams params = pic.getLayoutParams();
        //设置图片的相对于屏幕的宽高比
        params.width = width/3;
        params.height =  (int) (200 + Math.random() * 400) ;
        pic.setLayoutParams(params);

    }
}
