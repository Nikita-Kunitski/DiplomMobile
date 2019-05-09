package com.diplom.uedec.diplommobile.data.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import static android.arch.persistence.room.ForeignKey.CASCADE;
import static android.arch.persistence.room.ForeignKey.SET_NULL;

/**
 * Created by uedec on 04.05.2019.
 */

@Entity(foreignKeys = @ForeignKey(entity = AuditoriumType.class,parentColumns = "Id", childColumns = "auditoriumtype_id",onDelete = SET_NULL))
public class Auditorium {

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
    @ColumnInfo(name = "auditoriumtype_id")
    private int AuditoriumTypeId;

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
}
