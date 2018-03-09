package com.udaan18.udaan18.android.splash;

/**
 * Created by jack on 01-03-2018.
 */


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.gson.Gson;
import com.udaan18.udaan18.android.R;
import com.udaan18.udaan18.android.mainnavigation.MainActivity;
import com.udaan18.udaan18.android.model.eventCategory.Category;
import com.udaan18.udaan18.android.model.eventCategory.Container;
import com.udaan18.udaan18.android.model.eventCategory.Developer;
import com.udaan18.udaan18.android.util.Helper;
import com.udaan18.udaan18.android.util.RestClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SplashActivity extends Activity {


    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;
    VideoView videoView;
    private volatile boolean dataFetched = false;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private Context context;
    private RestClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        Window window = (SplashActivity.this).getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.colorTextSecondary1));


        videoView = (VideoView) findViewById(R.id.videoView);


        preferences = this.getSharedPreferences(this.getString(R.string.prefs_file_name), Context.MODE_PRIVATE);
        editor = preferences.edit();
        context = this;
        client = new RestClient();

        Uri video = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.splash);
        videoView.setVideoURI(video);

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                startNextActivity();
            }
        });

        videoView.start();
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
//                Log.d("hereim",""+(new Gson()).toJson(container).toString());
//                Toast.makeText(getApplicationContext(),"here"+(new Gson()).toJson(container).toString(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Container> call, Throwable t) {
                Log.d("NETWORK", "Error in response");
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
            }

            @Override
            public void onFailure(Call<List<Developer>> call, Throwable t) {
                Log.d("NETWORK", "Error in response");
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
            }
        });
        // performTask();
    }


    /* void performTask() {
         new Handler().postDelayed(new Runnable() {

             /*
              * Showing splash screen with a timer. This will be useful when you
              * want to show case your app logo / company
              */
/*
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
**/
    private void startNextActivity() {
        if (isFinishing())
            return;
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

}
