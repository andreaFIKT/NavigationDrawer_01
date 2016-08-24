package com.example.navigationdrawer_01;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MyCarFragment extends Fragment {
    TextView tInfo;
    TextView textManu;
    TextView textModel;
    TextView textYear;
    EditText tManu;
    EditText tModel;
    EditText tYear;
    Button edit;
    Button save;
    SharedPreferences sharedpreferences;


    public MyCarFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.my_car_fragment, container, false);
        tInfo = (TextView) v.findViewById(R.id.info);
        textManu =(TextView) v.findViewById(R.id.entManufacturer);
        tManu = (EditText) v.findViewById(R.id.manufacturer);
        textModel = (TextView) v.findViewById(R.id.entModel);
        tModel =(EditText) v.findViewById(R.id.model);
        textYear =(TextView) v.findViewById(R.id.entYear);
        tYear =(EditText) v.findViewById(R.id.year);
        edit = (Button) v.findViewById(R.id.btnEdit);
        save = (Button) v.findViewById(R.id.btnSave);
        final String prefs = "MyPrefs" ;
        final String Manufacturer = "Manufacturer";
        final String Model = "Model";
        final String Year = "Year";

        sharedpreferences = getContext().getSharedPreferences(prefs,0);
        tManu.setText(sharedpreferences.getString(Manufacturer,""));
        tModel.setText(sharedpreferences.getString(Model,""));
        tYear.setText(sharedpreferences.getString(Year,""));
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String manufacturer =tManu.getText().toString();
                String model = tModel.getText().toString();
                String year = tYear.getText().toString();
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString(Manufacturer,manufacturer);
                editor.putString(Model,model);
                editor.putString(Year,year);
                editor.commit();

            }
        });

        return  v;
    }


}
