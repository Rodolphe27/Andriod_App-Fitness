package com.example.a07;

import android.Manifest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

public class GPS extends AppCompatActivity {
    private TextView txtViewLong;
    private TextView txtViewLatit;
    private LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);

        txtViewLong= findViewById(R.id.txt_long);
        txtViewLatit= findViewById(R.id.txt_latit);
        locationManager=(LocationManager)getSystemService(LOCATION_SERVICE);

        if (ContextCompat.checkSelfPermission(GPS.this,Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(GPS.this,Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(GPS.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10, 1, new LocationListener() {
            @Override
            public void onLocationChanged( Location location) {
                txtViewLong.setText(String.valueOf(location.getLongitude()));
                txtViewLatit.setText(String.valueOf(location.getLatitude()));


            }
        });

    }
}