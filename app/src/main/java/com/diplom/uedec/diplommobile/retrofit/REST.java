package com.diplom.uedec.diplommobile.retrofit;

import com.diplom.uedec.diplommobile.data.entity.Event;
import com.diplom.uedec.diplommobile.fragments.EventFragment;

import java.util.List;

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

    @GET("api/events")
    Call<List<EventFragment.EventWithAllMembers>> getAllEvents();
}
