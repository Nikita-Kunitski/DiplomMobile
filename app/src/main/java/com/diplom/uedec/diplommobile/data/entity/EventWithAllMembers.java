package com.diplom.uedec.diplommobile.data.entity;

import android.arch.persistence.room.Embedded;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class EventWithAllMembers implements Parcelable {
    @SerializedName("Id")
    @Expose
    public int id;

    @SerializedName("Date")
    @Expose
    public Date date;

    @SerializedName("StartTime")
    @Expose
    public Date startTime;

    @SerializedName("EndTime")
    @Expose
    public Date endTime;

    @SerializedName("CountPeople")
    @Expose
    public int countPeople;

    @SerializedName("LessonId")
    @Expose
    public int lessonId;

    @SerializedName("EventName")
    @Expose
    public String eventName;

    @SerializedName("AuditoriumId")
    @Expose
    public int auditoriumId;

    @SerializedName("TeacherId")
    @Expose
    public String teacheId;

    @SerializedName("Auditorium")
    @Expose
    @Embedded
    public Auditorium auditorium;

    @SerializedName("Teacher")
    @Expose
    @Embedded
    public ApplicationUser teacher;

    @SerializedName("Lesson")
    @Expose
    @Embedded
    public Lesson lesson;

    @SerializedName("StudentsNeeds")
    @Expose
    @Embedded
    public List<ApplicationUser> students;

    public EventWithAllMembers(int id, Date date, Date startTime, Date endTime, int countPeople, int lessonId, String eventName, int auditoriumId, String teacheId, Auditorium auditorium, ApplicationUser teacher, Lesson lesson) {
        this.id = id;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.countPeople = countPeople;
        this.lessonId = lessonId;
        this.eventName = eventName;
        this.auditoriumId = auditoriumId;
        this.teacheId = teacheId;
        this.auditorium = auditorium;
        this.teacher = teacher;
        this.lesson = lesson;
    }

    public EventWithAllMembers( Date date, Date startTime, Date endTime, int countPeople, int lessonId, String eventName, int auditoriumId, String teacheId, Auditorium auditorium, ApplicationUser teacher, Lesson lesson, List<ApplicationUser> students) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.countPeople = countPeople;
        this.lessonId = lessonId;
        this.eventName = eventName;
        this.auditoriumId = auditoriumId;
        this.teacheId = teacheId;
        this.auditorium = auditorium;
        this.teacher = teacher;
        this.lesson = lesson;
        this.students=students;
    }

    public EventWithAllMembers( Date date, Date startTime, Date endTime, int countPeople, int lessonId, String eventName, int auditoriumId, String teacheId, Auditorium auditorium, ApplicationUser teacher, Lesson lesson) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.countPeople = countPeople;
        this.lessonId = lessonId;
        this.eventName = eventName;
        this.auditoriumId = auditoriumId;
        this.teacheId = teacheId;
        this.auditorium = auditorium;
        this.teacher = teacher;
        this.lesson = lesson;
    }

    protected EventWithAllMembers(Parcel in) {
        id = in.readInt();
        long tmpDate = in.readLong();
        date = tmpDate != -1 ? new Date(tmpDate) : null;
        long tmpStartTime = in.readLong();
        startTime = tmpStartTime != -1 ? new Date(tmpStartTime) : null;
        long tmpEndTime = in.readLong();
        endTime = tmpEndTime != -1 ? new Date(tmpEndTime) : null;
        countPeople = in.readInt();
        lessonId = in.readInt();
        eventName = in.readString();
        auditoriumId = in.readInt();
        teacheId = in.readString();
        auditorium = (Auditorium) in.readValue(Auditorium.class.getClassLoader());
        teacher = (ApplicationUser) in.readValue(ApplicationUser.class.getClassLoader());
        lesson = (Lesson) in.readValue(Lesson.class.getClassLoader());
        if (in.readByte() == 0x01) {
            students = new ArrayList<>();
            in.readList(students, ApplicationUser.class.getClassLoader());
        } else {
            students = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeLong(date != null ? date.getTime() : -1L);
        dest.writeLong(startTime != null ? startTime.getTime() : -1L);
        dest.writeLong(endTime != null ? endTime.getTime() : -1L);
        dest.writeInt(countPeople);
        dest.writeInt(lessonId);
        dest.writeString(eventName);
        dest.writeInt(auditoriumId);
        dest.writeString(teacheId);
        dest.writeValue(auditorium);
        dest.writeValue(teacher);
        dest.writeValue(lesson);
        if (students == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(students);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<EventWithAllMembers> CREATOR = new Parcelable.Creator<EventWithAllMembers>() {
        @Override
        public EventWithAllMembers createFromParcel(Parcel in) {
            return new EventWithAllMembers(in);
        }

        @Override
        public EventWithAllMembers[] newArray(int size) {
            return new EventWithAllMembers[size];
        }
    };

    public boolean equals(EventWithAllMembers obj) {
        if((this.auditoriumId==obj.auditoriumId)&&
                (this.lessonId==obj.lessonId)&&
                (this.teacheId==obj.teacheId)&&
                (this.date.compareTo(obj.date)==0)&&
                (this.startTime.compareTo(obj.startTime)==0)&&
                (this.endTime.compareTo(obj.endTime)==0)
                )
            return true;
        return false;
    }

}