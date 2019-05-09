package com.diplom.uedec.diplommobile.data.entity;

import android.app.FragmentTransaction;
import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static android.arch.persistence.room.ForeignKey.SET_NULL;


@Entity(foreignKeys = {
        @ForeignKey(entity = Lesson.class,parentColumns = "Id", childColumns = "lesson_id", onDelete = SET_NULL),
        @ForeignKey(entity = Auditorium.class,parentColumns = "Id", childColumns = "auditorium_id",onDelete = SET_NULL),
        @ForeignKey(entity = ApplicationUser.class, parentColumns = "Id", childColumns = "teacher_id",onDelete = SET_NULL)
})
public class Event {

    @SerializedName("Id")
    @Expose
    @PrimaryKey
    private int Id;


    private String Date;

    private String StartTime;

    private String EndTime;

    @SerializedName("CountPeople")
    @Expose
    private int CountPeople;

    @SerializedName("LessonId")
    @Expose
    @ColumnInfo(name = "lesson_id")
    private int LessonId;

    @SerializedName("EventName")
    @Expose
    private String EventName;

    @SerializedName("AuditoriumId")
    @Expose
    @ColumnInfo(name = "auditorium_id")
    private int AuditoriumId;

    @SerializedName("TeacherId")
    @Expose
    @ColumnInfo(name="teacher_id")
    private String TeacherId;
    @SerializedName("Date")
    @Expose
    @Ignore
    private Date dateD;
    @SerializedName("StartTime")
    @Expose
    @Ignore
    private Date startTimeD;
    @SerializedName("EndTime")
    @Expose
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
        if(checkDate==null)
            return;
        if(!checkDate.equals(dateD))
            dateD=checkDate;
    }

    public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(String startTime) {
        StartTime = startTime;
        Date checkDate =FromStringToDate(startTime,"HH:mm:ss");
        if(checkDate==null)
            return;
        if(!checkDate.equals(startTimeD))
            startTimeD=checkDate;
    }

    public String getEndTime() {
        return EndTime;
    }

    public void setEndTime(String endTime) {
        EndTime = endTime;
        Date checkDate= FromStringToDate(endTime,"HH:mm:ss");
        if(checkDate==null)
            return;
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

    public String getTeacherId() {
        return TeacherId;
    }

    public void setTeacherId(String teacherId) {
        TeacherId = teacherId;
    }

    public Date getDateD() {
        return dateD;
    }

    public void setDateD(Date dateD) {
        this.dateD = dateD;
        String checkOldDate=FromDateToString(dateD,"yyyy-MM-dd");
        if(checkOldDate==null)
            return;
        if(!checkOldDate.equals(Date))
            Date=checkOldDate;
    }

    public Date getStartTimeD() {
        return startTimeD;
    }

    public void setStartTimeD(Date startTimeD) {
        this.startTimeD = startTimeD;
        String checkOldDate=FromDateToString(startTimeD,"HH:mm:ss");
        if(checkOldDate==null)
            return;
        if(!checkOldDate.equals(StartTime))
            StartTime=checkOldDate;
    }

    public Date getEndTimeD() {
        return endTimeD;
    }

    public void setEndTimeD(Date endTimeD) {
        this.endTimeD = endTimeD;
        String checkOldDate=FromDateToString(endTimeD,"HH:mm:ss");
        if(checkOldDate==null)
            return;
        if(!checkOldDate.equals(EndTime))
            EndTime=checkOldDate;
    }

    private String FromDateToString(Date date, String format){
        DateFormat df=new SimpleDateFormat(format);
        return df.format(date);
    }

    private Date FromStringToDate(String date, String format)
    {
        DateFormat df=new SimpleDateFormat(format);
        if(date==null)
            return null;
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
