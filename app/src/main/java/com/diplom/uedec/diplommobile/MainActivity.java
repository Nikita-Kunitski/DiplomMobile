package com.diplom.uedec.diplommobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.diplom.uedec.diplommobile.data.App;
import com.diplom.uedec.diplommobile.data.AppDatabase;
import com.diplom.uedec.diplommobile.data.dao.AuditoriumTypeDao;
import com.diplom.uedec.diplommobile.data.entity.AuditoriumType;

import java.sql.Date;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
}
