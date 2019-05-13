package com.diplom.uedec.diplommobile;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.diplom.uedec.diplommobile.async.GetTeacherData;
import com.diplom.uedec.diplommobile.data.App;
import com.diplom.uedec.diplommobile.data.entity.TeacherData;
import com.diplom.uedec.diplommobile.fragments.student.DetailUserStudentFragment;
import com.diplom.uedec.diplommobile.fragments.student.EventsStudentFragment;
import com.diplom.uedec.diplommobile.fragments.teacher.EventsTeacherFragment;
import com.diplom.uedec.diplommobile.fragments.student.MySubscribesFragment;
import com.diplom.uedec.diplommobile.retrofit.REST;

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
                case R.id.home_teacher:
                    getSupportFragmentManager().beginTransaction().replace(R.id.home_container,new EventsTeacherFragment()).commit();
                    //TODO изменить выхов фрагментаs
                    return true;

                case R.id.home_student:
                    getSupportFragmentManager().beginTransaction().replace(R.id.home_container,new EventsStudentFragment()).commit();
                    return true;

                case R.id.navigation_dashboard:
                    getSupportFragmentManager().beginTransaction().replace(R.id.home_container,new MySubscribesFragment()).commit();
                    return true;
                case R.id.navigation_notifications:
                    //getSupportFragmentManager().beginTransaction().replace(R.id.home_container,new ReminderFragment()).commit();
                    return true;
                case R.id.detail_user:
                    getSupportFragmentManager().beginTransaction().replace(R.id.home_container,new DetailUserStudentFragment()).commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        if(App.role.equals("student")) {
            findViewById(R.id.home_teacher).setEnabled(false);
            findViewById(R.id.home_teacher).setVisibility(View.GONE);
            navigation.setSelectedItemId(R.id.home_student);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.home_container, new EventsStudentFragment())
                    .commit();
        }
        if(App.role.equals("teacher")) {
            findViewById(R.id.home_student).setEnabled(false);
            findViewById(R.id.home_student).setVisibility(View.GONE);
            navigation.setSelectedItemId(R.id.home_teacher);

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.home_container, new EventsTeacherFragment())
                    .commit();


        }
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }

}
