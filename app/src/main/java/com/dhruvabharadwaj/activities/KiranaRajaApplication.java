package com.dhruvabharadwaj.activities;

import android.app.Application;
import android.content.SharedPreferences;

public class KiranaRajaApplication extends Application {

    public static final String ADDRESS_BOOK = "com.kiranaRaja.addressBook";

    public void getDefaultDeliveryAddress() {

    }

    public SharedPreferences getSharedPreferences() {
        return KiranaRajaApplication.this.getSharedPreferences(ADDRESS_BOOK, MODE_PRIVATE);
    }
}
