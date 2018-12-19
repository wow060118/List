package com.example.yfr.list.rxjava.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @Author: fengrui.yang
 * @Description:
 * @Date: created in 下午6:24 2018/12/18
 * @Modified_By:
 */
public interface TestApi {
    @GET("top250")
    Call<ResponseBody> getString (@Query("start")int start, @Query("count")int count);
}
