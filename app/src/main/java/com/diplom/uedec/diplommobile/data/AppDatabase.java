package com.diplom.uedec.diplommobile.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.diplom.uedec.diplommobile.data.dao.ApplicationUserDao;
import com.diplom.uedec.diplommobile.data.dao.AuditoriumDao;
import com.diplom.uedec.diplommobile.data.dao.AuditoriumTypeDao;
import com.diplom.uedec.diplommobile.data.dao.EventDao;
import com.diplom.uedec.diplommobile.data.dao.LessonDao;
import com.diplom.uedec.diplommobile.data.dao.StudentEventDao;
import com.diplom.uedec.diplommobile.data.dao.TeacherLessonDao;
import com.diplom.uedec.diplommobile.data.entity.ApplicationUser;
import com.diplom.uedec.diplommobile.data.entity.Auditorium;
import com.diplom.uedec.diplommobile.data.entity.AuditoriumType;
import com.diplom.uedec.diplommobile.data.entity.Event;
import com.diplom.uedec.diplommobile.data.entity.Lesson;
import com.diplom.uedec.diplommobile.data.entity.StudentEvent;
import com.diplom.uedec.diplommobile.data.entity.TeacherLesson;

/**
 * Created by uedec on 04.05.2019.
 */

@Database(entities = {AuditoriumType.class, Auditorium.class, Lesson.class, ApplicationUser.class, Event.class, StudentEvent.class, TeacherLesson.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract AuditoriumTypeDao auditoriumTypeDao();
    public abstract AuditoriumDao auditoriumDao();
    public abstract LessonDao lessonDao();
    public abstract ApplicationUserDao applicationUserDao();
    public abstract EventDao eventDao();
    public abstract StudentEventDao studentEventDao();
    public abstract TeacherLessonDao teacherLessonDao();
}
