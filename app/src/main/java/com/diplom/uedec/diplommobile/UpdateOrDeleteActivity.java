package com.diplom.uedec.diplommobile;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import com.diplom.uedec.diplommobile.data.entity.Auditorium;
import com.diplom.uedec.diplommobile.data.entity.EventWithAllMembers;
import com.diplom.uedec.diplommobile.data.entity.Lesson;
import com.diplom.uedec.diplommobile.data.entity.TeacherData;
import com.diplom.uedec.diplommobile.retrofit.REST;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.text.InputType.TYPE_NULL;

public class UpdateOrDeleteActivity extends AppCompatActivity {

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
    EditText date, startTime, endTime,lesson, auditorium, countPeople;
    TeacherData teacherData;
    Button update, delete;
    int auditoriumId, lessonId;
    EventWithAllMembers eventWithAllMembers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Редактирование занятия");
        setContentView(R.layout.activity_update_or_delete);

        final DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        final DateFormat df2=new SimpleDateFormat("HH:mm:ss");
        final DateFormat df3=new SimpleDateFormat("HH:mm");
        final DateFormat df4=new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss");
        eventWithAllMembers = getIntent().getParcelableExtra("EventWithAllMembers");
        date=findViewById(R.id.eventDate);
        startTime = findViewById(R.id.eventStartTime);
        endTime=findViewById(R.id. eventEndTime);
        lesson=findViewById(R.id.lesson);
        auditorium=findViewById(R.id.auditorium);
        progressBar =findViewById(R.id.progressBar3);
        countPeople=findViewById(R.id.countPeople);

        date.setText(df.format(eventWithAllMembers.date));
        startTime.setText(df3.format(eventWithAllMembers.startTime));
        endTime.setText(df3.format(eventWithAllMembers.endTime));
        lesson.setText(eventWithAllMembers.lesson.getAbbreviation());
        auditorium.setText(eventWithAllMembers.auditorium.getAuditoriumName());
        countPeople.setText(String.valueOf(eventWithAllMembers.countPeople));

        update=findViewById(R.id.update);
        delete=findViewById(R.id.delete);


        OkHttpClient client1 = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30,TimeUnit.SECONDS)
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

        date.setInputType(TYPE_NULL);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                picker = new DatePickerDialog(UpdateOrDeleteActivity.this, new DatePickerDialog.OnDateSetListener() {
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
                start=new TimePickerDialog(UpdateOrDeleteActivity.this,
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
                end=new TimePickerDialog(UpdateOrDeleteActivity.this,
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
                AlertDialog.Builder builder=new AlertDialog.Builder(UpdateOrDeleteActivity.this);
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
                AlertDialog.Builder builder=new AlertDialog.Builder(UpdateOrDeleteActivity.this);
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

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Auditorium auditorium=teacherData.auditoriums.get(auditoriumId);
                Lesson lesson=teacherData.lessons.get(lessonId);
                Date Date=new Date(), StartTime=new Date(), EndTime=new Date();
                try {
                    Date=df.parse(date.getText().toString());
                    StartTime=df4.parse(date.getText().toString()+":"+ startTime.getText().toString()+":00");
                    EndTime=df4.parse(date.getText().toString()+":"+ endTime.getText().toString()+":00");
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                EventWithAllMembers update=new EventWithAllMembers(eventWithAllMembers.id,
                                                                    eventWithAllMembers.date,
                                                                    eventWithAllMembers.startTime,
                                                                    eventWithAllMembers.endTime,
                                                                    eventWithAllMembers.countPeople,
                                                                    eventWithAllMembers.lessonId,
                                                                    eventWithAllMembers.eventName,
                                                                    eventWithAllMembers.auditoriumId,
                                                                    eventWithAllMembers.teacheId,
                                                                    eventWithAllMembers.auditorium,
                                                                    eventWithAllMembers.teacher,
                                                                    eventWithAllMembers.lesson);
                if(update.auditoriumId!=auditorium.getId())
                {
                    update.auditoriumId = auditorium.getId();
                    update.auditorium = auditorium;
                }
                if(update.lessonId!=lesson.getId())
                {
                    update.lessonId=lesson.getId();
                    update.lesson=lesson;
                }
                update.countPeople=Integer.parseInt(countPeople.getText().toString());
                update.date=Date;
                update.startTime=StartTime;
                update.endTime=EndTime;
                if(update.equals(eventWithAllMembers)) {
                    Toast.makeText(UpdateOrDeleteActivity.this, "Вы ничего не изменили", Toast.LENGTH_SHORT).show();
                    return;
                }

                Call<Void> call=rest.Update(update);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if(response.code()==200)
                        {
                            Toast.makeText(UpdateOrDeleteActivity.this, "Изменено", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(UpdateOrDeleteActivity.this, TeacherHomeActivity.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<Void> call=rest.Delete(eventWithAllMembers);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if(response.code()==200)
                        {
                            Toast.makeText(UpdateOrDeleteActivity.this, "Удалено", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(UpdateOrDeleteActivity.this, HomeActivity.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });
            }
        });
    }
}
