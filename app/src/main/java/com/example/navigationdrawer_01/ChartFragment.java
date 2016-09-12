package com.example.navigationdrawer_01;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.activeandroid.query.Select;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Inellipse10 on 12/09/2016.
 */
public class ChartFragment extends Fragment {
    BarChart barChart;
    FuelAdd fChart;
    XAxis xAxis;
    YAxis yAxis;
    List<FuelAdd> fCharts;

    public ChartFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.chart_fragment,container,false);
        //String [] months = {"Jan","Feb","March","April","May","June","July","Aug","Sep","Oct","Nov","Dec"};
        fChart = new FuelAdd();
        fCharts = HistoryFragment.getAll();
        barChart = (BarChart) v.findViewById(R.id.barGraph);
        barChart.setDrawValueAboveBar(true);
        barChart.setDrawBarShadow(true);
        barChart.setHighlightFullBarEnabled(true);
        barChart.setDescription("Chart about spent money per month");
        ArrayList<BarEntry> xLabel = new ArrayList<BarEntry>();

        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setAxisLineWidth(0.8f);
        xAxis.setGranularity(7f); // only intervals of 1 day
        xAxis.setLabelCount(10);

        YAxis yAxis = barChart.getAxisLeft();
        yAxis.setTextSize(12f);
        yAxis.setAxisLineColor(Color.CYAN);
        yAxis.setAxisLineWidth(5f);
        yAxis.setGranularity(1f); // interval 1
        yAxis.setLabelCount(6, true);

        Legend l = barChart.getLegend();
        l.setPosition(Legend.LegendPosition.LEFT_OF_CHART);
        l.setForm(Legend.LegendForm.SQUARE);
        l.setFormSize(9f);
        l.setTextSize(11f);
        l.setXEntrySpace(10f);

        ArrayList<BarEntry> barEntries = new ArrayList<BarEntry>();
        for(int i = 0; i< fCharts.size();i++)
        {
//            String month = String.valueOf(Calendar.MONTH);
            String month = "Sept";
            if(fCharts.get(i).getDate().contains(month)) {
                int index = 0;
                barEntries.add(new BarEntry(Float.valueOf(fCharts.get(i).getPrice()), index));
                index++;

            }
            /*xAxis.setLabelCount(i);
            yAxis.setLabelCount(Integer.valueOf(fCharts.get(i).getPrice()));*/
        }

        BarDataSet barDataSet = new BarDataSet(barEntries,"Date");

        BarData barData = new BarData(barDataSet);
        barChart.notifyDataSetChanged();
        barChart.setData(barData);
        barChart.invalidate();

        return  v;

    }

}
