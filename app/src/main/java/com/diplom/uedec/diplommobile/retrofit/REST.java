package com.diplom.uedec.diplommobile.retrofit;

import com.diplom.uedec.diplommobile.data.entity.ApplicationUser;
import com.diplom.uedec.diplommobile.data.entity.Event;
import com.diplom.uedec.diplommobile.data.entity.EventWithAllMembers;
import com.diplom.uedec.diplommobile.data.entity.StudentEvent;
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
    Call<ApplicationUser> Auth(@Query("email") String email, @Query("password") String password);

    @POST("api/registration/student")
    Call<Void> Register(@Body RequestRegisterStudent requestRegisterStudent);

    @GET("api/events")
    Call<List<EventWithAllMembers>> getAllEvents();

    @POST("api/events/subscribe")
    Call<Void> Subscribe(@Body StudentEvent studentEvent);
}
