package com.udaan18.udaan18.android.notification;

import android.annotation.SuppressLint;
import android.app.Service;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by abhishek on 3/22/2018.
 */

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {
    private static final String TAG="MyFirebaseInstanceIDService";

    @SuppressLint("LongLogTag")
    @Override
    public void onTokenRefresh() {
        String refreshedtoken= FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG,"new Token : " + refreshedtoken);
    }
}
