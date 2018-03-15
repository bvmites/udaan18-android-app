package com.udaan18.udaan18.android.events;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.udaan18.udaan18.android.R;
import com.udaan18.udaan18.android.adapters.RecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jack on 15-03-2018.
 */

public class DetailEventFragment extends Fragment {
    View rootView;
    RecyclerView recyclerView;

    public static DetailEventFragment newInstance() {
        DetailEventFragment fragment = new DetailEventFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_detail_event, container, false);
        recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        final RecyclerAdapter adapter = new RecyclerAdapter(getContext());
        recyclerView.setAdapter(adapter);

        //fill with empty objects
        final List<Object> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            list.add(new Object());
        }
        adapter.setItems(list);
        return rootView;
    }
}
