package com.udaan18.udaan18.android.mainnavigation;

import android.databinding.DataBindingUtil;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import com.udaan18.udaan18.android.R;
import com.udaan18.udaan18.android.about.AboutUdaanFragment;
import com.udaan18.udaan18.android.databinding.ActivityMainBinding;
import com.udaan18.udaan18.android.events.DepartmentFragment;
import com.udaan18.udaan18.android.events.EventDetailFragment;
import com.udaan18.udaan18.android.events.EventsFragment;
import com.udaan18.udaan18.android.events.MainCategoryFragment;
import com.udaan18.udaan18.android.events.TechEventFragment;
import com.udaan18.udaan18.android.model.eventCategory.Event;
import com.udaan18.udaan18.android.news.NewsFragment;
import com.udaan18.udaan18.android.photo.PhotoFragment;
import com.udaan18.udaan18.android.team.ContainedDetail;
import com.udaan18.udaan18.android.util.SharedPreferenceHelper;

import org.json.JSONException;

/**
 * Created by abhishek on 2/25/2018.
 */

public class MainActivity extends AppCompatActivity {
  private ActivityMainBinding dataBinding;
  
  private EventsFragment eventsFragment;
  private PhotoFragment photoFragment;
  private AboutUdaanFragment aboutUdaanFragment;
    private MainCategoryFragment category;
    private ContainedDetail containedDetail;
  private NewsFragment newsFragment;

  
  @IdRes
  private int currentSelectedSection;
  
  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);


    this.dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    this.dataBinding
        .bottomNavigation
        .setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
          @Override
          public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            MainActivity.this.handleBottomNavigationItemSelect(item.getItemId());
            return true;
          }
        });
      this.loadFragment(this.getCategoryFragment());
      this.dataBinding.bottomNavigation.setSelectedItemId(R.id.action_events);


  }
  
  private void handleBottomNavigationItemSelect(@IdRes int itemId) {
      // if (itemId != this.currentSelectedSection) {
      //  this.currentSelectedSection = itemId;
      switch (itemId) {
        case R.id.action_events:
            this.dataBinding.toolbarTitle.setText("Events");
            this.dataBinding.toolbarTitle.setBackgroundColor(getResources().getColor(R.color.color_events));
            this.dataBinding.bottomNavigation.setItemBackgroundResource(R.color.color_events);
            //removeBack(MainActivity.this);
            if (android.os.Build.VERSION.SDK_INT >= 21) {
                Window window = (MainActivity.this).getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.setStatusBarColor(this.getResources().getColor(R.color.color_events));
            }
            this.loadFragment(this.getCategoryFragment());
          break;
        case R.id.action_photo:
            this.dataBinding.toolbarTitle.setText("Photo");
            this.dataBinding.toolbarTitle.setBackgroundColor(getResources().getColor(R.color.color_photo));
            this.dataBinding.bottomNavigation.setItemBackgroundResource(R.color.color_photo);
            if (android.os.Build.VERSION.SDK_INT >= 21) {
                Window window = (MainActivity.this).getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.setStatusBarColor(this.getResources().getColor(R.color.color_photo));
            }
          this.loadFragment(this.getPhotoFragment());
          break;
        case R.id.action_aboutUdaan:
            this.dataBinding.toolbarTitle.setText("About Udaan");
            this.dataBinding.toolbarTitle.setBackgroundColor(getResources().getColor(R.color.color_about));
            this.dataBinding.bottomNavigation.setItemBackgroundResource(R.color.color_about);
            if (android.os.Build.VERSION.SDK_INT >= 21) {
                Window window = (MainActivity.this).getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.setStatusBarColor(this.getResources().getColor(R.color.color_about));
            }
          this.loadFragment(this.getAboutUdaanFragment());
          break;
        case R.id.action_team:
            this.dataBinding.toolbarTitle.setText("Team");
            this.dataBinding.toolbarTitle.setBackgroundColor(getResources().getColor(R.color.color_team));
            this.dataBinding.bottomNavigation.setItemBackgroundResource(R.color.color_team);
            if (android.os.Build.VERSION.SDK_INT >= 21) {
                Window window = (MainActivity.this).getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.setStatusBarColor(this.getResources().getColor(R.color.color_team));
            }
          /*this.dataBinding.tolMain.setVisibility(View.GONE);
          this.dataBinding.tolCom.setVisibility(View.VISIBLE);*/
            this.loadFragment(this.getContainedDetail());

          break;
        case R.id.action_newsFeed:
            this.dataBinding.toolbarTitle.setText("News");
            this.dataBinding.toolbarTitle.setBackgroundColor(getResources().getColor(R.color.color_news));
            this.dataBinding.bottomNavigation.setItemBackgroundResource(R.color.color_news);
            if (android.os.Build.VERSION.SDK_INT >= 21) {
                Window window = (MainActivity.this).getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.setStatusBarColor(this.getResources().getColor(R.color.color_news));
            }
            this.loadFragment(this.getNewsFragment());
          break;
      }
      // }
  }

    public void setBack() {
        setSupportActionBar(this.dataBinding.toolbar);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.color_events)));
    }

    public void removeBack() {
        this.setSupportActionBar(this.dataBinding.toolbar);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

  
  private Fragment getPhotoFragment() {
    if (this.photoFragment == null) {
      this.photoFragment = PhotoFragment.newInstance();
    }
    
    return this.photoFragment;
  }

    private Fragment getCategoryFragment() {
        removeBack();
        if (this.category == null) {
            this.category = MainCategoryFragment.newInstance();
        }
        return this.category;
    }
  
  private Fragment getAboutUdaanFragment() {
    if (this.aboutUdaanFragment == null) {
      this.aboutUdaanFragment = AboutUdaanFragment.newInstance();
    }
    
    return this.aboutUdaanFragment;
  }

    private Fragment getContainedDetail() {


        this.containedDetail = ContainedDetail.newInstance();


        return this.containedDetail;
  }
  
  private Fragment getNewsFragment() {
    if (this.newsFragment == null) {
      this.newsFragment = NewsFragment.newInstance();
    }
    
    return this.newsFragment;
  }
  
  private void loadFragment(Fragment fragment) {
      FragmentManager manager = this.getSupportFragmentManager();
      while (manager.getBackStackEntryCount() > 0) {
          manager.popBackStackImmediate();
      }

    this.getSupportFragmentManager()
        .beginTransaction()
        .replace(
            this.dataBinding.contentContainer.getId(),
            fragment
        ).commit();
  }

    private void loadFragmentWithBackstack(Fragment fragment) {
        this.getSupportFragmentManager()
                .beginTransaction()
                .replace(
                        this.dataBinding.contentContainer.getId(),
                        fragment
                ).addToBackStack(null)
                .commit();
        if (this.getSupportFragmentManager().getBackStackEntryCount() == 0) {
            removeBack();
        }
    }

    public void loadTechEvents(int position) {
        TechEventFragment fragment = TechEventFragment.newInstance(position);

        this.loadFragmentWithBackstack(fragment);
    }

    public void loadNonTechEvents() {
        EventsFragment fragment = null;
        try {
            fragment = EventsFragment.newInstance(SharedPreferenceHelper.getInstance(MainActivity.this).getNonTechList());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.loadFragmentWithBackstack(fragment);
    }

    public void loadCulturalEvents() {
        EventsFragment fragment = null;
        try {
            fragment = EventsFragment.newInstance(SharedPreferenceHelper.getInstance(MainActivity.this).getCulturalList());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.loadFragmentWithBackstack(fragment);
    }

    public void loadDepartments() {
        DepartmentFragment departmentFragment = null;

        departmentFragment = DepartmentFragment.newInstance();
        this.loadFragmentWithBackstack(departmentFragment);
    }

    public void getEventDetailFragment(Event event) {
        EventDetailFragment eventDetailFragment = null;
        eventDetailFragment = EventDetailFragment.newInstence(event);


        this.loadFragmentWithBackstack(eventDetailFragment);
    }
}
