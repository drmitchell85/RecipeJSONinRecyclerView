package com.danmitch.android.recipejsoninrecyclerview;

public class User {

    private static final String TAG = "User";

    // vars
    private String mFirstName, mLastName, mPhoneNumber, mCountry;

    public User(String firstName, String lastName, String phoneNumber, String country) {
        mFirstName = firstName;
        mLastName = lastName;
        mPhoneNumber = phoneNumber;
        mCountry = country;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public String getPhoneNumber() {
        return mPhoneNumber;
    }

    public String getCountry() {
        return mCountry;
    }
}
