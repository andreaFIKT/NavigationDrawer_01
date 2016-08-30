package com.example.navigationdrawer_01;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class CostFragment extends Fragment {
    TextView etCategory;
    Spinner spinnerCategory;
    EditText tvTitle;
    EditText tvCost;
    TextView tvDate;
    DatePicker datePicker;
    Button buttonSaveCost;

    public CostFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.cost_fragment,container,false);
        etCategory = (TextView) v.findViewById(R.id.etCateg);
        spinnerCategory = (Spinner) v.findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.category, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinnerCategory.setAdapter(adapter);
        tvTitle = (EditText) v.findViewById(R.id.title);
        tvCost = (EditText) v.findViewById(R.id.editText2);
        tvDate =(TextView) v.findViewById(R.id.textView3);
        datePicker =  (DatePicker) v.findViewById(R.id.datePicker);
        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePicker.getDayOfMonth();
                datePicker.getMonth();
                datePicker.getYear();
            }
        });
        buttonSaveCost = (Button) v.findViewById(R.id.btnSaveCost);
        buttonSaveCost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Cost costInput = new Cost();
                costInput.date = datePicker.toString();
                costInput.category = spinnerCategory.toString();
                costInput.title = tvTitle.getText().toString();
                costInput.cost = tvCost.getText().toString();
                costInput.save();
                Log.d("Saved COST","number :"+ costInput.save());
            }
        });
        return  v;

    }


}
