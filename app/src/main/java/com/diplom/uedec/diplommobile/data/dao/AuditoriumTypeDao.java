package com.diplom.uedec.diplommobile.data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.diplom.uedec.diplommobile.data.entity.AuditoriumType;

import java.util.List;

/**
 * Created by uedec on 04.05.2019.
 */

@Dao
public interface AuditoriumTypeDao {
    @Query("SELECT * FROM auditoriumtype")
    List<AuditoriumType> getAll();

    @Query("Select * from auditoriumtype")
    AuditoriumType getById(int id);

    @Insert
    void insert(AuditoriumType auditoriumType);

    @Update
    void update(AuditoriumType auditoriumType);

    @Delete
    void delete(AuditoriumType auditoriumType);
}
