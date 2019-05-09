package com.diplom.uedec.diplommobile.data.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by uedec on 04.05.2019.
 */

@Entity
public class AuditoriumType {
    @SerializedName("id")
    @Expose
    @PrimaryKey(autoGenerate = true)
    private int Id;

    @SerializedName("auditoriumAbbreviation")
    @Expose
    private String AuditoriumAbbreviation;

    @SerializedName("auditoriumName")
    @Expose
    private String AuditoriumName;

    public AuditoriumType() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getAuditoriumAbbreviation() {
        return AuditoriumAbbreviation;
    }

    public void setAuditoriumAbbreviation(String auditoriumAbbreviation) {
        AuditoriumAbbreviation = auditoriumAbbreviation;
    }

    public String getAuditoriumName() {
        return AuditoriumName;
    }

    public void setAuditoriumName(String auditoriumName) {
        AuditoriumName = auditoriumName;
    }
}
