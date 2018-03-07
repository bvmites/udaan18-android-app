package com.udaan18.udaan18.android.events;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.udaan18.udaan18.android.util.SharedPreferenceHelper;

import org.json.JSONException;

/**
 * Created by jack on 03-03-2018.
 */

public class TechEventAadapter extends FragmentStatePagerAdapter {

    private String title[] = {"Event", "Head"};
    private Activity activity;
    private int pos;

    public TechEventAadapter(FragmentManager fm, Activity activity, int position) {
        super(fm);
        this.activity = activity;
        this.pos = position;

    }

    @Override
    public Fragment getItem(int position) {

        Fragment fragment = null;

        switch (position) {
            case 0:
                try {
                    // Toast.makeText(activity.getApplicationContext(),"position="+SharedPreferenceHelper.getInstance(activity).getDepartmentsList().get(this.position).getEvents(),Toast.LENGTH_LONG).show();
                    fragment = EventsFragment.newInstance(SharedPreferenceHelper.getInstance(activity).getDepartmentsList().get(this.pos).getEvents());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case 1:
                try {
                    fragment = ManagerFragment.getInstance(SharedPreferenceHelper.getInstance(activity).getDepartmentsList().get(pos));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
