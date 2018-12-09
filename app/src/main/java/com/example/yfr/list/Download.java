package com.example.yfr.list;

import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.yfr.list.api.DownloadApi;
import com.example.yfr.list.util.DownloadListener;
import com.example.yfr.list.util.DownloadUtil;

import java.io.File;

public class Download extends AppCompatActivity implements View.OnClickListener {
    Button button, test;
    int mProgressStatus = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.download);
        final ProgressBar bar = findViewById(R.id.progress);
        final TextView textView = findViewById(R.id.tvProgress);
        button = findViewById(R.id.download1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url="http://pic41.nipic.com/";
//                String url="http://7xstkb.com1.z0.glb.clouddn.com/agen_apple.mp4/";
                String path = Environment.getExternalStorageDirectory() + "/DownloadFileTwo";
                DownloadUtil.download(url, path, new DownloadListener() {
                    @Override
                    public void onStart() {
                        Toast.makeText(Download.this, "文件开始下载", Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onProgress(int progress) {
                        final int text = progress;
                        bar.setProgress(progress);
                        textView.setText(text + "%");

                    }

                    @Override
                    public void onFinish(String path) {
                        Toast.makeText(Download.this, "下载成功，存放路经是：" + path, Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onFail(String errorInfo) {
                        Toast.makeText(Download.this, "errorInfo", Toast.LENGTH_LONG).show();

                    }
                });
            }
        });


//        new Thread(){
//            @Override
//            public void run() {
//                int i=0;
//                while(i<100){
//                    i++;
//                    try {
//                        Thread.sleep(80);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    final int j=i;
//                    bar.setProgress(i);
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            textView.setText(j+"%");
//                        }
//                    });
//                }
//            }
//        }.start();


    }

    @Override
    public void onClick(View v) {

    }
}
