package com.zerohour.model;

import java.util.ArrayList;

/**
 * Created by Shankar on 5/19/2017.
 */

public class InviteHistoryModel {
    private String month;
    private String day;
    private String purpose;
    private ArrayList<String> contactList;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public ArrayList<String> getContactList() {
        return contactList;
    }

    public void setContactList(ArrayList<String> contactList) {
        this.contactList = contactList;
    }
}
