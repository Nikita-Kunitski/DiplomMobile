package com.diplom.uedec.diplommobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.diplom.uedec.diplommobile.retrofit.REST;

import java.net.CookieHandler;
import java.net.CookieManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*AppDatabase db = App.getInstance().getDatabase();
        AuditoriumTypeDao auditoriumTypeDao=db.auditoriumTypeDao();
        AuditoriumDao auditoriumDao=db.auditoriumDao();
        ApplicationUserDao applicationUserDao=db.applicationUserDao();
        LessonDao lessonDao=db.lessonDao();
        EventDao eventDao=db.eventDao();
        StudentEventDao studentEventDao=db.studentEventDao();*/

        CookieHandler cookieHandler = new CookieManager();

        Retrofit retrofit=new Retrofit.Builder().baseUrl("http://192.168.163.2:2314/").build();
        REST REST =retrofit.create(REST.class);
        Call<Void> call= REST.Auth("admin@gmail.com","10Nikita.");

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
               Log.i("responce-message",response.raw().message());
                Log.i("responce-headers",response.headers().toString());
                Log.i("responce-cookie",response.raw().request().headers().get("Cookie")==null?"null":response.raw().request().headers().get("Cookie"));
                Log.i("responce-headers",response.headers().get("Set-Cookie"));
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.i("responce",t.toString());
            }
        });
        /*AuditoriumType auditoriumType=new AuditoriumType();
        auditoriumType.setId(1);
        auditoriumType.setAuditoriumAbbreviation("ЛК");
        auditoriumType.setAuditoriumName("Лекционная");
        auditoriumTypeDao.insert(auditoriumType);

        Auditorium auditorium=new Auditorium();
        auditorium.setId(1);
        auditorium.setAuditoriumCapacity(10);
        auditorium.setAuditoriumName("301");
        auditorium.setAuditoriumTypeId(1);
        auditoriumDao.insert(auditorium);

        Lesson lesson=new Lesson();
        lesson.setId(1);
        lesson.setCourse(2);
        lesson.setAbbreviation("СУБД");
        lesson.setName("Система управления базами данных");
        lessonDao.insert(lesson);

        ApplicationUser teacher=new ApplicationUser();
        teacher.setId(1);
        teacher.setEmail("teacher@gmail.com");
        applicationUserDao.insert(teacher);

        Event event=new Event();
        event.setId(1);
        event.setDate("2019-05-05");
        event.setStartTime("10:25:00");
        event.setEndTime("11:25:00");
        event.setLessonId(1);
        event.setAuditoriumId(1);
        event.setTeacherId(1);
        eventDao.insert(event);

        StudentEvent studentEvent=new StudentEvent(teacher.getId(),event.getId());
        studentEventDao.insert(studentEvent);
        List<Event> list=eventDao.getAll();


        List<EventWithStudents>list1=eventDao.getEventWithStudents();
        event.getDate();*/
    }
}
