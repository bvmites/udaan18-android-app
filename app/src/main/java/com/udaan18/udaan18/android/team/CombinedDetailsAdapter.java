package com.udaan18.udaan18.android.team;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


/**
 * Created by pranshu on 15/3/17.
 */

public class CombinedDetailsAdapter extends FragmentStatePagerAdapter {

    private String title[] = {"TEAM UDAAN", "DEVELOPERS"};

    public CombinedDetailsAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        Fragment fragment = null;

        switch (position) {
            case 0:
                fragment = new TeamUdaanFragment();
                break;
            case 1:
                fragment = new DeveloperFragment();
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
