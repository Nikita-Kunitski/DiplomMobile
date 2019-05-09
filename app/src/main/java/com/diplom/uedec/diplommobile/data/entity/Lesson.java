package com.diplom.uedec.diplommobile.data.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by uedec on 04.05.2019.
 */

@Entity
public class Lesson implements Parcelable {
    @PrimaryKey
    @SerializedName("Id")
    @Expose
    private int Id;

    @SerializedName("Abbreviation")
    @Expose
    private String Abbreviation;

    @SerializedName("Name")
    @Expose
    private String Name;

    @SerializedName("Course")
    @Expose
    private int Course;

    public Lesson() {
    }

    public Lesson(String abbreviation, String name) {
        Abbreviation = abbreviation;
        Name = name;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getAbbreviation() {
        return Abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        Abbreviation = abbreviation;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getCourse() {
        return Course;
    }

    public void setCourse(int course) {
        Course = course;
    }

    protected Lesson(Parcel in) {
        Id = in.readInt();
        Abbreviation = in.readString();
        Name = in.readString();
        Course = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(Id);
        dest.writeString(Abbreviation);
        dest.writeString(Name);
        dest.writeInt(Course);
    }

    @SuppressWarnings("unused")
    @Ignore
    public static final Parcelable.Creator<Lesson> CREATOR = new Parcelable.Creator<Lesson>() {
        @Override
        public Lesson createFromParcel(Parcel in) {
            return new Lesson(in);
        }

        @Override
        public Lesson[] newArray(int size) {
            return new Lesson[size];
        }
    };
}