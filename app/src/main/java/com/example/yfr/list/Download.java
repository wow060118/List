package com.example.yfr.list;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
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

    ProgressBar bar;
    TextView textView ;

    private static final int UPDATE=1;
    private Handler handler=new Handler(){
        public void handleMessage(Message message){
            switch (message.what){
                case UPDATE:
                    Bundle data = message.getData();
                    final int text = data.getInt("progress");
                    System.out.println("update"+ text);
                    bar.setProgress(text);
                    textView.setText(text + "%");
            }

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.download);

        bar = findViewById(R.id.progress);
        textView = findViewById(R.id.tvProgress);

        button = findViewById(R.id.download1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String url="http://pic41.nipic.com/";
                String url="https://github.com/wow060118/petreact/archive/";
//                String url="https://dl.google.com/dl/android/studio/install/3.2.0.26/";
                String path = Environment.getExternalStorageDirectory() + "/DownloadFileTwo";

                final int REQUEST_EXTERNAL_STORAGE = 1;
                String[] PERMISSIONS_STORAGE = {
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE};
                int permission = ActivityCompat.checkSelfPermission(Download.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE);

                if (permission != PackageManager.PERMISSION_GRANTED) {
                    // We don't have permission so prompt the user
                    ActivityCompat.requestPermissions(Download.this, PERMISSIONS_STORAGE,
                            REQUEST_EXTERNAL_STORAGE);
                }



                DownloadUtil.download(url, path, new DownloadListener() {
                    @Override
                    public void onStart() {
                        Toast.makeText(Download.this, "文件开始下载", Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onProgress(int progress) {


                    }

                    @Override
                    public void onFinish(String path) {
                        Toast.makeText(Download.this, "下载成功，存放路经是：" + path, Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onFail(String errorInfo) {
                        Toast.makeText(Download.this, errorInfo, Toast.LENGTH_LONG).show();

                    }
                },handler);
            }
        });

        test =findViewById(R.id.test);
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(){
                    @Override
                    public void run() {
                        int i=0;
                        while(i<100){
                            i++;
                            try {
                                Thread.sleep(80);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            final int j=i;
                            bar.setProgress(i);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    textView.setText(j+"%");
                                }
                            });
                        }
                    }
                }.start();
            }
        });


    }

    @Override
    public void onClick(View v) {

    }
}
