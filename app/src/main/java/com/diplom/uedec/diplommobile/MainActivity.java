package com.diplom.uedec.diplommobile;

import android.app.Application;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.diplom.uedec.diplommobile.data.App;
import com.diplom.uedec.diplommobile.data.AppDatabase;
import com.diplom.uedec.diplommobile.fragments.LoginFragment;
import com.diplom.uedec.diplommobile.fragments.RegisterFragment;


public class MainActivity extends AppCompatActivity {


    public void registerFragments() {
        getSupportFragmentManager().beginTransaction().replace(R.id.container,new RegisterFragment()).commit();
    }
    public void loginFragments() {
        getSupportFragmentManager().beginTransaction().replace(R.id.container,new LoginFragment()).commit();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppDatabase db = App.getInstance().getDatabase();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new LoginFragment())
                    .commit();
        }
        /*
        AuditoriumTypeDao auditoriumTypeDao=db.auditoriumTypeDao();
        AuditoriumDao auditoriumDao=db.auditoriumDao();
        ApplicationUserDao applicationUserDao=db.applicationUserDao();
        LessonDao lessonDao=db.lessonDao();
        EventDao eventDao=db.eventDao();
        StudentEventDao studentEventDao=db.studentEventDao();*/
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
