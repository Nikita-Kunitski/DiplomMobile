package com.diplom.uedec.diplommobile.fragments;

import android.arch.persistence.room.Embedded;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.diplom.uedec.diplommobile.R;
import com.diplom.uedec.diplommobile.RecyclerViewAdapters.DataAdapter;
import com.diplom.uedec.diplommobile.data.entity.ApplicationUser;
import com.diplom.uedec.diplommobile.data.entity.Auditorium;
import com.diplom.uedec.diplommobile.data.entity.Event;
import com.diplom.uedec.diplommobile.data.entity.Lesson;
import com.diplom.uedec.diplommobile.retrofit.REST;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by uedec on 08.05.2019.
 */

public class EventFragment extends Fragment {


    public class EventWithAllMembers
    {
        @SerializedName("Id")
        @Expose
        public int id;
        @SerializedName("Date")
        @Expose
        public Date date;
        @SerializedName("StartTime")
        @Expose
        public Date startTime;
        @SerializedName("EndTime")
        @Expose
        public Date endTime;
        @SerializedName("CountPeople")
        @Expose
        public int countPeople;
        @SerializedName("LessonId")
        @Expose
        public int lessonId;

        @SerializedName("EventName")
        @Expose
        public String eventName;
        @SerializedName("AuditoriumId")
        @Expose
        public int auditoriumId;
        @SerializedName("TeacherId")
        @Expose
        public String teacheId;
        @SerializedName("Auditorium")
        @Expose
        @Embedded
        public Auditorium auditorium;
        @SerializedName("Teacher")
        @Expose
        @Embedded
        public ApplicationUser teacher;
        @SerializedName("Lesson")
        @Expose
        @Embedded
        public Lesson lesson;

    }

     List<EventWithAllMembers> result;
    RecyclerView recyclerView;
    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.events_fragment, container, false);
        Retrofit retrofit=new Retrofit.Builder().baseUrl(getResources().getString(R.string.BASE_URL)).addConverterFactory(GsonConverterFactory.create()).build();
        REST REST =retrofit.create(REST.class);
        Call<List<EventWithAllMembers>> call= REST.getAllEvents();
        recyclerView = (RecyclerView)view.findViewById(R.id.list);

        call.enqueue(new Callback<List<EventWithAllMembers>>() {
            @Override
            public void onResponse(Call<List<EventWithAllMembers>> call, Response<List<EventWithAllMembers>> response) {
                Log.i("responce-message",response.raw().message());
                Log.i("responce-headers",response.headers().toString());
                Log.i("responce-Set-Cookie",response.headers().get("Set-Cookie")==null ? "null":response.headers().get("Set-Cookie"));
                Log.i("responce-headers",response.raw().message().equals("Bad Request")? "lox" : "success");
                result=response.body();
                DataAdapter adapter = new DataAdapter(getContext(), result);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<EventWithAllMembers>> call, Throwable t) {
                Log.i("responce-headers",t.getMessage());
            }
        });
        return view;
    }
}
