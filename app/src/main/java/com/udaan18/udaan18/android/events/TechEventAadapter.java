package com.udaan18.udaan18.android.events;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.udaan18.udaan18.android.model.eventCategory.Department;

/**
 * Created by jack on 03-03-2018.
 */

public class TechEventAadapter extends FragmentStatePagerAdapter {

    private String title[] = {"Event", "Head"};
    private Department department;

    public TechEventAadapter(FragmentManager fm, Department department) {
        super(fm);
        this.department = department;

    }

    @Override
    public Fragment getItem(int position) {

        Fragment fragment = null;

        switch (position) {
            case 0:
                fragment = EventsFragment.newInstance(department.getEvents(), department.getAlis().toUpperCase());
                break;
            case 1:
                fragment = ManagerFragment.getInstance(department);
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
