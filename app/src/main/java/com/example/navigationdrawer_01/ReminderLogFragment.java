package com.example.navigationdrawer_01;

import android.app.AlarmManager;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;

import java.util.List;

/**
 * Created by Inellipse10 on 07/09/2016.
 */
public class ReminderLogFragment extends Fragment {
    ReminderAdapter adapter;
    Cursor c;
    ReminderModel remind;
    ListView listView;
    List<ReminderModel> reminderLog;
    private String[]context_menu={"EDIT","DELETE"};


    public ReminderLogFragment() {
        // Required empty public constructor
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.reminder_log_fragment,container,false);
        final ListView listView = (ListView) v.findViewById(R.id.listOfReminders);
        final List<ReminderModel> reminderLog = getAll();
// Create the adapter to convert the array to views
        adapter =new ReminderAdapter( getContext(), R.layout.reminder_item_of_list, reminderLog);

// Attach the adapter to a ListView
        listView.setAdapter(adapter);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(final AdapterView<?> p, View v,final int po, long id) {


                long itemID = reminderLog.get(po).getId();
                Log.d("ItemID","= "+ itemID);
                new Delete().from(FuelAdd.class).where("Id = ?",itemID).execute();
                reminderLog.remove(po);
                adapter.notifyDataSetChanged();
                return true;
            }
        });
        return  v;
    }

    public static List<ReminderModel> getAll() {
        return new Select()
                .from(ReminderModel.class)
                .execute();
    }
}
