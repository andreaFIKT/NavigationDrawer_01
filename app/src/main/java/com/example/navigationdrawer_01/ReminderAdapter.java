package com.example.navigationdrawer_01;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Inellipse10 on 07/09/2016.
 */
public class ReminderAdapter extends ArrayAdapter<ReminderModel> {

    public ReminderAdapter(Context context, int resource, List<ReminderModel> objects) {
        super(context, resource, objects);

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position

       ReminderModel remindMe = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.reminder_item_of_list, parent, false);
        }
        TextView title = (TextView) convertView.findViewById(R.id.txtTitle);
        ImageView alarm = (ImageView) convertView.findViewById(R.id.alarmImage);
        TextView date = (TextView) convertView.findViewById(R.id.txtDate);
        TextView odometer = (TextView) convertView.findViewById(R.id.txtOdometer);
        TextView note = (TextView) convertView.findViewById(R.id.txtNote);

        // Populate fields with extracted properties
        title.setText(remindMe.getReminderTitle());
        date.setText(remindMe.getReminderDate());
        odometer.setText(remindMe.getReminderOdometer());
        note.setText(remindMe.getReminderNote());


        return convertView;
    }

}
