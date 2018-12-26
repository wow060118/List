package com.example.yfr.demo.follow;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.yfr.demo.R;
import com.example.yfr.demo.view.EditTextWithDel;

/**
 * @Author: fengrui.yang
 * @Description:
 * @Date: created in 下午3:34 2018/12/26
 * @Modified_By:
 */
public class LikedFallActivity extends AppCompatActivity {
    private EditText textViewdel;

    @Override
    public void onCreate(Bundle startInstanceState) {

        super.onCreate(startInstanceState);
        setContentView(R.layout.demo_follow_fall);

        textViewdel=findViewById(R.id.ceshi);
//        textViewnull=findViewById(R.id.ceshi);
        textViewdel.setFocusable(true);
        textViewdel.setFocusableInTouchMode(true);
        textViewdel.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(textViewdel,InputMethodManager.SHOW_FORCED);
        imm.hideSoftInputFromWindow(textViewdel.getWindowToken(), 0);
    }
}
