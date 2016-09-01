package com.example.navigationdrawer_01;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.Service;
import android.widget.TextView;

import com.google.android.gms.location.LocationListener;

public class InputFragment extends Fragment {
    EditText todayDate;
    EditText entLiters;
    EditText entPrice;
    EditText entCost;
    Button btnSubmit;
    Button btnLoc;
    TextView lat;
    TextView lng;
    FuelAdd fuel;

    public InputFragment() {  // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_input_data, container, false);
        ActivityCompat.requestPermissions(
                getActivity(),
                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                0);
        todayDate =(EditText) v.findViewById(R.id.date);
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = df.format(date);
        todayDate.setText(formattedDate);

        entLiters = (EditText) v.findViewById(R.id.entLiters);
        entPrice = (EditText) v.findViewById(R.id.entPrice);
        entCost = (EditText) v.findViewById(R.id.entCost);
        btnSubmit = (Button) v.findViewById(R.id.btnSubmit);
        lat = (TextView) v.findViewById(R.id.latitude);
        lng = (TextView) v.findViewById(R.id.longitude);
        btnLoc =(Button) v.findViewById(R.id.btnGPS);
        btnLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("btnGPS", "CLicked");
                Location location;
                LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
                LocationListener locationListener = new MyLocationListenner();
                location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                double latit = location.getLatitude();
                double longit = location.getLongitude();
                locationListener.onLocationChanged(location);
               // Log.d("LOCATION","Latitude "+location.getLatitude() + "Longitude " +location.getLongitude());
                String lt = String.valueOf(latit);
                String ln = String.valueOf(longit);
                lat.setText(lt);
                lng.setText(ln);

                fuel = new FuelAdd(latit,longit);
                fuel.save();

                //List<FuelAdd> locations = new ArrayList<FuelAdd>();
                //locations.add(fuel);
                Log.d("LocationSaved","Latitude and longitude are stored");

            }
        });
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Log.d("Fuel","Clicked Button");
               // List<FuelAdd> fuels = new ArrayList<FuelAdd>();
                fuel = new FuelAdd();
                fuel.date = todayDate.getText().toString();
                fuel.liters = entLiters.getText().toString();
                fuel.price = entPrice.getText().toString();
                fuel.cost = entCost.getText().toString();
                //fuel.latitude = Double.valueOf((String) lat.getText());
                //fuel.longitude = Double.valueOf((String) lng.getText());
                fuel.save();
                //fuels.add(fuel);
                Log.d("DATABASE","Data saved"+ fuel.save());
            }
        });
        return v;
    }


}
