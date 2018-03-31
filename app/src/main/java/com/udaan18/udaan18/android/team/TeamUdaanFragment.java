package com.udaan18.udaan18.android.team;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.udaan18.udaan18.android.R;
import com.udaan18.udaan18.android.model.eventCategory.Category;
import com.udaan18.udaan18.android.model.eventCategory.TeamSection;
import com.udaan18.udaan18.android.util.Helper;
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
        int sub = 0;
        if (sectionId == 0) {
            Helper.makeCall(teamUdaan.get(sectionId).getMembers().get(position - 1).getMobile(), getContext());
            //Toast.makeText(getContext(), "" + teamUdaan.get(sectionId).getMembers().get(position - 1).ge, Toast.LENGTH_SHORT).show();
        } else {
            for (int i = 0; i < sectionId; i++) {
                sub += teamUdaan.get(i).getMembers().size();
            }
            sub += sectionId + 1;
            Helper.makeCall(teamUdaan.get(sectionId).getMembers().get(position - sub).getMobile(), getContext());
            //Toast.makeText(getContext(), "" + teamUdaan.get(sectionId).getMembers().get(position - sub).getName(), Toast.LENGTH_SHORT).show();

        }
//        switch (sectionId){
//            case 1:
//                Toast.makeText(getContext(),""+ teamUdaan.get())
//                break;
//            case 2:
//                break;
//            case 3:
//                break;
//            case 4:
//                break;
//            case 5:
//                break;
//            case 6:
//                break;
//            case 7:
//                break;
//            case 8:
//                break;
//            case 9:
//                break;
//            case 10:
//                break;
//            case 11:
//                break;
//            case 12:
//                break;
//        }
    }
}
