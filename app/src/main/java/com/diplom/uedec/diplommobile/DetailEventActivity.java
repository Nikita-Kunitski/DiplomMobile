package com.diplom.uedec.diplommobile;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.diplom.uedec.diplommobile.data.entity.EventWithAllMembers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class DetailEventActivity extends AppCompatActivity {

    TextView eventName, dateAndTime, auditorium, lesson, teacher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventWithAllMembers eventWithAllMembers;
        eventWithAllMembers = (EventWithAllMembers) getIntent().getParcelableExtra("EventWithAllMembers");
        setTitle(eventWithAllMembers.eventName);
        setContentView(R.layout.activity_detail_event);
        eventName = findViewById(R.id.eventName);
        dateAndTime = findViewById(R.id.date_and_time);
        auditorium = findViewById(R.id.auditorium);
        lesson=findViewById(R.id.lesson);
        teacher=findViewById(R.id.teacher);

        DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        DateFormat df2=new SimpleDateFormat("HH:mm");
        eventName.setText(eventWithAllMembers.eventName);
        dateAndTime.setText(df.format(eventWithAllMembers.date)+"\n"+df2.format(eventWithAllMembers.startTime)+" - "+df2.format(eventWithAllMembers.endTime));
        auditorium.setText(eventWithAllMembers.auditorium.getAuditoriumName());
        lesson.setText(eventWithAllMembers.lesson.getName());
        teacher.setText(eventWithAllMembers.teacher.getEmail());
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}
