package com.diplom.uedec.diplommobile.retrofit;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface REST {
    @POST("api/authentication/student")
    Call<Void> Auth(@Query("email") String email, @Query("password") String password);

    @POST("api/registration/student")
    Call<Void> Register(@Body RequestRegisterStudent requestRegisterStudent);
}
