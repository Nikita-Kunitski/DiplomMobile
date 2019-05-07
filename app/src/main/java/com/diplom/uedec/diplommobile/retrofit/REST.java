package com.diplom.uedec.diplommobile.retrofit;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by uedec on 07.05.2019.
 */

public interface REST {
    @POST("api/Token/")
    Call<Void> Auth(@Query("email") String email, @Query("password") String password);
}
