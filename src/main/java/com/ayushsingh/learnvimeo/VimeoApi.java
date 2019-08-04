package com.ayushsingh.learnvimeo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface VimeoApi {
    @GET("oembed.json")
    Call<Frame> getFrame(@Query("url") String ad);
}
