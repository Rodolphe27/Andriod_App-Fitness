package com.example.a07.entity;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="gps_table")
public class GpsEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String timeAndDateStamp;

    private double longitude;

    private double latitude;

    public GpsEntity(){

    }

    public GpsEntity(long longitude, long latitude, String timeAndDateStamp) {
        this.longitude = longitude;
        this.timeAndDateStamp = timeAndDateStamp;
        this.latitude = latitude;
    }
    public String getTimeAndDateStamp() {
        return timeAndDateStamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setTimeAndDateStamp(String timeAndDateStamp) {
        this.timeAndDateStamp = timeAndDateStamp;
    }
    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
