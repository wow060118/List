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
import com.example.yfr.list.util.SystemUtil;

public class AdviceActivity extends AppCompatActivity {

    private TextView email;
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

        submit = findViewById(R.id.btn_submit);
        adviceText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                judgeClick();
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

                if (radioEntity.isChecked() && radioEntity.getAdviceEnum().name() == AdviceEnum.ADVICE.name()) {
                    radioEntity.getRadioButton().setChecked(false);
                    radioEntity.setAdviceEnum(AdviceEnum.NONE);
                    radioEntity.setRadioButton(null);
                    radioEntity.setChecked(false);
                } else if (radioEntity.isChecked() && radioEntity.getAdviceEnum().name() != AdviceEnum.ADVICE.name()) {
                    radioEntity.getRadioButton().setChecked(false);
                    radioEntity.setRadioButton(radioButtonAdvice);
                    radioEntity.setAdviceEnum(AdviceEnum.ADVICE);
                } else {
                    radioEntity.setChecked(true);
                    radioEntity.setAdviceEnum(AdviceEnum.ADVICE);
                    radioEntity.setRadioButton(radioButtonAdvice);
                }
                judgeClick();
            }
        });

        radioButtonBug = findViewById(R.id.button_bug);
        radioButtonBug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (radioEntity.isChecked() && radioEntity.getAdviceEnum().name() == AdviceEnum.BUG.name()) {
                    radioEntity.getRadioButton().setChecked(false);
                    radioEntity.setAdviceEnum(AdviceEnum.NONE);
                    radioEntity.setRadioButton(null);
                    radioEntity.setChecked(false);
                } else if (radioEntity.isChecked() && radioEntity.getAdviceEnum().name() != AdviceEnum.BUG.name()) {
                    radioEntity.getRadioButton().setChecked(false);
                    radioEntity.setRadioButton(radioButtonBug);
                    radioEntity.setAdviceEnum(AdviceEnum.BUG);
                } else {
                    radioEntity.setChecked(true);
                    radioEntity.setAdviceEnum(AdviceEnum.BUG);
                    radioEntity.setRadioButton(radioButtonBug);
                }
                judgeClick();
            }
        });
        radioButtonOther = findViewById(R.id.button_other);
        radioButtonOther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (radioEntity.isChecked() && radioEntity.getAdviceEnum().name() == AdviceEnum.OTHER.name()) {
                    radioEntity.getRadioButton().setChecked(false);
                    radioEntity.setAdviceEnum(AdviceEnum.NONE);
                    radioEntity.setRadioButton(null);
                    radioEntity.setChecked(false);
                } else if (radioEntity.isChecked() && radioEntity.getAdviceEnum().name() != AdviceEnum.OTHER.name()) {
                    radioEntity.getRadioButton().setChecked(false);
                    radioEntity.setRadioButton(radioButtonOther);
                    radioEntity.setAdviceEnum(AdviceEnum.OTHER);
                } else {
                    radioEntity.setChecked(true);
                    radioEntity.setAdviceEnum(AdviceEnum.OTHER);
                    radioEntity.setRadioButton(radioButtonOther);
                }
                judgeClick();
            }
        });

        radioButtonUser = findViewById(R.id.button_user);
        radioButtonUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radioEntity.isChecked() && radioEntity.getAdviceEnum().name() == AdviceEnum.USER.name()) {
                    radioEntity.getRadioButton().setChecked(false);
                    radioEntity.setAdviceEnum(AdviceEnum.NONE);
                    radioEntity.setRadioButton(null);
                    radioEntity.setChecked(false);
                } else if (radioEntity.isChecked() && radioEntity.getAdviceEnum().name() != AdviceEnum.USER.name()) {
                    radioEntity.getRadioButton().setChecked(false);
                    radioEntity.setRadioButton(radioButtonUser);
                    radioEntity.setAdviceEnum(AdviceEnum.BUG);
                } else {
                    radioEntity.setChecked(true);
                    radioEntity.setAdviceEnum(AdviceEnum.BUG);
                    radioEntity.setRadioButton(radioButtonUser);
                }
                judgeClick();

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

                //传递信息 用intent的extra
                Intent i = new Intent(AdviceActivity.this, MainActivity.class);
                i.putExtra("data", "反馈已提交");
                startActivity(i);
            }
        });

        backIcon = findViewById(R.id.backIcon);
        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void init() {
        radioEntity = new RadioEntity();
        radioEntity.setChecked(false);
        radioEntity.setAdviceEnum(AdviceEnum.NONE);

    }


    private void judgeClick() {
        advice = adviceText.getText().toString();

        if (advice.length() > 0 && radioEntity.getAdviceEnum().name() != AdviceEnum.NONE.name()) {
            submit.setClickable(true);
            submit.setBackground(getResources().getDrawable(R.drawable.me_bg_next_step));
        } else {
            submit.setClickable(false);
            submit.setBackground(getResources().getDrawable(R.drawable.me_bg_next_step_disable));
        }
    }


}
