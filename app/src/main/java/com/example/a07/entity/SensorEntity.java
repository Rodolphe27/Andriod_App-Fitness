package com.example.a07.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "SensorEntity")
public class SensorEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String timeAndDateStamp;

    private double x;

    private double y;

    private double z;

    private String source;

    public SensorEntity() {
    }

    public SensorEntity(int id, String timeAndDateStamp, double x, double y, double z, String source) {
        this.id = id;
        this.timeAndDateStamp = timeAndDateStamp;
        this.x = x;
        this.y = y;
        this.z = z;
        this.source = source;
    }

    public int getId() {
        return id;
    }

    public String getTimeAndDateStamp() {
        return timeAndDateStamp;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public String getSource() {
        return source;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTimeAndDateStamp(String timeAndDateStamp) {
        this.timeAndDateStamp = timeAndDateStamp;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
