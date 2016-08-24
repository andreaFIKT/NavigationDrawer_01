package com.example.navigationdrawer_01;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.activeandroid.query.Select;

import java.util.List;

public class HistoryFragment extends Fragment {
    FuelAdapter adapter;
    ListView listView;
    List<FuelAdd> fuelInputs;

    public HistoryFragment() {
        // Required empty public constructor
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.history_fragment,container,false);
        ListView listView = (ListView) v.findViewById(R.id.listView);
        List<FuelAdd> fuelInputs = getAll();
// Create the adapter to convert the array to views
       adapter =new FuelAdapter(getContext(), R.layout.fuel_input_items_of_list, fuelInputs);
        adapter.addAll(fuelInputs);

// Attach the adapter to a ListView
        listView.setAdapter(adapter);
        return  v;
    }
    public static List<FuelAdd> getAll() {
        return new Select()
                .from(FuelAdd.class)
                .execute();
    }


}
