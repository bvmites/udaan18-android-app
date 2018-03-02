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

import com.udaan18.udaan18.android.R;
import com.udaan18.udaan18.android.model.eventCategory.Developer;
import com.udaan18.udaan18.android.util.Helper;
import com.udaan18.udaan18.android.util.SharedPreferenceHelper;

import org.json.JSONException;

import java.util.List;


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
            afterIn();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return rootView;
    }

    void afterIn() throws JSONException {
        developersArrayList = SharedPreferenceHelper.getInstance(getActivity()).getDevelopersList();
        this.developerRecyclerView = (RecyclerView) rootView.findViewById(R.id.developer_recyclerView);
        developerAdapter = new DeveloperAdapter(developersArrayList, this.getContext());

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
