package com.diplom.uedec.diplommobile.data.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(primaryKeys = {"teacherId","lessonId"},
        foreignKeys = {
            @ForeignKey(entity = ApplicationUser.class,parentColumns = "Id",childColumns = "teacherId",onDelete = CASCADE),
            @ForeignKey(entity = Lesson.class,parentColumns = "Id",childColumns = "lessonId",onDelete = CASCADE)})

public class TeacherLesson implements Parcelable {
    @SerializedName("ApplicationUserId")
    @Expose
    @NonNull
    private final String teacherId;
    @SerializedName("LessonId")
    @Expose
    @NonNull
    private final int lessonId;
    public TeacherLesson(String teacherId, int lessonId)
    {
        this.lessonId=lessonId;
        this.teacherId=teacherId;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public int getLessonId() {
        return lessonId;
    }

    protected TeacherLesson(Parcel in) {
        teacherId = in.readString();
        lessonId = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(teacherId);
        dest.writeInt(lessonId);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<TeacherLesson> CREATOR = new Parcelable.Creator<TeacherLesson>() {
        @Override
        public TeacherLesson createFromParcel(Parcel in) {
            return new TeacherLesson(in);
        }

        @Override
        public TeacherLesson[] newArray(int size) {
            return new TeacherLesson[size];
        }
    };
}