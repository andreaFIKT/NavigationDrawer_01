package com.example.navigationdrawer_01;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by Inellipse10 on 30/08/2016.
 */
@Table(name="Cost")
public class Cost extends Model {

    @Column(name = "Date")
    String date;
    @Column(name = "Category")
    String category;
    @Column(name = "Title")
    String title;
    @Column(name = "Cost")
    String cost;

    public Cost() {
        super();
    }

    public Cost(String date, String category, String title, String cost) {
        super();
        this.date = date;
        this.category = category;
        this.title = title;
        this.cost = cost;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }
}
