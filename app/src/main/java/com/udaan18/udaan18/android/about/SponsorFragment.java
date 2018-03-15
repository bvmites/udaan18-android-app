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
 * Created by jack on 12-03-2018.
 */

public class SponsorFragment extends Fragment {
    private View rootView;

    public static SponsorFragment newInstance() {
        SponsorFragment fragment = new SponsorFragment();
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).setToolTitle("Sponsors".toUpperCase());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_sponsor, container, false);
        ((MainActivity) getActivity()).setBack();
        return rootView;
    }
}
