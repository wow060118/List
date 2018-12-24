package com.example.yfr.demo.followpic;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.yfr.demo.R;


public class FallHolder extends RecyclerView.ViewHolder {
    ImageView pic;
    Button remove;
    public FallHolder(@NonNull View itemView) {
        super(itemView);
        pic=itemView.findViewById(R.id.pic);
        remove = itemView.findViewById(R.id.remove_pic);
        int width = ((Activity) pic.getContext()).getWindowManager().getDefaultDisplay().getWidth();
        ViewGroup.LayoutParams params = pic.getLayoutParams();
        //设置图片的相对于屏幕的宽高比
        params.width = width/4;
//        params.height =  (int) (200 + Math.random() * 400) ;
        params.height=width/4;
        pic.setLayoutParams(params);

    }
//    public void setVisibility(boolean isVisible){
//        RecyclerView.LayoutParams param = (RecyclerView.LayoutParams)itemView.getLayoutParams();
//        if (isVisible){
//            remove.setVisibility(View.INVISIBLE);
//        } else
//            remove.setVisibility(View.VISIBLE);
//    }

}
