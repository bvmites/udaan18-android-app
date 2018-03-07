package com.udaan18.udaan18.android.events;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.udaan18.udaan18.android.R;
import com.udaan18.udaan18.android.databinding.FragmentTechEventBinding;

/**
 * Created by jack on 03-03-2018.
 */

public class TechEventFragment extends Fragment {
    private static int pos;
    private FragmentTechEventBinding binding;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TechEventAadapter adapter;

    public static TechEventFragment newInstance(int position) {
        TechEventFragment fragment = new TechEventFragment();
        pos = position;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tech_event, container, false);
        adapter = new TechEventAadapter(getFragmentManager(), getActivity(), pos);
        tabLayout = this.binding.techEventTablayout;
        viewPager = this.binding.techEventViewPager;
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        this.binding.techEventNestScrollView.setFillViewport(true);
        //this.dataBinding.combinedDetailsTabLayout.setBackground(context,R.color.colorPrimary);
        viewPager.setCurrentItem(0);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return this.binding.getRoot();
    }
}
