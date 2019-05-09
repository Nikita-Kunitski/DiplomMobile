package com.diplom.uedec.diplommobile.data.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by uedec on 04.05.2019.
 */

@Entity
public class Lesson {
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
}
