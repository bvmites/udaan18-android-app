package com.udaan18.udaan18.android.events;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.udaan18.udaan18.android.R;
import com.udaan18.udaan18.android.adapters.EventAdapter;
import com.udaan18.udaan18.android.databinding.FragmentEventsBinding;
import com.udaan18.udaan18.android.mainnavigation.MainActivity;
import com.udaan18.udaan18.android.model.eventCategory.Event;
import com.udaan18.udaan18.android.util.listeners.ListItemClickCallBack;

import java.util.List;

/**
 * Creator: Varun Barad
 * Date: 26-02-2018
 * Project: udaan18-android-app
 */
public class EventsFragment extends Fragment implements ListItemClickCallBack {
    private static List<Event> events;
    private FragmentEventsBinding dataBinding;

    public static EventsFragment newInstance(List<Event> ev) {
        EventsFragment fragment = new EventsFragment();
        events = ev;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_events, container, false);
        fetchEvent();
        return this.dataBinding.getRoot();
    }

    public void fetchEvent() {
        //Toast.makeText(dataBinding.getRoot().getContext(),"Error1"+event.get(0).getEvents().size(),Toast.LENGTH_LONG).show();
        RecyclerView view = dataBinding.getRoot().findViewById(R.id.event_recycleview);
        EventAdapter adapter = new EventAdapter(events, getContext());

        view.setAdapter(adapter);

        view.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        adapter.setItemClickCallBack(this);

    }


    @Override
    public void onItemClick(int position, int viewId) {
        ((MainActivity) getActivity()).getEventDetailFragment(events.get(position));
        Toast.makeText(getContext(), "here" + position, Toast.LENGTH_LONG).show();
    }
}
