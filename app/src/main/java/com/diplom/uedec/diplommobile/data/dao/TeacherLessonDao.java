package com.diplom.uedec.diplommobile.data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.diplom.uedec.diplommobile.data.entity.TeacherLesson;

import java.util.List;

@Dao
public interface TeacherLessonDao {
    @Query("SELECT * FROM TeacherLesson")
    List<TeacherLesson> getAll();

    @Query("SELECT * FROM TeacherLesson WHERE teacherId = :teacherId")
    List<TeacherLesson>getByTeacherId(String teacherId);

    @Query("SELECT * FROM TeacherLesson WHERE lessonId = :lessonId")
    List<TeacherLesson>getByLessonId(int lessonId);

    @Insert
    void insert(TeacherLesson teacherLesson);
}
