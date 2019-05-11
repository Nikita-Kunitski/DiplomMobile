package com.diplom.uedec.diplommobile.data.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import static android.arch.persistence.room.ForeignKey.CASCADE;
import static android.arch.persistence.room.ForeignKey.SET_NULL;

/**
 * Created by uedec on 04.05.2019.
 */

@Entity//(foreignKeys = @ForeignKey(entity = AuditoriumType.class,parentColumns = "Id", childColumns = "auditoriumtype_id",onDelete = SET_NULL))
public class Auditorium implements Parcelable {

    @SerializedName("Id")
    @Expose
    @PrimaryKey
    private int Id;

    @SerializedName("AuditoriumName")
    @Expose
    private String AuditoriumName;

    @SerializedName("AuditoriumCapacity")
    @Expose
    private int AuditoriumCapacity;
    @SerializedName("AuditoriumTypeId")
    @Expose
    //@ColumnInfo(name = "auditoriumtype_id")
    private int AuditoriumTypeId;

    @SerializedName("AuditoriumType")
    @Expose
    @Ignore
    private  AuditoriumType AuditoriumType;

    public AuditoriumType getAuditoriumType() {
        return AuditoriumType;
    }

    public void setAuditoriumType(AuditoriumType auditoriumType) {
        AuditoriumType = auditoriumType;
    }

    public Auditorium() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getAuditoriumName() {
        return AuditoriumName;
    }

    public void setAuditoriumName(String auditoriumName) {
        AuditoriumName = auditoriumName;
    }

    public int getAuditoriumCapacity() {
        return AuditoriumCapacity;
    }

    public void setAuditoriumCapacity(int auditoriumCapacity) {
        AuditoriumCapacity = auditoriumCapacity;
    }

    public int getAuditoriumTypeId() {
        return AuditoriumTypeId;
    }

    public void setAuditoriumTypeId(int auditoriumTypeId) {
        AuditoriumTypeId = auditoriumTypeId;
    }

    protected Auditorium(Parcel in) {
        Id = in.readInt();
        AuditoriumName = in.readString();
        AuditoriumCapacity = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(Id);
        dest.writeString(AuditoriumName);
        dest.writeInt(AuditoriumCapacity);
    }

    @SuppressWarnings("unused")
    @Ignore
    public static final Parcelable.Creator<Auditorium> CREATOR = new Parcelable.Creator<Auditorium>() {
        @Override
        public Auditorium createFromParcel(Parcel in) {
            return new Auditorium(in);
        }

        @Override
        public Auditorium[] newArray(int size) {
            return new Auditorium[size];
        }
    };
}