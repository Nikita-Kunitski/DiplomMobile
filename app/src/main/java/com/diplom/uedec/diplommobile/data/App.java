package com.diplom.uedec.diplommobile.data;

import android.app.Application;
import android.arch.persistence.room.Room;

/**
 * Created by uedec on 05.05.2019.
 */

public class App extends Application {
    public static App instance;

    private AppDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        database = Room.databaseBuilder(this, AppDatabase.class, "database").allowMainThreadQueries()
                .build();
    }

    public static App getInstance() {
        return instance;
    }

    public AppDatabase getDatabase() {
        return database;
    }
}
