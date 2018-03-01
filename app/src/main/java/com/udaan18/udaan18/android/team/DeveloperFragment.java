package com.udaan18.udaan18.android.team;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.udaan18.udaan18.android.R;
import com.udaan18.udaan18.android.model.eventCategory.Developer;
import com.udaan18.udaan18.android.util.Helper;
import com.udaan18.udaan18.android.util.RestClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DeveloperFragment extends Fragment implements AdapterView.OnItemClickListener {

    private View rootView;
    private RecyclerView developerRecyclerView;
    private DeveloperAdapter developerAdapter;

    private List<Developer> developersArrayList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_developer, container, false);

        try {
            RestClient client = new RestClient();
            Call<List<Developer>> call = client.getApiHelper().getDevelopers();

            call.enqueue(new Callback<List<Developer>>() {
                @Override
                public void onResponse(Call<List<Developer>> call, Response<List<Developer>> response) {
                    developersArrayList = response.body();
                    Toast.makeText(getContext(), "Complete :" + developersArrayList.size(), Toast.LENGTH_LONG).show();
                    afterIn();
                }

                @Override
                public void onFailure(Call<List<Developer>> call, Throwable t) {
                    Toast.makeText(getContext(), "error in fetching", Toast.LENGTH_LONG).show();
                }
            });
            //developerAdapter.

//      developerRecyclerView.setNestedScrollingEnabled(false);

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "Error in developer freg: " + e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
        }
        return rootView;
    }

    void afterIn() {
        this.developerRecyclerView = (RecyclerView) rootView.findViewById(R.id.developer_recyclerView);
        developerAdapter = new DeveloperAdapter(this.developersArrayList, this.getContext());

        this.developerRecyclerView.setLayoutManager(new GridLayoutManager(this.getContext(), 1, LinearLayoutManager.VERTICAL, false));
        this.developerRecyclerView.setAdapter(developerAdapter);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (view.getId()) {
            case R.id.developer_mobile:
                Helper.makeCall(this.developersArrayList.get(position).getMobile(), this.getContext());
                break;
            case R.id.developer_email:
                Helper.sendEmail(this.developersArrayList.get(position).getEmail(), this.getContext());
                break;
            case R.id.developer_github:
                Helper.openUrlInBrowser(this.developersArrayList.get(position).getGithub(), this.getContext());
                break;
        }
    }
}
