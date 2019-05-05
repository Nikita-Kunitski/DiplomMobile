package com.diplom.uedec.diplommobile.data.entity;

import android.app.FragmentTransaction;
import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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
    private int TeacherId;
    @Ignore
    private Date dateD;
    @Ignore
    private Date startTimeD;
    @Ignore
    private Date endTimeD;

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
        Date checkDate =FromStringToDate(Date,"yyyy-MM-dd");
        if(!checkDate.equals(dateD))
            dateD=checkDate;
    }

    public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(String startTime) {
        StartTime = startTime;
        Date checkDate =FromStringToDate(startTime,"HH:mm:ss");
        if(!checkDate.equals(startTimeD))
            startTimeD=checkDate;
    }

    public String getEndTime() {
        return EndTime;
    }

    public void setEndTime(String endTime) {
        EndTime = endTime;
        Date checkDate= FromStringToDate(endTime,"HH:mm:ss");
        if(!checkDate.equals(endTimeD))
            endTimeD=checkDate;
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

    public int getTeacherId() {
        return TeacherId;
    }

    public void setTeacherId(int teacherId) {
        TeacherId = teacherId;
    }

    public Date getDateD() {
        return dateD;
    }

    public void setDateD(Date dateD) {
        this.dateD = dateD;
        String checkOldDate=FromDateToString(dateD,"yyyy-MM-dd");
        if(!checkOldDate.equals(Date))
            Date=checkOldDate;
    }

    public Date getStartTimeD() {
        return startTimeD;
    }

    public void setStartTimeD(Date startTimeD) {
        this.startTimeD = startTimeD;
        String checkOldDate=FromDateToString(startTimeD,"HH:mm:ss");
        if(!checkOldDate.equals(StartTime))
            StartTime=checkOldDate;
    }

    public Date getEndTimeD() {
        return endTimeD;
    }

    public void setEndTimeD(Date endTimeD) {
        this.endTimeD = endTimeD;
        String checkOldDate=FromDateToString(endTimeD,"HH:mm:ss");
        if(!checkOldDate.equals(EndTime))
            EndTime=checkOldDate;
    }

    public String FromDateToString(Date date, String format){
        DateFormat df=new SimpleDateFormat(format);
        return df.format(date);
    }

    public Date FromStringToDate(String date, String format)
    {
        DateFormat df=new SimpleDateFormat(format);
        Date dateReturn;
        try {
            dateReturn=df.parse(date);
        } catch (ParseException e) {
            dateReturn=null;
            e.printStackTrace();
        }
        return dateReturn;
    }


}
