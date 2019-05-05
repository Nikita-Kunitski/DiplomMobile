package com.diplom.uedec.diplommobile.data.entity;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import java.util.List;

/**
 * Created by uedec on 05.05.2019.
 */

public class TeacherWithLessons {
    @Embedded
    public ApplicationUser teacher;

    @Relation(parentColumn = "Id",entityColumn = "teacherId")
    public List<TeacherLesson> lessonList;
}
