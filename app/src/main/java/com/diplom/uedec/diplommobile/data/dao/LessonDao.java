package com.diplom.uedec.diplommobile.data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.diplom.uedec.diplommobile.data.entity.Lesson;

import java.util.List;


@Dao
public interface LessonDao {
    @Query("Select * from lesson")
    List<Lesson> getAll();

    @Query("SELECT * FROM lesson WHERE Id = :id")
    Lesson getById(int id);

    @Insert
    void insert (Lesson lesson);

    @Update
    void update(Lesson lesson);

    @Delete
    void delete(Lesson lesson);

    @Query("Delete From lesson")
    void deleteAll();
}
