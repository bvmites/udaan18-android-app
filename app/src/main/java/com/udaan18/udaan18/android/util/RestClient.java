package com.udaan18.udaan18.android.util;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by jack on 27-02-2018.
 */

public class RestClient {
    private ApiHelper api;
    private String url;

    public RestClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiHelper.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();
        api = retrofit.create(ApiHelper.class);
    }

    public ApiHelper getApiHelper() {
        return api;
    }
}
