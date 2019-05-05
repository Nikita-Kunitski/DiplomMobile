package com.diplom.uedec.diplommobile.data.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

/**
 * Created by uedec on 04.05.2019.
 */

@Entity(primaryKeys = {"teacherId","lessonId"},
        foreignKeys = {
            @ForeignKey(entity = ApplicationUser.class,parentColumns = "Id",childColumns = "teacherId",onDelete = CASCADE),
            @ForeignKey(entity = Lesson.class,parentColumns = "Id",childColumns = "lessonId",onDelete = CASCADE)})
public class TeacherLesson {
    private final int teacherId;
    private final int lessonId;
    public TeacherLesson(int teacherId, int lessonId)
    {
        this.lessonId=lessonId;
        this.teacherId=teacherId;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public int getLessonId() {
        return lessonId;
    }
}
