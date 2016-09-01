package com.example.navigationdrawer_01;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

/**
 * Created by Inellipse10 on 24/08/2016.
 */
@Table(name="FuelAdd")
public class FuelAdd extends Model {

    @Column(name = "TodayDate")
    String date;
    @Column(name = "Liters")
    String liters;
    @Column(name = "PricePerLiter")
    String price;
    @Column(name = "TotalCost")
    String cost;
    @Column(name = "Latitude")
    double latitude;
    @Column(name = "Longitude")
    double longitude;

    public FuelAdd() {
        super();
    }

    public FuelAdd(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public FuelAdd(String date, String liters, String price, String cost,double latitude, double longitude){
        super();
        this.date = date;
        this.liters = liters;
        this.price = price;
        this.cost = cost;
        this.latitude = latitude;
        this.longitude = longitude;
    }


    public String getCost() {
        return cost;
    }

    public String getPrice() {
        return price;
    }

    public String getLiters() {
        return liters;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setLiters(String liters) {
        this.liters = liters;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public double getLatitude() { return latitude; }

    public void setLatitude(double latitude) { this.latitude = latitude; }

    public double getLongitude() { return longitude; }

    public void setLongitude(double longitude) { this.longitude = longitude; }

    @Override
    public String toString() {
        return "FuelAdd{" +
                "Today date is :'" + date + '\'' +
                ", Entered liters :'" + liters + '\'' +
                ", Entered price :'" + price + '\'' +
                ", Entered cost :'" + cost + '\'' +
                ", Latitude :'" + latitude + '\'' +
                ", Longitude :'" + longitude + '\'' +
                '}';
    }
    public static List<FuelAdd> getAll() {
        // This is how you execute a query
        return new Select()
                .from(FuelAdd.class)
                .execute();
    }
    public static void delete(FuelAdd obj){
        obj.delete();
    }
}
