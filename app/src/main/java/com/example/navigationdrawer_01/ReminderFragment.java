package com.example.navigationdrawer_01;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Inellipse10 on 06/09/2016.
 */
public class ReminderFragment extends Fragment {
    TextView reminder;
    TextView reminderTitle;
    EditText etReminderTitle;
    EditText odometer;
    EditText date;
    EditText note;
    Button addReminder;



    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.reminder_fragment, container, false);
        reminder = (TextView)v.findViewById(R.id.textView2);
        reminderTitle =(TextView) v.findViewById(R.id.reminderTitle);
        etReminderTitle =(EditText) v.findViewById(R.id.editText);
        odometer =(EditText) v.findViewById(R.id.editText3);
        date = (EditText) v.findViewById(R.id.editText4);

        note = (EditText) v.findViewById(R.id.editText5);
        addReminder =(Button) v.findViewById(R.id.addReminder);

        return v;
    }
}
