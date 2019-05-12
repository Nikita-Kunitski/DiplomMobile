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

import com.diplom.uedec.diplommobile.CreateEventActivity;
import com.diplom.uedec.diplommobile.R;
import com.diplom.uedec.diplommobile.RecyclerViewAdapters.DataAdapter;
import com.diplom.uedec.diplommobile.async.GetTeacherData;
import com.diplom.uedec.diplommobile.data.App;
import com.diplom.uedec.diplommobile.data.entity.EventWithAllMembers;
import com.diplom.uedec.diplommobile.data.entity.TeacherData;
import com.diplom.uedec.diplommobile.retrofit.REST;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by uedec on 12.05.2019.
 */

public class EventsTeacherFragment extends Fragment implements DataAdapter.onEventListner {

    @Override
    public void onEventClick(int position) {

    }

    public void SetAdapter(List<EventWithAllMembers> mresult)
    {
        DataAdapter adapter = new DataAdapter(getContext(), mresult, this);
        recyclerView.setAdapter(adapter);
    }
    FloatingActionButton fab;
    List<EventWithAllMembers> result;
    RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.events_teacher_fragment, container, false);

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30,TimeUnit.SECONDS)
                .build();

        fab  = (FloatingActionButton) view.findViewById(R.id.fab);

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.BASE_URL))
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        REST REST =retrofit.create(REST.class);
        Call<List<EventWithAllMembers>> call= REST.getAllEventsTeacher(App.user.getId());
        recyclerView = (RecyclerView)view.findViewById(R.id.list);
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
}