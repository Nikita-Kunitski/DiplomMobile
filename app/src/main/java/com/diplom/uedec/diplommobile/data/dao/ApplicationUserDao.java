package com.diplom.uedec.diplommobile.data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.diplom.uedec.diplommobile.data.entity.ApplicationUser;
import com.diplom.uedec.diplommobile.data.entity.StudentWithEvents;
import com.diplom.uedec.diplommobile.data.entity.TeacherWithLessons;

import java.util.List;

@Dao
public interface ApplicationUserDao {
    @Query("SELECT * FROM ApplicationUser")
    List<ApplicationUser> getAll();

    @Query("SELECT * FROM ApplicationUser WHERE Id = :id")
    ApplicationUser getById(String id);

    @Insert
    void insert(ApplicationUser applicationUser);

    @Update
    void update(ApplicationUser applicationUser);

    @Delete
    void delete(ApplicationUser applicationUser);

    @Query("Select * from ApplicationUser")
    List<StudentWithEvents> getStudentWithEvents();

    @Query("Select * from ApplicationUser")
    List<TeacherWithLessons> getTeacherWithLessons();
}
