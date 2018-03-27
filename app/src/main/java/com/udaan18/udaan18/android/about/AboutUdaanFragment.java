package com.udaan18.udaan18.android.about;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeIntents;
import com.udaan18.udaan18.android.R;
import com.udaan18.udaan18.android.mainnavigation.MainActivity;
import com.udaan18.udaan18.android.util.Helper;

/**
 * Creator: Varun Barad
 * Date: 26-02-2018
 * Project: udaan18-android-app
 */
public class AboutUdaanFragment extends Fragment implements View.OnClickListener {

    public static int location = 1;
    private final String EMAIL_ADDRESS = "developer.team.udaan@gmail.com";
    private final String YOUTUBE_LINK = "UCnqRgS6O0MGF8sTYb_fHjWA";
    private final String FACEBOOK_LINK = "https://www.facebook.com/teamudaan18";
    private final String INSTAGRAM_LINK = "https://www.instagram.com/teamudaan";
    private final String PLAY_STORE = "";
    private final String WEB_LINK = "https://udaan18.com/";
    private final String lat = "22.5525703";
    private final String lon = "72.9240181";
    private final String mapTitle = "BVM Engineering College";
    private final String FREEPIC_LINK = "https://www.freepik.com";
    private final String FLATICON_LINK = "https://www.flaticon.com";

    private View rootView;
    private AppCompatImageButton mail;
    private AppCompatImageButton youtube;
    private AppCompatImageButton facebook;
    private AppCompatImageButton playStore;
    private AppCompatImageButton weblink;
    private AppCompatImageButton maps;
    private AppCompatImageButton windows;
    private AppCompatImageButton arIos;
    private AppCompatImageButton arAndroid;
    private AppCompatImageButton instagram;
    private AppCompatTextView sponcers;
    private AppCompatImageButton appStore;


    private AppCompatTextView contactUs;
    private AppCompatTextView available_on;
    private AppCompatTextView about_us_title;
    private AppCompatTextView about_us_ar;
    private AppCompatTextView about_us_reach;
    private AppCompatTextView about_us_address;
    private AppCompatTextView about_us_freepik;
    private AppCompatTextView about_us_flaticon;
    private AppCompatTextView about_us_attri;


    private Typeface custom_font_dec;
    private Typeface custom_font_label;
    private Typeface custom_font_reg;
    private Typeface custom_font_tagline;

