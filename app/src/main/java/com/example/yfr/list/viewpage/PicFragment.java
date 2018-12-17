package com.example.yfr.list.viewpage;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yfr.list.R;

public class PicFragment extends Fragment {
    private ViewPageEntity viewPageEntity;
    private View mView;
    private TextView tag;
    private ImageView pic;

    @Override
    public void onActivityCreated(Bundle saveInstaceState) {
        super.onActivityCreated(saveInstaceState);
        init();
        initData();
    }

    private void initData() {
        tag.setText(viewPageEntity.getTag());
        pic.setImageResource(viewPageEntity.getPicImg());
    }

    private void init() {
        tag=mView.findViewById(R.id.fragment_text);
        pic=mView.findViewById(R.id.fragment_pic);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.view_pager_fragment, container, false);
        Bundle bundle=getArguments();
        if(bundle!=null){
            viewPageEntity=bundle.getParcelable("picEntity");
        }
        return mView;
    }
}
