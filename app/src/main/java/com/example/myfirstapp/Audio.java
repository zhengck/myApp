package com.example.myfirstapp;

import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.util.Log;

public class Audio extends AppCompatActivity {

    LocationManager locationManager;
    LocationListener llistener;
    String provider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);

        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setCostAllowed(true);
        criteria.setPowerRequirement(Criteria.POWER_LOW);
        String serviceName = Context.LOCATION_SERVICE;
        locationManager = (LocationManager) getSystemService(serviceName);
        locationManager.setTestProviderEnabled("gps", true);
        provider = locationManager.getBestProvider(criteria, true);
        Log.d("provider", provider);

        llistener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location)
            {
                // TODO Auto-generated method stub
                Log.i("onLocationChanged", "come in");
                if (location != null)
                {
                    Log.w("Location", "Current altitude = "
                            + location.getAltitude());
                    Log.w("Location", "Current latitude = "
                            + location.getLatitude());
                }
                locationManager.removeUpdates(this);
                locationManager.setTestProviderEnabled(provider, false);
            }

            @Override
            public void onProviderDisabled(String provider)
            {
                // TODO Auto-generated method stub
                Log.i("onProviderDisabled", "come in");

            }

            @Override
            public void onProviderEnabled(String provider)
            {
                // TODO Auto-generated method stub
                Log.i("onProviderEnabled", "come in");
            }

            @Override
            public void onStatusChanged(String provider, int status,
                                        Bundle extras)
            {
                // TODO Auto-generated method stub
                Log.i("onStatusChanged", "come in");

            }

        };
        locationManager.requestLocationUpdates(provider, 1000, (float) 1000.0, llistener);
    }

    protected void onDestroy()
    {
        locationManager.removeUpdates(llistener);
        locationManager.setTestProviderEnabled(provider, false);
        super.onDestroy();
    }
