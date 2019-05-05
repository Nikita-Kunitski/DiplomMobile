package com.diplom.uedec.diplommobile.data.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.List;

/**
 * Created by uedec on 04.05.2019.
 */

@Entity
    public class ApplicationUser {
       @NonNull
       @PrimaryKey
        private String Id;
        private String TeacherNumber;
        private String StudentNumber;
        private String FirstName;
        private String LastName;
        private String Patronymic;
        private int Group;
        private int Course;

    public ApplicationUser() {
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getTeacherNumber() {
        return TeacherNumber;
    }

    public void setTeacherNumber(String teacherNumber) {
        TeacherNumber = teacherNumber;
    }

    public String getStudentNumber() {
        return StudentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        StudentNumber = studentNumber;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getPatronymic() {
        return Patronymic;
    }

    public void setPatronymic(String patronymic) {
        Patronymic = patronymic;
    }

    public int getGroup() {
        return Group;
    }

    public void setGroup(int group) {
        Group = group;
    }

    public int getCourse() {
        return Course;
    }

    public void setCourse(int course) {
        Course = course;
    }
}
