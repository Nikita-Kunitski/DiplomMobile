package com.diplom.uedec.diplommobile.fragments.teacher;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.diplom.uedec.diplommobile.CreateEventActivity;
import com.diplom.uedec.diplommobile.R;
import com.diplom.uedec.diplommobile.RecyclerViewAdapters.DataEventsAdapter;
import com.diplom.uedec.diplommobile.UpdateOrDeleteActivity;
import com.diplom.uedec.diplommobile.data.App;
import com.diplom.uedec.diplommobile.data.entity.EventWithAllMembers;
import com.diplom.uedec.diplommobile.data.entity.Lesson;
import com.diplom.uedec.diplommobile.retrofit.REST;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class EventsTeacherFragment extends Fragment implements DataEventsAdapter.onEventListner {

    @Override
    public void onEventClick(int position) {
        Intent intent=new Intent(getActivity(), UpdateOrDeleteActivity.class);
        EventWithAllMembers item = result.get(position);
        intent.putExtra("EventWithAllMembers",item);
        startActivity(intent);
    }

    public void SetAdapter(List<EventWithAllMembers> mresult)
    {
        DataEventsAdapter adapter = new DataEventsAdapter(getContext(), mresult, this);
        recyclerView.setAdapter(adapter);
        progressBar.setVisibility(View.GONE);
    }
    FloatingActionButton fab;
    List<EventWithAllMembers> result;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    OkHttpClient client;
    Retrofit retrofit;
    REST rest;
    Call<List<EventWithAllMembers>> call;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        client = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30,TimeUnit.SECONDS)
                .build();

        retrofit=new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.BASE_URL))
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        rest =retrofit.create(REST.class);
        call= rest.getAllEventsTeacher(App.user.getId());
        result=null;
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.events_teacher_fragment, container, false);

        fab  = (FloatingActionButton) view.findViewById(R.id.fab);

        progressBar=view.findViewById(R.id.progressBar5);
        recyclerView = (RecyclerView)view.findViewById(R.id.list);
        progressBar.setVisibility(View.VISIBLE);
        call.enqueue(new Callback<List<EventWithAllMembers>>() {
            @Override
            public void onResponse(Call<List<EventWithAllMembers>> call, Response<List<EventWithAllMembers>> response) {
                Log.i("responce-message",response.raw().message());
                Log.i("responce-headers",response.headers().toString());
                Log.i("responce-Set-Cookie",response.headers().get("Set-Cookie")==null ? "null":response.headers().get("Set-Cookie"));
                Log.i("responce-headers",response.raw().message().equals("Bad Request")? "lox" : "success");
                result=response.body();
                SetAdapter(result);
            }

            @Override
            public void onFailure(Call<List<EventWithAllMembers>> call, Throwable t) {
                Log.i("responce-headers",t.getMessage());
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), CreateEventActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onStop() {
        if(result==null)
            call.cancel();
        super.onStop();
    }
}
