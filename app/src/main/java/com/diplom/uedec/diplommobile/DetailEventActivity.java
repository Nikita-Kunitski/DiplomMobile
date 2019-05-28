package com.diplom.uedec.diplommobile;

import android.app.ActionBar;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.diplom.uedec.diplommobile.async.WriteToDataBase;
import com.diplom.uedec.diplommobile.data.App;
import com.diplom.uedec.diplommobile.data.AppDatabase;
import com.diplom.uedec.diplommobile.data.dao.ApplicationUserDao;
import com.diplom.uedec.diplommobile.data.dao.AuditoriumDao;
import com.diplom.uedec.diplommobile.data.dao.EventDao;
import com.diplom.uedec.diplommobile.data.dao.LessonDao;
import com.diplom.uedec.diplommobile.data.dao.StudentEventDao;
import com.diplom.uedec.diplommobile.data.entity.ApplicationUser;
import com.diplom.uedec.diplommobile.data.entity.Auditorium;
import com.diplom.uedec.diplommobile.data.entity.Event;
import com.diplom.uedec.diplommobile.data.entity.EventWithAllMembers;
import com.diplom.uedec.diplommobile.data.entity.Lesson;
import com.diplom.uedec.diplommobile.data.entity.StudentEvent;
import com.diplom.uedec.diplommobile.retrofit.REST;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailEventActivity extends AppCompatActivity {



    public void SubscribeToEvent()
    {
        sPref=getSharedPreferences(App.APP_PREFERENCES,MODE_PRIVATE);
        final String token=sPref.getString(App.TOKEN,"");
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30,TimeUnit.SECONDS)
                .addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        Request newRequest  = chain.request().newBuilder()
                                .addHeader("Authorization", "Bearer " + token)
                                .build();
                        return chain.proceed(newRequest);
                    }
                })
                .build();
        Retrofit retrofit=new Retrofit.Builder()
                        .baseUrl(getResources().getString(R.string.BASE_URL))
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(client)
                        .build();
        REST REST =retrofit.create(REST.class);
        StudentEvent studentEvent=new StudentEvent(App.user.getId(),eventWithAllMembers.id);
        Call<Void> call = REST.Subscribe(studentEvent);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.i("responce-message",response.raw().message());
                Toast.makeText(getApplicationContext(),"Вы записались на это занятие",Toast.LENGTH_SHORT).show();
                //new WriteToDataBase().execute(eventWithAllMembers);
                Intent intent=new Intent(DetailEventActivity.this,HomeActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.i("responce",t.toString());
                Toast.makeText(getApplicationContext(),"Возникла ошибка при подписке",Toast.LENGTH_SHORT).show();
            }
        });

    }
    EventWithAllMembers eventWithAllMembers;
    TextView eventName, dateAndTime, auditorium, lesson, teacher;
    SharedPreferences sPref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        eventWithAllMembers = (EventWithAllMembers) getIntent().getParcelableExtra("EventWithAllMembers");
        setTitle(eventWithAllMembers.eventName);
        setContentView(R.layout.activity_detail_event);
        eventName = findViewById(R.id.eventName);
        dateAndTime = findViewById(R.id.date_and_time);
        auditorium = findViewById(R.id.auditorium);
        lesson=findViewById(R.id.lesson);
        teacher=findViewById(R.id.teacher);

        DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        DateFormat df2=new SimpleDateFormat("HH:mm");
        eventName.setText(eventWithAllMembers.eventName);

        dateAndTime.setText(df.format(eventWithAllMembers.date)+"\n"+df2.format(eventWithAllMembers.startTime)+" - "+df2.format(eventWithAllMembers.endTime));
        auditorium.setText(eventWithAllMembers.auditorium.getAuditoriumName());
        lesson.setText(eventWithAllMembers.lesson.getName());
        teacher.setText(eventWithAllMembers.teacher.getEmail());
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SubscribeToEvent();
            }
        });
    }
}
