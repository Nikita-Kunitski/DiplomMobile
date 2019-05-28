package com.diplom.uedec.diplommobile;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.drm.DrmStore;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TimePicker;
import android.widget.Toast;

import com.diplom.uedec.diplommobile.data.App;
import com.diplom.uedec.diplommobile.data.entity.ApplicationUser;
import com.diplom.uedec.diplommobile.data.entity.Auditorium;
import com.diplom.uedec.diplommobile.data.entity.EventWithAllMembers;
import com.diplom.uedec.diplommobile.data.entity.Lesson;
import com.diplom.uedec.diplommobile.data.entity.TeacherData;
import com.diplom.uedec.diplommobile.retrofit.REST;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.text.InputType.TYPE_NULL;

public class CreateEventActivity extends AppCompatActivity {

    public void ChangeValueEditors(boolean flag)
    {
        date.setEnabled(flag);
        endTime.setEnabled(flag);
        startTime.setEnabled(flag);
        lesson.setEnabled(flag);
        auditorium.setEnabled(flag);
    }

    DatePickerDialog picker;
    TimePickerDialog start,end;
    ProgressBar progressBar;
    EditText date, startTime, endTime,lesson, auditorium, countPeople, students;
    TeacherData teacherData;
    Button create;
    ArrayList<ApplicationUser> studentsToRequest;
    int auditoriumId, lessonId;
    SharedPreferences sPref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Создание нового занятия");
        setContentView(R.layout.activity_create_event);
        sPref=getSharedPreferences(App.APP_PREFERENCES,MODE_PRIVATE);
        final String token=sPref.getString(App.TOKEN,"");
        date=findViewById(R.id.eventDate);
        startTime = findViewById(R.id.eventStartTime);
        endTime=findViewById(R.id. eventEndTime);
        lesson=findViewById(R.id.lesson);
        auditorium=findViewById(R.id.auditorium);
        progressBar =findViewById(R.id.progressBar3);
        create=findViewById(R.id.create);
        countPeople=findViewById(R.id.countPeople);
        students=findViewById(R.id.students);
        studentsToRequest=new ArrayList<>();
        OkHttpClient client1 = new OkHttpClient.Builder()
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

