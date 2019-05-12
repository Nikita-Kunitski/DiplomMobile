package com.diplom.uedec.diplommobile.data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.diplom.uedec.diplommobile.data.entity.Auditorium;

import java.util.List;


@Dao
public interface AuditoriumDao {
    @Query("SELECT * FROM auditorium")
    List<Auditorium> getAll();

    @Query("SELECT * FROM auditorium WHERE Id = :id")
    Auditorium getById(int id);

    @Insert
    void insert (Auditorium auditorium);

    @Update
    void update(Auditorium auditorium);

    @Delete
    void delete(Auditorium auditorium);

    @Query("Delete From auditorium")
    void deleteAll();
}
