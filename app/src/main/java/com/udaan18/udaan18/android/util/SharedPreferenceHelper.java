package com.udaan18.udaan18.android.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.udaan18.udaan18.android.R;
import com.udaan18.udaan18.android.model.eventCategory.Container;

/**
 * Created by jack on 28-02-2018.
 */

public class SharedPreferenceHelper {
    public static void storeEventsData(Context context, Container container) {
        SharedPreferences preferences = context.getSharedPreferences(context.getString(R.string.PREFS_FILE_NAME), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString(context.getString(R.string.PREFS_EVENTS_DATA), (new Gson()).toJson(container));
    }
}
