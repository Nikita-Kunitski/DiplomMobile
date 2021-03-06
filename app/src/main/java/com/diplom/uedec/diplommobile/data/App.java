package com.diplom.uedec.diplommobile.data;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.os.AsyncTask;

import com.diplom.uedec.diplommobile.data.entity.ApplicationUser;
import com.diplom.uedec.diplommobile.data.entity.Lesson;

import java.util.List;

import okhttp3.Headers;


/**
 * Created by uedec on 05.05.2019.
 */

public class App extends Application {

    class CreateDataBase extends AsyncTask<RoomDatabase.Builder<AppDatabase>,Void,AppDatabase>{
        @Override
        protected AppDatabase doInBackground(RoomDatabase.Builder<AppDatabase>[] builders) {
            AppDatabase db=builders[0].build();
            database=db;
            return db;
        }
        @Override
        protected void onPostExecute(AppDatabase db)
        {

        }
    }
//TODO переработать принцип сохранения данных SHARED PREFERENCEs
    public static App instance;
    public static String role;
    public static String token;
    public static ApplicationUser user;
    public static List<Lesson> lessons;
    public static final String APP_PREFERENCES = "mysettings";
    public static String ROLE="ROLE";
    public static String USER="USER";
    public static String TOKEN="TOKEN";
    private AppDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        new CreateDataBase().execute(Room.databaseBuilder(instance, AppDatabase.class, "database"));
    }

    public static App getInstance() {
        return instance;
    }

    public AppDatabase getDatabase() {
        return database;
    }
}

