package com.example.a07.entity;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="gps_table")
public class GpsEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String timeAndDateStamp;

    private float longitude;

    private float latitude;

    public GpsEntity(float longitude, float latitude, String timeAndDateStamp) {
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
    public float getLongitude() {
        return longitude;
    }

    public float getLatitude() {
        return latitude;
    }
}
