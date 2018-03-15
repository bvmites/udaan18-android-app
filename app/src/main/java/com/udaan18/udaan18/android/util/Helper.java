package com.udaan18.udaan18.android.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.provider.Settings;

import com.udaan18.udaan18.android.R;

public class Helper {

    /**
     * Used for displaying colors
     */
    public static final int[] colors = new int[]{R.color.colorDeepOrange, R.color.colorBlueGrey, R.color.colorDeepPurple, R.color.colorBlue, R.color.colorTeal};

    public static boolean hasNetworkConnection(Context context) {

        boolean status = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            status = true;
        }

        return status;
    }

    public static void showNetworkAlertPopup(final Activity activity) {

        AlertDialog.Builder builder = new AlertDialog.Builder(activity, R.style.AlertTheme);

        builder.setMessage("Network connection is required when you first connect to the internet. Please turn on mobile network or Wi-Fi in Settings")
                .setTitle("Unable to connect")
                .setCancelable(false)
                .setPositiveButton("Settings", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
                                activity.startActivity(intent);
                            }
                        }
                )
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                activity.finish();
                            }
                        }
                );
        AlertDialog alert = builder.create();
        alert.show();
    }

    public static void makeCall(String mobile, Context context) {
        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:" + mobile));
        context.startActivity(callIntent);
    }

    public static void sendEmail(String emailAddress, Context context) {
        Uri emailUri = Uri.parse("mailto:" + emailAddress);
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, emailUri);
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{emailAddress});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Review regarding work done in Udaan17");
        context.startActivity(Intent.createChooser(emailIntent, "Send mail"));
    }

    public static void openUrlInBrowser(String url, Context context) {
        Uri githubUri = Uri.parse(url);
        Intent githubIntent = new Intent(Intent.ACTION_VIEW, githubUri);
        context.startActivity(githubIntent);
    }

    public static String getResourceNameFromTitle(String title) {
        title = (title != null) ? title : "";
        return
                title.toLowerCase()
                        .replaceAll("[\\s-]+", "_")
                        .replaceAll("[^a-zA-Z0-9_]", "");
    }
}
