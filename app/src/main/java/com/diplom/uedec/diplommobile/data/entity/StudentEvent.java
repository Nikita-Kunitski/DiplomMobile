package com.diplom.uedec.diplommobile.data.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.support.annotation.NonNull;

import static android.arch.persistence.room.ForeignKey.CASCADE;


@Entity( primaryKeys = {"studentId","eventId"},foreignKeys =
        {
                @ForeignKey(entity = ApplicationUser.class,parentColumns = "Id",childColumns = "studentId", onDelete = CASCADE),
                @ForeignKey(entity = Event.class,parentColumns = "Id", childColumns = "eventId", onDelete = CASCADE)
        })
public class StudentEvent {
    @NonNull
    private final String studentId;
    private final int eventId;
    public StudentEvent(String studentId, int eventId)
    {
        this.studentId=studentId;
        this.eventId=eventId;
    }

    public String getStudentId() {
        return studentId;
    }

    public int getEventId() {
        return eventId;
    }
}
