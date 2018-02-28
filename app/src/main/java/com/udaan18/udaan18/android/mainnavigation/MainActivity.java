package com.udaan18.udaan18.android.mainnavigation;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.udaan18.udaan18.android.R;
import com.udaan18.udaan18.android.about.AboutUdaanFragment;
import com.udaan18.udaan18.android.databinding.ActivityMainBinding;
import com.udaan18.udaan18.android.events.EventsFragment;
import com.udaan18.udaan18.android.news.NewsFragment;
import com.udaan18.udaan18.android.photo.PhotoFragment;
import com.udaan18.udaan18.android.team.TeamFragment;

/**
 * Created by abhishek on 2/25/2018.
 */

public class MainActivity extends AppCompatActivity {
  private ActivityMainBinding dataBinding;
  
  private EventsFragment eventsFragment;
  private PhotoFragment photoFragment;
  private AboutUdaanFragment aboutUdaanFragment;
  private TeamFragment teamFragment;
  private NewsFragment newsFragment;
  
  @IdRes
  private int currentSelectedSection;
  
  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
  
    this.setSupportActionBar(this.dataBinding.toolbar);
    
    this.dataBinding
        .bottomNavigation
        .setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
          @Override
          public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            MainActivity.this.handleBottomNavigationItemSelect(item.getItemId());
            return true;
          }
        });
    
    this.loadFragment(this.getEventsFragment());
  }
  
  private void handleBottomNavigationItemSelect(@IdRes int itemId) {
    if (itemId != this.currentSelectedSection) {
      this.currentSelectedSection = itemId;
      switch (itemId) {
        case R.id.action_events:
          this.loadFragment(this.getEventsFragment());
          break;
        case R.id.action_photo:
          this.loadFragment(this.getPhotoFragment());
          break;
        case R.id.action_aboutUdaan:
          this.loadFragment(this.getAboutUdaanFragment());
          break;
        case R.id.action_team:
          this.loadFragment(this.getTeamFragment());
          break;
        case R.id.action_newsFeed:
          this.loadFragment(this.getNewsFragment());
          break;
      }
    }
  }
  
  private Fragment getEventsFragment() {
    if (this.eventsFragment == null) {
      this.eventsFragment = EventsFragment.newInstance();
    }
    
    return this.eventsFragment;
  }
  
  private Fragment getPhotoFragment() {
    if (this.photoFragment == null) {
      this.photoFragment = PhotoFragment.newInstance();
    }
    
    return this.photoFragment;
  }
  
  private Fragment getAboutUdaanFragment() {
    if (this.aboutUdaanFragment == null) {
      this.aboutUdaanFragment = AboutUdaanFragment.newInstance();
    }
    
    return this.aboutUdaanFragment;
  }
  
  private Fragment getTeamFragment() {
    if (this.teamFragment == null) {
      this.teamFragment = TeamFragment.newInstance();
    }
    
    return this.teamFragment;
  }
  
  private Fragment getNewsFragment() {
    if (this.newsFragment == null) {
      this.newsFragment = NewsFragment.newInstance();
    }
    
    return this.newsFragment;
  }
  
  private void loadFragment(Fragment fragment) {
    this.getSupportFragmentManager()
        .beginTransaction()
        .replace(
            this.dataBinding.contentContainer.getId(),
            fragment
        ).commit();
  }
}
