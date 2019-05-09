package com.diplom.uedec.diplommobile.data.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity
public class ApplicationUser implements Parcelable {
    @NonNull
    @PrimaryKey
    @SerializedName("Id")
    @Expose
    private String Id;

    @SerializedName("Email")
    @Expose
    private  String Email;

    @SerializedName("TeacherNumber")
    @Expose
    private String TeacherNumber;

    @SerializedName("StudentNumber")
    @Expose
    private String StudentNumber;

    @SerializedName("FirstName")
    @Expose
    private String FirstName;

    @SerializedName("LastName")
    @Expose
    private String LastName;

    @SerializedName("Patronymic")
    @Expose
    private String Patronymic;

    @SerializedName("Group")
    @Expose
    private int Group;

    @SerializedName("Course")
    @Expose
    private int Course;

    public ApplicationUser(  String firstName, String lastName, String patronymic, int group, int course) {
        FirstName = firstName;
        LastName = lastName;
        Patronymic = patronymic;
        Group = group;
        Course = course;
    }

    public ApplicationUser(String email) {
        Email = email;
    }

    public ApplicationUser() {
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
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

    protected ApplicationUser(Parcel in) {
        Id = in.readString();
        Email = in.readString();
        TeacherNumber = in.readString();
        StudentNumber = in.readString();
        FirstName = in.readString();
        LastName = in.readString();
        Patronymic = in.readString();
        Group = in.readInt();
        Course = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Id);
        dest.writeString(Email);
        dest.writeString(TeacherNumber);
        dest.writeString(StudentNumber);
        dest.writeString(FirstName);
        dest.writeString(LastName);
        dest.writeString(Patronymic);
        dest.writeInt(Group);
        dest.writeInt(Course);
    }

    @SuppressWarnings("unused")
    @Ignore
    public static final Parcelable.Creator<ApplicationUser> CREATOR = new Parcelable.Creator<ApplicationUser>() {
        @Override
        public ApplicationUser createFromParcel(Parcel in) {
            return new ApplicationUser(in);
        }

        @Override
        public ApplicationUser[] newArray(int size) {
            return new ApplicationUser[size];
        }
    };
}