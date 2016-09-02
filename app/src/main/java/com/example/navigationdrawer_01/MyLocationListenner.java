package com.example.navigationdrawer_01;

import android.location.Location;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.location.LocationListener;

/**
 * Created by Inellipse10 on 02/09/2016.
 */
public class MyLocationListenner implements LocationListener {
    @Override
    public void onLocationChanged(Location location) {
        if(location!=null)
        {
            Log.d("Latitude","= " + location.getLatitude());
            Log.d("Longitude","= " + location.getLongitude());

        }

    }

    /*@Override
    public  void onProviderEnabled(String provider){


    }
    @Override
    public  void onProviderDisabled(String provider){

    }
     @Override
    public void onStatusChanged(String provider, int status, Bundle extras){

     }*/

}
