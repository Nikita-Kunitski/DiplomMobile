package com.diplom.uedec.diplommobile.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.diplom.uedec.diplommobile.data.dao.AuditoriumTypeDao;
import com.diplom.uedec.diplommobile.data.entity.AuditoriumType;

/**
 * Created by uedec on 04.05.2019.
 */

@Database(entities = {AuditoriumType.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract AuditoriumTypeDao auditoriumTypeDao();
}
