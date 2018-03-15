package com.udaan18.udaan18.android.util;


import com.udaan18.udaan18.android.model.eventCategory.Category;
import com.udaan18.udaan18.android.model.eventCategory.Container;
import com.udaan18.udaan18.android.model.eventCategory.Developer;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by jack on 27-02-2018.
 */

public interface ApiHelper {
    String BASE_URL = "https://raw.githubusercontent.com/bvmites/udaan18-website/master/";

    @GET("data.json")
    Call<Container> getEvents();

    @GET("developers.json")
    Call<List<Developer>> getDevelopers();

    @GET("team-udaan.json")
    Call<List<Category>> getCategory();
}
