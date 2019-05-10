package com.diplom.uedec.diplommobile.async;

import android.os.AsyncTask;

import com.diplom.uedec.diplommobile.data.App;
import com.diplom.uedec.diplommobile.data.AppDatabase;
import com.diplom.uedec.diplommobile.data.dao.ApplicationUserDao;
import com.diplom.uedec.diplommobile.data.dao.AuditoriumDao;
import com.diplom.uedec.diplommobile.data.dao.EventDao;
import com.diplom.uedec.diplommobile.data.dao.LessonDao;
import com.diplom.uedec.diplommobile.data.dao.StudentEventDao;
import com.diplom.uedec.diplommobile.data.entity.ApplicationUser;
import com.diplom.uedec.diplommobile.data.entity.Auditorium;
import com.diplom.uedec.diplommobile.data.entity.Event;
import com.diplom.uedec.diplommobile.data.entity.EventWithAllMembers;
import com.diplom.uedec.diplommobile.data.entity.Lesson;
import com.diplom.uedec.diplommobile.data.entity.StudentEvent;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by uedec on 10.05.2019.
 */

public class DeleteFromDataBase extends AsyncTask<EventWithAllMembers,Void,Void> {
    @Override
    protected Void doInBackground(EventWithAllMembers... eventWithAllMembers) {
        AppDatabase db= App.getInstance().getDatabase();
        ApplicationUserDao applicationUserDao=db.applicationUserDao();
        AuditoriumDao auditoriumDao=db.auditoriumDao();
        LessonDao lessonDao=db.lessonDao();
        EventDao eventDao=db.eventDao();
        StudentEventDao studentEventDao=db.studentEventDao();

        DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        DateFormat df2=new SimpleDateFormat("HH:mm");
        ApplicationUser teacher=eventWithAllMembers[0].teacher;
        Lesson lesson=eventWithAllMembers[0].lesson;
        Auditorium auditorium=eventWithAllMembers[0].auditorium;
        Event event=new Event(eventWithAllMembers[0].id,
                df.format(eventWithAllMembers[0].date),
                df2.format(eventWithAllMembers[0].startTime),
                df2.format(eventWithAllMembers[0].endTime),
                eventWithAllMembers[0].countPeople,
                eventWithAllMembers[0].lessonId,
                eventWithAllMembers[0].eventName,
                eventWithAllMembers[0].teacheId,
                eventWithAllMembers[0].auditoriumId);
        StudentEvent studentEvent=new StudentEvent(App.user.getId(),event.getId());
        studentEventDao.delete(studentEvent);
        eventDao.delete(event);
        lessonDao.delete(lesson);
        auditoriumDao.delete(auditorium);
        applicationUserDao.delete(teacher);
        return null;
    }
}
