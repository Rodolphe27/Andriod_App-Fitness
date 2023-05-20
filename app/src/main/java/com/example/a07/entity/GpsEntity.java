package com.example.a07.entity;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="gps_table")
public class GpsEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private float longitude;

    private float latitude;

    public GpsEntity(float longitude, float latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getLongitude() {
        return longitude;
    }

    public float getLatitude() {
        return latitude;
    }
}
