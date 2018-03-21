package com.udaan18.udaan18.android.team;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.udaan18.udaan18.android.R;
import com.udaan18.udaan18.android.databinding.FragmentCombinedDetailsBinding;
import com.udaan18.udaan18.android.mainnavigation.MainActivity;


/**
 * Creator: Varun Barad
 * Date: 26-02-2018
 * Project: udaan18-android-app
 */
public class ContainedDetail extends Fragment {
    public static int location = 4;
    private FragmentCombinedDetailsBinding dataBinding;
    private CombinedDetailsAdapter adapter;

    public static ContainedDetail newInstance() {
        ContainedDetail fragment = new ContainedDetail();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_combined_details, container, false);
        ((MainActivity) getActivity()).removeTitle("Team");
        ((MainActivity) getActivity()).setAllColorChanage(R.color.color_team);
        this.adapter = new CombinedDetailsAdapter(getChildFragmentManager());
        this.dataBinding.combinedDetailsViewPager.setAdapter(adapter);
        this.dataBinding.combinedDetailsTabLayout.setupWithViewPager(this.dataBinding.combinedDetailsViewPager);
        // this.dataBinding.combinedDetailsTabLayout.setBackgroundColor(ContextCompat.getColor(this.getContext(), ));
        this.dataBinding.combinedDetailsViewPager.setCurrentItem(0);
        return this.dataBinding.getRoot();
    }
}
