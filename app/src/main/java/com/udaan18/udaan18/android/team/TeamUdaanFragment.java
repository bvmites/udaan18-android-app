package com.udaan18.udaan18.android.team;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.udaan18.udaan18.android.R;
import com.udaan18.udaan18.android.model.eventCategory.Category;
import com.udaan18.udaan18.android.model.eventCategory.TeamSection;
import com.udaan18.udaan18.android.util.SharedPreferenceHelper;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;


public class TeamUdaanFragment extends Fragment implements TeamSection.ManagerCallItemClick {

    private View rootView;
    private List<Category> teamUdaan;

    private RecyclerView recyclerView;
    private SectionedRecyclerViewAdapter teamUdaanAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        this.rootView = inflater.inflate(R.layout.fragment_team_udaan, container, false);

        try {
            this.initializeElements(rootView);
            afterT();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return rootView;
    }

    private void initializeElements(View rootView) throws JSONException {
        teamUdaan = SharedPreferenceHelper.getInstance(getActivity()).getTeamUdaan();
        if (teamUdaan == null) {
            teamUdaan = new ArrayList<>();
            //Toast.makeText(getContext(), "the about is empty", Toast.LENGTH_LONG).show();
        }
    }

    void afterT() {
        this.recyclerView = (RecyclerView) this.rootView.findViewById(R.id.team_recycler_view);
        this.teamUdaanAdapter = new SectionedRecyclerViewAdapter();
        for (int index = 0; index < this.teamUdaan.size(); index++) {
            this.teamUdaanAdapter.addSection(String.valueOf(index), new TeamSection(this.teamUdaan.get(index), index, this, getContext()));
        }
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false));
        this.recyclerView.setAdapter(this.teamUdaanAdapter);
    }

    @Override
    public void onItemClick(int position, int sectionId) {
        Log.d("ManagerFragment", "onItemClick: " + position);
        if (sectionId == 1) {
            Toast.makeText(getContext(), "hello", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "hello again", Toast.LENGTH_SHORT).show();
        }
    }
}
