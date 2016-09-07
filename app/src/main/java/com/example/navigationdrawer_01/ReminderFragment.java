package com.example.navigationdrawer_01;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by Inellipse10 on 06/09/2016.
 */
public class ReminderFragment extends Fragment {
    TextView reminder;
    TextView reminderTitle;
    EditText etReminderTitle;
    TextView remindOdometer;
    EditText entOdometer;
    TextView rDate;
    EditText chooseDate;
    TextView remindNote;
    EditText entNote;
    Button add;
    private PendingIntent pi;
    private BroadcastReceiver br;
    Calendar c;
    TimePickerDialog mTimePicker;

    NotificationManager notificationManager;
    private AlarmManager alarmManager;



    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.reminder_fragment, container, false);
        br = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String notes = " ";
                Bundle extras = intent.getExtras();
                if(extras != null)
                {
                    notes = extras.getString("notes");

                }
                Toast.makeText(getContext(),"WakeUP"+notes,Toast.LENGTH_SHORT).show();
                createNotification(notes);



            }
        };
        reminder = (TextView) v.findViewById(R.id.txtReminder);
        reminderTitle = (TextView) v.findViewById(R.id.reminderTitle);
        etReminderTitle = (EditText) v.findViewById(R.id.entReminderTitle);
        remindOdometer = (TextView) v.findViewById(R.id.reminderOdometer);
        entOdometer = (EditText)  v.findViewById(R.id.entReminderOdometer);
        rDate = (TextView) v.findViewById(R.id.txtReminderDate);
        chooseDate = (EditText)  v.findViewById(R.id.date) ;
        chooseDate.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);
                DatePickerDialog ddp = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int sYear, int sMonth, int sDay) {
                        chooseDate.setText(sYear + "/" + sMonth +"/" + sDay);

                    }
                },year,month,day);




                mTimePicker = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                       chooseDate.setText( selectedHour + ":" + selectedMinute);

                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
                ddp.show();
//                chooseDate.setText(year + "/" + month + "/" + day + "/" + hour + "/" + minute);


            }
        });
        remindNote = (TextView)v.findViewById(R.id.reminderNote);
        entNote = (EditText) v.findViewById(R.id.entReminderNote);
        add = (Button) v.findViewById(R.id.addReminder);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ReminderModel addNewReminder = new ReminderModel();
                addNewReminder.reminderTitle = etReminderTitle.getText().toString();
                addNewReminder.reminderOdometer = entOdometer.getText().toString();
                addNewReminder.reminderNote = entNote.getText().toString();
                addNewReminder.reminderDate = chooseDate.getText().toString();
                addNewReminder.save();
                Log.d("REMINDER", "Saved:" +addNewReminder.save());


                String notes = entNote.getText().toString();
                getActivity().registerReceiver(br, new IntentFilter("com.example.navigationdrawer_01"));
                Intent alarmIntent = new Intent("com.example.navigationdrawer_01");
                alarmIntent.putExtra("notes",notes);
                pi = PendingIntent.getBroadcast(getContext(),0, alarmIntent, PendingIntent.FLAG_CANCEL_CURRENT);
                alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP,c.getTimeInMillis()  ,pi);

            }
        });


        return v;
    }

    private void createNotification(String notes)
    {
        Intent intent = new Intent(getContext(), ReminderFragment.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(getContext(),0,intent,0);
        Notification n = new Notification.Builder(getContext())
                .setContentTitle("REMINDER LOG")
                .setContentText(notes)
                .setSmallIcon(R.drawable.fuel_icon)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build();
        NotificationManager notificationManager = (NotificationManager)
                getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0,n);

    }

    @Override
    public void onDestroy(){
        alarmManager.cancel(pi);
        getActivity().unregisterReceiver(br);
        super.onDestroy();
    }
}

