package com.example.yfr.list.advice;

import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Property;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.yfr.list.Enum.AdviceEnum;
import com.example.yfr.list.MainActivity;
import com.example.yfr.list.R;
import com.example.yfr.list.entity.AdviceEntity;
import com.example.yfr.list.entity.RadioEntity;
import com.example.yfr.list.setup.SetUpActivity;
import com.example.yfr.list.util.SystemUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class AdviceActivity extends AppCompatActivity {

    private TextView email,title;
    private RadioButton radioButtonBug, radioButtonAdvice, radioButtonOther, radioButtonUser;
    private EditText adviceText, contactText;
    private Button submit;
    private String advice;
    private AdviceEnum adviceEnum;
    private RadioEntity radioEntity;
    private ImageView backIcon;

    //    private Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.advice);

        init();
        email = findViewById(R.id.email);
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData mClipData = ClipData.newPlainText("email", email.getText().toString());
                cm.setPrimaryClip(mClipData);
                Toast.makeText(AdviceActivity.this, "复制成功", Toast.LENGTH_LONG).show();
            }
        });

        adviceText = findViewById(R.id.advice_text);
        contactText = findViewById(R.id.contact);



        adviceText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                judgeSubmitClick();
                advice = adviceText.getText().toString();
                if (advice.length() > 1000) {
                    Toast.makeText(AdviceActivity.this, "超过1000字", Toast.LENGTH_LONG).show();
                }


            }
        });


        radioButtonAdvice = findViewById(R.id.button_advice);
        radioButtonAdvice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                judgeAdviceTypeClick(radioButtonAdvice,AdviceEnum.ADVICE);
            }
        });

        radioButtonBug = findViewById(R.id.button_bug);
        radioButtonBug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                judgeAdviceTypeClick(radioButtonBug,AdviceEnum.BUG);
            }
        });
        radioButtonOther = findViewById(R.id.button_other);
        radioButtonOther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                judgeAdviceTypeClick(radioButtonOther,AdviceEnum.OTHER);
            }
        });

        radioButtonUser = findViewById(R.id.button_user);
        radioButtonUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                judgeAdviceTypeClick(radioButtonUser,AdviceEnum.USER);

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdviceEntity adviceEntity = new AdviceEntity();
                adviceEntity.setAdvice(advice);
                adviceEntity.setContact(contactText.getText().toString());
                adviceEntity.setAdviceEnum(radioEntity.getAdviceEnum());
                adviceEntity.setVersion(SystemUtil.getSystemVersion());
                adviceEntity.setPhoneModle(SystemUtil.getSystemModel());
                Log.i("advice", JSON.toJSONString(adviceEntity));
                EventBus.getDefault().post("反馈已提交");
//                Intent i = new Intent(AdviceActivity.this,SetUpActivity.class);
//                startActivity(i);
                finish();
            }
        });
        submit.setClickable(false);
        backIcon = findViewById(R.id.backIcon);
        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        title=findViewById(R.id.titleView);
        title.setText("意见反馈");
    }

    private void init() {
//        EventBus.getDefault().register(this);
        radioEntity = new RadioEntity();
        radioEntity.setChecked(false);
        radioEntity.setAdviceEnum(AdviceEnum.NONE);
        submit = findViewById(R.id.btn_submit);

        Log.i("click: ",submit.isClickable()+"");
    }


    private void judgeSubmitClick() {
        advice = adviceText.getText().toString();

        if (advice.length() > 0 && radioEntity.getAdviceEnum().name() != AdviceEnum.NONE.name()) {
            submit.setClickable(true);
            submit.setBackground(getResources().getDrawable(R.drawable.me_bg_next_step));
        } else {
            submit.setClickable(false);
            submit.setBackground(getResources().getDrawable(R.drawable.me_bg_next_step_disable));
        }
    }

    private void judgeAdviceTypeClick(RadioButton r,AdviceEnum a){
        if (radioEntity.isChecked() && radioEntity.getAdviceEnum().name() == a.name()) {
            radioEntity.getRadioButton().setChecked(false);
            radioEntity.setAdviceEnum(AdviceEnum.NONE);
            radioEntity.setRadioButton(null);
            radioEntity.setChecked(false);
        } else if (radioEntity.isChecked() && radioEntity.getAdviceEnum().name() != a.name()) {
            radioEntity.getRadioButton().setChecked(false);
            radioEntity.setRadioButton(r);
            radioEntity.setAdviceEnum(a);
        } else {
            radioEntity.setChecked(true);
            radioEntity.setAdviceEnum(a);
            radioEntity.setRadioButton(r);
        }
        judgeSubmitClick();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventBus(String msg){
        Log.i("this is advice","msg");
    }
}
