package com.diplom.uedec.diplommobile.data.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import java.sql.Date;

import static android.arch.persistence.room.ForeignKey.SET_NULL;

/**
 * Created by uedec on 05.05.2019.
 */
@Entity(foreignKeys = {
        @ForeignKey(entity = Lesson.class,parentColumns = "Id", childColumns = "lesson_id", onDelete = SET_NULL),
        @ForeignKey(entity = Auditorium.class,parentColumns = "Id", childColumns = "auditorium_id",onDelete = SET_NULL),
        @ForeignKey(entity = ApplicationUser.class, parentColumns = "Id", childColumns = "teacher_id",onDelete = SET_NULL)
})
public class Event {
    @PrimaryKey
    private int Id;
    private String Date;
    private String StartTime;
    private String EndTime;
    private int CountPeople;
    @ColumnInfo(name = "lesson_id")
    private int LessonId;
    private String EventName;
    @ColumnInfo(name = "auditorium_id")
    private int AuditoriumId;
    @ColumnInfo(name="teacher_id")
    private String TeacherId;

    public Event() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(String startTime) {
        StartTime = startTime;
    }

    public String getEndTime() {
        return EndTime;
    }

    public void setEndTime(String endTime) {
        EndTime = endTime;
    }

    public int getCountPeople() {
        return CountPeople;
    }

    public void setCountPeople(int countPeople) {
        CountPeople = countPeople;
    }

    public int getLessonId() {
        return LessonId;
    }

    public void setLessonId(int lessonId) {
        LessonId = lessonId;
    }

    public String getEventName() {
        return EventName;
    }

    public void setEventName(String eventName) {
        EventName = eventName;
    }

    public int getAuditoriumId() {
        return AuditoriumId;
    }

    public void setAuditoriumId(int auditoriumId) {
        AuditoriumId = auditoriumId;
    }

    public String getTeacherId() {
        return TeacherId;
    }

    public void setTeacherId(String teacherId) {
        TeacherId = teacherId;
    }



}
