package com.udaan18.udaan18.android.team;

import android.content.Context;
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
import com.udaan18.udaan18.android.databinding.FragmentCombinedDetailsBinding;


/**
 * Creator: Varun Barad
 * Date: 26-02-2018
 * Project: udaan18-android-app
 */
public class ContainedDetail extends Fragment {
    private FragmentCombinedDetailsBinding dataBinding;
    private CombinedDetailsAdapter adapter;
    private ViewPager pager;
    private Context context;

    public static ContainedDetail newInstance() {
        ContainedDetail fragment = new ContainedDetail();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_combined_details, container, false);
        adapter = new CombinedDetailsAdapter(getFragmentManager());
        pager = this.dataBinding.combinedDetailsViewPager;
        context = this.dataBinding.getRoot().getContext();
        helpTab();
        return this.dataBinding.getRoot();
    }

    void helpTab() {
        pager.setAdapter(adapter);
        this.dataBinding.combinedDetailsTabLayout.setupWithViewPager(pager);
        this.dataBinding.combinedDetailsNestScrollView.setFillViewport(true);
        //this.dataBinding.combinedDetailsTabLayout.setBackground(context,R.color.colorPrimary);
        this.dataBinding.combinedDetailsViewPager.setCurrentItem(0);

        this.dataBinding.combinedDetailsViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(
                this.dataBinding.combinedDetailsTabLayout));
        this.dataBinding.combinedDetailsTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