    public static AboutUdaanFragment newInstance() {
        AboutUdaanFragment fragment = new AboutUdaanFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_about_udaan, container, false);
        intializeObjects();
        ((MainActivity) getActivity()).removeTitle("About");
        ((MainActivity) getActivity()).setAllColorChanage(R.color.color_about);
        custom_font_dec = Typeface.createFromAsset(getContext().getAssets(), "fonts/ProzaLibreBold.ttf");
        custom_font_label = Typeface.createFromAsset(getContext().getAssets(), "fonts/ProzaLibreSemiBold.ttf");
        custom_font_reg = Typeface.createFromAsset(getContext().getAssets(), "fonts/ProzaLibreRegular.ttf");
        custom_font_tagline = Typeface.createFromAsset(getContext().getAssets(), "fonts/BungeeRegular.ttf");
        return rootView;
    }

    public void intializeObjects() {

        mail = (AppCompatImageButton) rootView.findViewById(R.id.mail);
        youtube = (AppCompatImageButton) rootView.findViewById(R.id.youtube);
        facebook = (AppCompatImageButton) rootView.findViewById(R.id.facebook);
        playStore = (AppCompatImageButton) rootView.findViewById(R.id.playstore);
        weblink = (AppCompatImageButton) rootView.findViewById(R.id.website);
        maps = (AppCompatImageButton) rootView.findViewById(R.id.map_view);
        windows = (AppCompatImageButton) rootView.findViewById(R.id.windows);
        arIos = (AppCompatImageButton) rootView.findViewById(R.id.arios);
        arAndroid = (AppCompatImageButton) rootView.findViewById(R.id.arandroid);
        sponcers = (AppCompatTextView) rootView.findViewById(R.id.our_sponsors);
        instagram = rootView.findViewById(R.id.instagram);
        appStore = rootView.findViewById(R.id.appstore);
        about_us_attri = rootView.findViewById(R.id.attri);

        about_us_ar = rootView.findViewById(R.id.our_ar);
        about_us_reach = rootView.findViewById(R.id.about_us_reach);
        about_us_title = rootView.findViewById(R.id.about_us_title);
        available_on = rootView.findViewById(R.id.available_on);
        contactUs = rootView.findViewById(R.id.about_us_connect);
        about_us_address = rootView.findViewById(R.id.about_us_address);
        about_us_flaticon = rootView.findViewById(R.id.flaticon);
        about_us_freepik = rootView.findViewById(R.id.freepic);

        about_us_title.setTypeface(custom_font_tagline);
        about_us_ar.setTypeface(custom_font_label);
        about_us_reach.setTypeface(custom_font_label);
        available_on.setTypeface(custom_font_label);
        contactUs.setTypeface(custom_font_label);
        about_us_flaticon.setTypeface(custom_font_label);
        about_us_freepik.setTypeface(custom_font_label);

        about_us_address.setTypeface(custom_font_reg);
        sponcers.setTypeface(custom_font_label);

        mail.setOnClickListener(this);
        youtube.setOnClickListener(this);
        facebook.setOnClickListener(this);
        playStore.setOnClickListener(this);
        weblink.setOnClickListener(this);
        maps.setOnClickListener(this);
        windows.setOnClickListener(this);
        instagram.setOnClickListener(this);
        sponcers.setOnClickListener(this);
        arAndroid.setOnClickListener(this);
        arIos.setOnClickListener(this);
        appStore.setOnClickListener(this);
        about_us_freepik.setOnClickListener(this);
        about_us_flaticon.setOnClickListener(this);

    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) this.getActivity()).removeBack();
        ((MainActivity) getActivity()).removeTitle(getContext().getString(R.string.about_us_title));
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.mail:
                Helper.sendEmail(EMAIL_ADDRESS, this.getContext());
                // Toast.makeText(this.getContext(), "here i clicked mail", Toast.LENGTH_SHORT).show();

                break;
            case R.id.youtube:
                intent = YouTubeIntents.createChannelIntent(getContext(), YOUTUBE_LINK);
                startActivity(intent);
                break;
            case R.id.facebook:
                intent = getFacebookIntent(this.getActivity().getPackageManager(), FACEBOOK_LINK);
                startActivity(intent);
                break;
            case R.id.instagram:
                intent = newInstagramIntent(this.getActivity().getPackageManager(), INSTAGRAM_LINK);
                startActivity(intent);
                break;
            case R.id.appstore:
                Toast.makeText(getContext(), "Coming soon", Toast.LENGTH_SHORT).show();
                break;
            case R.id.playstore:
                String appPackageName = this.getActivity().getPackageName();
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                } catch (android.content.ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                }
                //Toast.makeText(getContext(), "Comming soon", Toast.LENGTH_SHORT).show();
                break;
            case R.id.website:
                Helper.openUrlInBrowser(WEB_LINK, this.getContext());
                break;
            case R.id.map_view:
                String geoUri = "http://maps.google.com/maps?q=loc:" + lat + "," + lon + " (" + mapTitle + ")";
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse(geoUri));
                startActivity(intent);
                break;
            case R.id.windows:
                Toast.makeText(getContext(), "Coming soon", Toast.LENGTH_SHORT).show();
                // intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.microsoft.com/en-us/store/p/udaan-17/9p55q9j2bkq7"));
                // startActivity(intent);
                break;
            case R.id.our_sponsors:
                //((MainActivity) getActivity()).loadSponsor();
                Toast.makeText(getContext(), "Coming soon", Toast.LENGTH_SHORT).show();
                break;
            case R.id.arandroid:
                Toast.makeText(getContext(), "Coming soon", Toast.LENGTH_SHORT).show();
                break;
            case R.id.arios:
                Toast.makeText(getContext(), "Coming soon", Toast.LENGTH_SHORT).show();
                break;
            case R.id.freepic:
                Helper.openUrlInBrowser(FREEPIC_LINK, this.getContext());
                break;
            case R.id.flaticon:
                Helper.openUrlInBrowser(FLATICON_LINK, this.getContext());
                break;
        }

    }

    private Intent getFacebookIntent(PackageManager pm, String url) {
        Uri uri = Uri.parse(url);
        try {
            ApplicationInfo applicationInfo = pm.getApplicationInfo("com.facebook.katana", 0);

            if (applicationInfo.enabled) {
                uri = Uri.parse("fb://facewebmodal/f?href=" + url);
            }

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return new Intent(Intent.ACTION_VIEW, uri);
    }

    public Intent newInstagramIntent(PackageManager pm, String url) {
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);

        try {
            ApplicationInfo applicationInfo = pm.getApplicationInfo("com.instagram.android", 0);
            if (applicationInfo.enabled) {
                intent.setPackage("com.instagram.android");
            }
        } catch (PackageManager.NameNotFoundException ignored) {

        }

        return intent;
    }
}
