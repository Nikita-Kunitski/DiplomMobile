package com.diplom.uedec.diplommobile;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.diplom.uedec.diplommobile.data.App;
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
        navigation.setSelectedItemId(R.id.home_teacher);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.home_container, new EventsTeacherFragment())
                .commit();
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder quitDialog = new AlertDialog.Builder(
                TeacherHomeActivity.this);
        quitDialog.setTitle("Хотите выйти из аккаунта?");

        quitDialog.setPositiveButton("Да", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SharedPreferences sPreference;
                sPreference=getSharedPreferences(App.APP_PREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor ed=sPreference.edit();
                ed.remove("ROLE");
                ed.remove("USER");
                ed.commit();
                finish();
            }
        });

        quitDialog.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        quitDialog.show();

    }
}
