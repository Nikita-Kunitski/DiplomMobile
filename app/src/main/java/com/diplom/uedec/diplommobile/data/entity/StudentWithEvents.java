package com.diplom.uedec.diplommobile.data.entity;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import java.util.List;

/**
 * Created by uedec on 05.05.2019.
 */

public class StudentWithEvents {
    @Embedded
    ApplicationUser student;
    @Relation(parentColumn = "Id",entityColumn = "studentId")
    public List<StudentEvent> events;
}
