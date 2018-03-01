package com.udaan18.udaan18.android.team;

import android.content.Context;
import android.content.Intent;
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
import com.udaan18.udaan18.android.util.RestClient;

import java.util.ArrayList;
import java.util.List;

import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TeamUdaanFragment extends Fragment {

    private View rootView;
    private List<Category> teamUdaan;

    private RecyclerView recyclerView;
    private SectionedRecyclerViewAdapter teamUdaanAdapter;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, TeamUdaanFragment.class);
        context.startActivity(intent);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        this.rootView = inflater.inflate(R.layout.fragment_team_udaan, container, false);

        this.initializeElements(rootView);
        return rootView;
    }

    private void initializeElements(View rootView) {
        try {
            //this.teamUdaan = DataSingleton.getInstance(this.getActivity()).getTeamUdaan();
            RestClient client = new RestClient();
            Call<List<Category>> call = client.getApiHelper().getCategory();
            call.enqueue(new Callback<List<Category>>() {
                @Override
                public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                    teamUdaan = response.body();
                    Toast.makeText(getContext(), "done Team udan", Toast.LENGTH_LONG).show();
                    Log.d("Value-Size", "onResponse: " + teamUdaan.size());
                    afterT();
                }

                @Override
                public void onFailure(Call<List<Category>> call, Throwable t) {
                    Toast.makeText(getContext(), "error Team udan: fetching : " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "Error", Toast.LENGTH_LONG).show();
            this.teamUdaan = new ArrayList<>();
        }


//    this.recyclerView.setNestedScrollingEnabled(false);
    }

    void afterT() {
        this.recyclerView = (RecyclerView) this.rootView.findViewById(R.id.team_recycler_view);
        this.teamUdaanAdapter = new SectionedRecyclerViewAdapter();
        for (int index = 0; index < this.teamUdaan.size(); index++) {
            this.teamUdaanAdapter.addSection(String.valueOf(index), new TeamSection(this.teamUdaan.get(index)));
        }
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false));
        this.recyclerView.setAdapter(this.teamUdaanAdapter);
    }
}
