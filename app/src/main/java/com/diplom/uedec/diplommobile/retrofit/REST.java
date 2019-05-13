package com.diplom.uedec.diplommobile.retrofit;

import com.diplom.uedec.diplommobile.data.entity.ApplicationUser;
import com.diplom.uedec.diplommobile.data.entity.EventWithAllMembers;
import com.diplom.uedec.diplommobile.data.entity.StudentEvent;
import com.diplom.uedec.diplommobile.data.entity.TeacherData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface REST {
    @POST("api/authentication")
    Call<ApplicationUser> Auth(@Query("email") String email, @Query("password") String password);

    @POST("api/registration/student")
    Call<Void> Register(@Body RequestRegisterStudent requestRegisterStudent);

    @GET("api/events")
    Call<List<EventWithAllMembers>> getAllEvents();

    @GET("api/events/teachers")
    Call<List<EventWithAllMembers>> getAllEventsTeacher(@Query("id") String id);

    @GET("api/data")
    Call<TeacherData> getTeacherData(@Query("id")String id);

    @POST("api/events/subscribe")
    Call<Void> Subscribe(@Body StudentEvent studentEvent);

    @POST("api/events/unsubscribe")
    Call<Void> Unsubscribe(@Body StudentEvent studentEvent);

    @POST("api/events/create")
    Call<EventWithAllMembers> Create(@Body EventWithAllMembers eventWithAllMembers);

    @POST("api/events/update")
    Call<Void> Update(@Body EventWithAllMembers eventWithAllMembers);
}
