package com.udaan18.udaan18.android.about;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatTextView;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.udaan18.udaan18.android.R;
import com.udaan18.udaan18.android.sponsors.SponsorActivity;
import com.udaan18.udaan18.android.util.Helper;

//import com.google.android.youtube.player.YouTubeIntents;


/**
 * Creator: Varun Barad
 * Date: 26-02-2018
 * Project: udaan18-android-app
 */
public class AboutUdaanFragment extends Fragment {
    private final String EMAIL_ADDRESS = "developer.team.udaan@gmail.com";
    private final String YOUTUBE_LINK = "UCnqRgS6O0MGF8sTYb_fHjWA";
    private final String FACEBOOK_LINK = "https://www.facebook.com/teamudaan17/";
    private final String PLAY_STORE = "";
    private final String WEB_LINK = "https://www.udaan17.in";
    private final String INSTAGRAM_LINK = "";
    private final String lat = "22.5525703";
    private final String lon = "72.9240181";
    private final String mapTitle = "BVM Engineering College";
    View rootView;

    private AppCompatImageButton mail;
    private AppCompatImageButton youtube;
    private AppCompatImageButton facebook;
    private AppCompatImageButton playStore;
    private AppCompatImageButton weblink;
    private AppCompatImageButton maps;
    private AppCompatImageButton windows;
    private AppCompatImageButton instagram;
    private AppCompatImageButton appstore;
    private AppCompatImageButton appstorear;
    private AppCompatImageButton playstorear;
    private AppCompatTextView sponsors;
    private AppCompatTextView attribution;
    private AppCompatTextView freepikAttribution;
    private AppCompatTextView flatIconAttribution;



  public static AboutUdaanFragment newInstance() {
    AboutUdaanFragment fragment = new AboutUdaanFragment();
    return fragment;
  }
  
  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      rootView = inflater.inflate(R.layout.fragment_about_udaan, container, false);


      mail = (AppCompatImageButton) rootView.findViewById(R.id.mail);
      youtube = (AppCompatImageButton) rootView.findViewById(R.id.youtube);
      facebook = (AppCompatImageButton) rootView.findViewById(R.id.facebook);
      playStore = (AppCompatImageButton) rootView.findViewById(R.id.playstore);
      weblink = (AppCompatImageButton) rootView.findViewById(R.id.website);
      maps = (AppCompatImageButton) rootView.findViewById(R.id.map_view);
      windows = (AppCompatImageButton) rootView.findViewById(R.id.phone);
      instagram = (AppCompatImageButton) rootView.findViewById(R.id.instagram);
      appstore = (AppCompatImageButton) rootView.findViewById(R.id.appstore);
      appstorear = (AppCompatImageButton) rootView.findViewById(R.id.arios);
      playstorear = (AppCompatImageButton) rootView.findViewById(R.id.arandroid);
      sponsors = (AppCompatTextView) rootView.findViewById(R.id.our_sponsors);
      attribution = (AppCompatTextView) rootView.findViewById(R.id.contact_us_attribution);
      freepikAttribution = (AppCompatTextView) rootView.findViewById(R.id.contact_us_icons_attribution);
      flatIconAttribution = (AppCompatTextView) rootView.findViewById(R.id.contact_us_icons_attribution1);


      final SpannableStringBuilder str = new SpannableStringBuilder("Attribution to Kode Logic");
      str.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 15, 25, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
      attribution.setText(str);

      mail.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Helper.sendEmail(EMAIL_ADDRESS, AboutUdaanFragment.this.getContext());
          }
      });

      sponsors.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Intent intent = new Intent(getContext(), SponsorActivity.class);
              startActivity(intent);
          }
      });

      youtube.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent appintent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + YOUTUBE_LINK));
              Intent webintent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=" + YOUTUBE_LINK));

              try {
                  startActivity(appintent);
              } catch (ActivityNotFoundException e) {
                  startActivity(webintent);
              }
          }
      });

      facebook.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent intent = getFacebookIntent(AboutUdaanFragment.this.getActivity().getPackageManager(), FACEBOOK_LINK);
              startActivity(intent);
          }
      });

      instagram.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Uri uri = Uri.parse("http://instagram.com/_u/" + INSTAGRAM_LINK);
              Intent intent = new Intent(Intent.ACTION_VIEW, uri);

              intent.setPackage("com.instagram.android");
              try {
                  startActivity(intent);
              } catch (ActivityNotFoundException e) {
                  startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://instagram.com/_u/" + INSTAGRAM_LINK)));
              }
          }
      });

      weblink.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Helper.openUrlInBrowser(WEB_LINK, AboutUdaanFragment.this.getContext());
          }
      });

      playStore.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              String appPackageName = AboutUdaanFragment.this.getActivity().getPackageName();
              try {
                  startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
              } catch (android.content.ActivityNotFoundException e) {
                  startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
              }
          }
      });

      maps.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              String geoUri = "http://maps.google.com/maps?q=loc:" + lat + "," + lon + " (" + mapTitle + ")";
              Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(geoUri));
              startActivity(intent);
          }
      });

      windows.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.microsoft.com/en-us/store/p/udaan-17/9p55q9j2bkq7"));
              startActivity(intent);
          }
      });

      attribution.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.behance.net/bosslogic")));
          }
      });

      freepikAttribution.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.freepik.com")));
          }
      });

      flatIconAttribution.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.flaticon.com")));
          }
      });

//        NestedScrollView nestedScrollView = (NestedScrollView) rootView.findViewById(R.id.contact_us_nestedScrollView);
//        nestedScrollView.setNestedScrollingEnabled(false);
      return rootView;

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


}
