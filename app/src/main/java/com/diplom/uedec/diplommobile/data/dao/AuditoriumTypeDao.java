package com.diplom.uedec.diplommobile.data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.diplom.uedec.diplommobile.data.entity.AuditoriumType;

import java.util.List;


@Dao
public interface AuditoriumTypeDao {
    @Query("SELECT * FROM auditoriumtype")
    List<AuditoriumType> getAll();

    @Query("Select * from auditoriumtype WHERE Id = :id")
    AuditoriumType getById(int id);

    @Insert
    void insert(AuditoriumType auditoriumType);

    @Update
    void update(AuditoriumType auditoriumType);

    @Delete
    void delete(AuditoriumType auditoriumType);

    @Query("Delete From auditoriumtype")
    void deleteAll();
}
