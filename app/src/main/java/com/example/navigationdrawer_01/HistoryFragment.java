package com.example.navigationdrawer_01;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;

import java.util.List;

public class HistoryFragment extends Fragment {
    FuelAdapter adapter;
    Cursor c;
    FuelAdd fuel;
    ListView listView;
    List<FuelAdd> fuelInputs;
    private String[]context_menu={"EDIT","DELETE"};

    public HistoryFragment() {
        // Required empty public constructor
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.history_fragment,container,false);
        final ListView listView = (ListView) v.findViewById(R.id.listView);
        final List<FuelAdd> fuelInputs = getAll();
// Create the adapter to convert the array to views
       adapter =new FuelAdapter(getContext(), R.layout.fuel_input_items_of_list, fuelInputs);

// Attach the adapter to a ListView
        listView.setAdapter(adapter);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(final AdapterView<?> p, View v,final int po, long id) {

                fuelInputs.remove(po);
                long itemID = fuelInputs.get(po).getId();
                Log.d("ItemID","= "+ itemID);
                new Delete().from(FuelAdd.class).where("Id = ?",itemID-1).execute();
                adapter.notifyDataSetChanged();
                return true;
            }
        });
        return  v;
    }

    public static List<FuelAdd> getAll() {
        return new Select()
                .from(FuelAdd.class)
                .execute();
    }




}
