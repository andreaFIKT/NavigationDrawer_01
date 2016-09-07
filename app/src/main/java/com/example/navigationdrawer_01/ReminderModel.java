package com.example.navigationdrawer_01;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by Inellipse10 on 07/09/2016.
 */
@Table(name="Reminder")
public class ReminderModel extends Model {

    @Column(name = "Title")
    String reminderTitle;
    @Column(name = "Odometer")
    String reminderOdometer;
    @Column(name = "Date")
    String reminderDate;
    @Column(name = "Note")
    String reminderNote;

    public ReminderModel() {
    }

    public ReminderModel(String reminderTitle, String reminderOdometer, String reminderDate, String reminderNote) {
        this.reminderTitle = reminderTitle;
        this.reminderOdometer = reminderOdometer;
        this.reminderDate = reminderDate;
        this.reminderNote = reminderNote;
    }

    public String getReminderTitle() {
        return reminderTitle;
    }

    public void setReminderTitle(String reminderTitle) {
        this.reminderTitle = reminderTitle;
    }

    public String getReminderOdometer() {
        return reminderOdometer;
    }

    public void setReminderOdometer(String reminderOdometer) {
        this.reminderOdometer = reminderOdometer;
    }

    public String getReminderDate() {
        return reminderDate;
    }

    public void setReminderDate(String reminderDate) {
        this.reminderDate = reminderDate;
    }

    public String getReminderNote() {
        return reminderNote;
    }

    public void setReminderNote(String reminderNote) {
        this.reminderNote = reminderNote;
    }
}
