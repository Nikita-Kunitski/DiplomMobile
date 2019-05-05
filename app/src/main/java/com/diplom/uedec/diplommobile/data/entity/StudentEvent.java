package com.diplom.uedec.diplommobile.data.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

/**
 * Created by uedec on 05.05.2019.
 */

@Entity( primaryKeys = {"studentId","eventId"},foreignKeys =
        {
                @ForeignKey(entity = ApplicationUser.class,parentColumns = "Id",childColumns = "studentId", onDelete = CASCADE),
                @ForeignKey(entity = Event.class,parentColumns = "Id", childColumns = "eventId", onDelete = CASCADE)
        })
public class StudentEvent {
    private final int studentId;
    private final int eventId;
    public StudentEvent(int studentId, int eventId)
    {
        this.studentId=studentId;
        this.eventId=eventId;
    }

    public int getStudentId() {
        return studentId;
    }

    public int getEventId() {
        return eventId;
    }
}
