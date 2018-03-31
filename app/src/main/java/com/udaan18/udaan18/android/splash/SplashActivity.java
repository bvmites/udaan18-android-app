package com.udaan18.udaan18.android.splash;

/**
 * Created by jack on 01-03-2018.
 */


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.udaan18.udaan18.android.BuildConfig;
import com.udaan18.udaan18.android.R;
import com.udaan18.udaan18.android.mainnavigation.MainActivity;
import com.udaan18.udaan18.android.model.eventCategory.Category;
import com.udaan18.udaan18.android.model.eventCategory.Container;
import com.udaan18.udaan18.android.model.eventCategory.Developer;
import com.udaan18.udaan18.android.model.eventCategory.VersionCheck;
import com.udaan18.udaan18.android.util.Helper;
import com.udaan18.udaan18.android.util.RestClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SplashActivity extends Activity {

    // Splash screen timer

    private volatile boolean dataFetched = false;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private Context context;
    private RestClient client;
    private Activity activity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        preferences = this.getSharedPreferences(this.getString(R.string.prefs_file_name), Context.MODE_PRIVATE);
        editor = preferences.edit();
        context = this;
        activity = this;
        client = new RestClient();

        if (Helper.hasNetworkConnection(this)) {
            try {
                getEventData();
                getDeveloperData();
                getTeamUdaanData();
                getVersionData();
                //getPhotos();
                // Toast.makeText(SplashActivity.this, "App Version" + Helper.check.getAppVersion() + "\n data version" + Helper.check.getDataVersion(), Toast.LENGTH_SHORT).show();


            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "error in" + e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }

        } else if (!this.getSharedPreferences(this.getString(R.string.prefs_file_name), Context.MODE_PRIVATE).contains(this.getString(R.string.prefs_event_data_json))) {
            Helper.showNetworkAlertPopup(this);
        } else {
            setDelay();
        }
    }

    void getEventData() {
        Call<Container> call = client.getApiHelper().getEvents();
        call.enqueue(new Callback<Container>() {
            @Override
            public void onResponse(Call<Container> call, Response<Container> response) {
                Container container = response.body();
                container.getNonTech().add(container.getTreasureHunt().get(0));
                container.getNonTech().add(container.getGirls().get(0));
                editor.putString(context.getString(R.string.prefs_event_data_json), container.toString());
                editor.apply();
                // Toast.makeText(SplashActivity.this, "error in event:"+container.getDepartments().size(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Container> call, Throwable t) {
                Log.d("NETWORK", "Error in response");
                Toast.makeText(SplashActivity.this, "error in event:" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    void getDeveloperData() {
        Call<List<Developer>> call = client.getApiHelper().getDevelopers();
        call.enqueue(new Callback<List<Developer>>() {
            @Override
            public void onResponse(Call<List<Developer>> call, Response<List<Developer>> response) {
                List<Developer> container = response.body();
                editor.putString(context.getString(R.string.prefs_developer_data_json), (new Gson()).toJson(container));
                editor.apply();
                //Toast.makeText(SplashActivity.this, "Done "+(new Gson()).toJson(container), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<Developer>> call, Throwable t) {
                Log.d("NETWORK", "Error in response");
                Toast.makeText(SplashActivity.this, "error in developer:" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    void getTeamUdaanData() {
        Call<List<Category>> call = client.getApiHelper().getCategory();
        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                List<Category> container = response.body();
                editor.putString(context.getString(R.string.prefs_team_udaan_data_json), (new Gson()).toJson(container));
                editor.apply();
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Log.d("NETWORK", "Error in response");
                Toast.makeText(SplashActivity.this, "error in team:" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    void getVersionData() {
        Call<VersionCheck> call = client.getApiHelper().getVersion();
        call.enqueue(new Callback<VersionCheck>() {
            @Override
            public void onResponse(Call<VersionCheck> call, Response<VersionCheck> response) {
                VersionCheck check = response.body();
                // Toast.makeText(SplashActivity.this, "version" + check.getAppVersion(), Toast.LENGTH_SHORT).show();
                if (check.getAppVersion() > BuildConfig.VERSION_CODE) {
                    Helper.showUpdatePopup(SplashActivity.this);
                } else {
                    SplashActivity.this.setDelay();
                }
            }

            @Override
            public void onFailure(Call<VersionCheck> call, Throwable t) {

            }
        });

    }


    void setDelay() {

        Intent i = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(i);
        SplashActivity.this.finish();
    }
}
