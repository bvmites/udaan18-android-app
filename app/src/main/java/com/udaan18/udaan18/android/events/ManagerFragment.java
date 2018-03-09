package com.udaan18.udaan18.android.events;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.udaan18.udaan18.android.R;
import com.udaan18.udaan18.android.model.eventCategory.Department;
import com.udaan18.udaan18.android.util.Helper;

import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;

/**
 * Created by pranshu on 12/3/17.
 */

public class ManagerFragment extends Fragment implements ManagerSection.ManagerCallItemClick {

    private static Department department;
    View rootView;
    private RecyclerView managerRecyclerView;
    private SectionedRecyclerViewAdapter managerAdapter;

    public static ManagerFragment getInstance(Department dep) {
        department = dep;
        ManagerFragment frag = new ManagerFragment();
        return frag;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_manager, container, false);

        //int position = this.getArguments().getInt(getString(R.string.activity_key_position), 0);
        try {

            this.managerRecyclerView = (RecyclerView) rootView.findViewById(R.id.new_manager_recycler_view);
            this.managerAdapter = new SectionedRecyclerViewAdapter();
            for (int index = 1; index <= 2; index++) {
                this.managerAdapter.addSection(new ManagerSection(department, index, this));
            }
            this.managerRecyclerView.setAdapter(managerAdapter);
            this.managerRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return rootView;
    }

    @Override
    public void onItemClick(int position, int sectionId) {
        Log.d("ManagerFragment", "onItemClick: " + position);
        if (sectionId == 1) {
            Helper.makeCall(ManagerFragment.this.department.getHeads().get(position - 1).getMobile(),
                    ManagerFragment.this.getContext());
        } else {
            int subtract = this.department.getHeads().size() + 2;
            Helper.makeCall(ManagerFragment.this.department.getCoHeads().get(position - subtract).getMobile(),
                    ManagerFragment.this.getContext());
        }
    }
}
