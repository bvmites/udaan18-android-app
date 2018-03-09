package com.udaan18.udaan18.android.about;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.udaan18.udaan18.android.R;
import com.udaan18.udaan18.android.mainnavigation.MainActivity;

/**
 * Creator: Varun Barad
 * Date: 26-02-2018
 * Project: udaan18-android-app
 */
public class AboutUdaanFragment extends Fragment {
    View rootView;

    public static AboutUdaanFragment newInstance() {
        AboutUdaanFragment fragment = new AboutUdaanFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_about_udaan, container, false);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) this.getActivity()).removeBack();
    }
}
