package com.zerohour.model;

import android.support.annotation.NonNull;

public class Contact implements Comparable<Contact> {


    public String picture;
    public String displayName;
    public Boolean checkBox;
    public String phoneNumber;
    private boolean mContacts_Flow = false;

    public Contact(String displayName, String phoneNumber, String picture) {
        setDisplayName(displayName);
        setPhoneNumber(phoneNumber);
        setPicture(picture);
    }

    public boolean ismContacts_Flow() {
        return mContacts_Flow;
    }

    public void setmContacts_Flow(boolean mContacts_Flow) {
        this.mContacts_Flow = mContacts_Flow;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String contactUri) {
        this.picture = contactUri;
    }

    public Boolean getCheckBox() {
        return checkBox;
    }

    public void setCheckBox(Boolean checkBox) {
        this.checkBox = checkBox;
    }


    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public int compareTo(@NonNull Contact another) {
        String oldNumber = this.phoneNumber.replace(" ", "").trim();
        String newNumber = another.phoneNumber.replace(" ", "").trim();
        return oldNumber.compareTo(newNumber);
    }
}
