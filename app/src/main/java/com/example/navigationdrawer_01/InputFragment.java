package com.example.navigationdrawer_01;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;

public class InputFragment extends Fragment {
    EditText todayDate;
    EditText entLiters;
    EditText entPrice;
    EditText entCost;
    Button btnSubmit;

    public InputFragment() {  // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_input_data, container, false);
        todayDate =(EditText) v.findViewById(R.id.date);
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = df.format(date);
        todayDate.setText(formattedDate);

        entLiters = (EditText) v.findViewById(R.id.entLiters);
        entPrice = (EditText) v.findViewById(R.id.entPrice);
        entCost = (EditText) v.findViewById(R.id.entCost);
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
                Log.d("DATABASE","Data saved"+ fuel.save());
            }
        });
        return v;
    }


}
