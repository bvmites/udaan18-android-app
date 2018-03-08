package com.udaan18.udaan18.android.splash;

/**
 * Created by jack on 01-03-2018.
 */


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.udaan18.udaan18.android.R;
import com.udaan18.udaan18.android.mainnavigation.MainActivity;
import com.udaan18.udaan18.android.model.eventCategory.Category;
import com.udaan18.udaan18.android.model.eventCategory.Container;
import com.udaan18.udaan18.android.model.eventCategory.Developer;
import com.udaan18.udaan18.android.util.ApiHelper;
import com.udaan18.udaan18.android.util.Helper;
import com.udaan18.udaan18.android.util.RestClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class SplashActivity extends Activity {

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;
    private volatile boolean dataFetched = false;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private Context context;
    private RestClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        preferences = this.getSharedPreferences(this.getString(R.string.prefs_file_name), Context.MODE_PRIVATE);
        editor = preferences.edit();
        context = this;
        client = new RestClient();

        if (Helper.hasNetworkConnection(this)) {
            try {
                getEventData();
                getDeveloperData();
                getTeamUdaanData();
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "error in" + e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }

        } else if (!this.getSharedPreferences(this.getString(R.string.prefs_file_name), Context.MODE_PRIVATE).contains(this.getString(R.string.prefs_event_data_json))) {
            Helper.showNetworkAlertPopup(this);
        } else {
            performTask();
        }
    }

    void getEventData() {
        Call<Container> call = client.getApiHelper().getEvents();
        call.enqueue(new Callback<Container>() {
            @Override
            public void onResponse(Call<Container> call, Response<Container> response) {
                Container container = response.body();
                editor.putString(context.getString(R.string.prefs_event_data_json), container.toString());
                editor.apply();
            }

            @Override
            public void onFailure(Call<Container> call, Throwable t) {
                Log.d("NETWORK", "Error in response");
                Toast.makeText(SplashActivity.this, "error in event:" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    void getDeveloperData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/bvmites/udaan17-android-app/master/mock-api/")
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();
        ApiHelper api = retrofit.create(ApiHelper.class);
        Call<List<Developer>> call = api.getDevelopers();
        call.enqueue(new Callback<List<Developer>>() {
            @Override
            public void onResponse(Call<List<Developer>> call, Response<List<Developer>> response) {
                List<Developer> container = response.body();
                editor.putString(context.getString(R.string.prefs_developer_data_json), (new Gson()).toJson(container));
                editor.apply();
                // Toast.makeText(SplashActivity.this, "Done "+(new Gson()).toJson(container), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<Developer>> call, Throwable t) {
                Log.d("NETWORK", "Error in response");
                Toast.makeText(SplashActivity.this, "error in developer:" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    void getTeamUdaanData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/bvmites/udaan17-android-app/master/mock-api/")
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();
        ApiHelper api = retrofit.create(ApiHelper.class);
        Call<List<Category>> call = api.getCategory();
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
        performTask();
    }

    void performTask() {
        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(i);

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

}
