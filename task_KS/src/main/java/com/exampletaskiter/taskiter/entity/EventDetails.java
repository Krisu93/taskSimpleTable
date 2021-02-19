package com.exampletaskiter.taskiter.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.relational.core.mapping.Column;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table
public class EventDetails {



    @javax.persistence.Id

    @Column
    private Timestamp dateEvt;
    @Column
    private Float value;
    @Column
    private String units;
    @Column
    private String quality;


    public Timestamp getDateEvt() {
        return dateEvt;
    }

    public void setDateEvt(Timestamp dateEvt) {
        this.dateEvt = dateEvt;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }
}
