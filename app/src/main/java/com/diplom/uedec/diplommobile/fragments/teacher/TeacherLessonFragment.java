package com.diplom.uedec.diplommobile.fragments.teacher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.diplom.uedec.diplommobile.R;
import com.diplom.uedec.diplommobile.RecyclerViewAdapters.DataEventsAdapter;
import com.diplom.uedec.diplommobile.RecyclerViewAdapters.DataLessonsAdapter;
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

/**
 * Created by uedec on 14.05.2019.
 */

public class TeacherLessonFragment extends Fragment implements DataLessonsAdapter.onEventListner {

    @Override
    public void onEventClick(int position) {

    }

    public void SetAdapter(List<Lesson> mresult)
    {
        DataLessonsAdapter adapter = new DataLessonsAdapter(getContext(), mresult, this);
        recyclerView.setAdapter(adapter);
    }

    List<Lesson> result;
    RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.teacher_lessons_fragment, container, false);

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30,TimeUnit.SECONDS)
                .build();

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.BASE_URL))
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        REST REST =retrofit.create(REST.class);
        Call<List<Lesson>> call = REST.getLessons(App.user.getId());
        recyclerView = (RecyclerView)view.findViewById(R.id.list);
        call.enqueue(new Callback<List<Lesson>>() {
            @Override
            public void onResponse(Call<List<Lesson>> call, Response<List<Lesson>> response) {
                Log.i("responce-message",response.raw().message());
                Log.i("responce-headers",response.headers().toString());
                Log.i("responce-Set-Cookie",response.headers().get("Set-Cookie")==null ? "null":response.headers().get("Set-Cookie"));
                Log.i("responce-headers",response.raw().message().equals("Bad Request")? "lox" : "success");
                result=response.body();
                SetAdapter(result);
            }

            @Override
            public void onFailure(Call<List<Lesson>> call, Throwable t) {
                Log.i("responce-headers",t.getMessage());
            }
        });

        return view;
    }
}
