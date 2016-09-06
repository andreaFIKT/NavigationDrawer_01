package com.example.navigationdrawer_01;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.cast.CastRemoteDisplayLocalService;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.text.SimpleDateFormat;
import java.util.Date;

public class InputFragment extends Fragment implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener{
    EditText todayDate;
    EditText entLiters;
    EditText entPrice;
    EditText entCost;
    Button btnSubmit;
    TextView lat;
    TextView lng;
    Button btnLocation;
    Context mContext;

    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 1000;
    //    protected LocationManager locationManager;
//    protected com.google.android.gms.location.LocationListener locationListener;
    protected boolean gps_enabled, network_enabled;
    Location location;

    double latitude;
    double longitude;
    GoogleApiClient mGoogleApiClient;
//    String lati, longi;

    // boolean flag to toggle periodic location updates
    private boolean mRequestingLocationUpdates = false;

    private LocationRequest mLocationRequest;
    private Location mLastLocation;


    public InputFragment() {  // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_input_data, container, false);

        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(5000);
        mLocationRequest.setFastestInterval(5000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        mLocationRequest.setSmallestDisplacement(10);

        mGoogleApiClient = new GoogleApiClient.Builder(InputFragment.this.getContext())
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
        mGoogleApiClient.connect();

        Log.d("GoogleAPIClient", " " + mGoogleApiClient);


        todayDate = (EditText) v.findViewById(R.id.date);
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = df.format(date);
        todayDate.setText(formattedDate);

        entLiters = (EditText) v.findViewById(R.id.entLiters);
        entPrice = (EditText) v.findViewById(R.id.entPrice);
        entCost = (EditText) v.findViewById(R.id.entCost);
        lat = (TextView) v.findViewById(R.id.latitude);
        lng = (TextView) v.findViewById(R.id.longitude);
        btnLocation = (Button) v.findViewById(R.id.btnGPS);

        btnLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLocationRequest = new LocationRequest();
                mLocationRequest.setInterval(5000);
                mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
                Log.d("GoogleAPIClient"," " + mGoogleApiClient);
                //LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
                mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
                Log.d("mLastLocation","loc"+mLastLocation);
                if (mLastLocation != null) {
                 double latitude = mLastLocation.getLatitude();
                 double longitude = mLastLocation.getLongitude();
                   lat.setText(String.valueOf(latitude));
                   lng.setText(String.valueOf(longitude));
                   }
                else {
                   Log.d("GPS", "Location not found");
                   }
            }
        });

        btnSubmit = (Button) v.findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Log.d("Fuel","Clicked Button");
                FuelAdd fuel = new FuelAdd();
                fuel.date = todayDate.getText().toString();
                fuel.liters = entLiters.getText().toString();
                fuel.price = entPrice.getText().toString();
                fuel.cost = entCost.getText().toString();
                fuel.save();
                Log.d("DATABASE", "Data saved" + fuel.save());
            }
        });

        return v;

    }

    @Override
    public void onConnected(Bundle bundle) {
        Toast.makeText(getContext(), "Connected", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConnectionSuspended(int i) {
        Toast.makeText(InputFragment.this.getContext(), "Disconnected. Please re-connect.",
                Toast.LENGTH_SHORT).show();
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.d("Failed", "Connection failed");
        mGoogleApiClient.connect();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mGoogleApiClient != null) {
            mGoogleApiClient.connect();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if(mGoogleApiClient.isConnected())
            mGoogleApiClient.disconnect();
    }

    @Override
    public void onResume() {
        super.onResume();

    }


   /* private void displayLocation() {

        if (ActivityCompat.checkSelfPermission(InputFragment.this.getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(InputFragment.this.getContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mLastLocation = LocationServices.FusedLocationApi
                .getLastLocation(mGoogleApiClient);

        if (mLastLocation != null) {
            double latitude = mLastLocation.getLatitude();
            double longitude = mLastLocation.getLongitude();

            lat.setText(String.valueOf(latitude));
            lng.setText(String.valueOf(longitude));

        } else {

            Log.d("Location not found","(Couldn't get the location. Make sure location is enabled on the device)");
        }
    }
    protected void startLocationUpdates() {

        if (ActivityCompat.checkSelfPermission(InputFragment.this.getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(InputFragment.this.getContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(
                mGoogleApiClient, mLocationRequest, (com.google.android.gms.location.LocationListener) this);

    }


    @Override
    public void onLocationChanged(Location location) {
        lat.setText(String.valueOf(location.getLatitude()));
        lng.setText(String.valueOf(location.getLongitude()));

    }*/
}



