package com.diplom.uedec.diplommobile.data.entity;

import android.arch.persistence.room.Embedded;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;



public class TeacherData implements Parcelable {
    @SerializedName("Auditoriums")
    @Expose
    @Embedded
    public List<Auditorium> auditoriums;
    @SerializedName("AuditoriumType")
    @Expose
    @Embedded
    public List<AuditoriumType> auditoriumTypes;
    @SerializedName("Lessons")
    @Expose
    @Embedded
    public List<Lesson> lessons;
    @SerializedName("Students")
    @Expose
    @Embedded
    public List<ApplicationUser> students;

    protected TeacherData(Parcel in) {
        if (in.readByte() == 0x01) {
            auditoriums = new ArrayList<>();
            in.readList(auditoriums, Auditorium.class.getClassLoader());
        } else {
            auditoriums = null;
        }
        if (in.readByte() == 0x01) {
            auditoriumTypes = new ArrayList<>();
            in.readList(auditoriumTypes, AuditoriumType.class.getClassLoader());
        } else {
            auditoriumTypes = null;
        }
        if (in.readByte() == 0x01) {
            lessons = new ArrayList<>();
            in.readList(lessons, Lesson.class.getClassLoader());
        } else {
            lessons = null;
        }
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
        if (auditoriums == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(auditoriums);
        }
        if (auditoriumTypes == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(auditoriumTypes);
        }
        if (lessons == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(lessons);
        }
        if (students == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(students);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<TeacherData> CREATOR = new Parcelable.Creator<TeacherData>() {
        @Override
        public TeacherData createFromParcel(Parcel in) {
            return new TeacherData(in);
        }

        @Override
        public TeacherData[] newArray(int size) {
            return new TeacherData[size];
        }
    };
}