package com.diplom.uedec.diplommobile.data.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by uedec on 04.05.2019.
 */

@Entity
public class AuditoriumType {
    @PrimaryKey
    private int Id;
    private String AuditoriumAbbreviation;
    private String AuditoriumName;

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
