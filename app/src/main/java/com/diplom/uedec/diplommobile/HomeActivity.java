package com.diplom.uedec.diplommobile;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


import com.diplom.uedec.diplommobile.data.App;
import com.diplom.uedec.diplommobile.data.entity.Lesson;
import com.diplom.uedec.diplommobile.fragments.LoginFragment;
import com.diplom.uedec.diplommobile.fragments.student.DetailUserStudentFragment;
import com.diplom.uedec.diplommobile.fragments.student.EventsStudentFragment;
import com.diplom.uedec.diplommobile.fragments.teacher.DetailUserTeacherFragment;
import com.diplom.uedec.diplommobile.fragments.teacher.EventsTeacherFragment;
import com.diplom.uedec.diplommobile.fragments.student.MySubscribesFragment;
import com.diplom.uedec.diplommobile.fragments.teacher.TeacherLessonFragment;
import com.diplom.uedec.diplommobile.retrofit.REST;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class HomeActivity extends AppCompatActivity {

    public void teacherFragments() {
        getSupportFragmentManager().beginTransaction().replace(R.id.home_container,new EventsTeacherFragment()).commit();
    }


    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {

                case R.id.home_student:
                    getSupportFragmentManager().beginTransaction().replace(R.id.home_container,new EventsStudentFragment()).commit();
                    return true;

                case R.id.student_subscriptions:
                    getSupportFragmentManager().beginTransaction().replace(R.id.home_container,new MySubscribesFragment()).commit();
                    return true;
                case R.id.student_notifications:
                    //getSupportFragmentManager().beginTransaction().replace(R.id.home_container,new ReminderFragment()).commit();
                    return true;
                case R.id.detail_student:
                    getSupportFragmentManager().beginTransaction().replace(R.id.home_container,new DetailUserStudentFragment()).commit();
                    return true;

            }
            return false;
        }
    };

    BottomNavigationView navigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Должник БГТУ");
        setContentView(R.layout.activity_home);

        mTextMessage = (TextView) findViewById(R.id.message);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setSelectedItemId(R.id.home_student);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.home_container, new EventsStudentFragment())
                .commit();
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }

}
