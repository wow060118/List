package com.example.yfr.list.rxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.example.yfr.list.rxjava.api.TestApi;
import com.example.yfr.list.rxjava.api.UserEntity;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @Author: fengrui.yang
 * @Description:
 * @Date: created in 下午6:18 2018/12/18
 * @Modified_By:
 */
public class RetrofitAndRxjavaAcvitity extends AppCompatActivity {
//    private static final String BASE_URL = "https://api.douban.com/v2/movie/";
    private static final String BASE_URL = "http://172.16.149.25:8080/user/";
    private Retrofit retrofit;

    @Override
    public void onCreate(Bundle starInstanceState) {
        super.onCreate(starInstanceState);
        init();
        TestApi testApi = retrofit.create(TestApi.class);
//        Call<ResponseBody> call = testApi.getString(0, 20);
        Call<UserEntity> call = testApi.getUser("username");
        testApi.getUser("username");
        call.enqueue(new Callback<UserEntity>(){

            @Override
            public void onResponse(Call<UserEntity> call, Response<UserEntity> response) {

                UserEntity entity = response.body();
                Log.i("response", entity+"");
//                Log.i(new UserEntity());

            }

            @Override
            public void onFailure(Call<UserEntity> call, Throwable t) {
                Log.i("response","fail");
            }
        });
    }

    private void init() {

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

}
