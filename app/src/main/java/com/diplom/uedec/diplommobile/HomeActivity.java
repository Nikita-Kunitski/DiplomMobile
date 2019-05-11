package com.diplom.uedec.diplommobile;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.diplom.uedec.diplommobile.data.App;
import com.diplom.uedec.diplommobile.fragments.DetailUserFragment;
import com.diplom.uedec.diplommobile.fragments.EventFragment;
import com.diplom.uedec.diplommobile.fragments.MySubscribesFragment;
import com.diplom.uedec.diplommobile.fragments.RegisterFragment;
import com.diplom.uedec.diplommobile.fragments.ReminderFragment;

public class HomeActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.home_student:
                    getSupportFragmentManager().beginTransaction().replace(R.id.home_container,new EventFragment()).commit();
                    return true;
                case R.id.home_teacher:
                    getSupportFragmentManager().beginTransaction().replace(R.id.home_container,new DetailUserFragment()).commit();
                    //TODO изменить выхов фрагмента
                    return true;
                case R.id.navigation_dashboard:
                    getSupportFragmentManager().beginTransaction().replace(R.id.home_container,new MySubscribesFragment()).commit();
                    return true;
                case R.id.navigation_notifications:
                    //getSupportFragmentManager().beginTransaction().replace(R.id.home_container,new ReminderFragment()).commit();
                    return true;
                case R.id.detail_user:
                    getSupportFragmentManager().beginTransaction().replace(R.id.home_container,new DetailUserFragment()).commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportFragmentManager().beginTransaction().replace(R.id.home_container,new EventFragment()).commit();
        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        if(App.role.equals("student"))
            findViewById(R.id.home_teacher).setVisibility(View.GONE);
    }

}
