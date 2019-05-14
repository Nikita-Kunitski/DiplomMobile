package com.diplom.uedec.diplommobile;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.diplom.uedec.diplommobile.fragments.student.EventsStudentFragment;
import com.diplom.uedec.diplommobile.fragments.teacher.DetailUserTeacherFragment;
import com.diplom.uedec.diplommobile.fragments.teacher.EventsTeacherFragment;
import com.diplom.uedec.diplommobile.fragments.teacher.TeacherLessonFragment;

public class TeacherHomeActivity extends AppCompatActivity {

    public void lessonFragments() {
        getSupportFragmentManager().beginTransaction().replace(R.id.home_container,new TeacherLessonFragment()).commit();
    }

    private TextView mTextMessage;
    BottomNavigationView navigation;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.home_teacher:
                    getSupportFragmentManager().beginTransaction().replace(R.id.home_container,new EventsTeacherFragment()).commit();

                    //TODO изменить выхов фрагментаs
                    return true;
                case R.id.detail_teacher:
                    getSupportFragmentManager().beginTransaction().replace(R.id.home_container,new DetailUserTeacherFragment()).commit();
                    return true;

                case R.id.teacher_lesson:
                    getSupportFragmentManager().beginTransaction().replace(R.id.home_container,new TeacherLessonFragment()).commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Должник БГТУ");
        setContentView(R.layout.activity_teacher_home);

        mTextMessage = (TextView) findViewById(R.id.message);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.home_teacher);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.home_container, new EventsTeacherFragment())
                .commit();
    }

}
