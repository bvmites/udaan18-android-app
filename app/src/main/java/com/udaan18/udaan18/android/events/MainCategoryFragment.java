package com.udaan18.udaan18.android.events;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.udaan18.udaan18.android.R;
import com.udaan18.udaan18.android.databinding.FragmentMainCategoryBinding;
import com.udaan18.udaan18.android.mainnavigation.MainActivity;

/**
 * Created by jack on 01-03-2018.
 */

public class MainCategoryFragment extends Fragment {
    FragmentMainCategoryBinding dataBinding;

    public static MainCategoryFragment newInstance() {
        MainCategoryFragment fragment = new MainCategoryFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_main_category, container, false);

        this.dataBinding
                .categoriesFragmentCardView1
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ((MainActivity) getActivity()).loadTechEvents();
                    }
                });

        return this.dataBinding.getRoot();
    }
}
