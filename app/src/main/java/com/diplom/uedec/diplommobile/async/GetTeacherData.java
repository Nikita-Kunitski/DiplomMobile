package com.diplom.uedec.diplommobile.async;

import android.os.AsyncTask;

import com.diplom.uedec.diplommobile.R;
import com.diplom.uedec.diplommobile.data.App;
import com.diplom.uedec.diplommobile.data.AppDatabase;
import com.diplom.uedec.diplommobile.data.dao.AuditoriumDao;
import com.diplom.uedec.diplommobile.data.dao.AuditoriumTypeDao;
import com.diplom.uedec.diplommobile.data.dao.LessonDao;
import com.diplom.uedec.diplommobile.data.entity.Auditorium;
import com.diplom.uedec.diplommobile.data.entity.AuditoriumType;
import com.diplom.uedec.diplommobile.data.entity.Lesson;
import com.diplom.uedec.diplommobile.data.entity.TeacherData;
import com.diplom.uedec.diplommobile.retrofit.REST;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by uedec on 12.05.2019.
 */

public class GetTeacherData extends AsyncTask<TeacherData,Void,Void> {
    @Override
    protected Void doInBackground(TeacherData... teacherData) {
        AppDatabase db = App.getInstance().getDatabase();
        AuditoriumTypeDao auditoriumTypeDao=db.auditoriumTypeDao();
        AuditoriumDao auditoriumDao=db.auditoriumDao();
        LessonDao lessonDao=db.lessonDao();

        auditoriumTypeDao.deleteAll();
        auditoriumDao.deleteAll();
        lessonDao.deleteAll();

        for (AuditoriumType item: teacherData[0].auditoriumTypes
             ) {
                auditoriumTypeDao.insert(item);
        }

        for (Auditorium item: teacherData[0].auditoriums
                ) {
                auditoriumDao.insert(item);
        }

        for (Lesson item: teacherData[0].lessons
                ) {
                lessonDao.insert(item);
        }
        return null;
    }
}
