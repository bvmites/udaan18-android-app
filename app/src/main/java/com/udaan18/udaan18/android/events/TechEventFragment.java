package com.udaan18.udaan18.android.events;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.udaan18.udaan18.android.R;
import com.udaan18.udaan18.android.databinding.FragmentTechEventBinding;
import com.udaan18.udaan18.android.mainnavigation.MainActivity;
import com.udaan18.udaan18.android.model.eventCategory.Department;
import com.udaan18.udaan18.android.util.SharedPreferenceHelper;

import org.json.JSONException;

/**
 * Created by jack on 03-03-2018.
 */

public class TechEventFragment extends Fragment {
    private static final String KEY_POSITION = "position";
    private static int id;
    private int pos;
    private FragmentTechEventBinding binding;
    private TechEventAadapter adapter;
    private Department department;

    public static TechEventFragment newInstance(int position, int i) {
        TechEventFragment fragment = new TechEventFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_POSITION, position);
        id = i;
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tech_event, container, false);
        this.pos = this.getArguments().getInt(KEY_POSITION, 0);


        department = null;
        try {
            department = SharedPreferenceHelper.getInstance(getContext()).getDepartmentsList().get(pos);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        adapter = new TechEventAadapter(
                getChildFragmentManager(),
                department
        );
        this.binding.techEventViewPager.setAdapter(adapter);
        this.binding.techEventTablayout.setupWithViewPager(this.binding.techEventViewPager);

        this.binding.techEventTablayout.setBackgroundColor(ContextCompat.getColor(this.getContext(), id));
        return this.binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).setToolTitle(department.getAlis().toUpperCase());
    }
}