        final Retrofit retrofit1=new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.BASE_URL))
                .addConverterFactory(GsonConverterFactory.create())
                .client(client1)
                .build();
        final REST rest=retrofit1.create(REST.class);

        progressBar.setVisibility(View.VISIBLE);
        ChangeValueEditors(false);
        Call<TeacherData> call2=rest.getTeacherData(App.user.getId());
        call2.enqueue(new Callback<TeacherData>() {
            @Override
            public void onResponse(Call<TeacherData> call, Response<TeacherData> response) {
                Log.i("responce-message",response.raw().message());
                Log.i("responce-headers",response.headers().toString());
                Log.i("responce-Set-Cookie",response.headers().get("Set-Cookie")==null ? "null":response.headers().get("Set-Cookie"));
                Log.i("responce-headers",response.raw().message().equals("Bad Request")? "lox" : "success");
                teacherData=response.body();
                //new GetTeacherData().execute(list);
                progressBar.setVisibility(View.GONE);
                ChangeValueEditors(true);
            }

            @Override
            public void onFailure(Call<TeacherData> call, Throwable t) {
                Log.i("responce-headers",t.getMessage());
            }
        });

        final DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        final DateFormat df2=new SimpleDateFormat("HH:mm:ss");

        date.setInputType(TYPE_NULL);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                picker = new DatePickerDialog(CreateEventActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        date.setText(i+"-"+(i1+1)+"-"+i2);
                    }
                },year, month, day);
                picker.show();
            }
        });
        startTime.setInputType(TYPE_NULL);
        startTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar cldr = Calendar.getInstance();
                int hour = cldr.get(Calendar.HOUR_OF_DAY);
                int minute = cldr.get(Calendar.MINUTE);
                int second = cldr.get(Calendar.SECOND);
                start=new TimePickerDialog(CreateEventActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                                startTime.setText(sHour + ":" + sMinute);
                            }
                        }, hour, minute, true);
                start.show();
            }
        });
        endTime.setInputType(TYPE_NULL);
        endTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar cldr = Calendar.getInstance();
                int hour = cldr.get(Calendar.HOUR_OF_DAY);
                int minute = cldr.get(Calendar.MINUTE);
                int second = cldr.get(Calendar.SECOND);
                end=new TimePickerDialog(CreateEventActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                                endTime.setText(sHour + ":" + sMinute);
                            }
                        }, hour, minute, true);
                end.show();
            }
        });

        lesson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(CreateEventActivity.this);
                final ArrayList<String> lessonString=new ArrayList<>();
                for (Lesson item:teacherData.lessons
                     ) {
                    lessonString.add(item.getAbbreviation());
                }
                final String [] arr = new String[teacherData.lessons.size()];
                lessonString.toArray(arr);
                builder.setTitle("Предмет")
                        .setCancelable(false)
                        .setNeutralButton("Назад", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        })
                        .setPositiveButton("Ок", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        })
                        .setSingleChoiceItems( arr, -1, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                lesson.setText(arr[i]);
                                lessonId=i;
                            }
                        }).show();

            }
        });

        auditorium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(CreateEventActivity.this);
                final ArrayList<String> auditoriumString=new ArrayList<>();
                for (Auditorium item:teacherData.auditoriums
                        ) {
                    auditoriumString.add(item.getAuditoriumName());
                }
                final String [] arr = new String[teacherData.auditoriums.size()];
                auditoriumString.toArray(arr);
                builder.setTitle("Аудитория")
                        .setCancelable(false)
                        .setNeutralButton("Назад", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        })
                        .setPositiveButton("Ок", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        })
                        .setSingleChoiceItems( arr, -1, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                auditorium.setText(arr[i]);
                                auditoriumId=i;
                            }
                        }).show();
            }
        });

        students.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(CreateEventActivity.this);
                final ArrayList<String> studentString=new ArrayList<>();
                for (ApplicationUser item:teacherData.students
                     ) {
                    studentString.add(item.getEmail());
                }
                studentsToRequest.retainAll(studentsToRequest);
                final String [] arr = new String[teacherData.students.size()];
                final boolean []arr_flag=new boolean[teacherData.students.size()];
                studentString.toArray(arr);
                builder.setTitle("Студенты")
                        .setMultiChoiceItems(arr, arr_flag, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                                arr_flag[i]=b;
                            }
                        })
                        .setCancelable(false)
                        .setNeutralButton("Назад", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        }).setPositiveButton("Ок", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        for(int j=0;j<teacherData.students.size();j++)
                        {
                            if(arr_flag[j]==true)
                            {
                                studentsToRequest.add(teacherData.students.get(j));
                            }
                        }
                    }
                }).show();

            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Auditorium auditorium=teacherData.auditoriums.get(auditoriumId);
                Lesson lesson=teacherData.lessons.get(lessonId);
                Date Date=new Date(), StartTime=new Date(), EndTime=new Date();
                try {
                    Date=df.parse(date.getText().toString());
                    StartTime=df2.parse(startTime.getText().toString()+":00");
                    EndTime=df2.parse(endTime.getText().toString()+":00");
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                EventWithAllMembers eventWithAllMembers=new EventWithAllMembers(Date,
                                                                                StartTime,
                                                                                EndTime,
                                                                                Integer.parseInt(countPeople.getText().toString()),
                                                                                lesson.getId(),
                                                                                lesson.getName()+" "+App.user.getEmail(),
                                                                                auditorium.getId(),
                                                                                App.user.getId(),
                                                                                auditorium,
                                                                                App.user,
                                                                                lesson,
                                                                                studentsToRequest);

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

                final Retrofit retrofit1=new Retrofit.Builder()
                        .baseUrl(getResources().getString(R.string.BASE_URL))
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(client)
                        .build();
                final REST rest=retrofit1.create(REST.class);
                Call<Void> call=rest.Create(eventWithAllMembers);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Log.i("responce-message",response.code()+" "+response.raw().message());
                        if(response.code()==200)
                        {

                           /* EventWithAllMembers eventWithAllMembers1=response.body();*///TODO пересмотреть момент с возвратом данных
                            Toast.makeText(CreateEventActivity.this,"Удача",Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(CreateEventActivity.this,TeacherHomeActivity.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Log.i("responce-failure",t.getMessage());
                    }
                });
            }
        });


    }
}
