package com.udaan18.udaan18.android;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by abhishek on 2/26/2018.
 */

class ViewPagerAdapter extends FragmentStatePagerAdapter{
    int tabcount;
    public ViewPagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabcount=tabCount;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position)
        {

            case 0:
                OneFragment oneFragment=new OneFragment();
                return oneFragment;
            case 1:
                SecondFragment secondFragment=new SecondFragment();
                return secondFragment;
            case 2:
                ThirdFragment thirdFragment=new ThirdFragment();
                return thirdFragment;
            case 3:
                ForthFragment forthFragment=new ForthFragment();
                return forthFragment;
            case 4:
                FifthFragment fifthFragment=new FifthFragment();
                return fifthFragment;

        }
        return null;
    }

    @Override
    public int getCount() {
        return tabcount;
    }
}
