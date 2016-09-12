package com.example.navigationdrawer_01;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.app.usage.UsageEvents;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.CalendarContract;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.WakefulBroadcastReceiver;
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

import com.google.android.gms.vision.barcode.Barcode;

import java.util.Calendar;
import java.util.GregorianCalendar;

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
        /*br = new BroadcastReceiver(){
            @Override
            public void onReceive(Context context, Intent intent) {
//                context.startService(new Intent(context, MyService.class));
                String notes = " ";
                Bundle extras = intent.getExtras();
                if(extras != null)
                {
                    notes = extras.getString("notes");

                }
                Toast.makeText(getContext(),"WakeUP"+notes,Toast.LENGTH_SHORT).show();
                getActivity().startService(new Intent(getContext(),MyService.class));
                createNotification(notes);
            }
        };*/
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
                        chooseDate.append(sYear + "/" + sMonth +"/" + sDay);
                        c.set(Calendar.YEAR,sYear);
                        c.set(Calendar.MONTH,sMonth);
                        c.set(Calendar.DAY_OF_MONTH,sDay);

                    }
                },year,month,day);
                ddp.show();

                mTimePicker = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                       chooseDate.append( selectedHour + ":" + selectedMinute);
                        c.set(Calendar.HOUR_OF_DAY,selectedHour);
                        c.set(Calendar.MINUTE,selectedMinute);

                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

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

               /* Calendar beginTime = Calendar.getInstance();
                beginTime.set(2016,9,12,11,37);
                Calendar endTime = Calendar.getInstance();
                endTime.set(2016,9,12,11,38);*/
                Intent intent = new Intent(Intent.ACTION_INSERT)
                        .setData(CalendarContract.Events.CONTENT_URI)
                        .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,c.getTimeInMillis())
                        .putExtra(CalendarContract.EXTRA_EVENT_END_TIME,c.getTimeInMillis())
                        .putExtra(CalendarContract.Events.TITLE,"Remider")
                        .putExtra(CalendarContract.Events.DESCRIPTION,"New Reminder")
                        .putExtra(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_BUSY);
                startActivity(intent);
            }
        });
        return v;
    }

 /*   private void createNotification(String notes)
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

    }*/

    /*@Override
    public void onDestroy(){
        alarmManager.cancel(pi);
        getActivity().unregisterReceiver(br);
        super.onDestroy();
    }*/
}

