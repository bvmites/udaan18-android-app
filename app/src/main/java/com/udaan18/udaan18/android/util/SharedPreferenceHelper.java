package com.udaan18.udaan18.android.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.udaan18.udaan18.android.R;
import com.udaan18.udaan18.android.model.eventCategory.Category;
import com.udaan18.udaan18.android.model.eventCategory.Department;
import com.udaan18.udaan18.android.model.eventCategory.Developer;
import com.udaan18.udaan18.android.model.eventCategory.Event;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by jack on 28-02-2018.
 */

public class SharedPreferenceHelper {

    private static SharedPreferenceHelper helper;


    private List<Department> departments;
    private List<Developer> developers;
    private List<Event> adventure;
    private List<Event> nonTechList;
    private List<Event> culturalList;
    private List<Category> teamUdaan;

    private SharedPreferenceHelper(Context context) throws JSONException {
        SharedPreferences preferences = context.getSharedPreferences(context.getString(R.string.prefs_file_name), Context.MODE_PRIVATE);
        String stringData = preferences.getString(context.getString(R.string.prefs_event_data_json), "");
        String developersData = preferences.getString(context.getString(R.string.prefs_developer_data_json), "");
        String teamUdaanData = preferences.getString(context.getString(R.string.prefs_team_udaan_data_json), "");

        JSONObject data = new JSONObject(stringData);
        JSONArray developers = new JSONArray(developersData);
        JSONArray teamUdaan = new JSONArray(teamUdaanData);

        parseAndLoadData(data, developers, teamUdaan);
    }

    public static SharedPreferenceHelper getInstance(Context context) throws JSONException {

        if (SharedPreferenceHelper.helper == null) {
            SharedPreferenceHelper.helper = new SharedPreferenceHelper(context);
        }
        return SharedPreferenceHelper.helper;

    }

    private void parseAndLoadData(JSONObject data, JSONArray developers, JSONArray teamUdaan) throws JSONException {
        Gson gson = new Gson();

        this.departments = new ArrayList<>(Arrays.asList(gson.fromJson(data.getJSONArray("technical").toString(), Department[].class)));
        this.nonTechList = new ArrayList<>(Arrays.asList(gson.fromJson(data.getJSONArray("nonTechnical").toString(), Event[].class)));
        this.culturalList = new ArrayList<>(Arrays.asList(gson.fromJson(data.getJSONArray("cultural").toString(), Event[].class)));
        this.adventure = new ArrayList<>(Arrays.asList(gson.fromJson(data.getJSONArray("adventure").toString(), Event[].class)));
        this.developers = new ArrayList<>(Arrays.asList(gson.fromJson(developers.toString(), Developer[].class)));
        this.teamUdaan = new ArrayList<>(Arrays.asList(gson.fromJson(teamUdaan.toString(), Category[].class)));
    }

    public List<Department> getDepartmentsList() {
        return departments;
    }

    public List<Developer> getDevelopersList() {
        return developers;
    }

    public List<Event> getNonTechList() {
        return nonTechList;
    }

    public List<Event> getCulturalList() {
        return culturalList;
    }

    public List<Category> getTeamUdaan() {
        return teamUdaan;
    }

    public List<Event> getAdventure() {
        return adventure;
    }



}
