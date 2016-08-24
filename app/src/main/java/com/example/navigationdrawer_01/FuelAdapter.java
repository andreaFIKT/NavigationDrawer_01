package com.example.navigationdrawer_01;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Inellipse10 on 24/08/2016.
 */
public class FuelAdapter extends ArrayAdapter<FuelAdd> {

    public FuelAdapter(Context context, int resource, List<FuelAdd> objects) {
        super(context, resource, objects);

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position

        FuelAdd fuel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.fuel_input_items_of_list, parent, false);
        }
        TextView dateToday = (TextView) convertView.findViewById(R.id.todayDateTextView);
        TextView enteredLiters = (TextView) convertView.findViewById(R.id.enteredLitersTextView);
        TextView enteredPrice = (TextView) convertView.findViewById(R.id.enteredPriceTextView);
        TextView enteredCost = (TextView) convertView.findViewById(R.id.enteredCost);

        // Populate fields with extracted properties
        dateToday.setText("Today Date Is :" + fuel.date);
        enteredLiters.setText("Entered Liters :" + fuel.liters);
        enteredPrice.setText("Entered Price :" + fuel.price);
        enteredCost.setText("Entered Cost :" + fuel.cost);


        return convertView;
    }
}
