package com.example.yfr.list.util;

import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.example.yfr.list.api.DownloadApi;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class DownloadUtil {


    private static int sBufferSize = 10240;

//    static final String real="4746986_145156378323_2.jpg";
    static final String real = "master.zip";
    public static void download(final String url, final String path, final DownloadListener downloadListener, final Handler handler) {

        System.out.println("start   ");
        new Thread(new Runnable() {
            @Override
            public void run() {
                Retrofit retrofit = new Retrofit.Builder()
                        //这个就是converterFactory 数据分析器
                        .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .baseUrl(url)
                        .build();

                DownloadApi dApi = retrofit.create(DownloadApi.class);
//
                Call<ResponseBody> news = dApi.down(real);
//                news.enqueue(new Callback<ResponseBody>() {
////                    @Override
////                    public void onResponse(Call<ResponseBody> call,  Response<ResponseBody> response) {
//////                        ResponseBody body = response.body();
//////                        System.out.println(call);
//////                        System.out.println("onResponse:   ="+ JSON.toJSONString(response)+"    body:"+body);
////                        writeResponseToDisk(path,response,downloadListener,handler);
////
////                    }
////
////                    @Override
////                    public void onFailure(Call<ResponseBody> call, Throwable t) {
////                        System.out.println("onResponse fail:   =" + t.getMessage());
////
////                    }
////                });
                try {
                    Response<ResponseBody> response = news.execute();
                    writeResponseToDisk(path,response,downloadListener,handler);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        System.out.println("end   ");

    }

    private static void writeResponseToDisk(String path, Response<ResponseBody> response, DownloadListener downloadListener,Handler handler) {
        //从response获取输入流以及总大小
        writeFileFromIS(path, response.body().byteStream(), response.body().contentLength(), downloadListener,handler);
    }



    //将输入流写入文件
    private static void writeFileFromIS(String path, InputStream is, final long totalLength, final DownloadListener downloadListener, final Handler handler) {
        //开始下载
//        downloadListener.onStart();
//        System.out.println("++++++"+path+"    ");
        File file = new File(path);
        //创建文件
        if (!file.exists()||file.isDirectory()) {
            file.mkdirs();
//            System.out.println("1111+"+file.mkdirs());
        }
        String newFilePath = path+File.separator+real;
//        String newFilePath = path+File.separator+"test_img.mp4";
        File newFile= new File(newFilePath);
        try {
            newFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
//            System.out.println("1111111111111+++++");
            newFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        OutputStream os = null;
        long currentLength = 0;
        try {
            os = new BufferedOutputStream(new FileOutputStream(newFile));
            byte data[] = new byte[sBufferSize];
            int len;
            while ((len = is.read(data, 0, sBufferSize)) != -1) {
                os.write(data, 0, len);
                currentLength += len;
                //计算当前下载进度
                System.out.println("total is ：" +totalLength+"   now :" +currentLength);
//                downloadListener.onProgress((int) (100 * currentLength / totalLength));
                Message message=new Message();
                message.what=1;
                Bundle bundle=new Bundle();
                bundle.putInt("progress",(int) (100 * currentLength / totalLength));
                message.setData(bundle);
                handler.sendMessage(message);
//                final long finalCurrentLength = currentLength;
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        handler.post(new Runnable() {
//                            @Override
//                            public void run() {
//                                downloadListener.onProgress((int) (100 * finalCurrentLength / totalLength));
//                            }
//                        });
//                    }
//                }).start();

            }
            //下载完成，并返回保存的文件路径
            downloadListener.onFinish(newFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
            downloadListener.onFail("IOException");
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


}
