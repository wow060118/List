package com.example.yfr.list.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface DownloadApi {

    @GET
    @Streaming
    Call<ResponseBody> down(@Url String url);

}
