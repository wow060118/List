package com.example.yfr.list.advice;

import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Property;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yfr.list.R;

public class AdviceActivity extends AppCompatActivity {

    private TextView email;
    private RadioButton radioButtonBug,radioButtonAdvice,radioButtonOther,radioButtonUser;
    private EditText adviceText,contactText;
    private Button submit;
    private String advice,contact;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.advice);

        email=findViewById(R.id.email);
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData mClipData = ClipData.newPlainText("email", email.getText().toString());
                cm.setPrimaryClip(mClipData);
                Toast.makeText(AdviceActivity.this, "复制成功", Toast.LENGTH_LONG).show();
            }
        });

        adviceText=findViewById(R.id.advice_text);
        contactText=findViewById(R.id.contact);

        submit= findViewById(R.id.btn_submit);
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
                if(advice.length()>1000){
                    Toast.makeText(AdviceActivity.this, "超过1000字", Toast.LENGTH_LONG).show();
                }


            }
        });

        contactText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                judgeClick();
            }
        });


    }

    private void judgeClick(){
        advice = adviceText.getText().toString();
        contact = contactText.getText().toString();

        if (advice.length() > 0 && contact.length()>0) {
            submit.setClickable(true);
            submit.setBackground(getResources().getDrawable(R.drawable.me_bg_next_step));
        } else {
            submit.setClickable(false);
            submit.setBackground(getResources().getDrawable(R.drawable.me_bg_next_step_disable));
        }
    }


}
