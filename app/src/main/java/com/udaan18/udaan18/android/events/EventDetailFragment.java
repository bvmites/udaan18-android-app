package com.udaan18.udaan18.android.events;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.udaan18.udaan18.android.R;
import com.udaan18.udaan18.android.databinding.FargmentEventDetailBinding;
import com.udaan18.udaan18.android.model.eventCategory.Event;

/**
 * Created by jack on 06-03-2018.
 */

public class EventDetailFragment extends Fragment {
    static Event event;
    FargmentEventDetailBinding binding;

    public static EventDetailFragment newInstence(Event evnt) {
        EventDetailFragment fragment = new EventDetailFragment();
        event = evnt;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.binding = DataBindingUtil.inflate(inflater, R.layout.fargment_event_detail, container, false);
        this.binding.textViewEventDetailsDescription.setText(event.getDescription());
        this.binding.textViewEventDetailsParticipants.setText(event.getParticipants());
        this.binding.textViewEventDetailsRounds.setText("" + event.getRounds());
        this.binding.textViewEventDetailsFees.setText(event.getFees());
        this.binding.textViewEventDetailsPrize.setText(event.getPrizes() + "");
        return this.binding.getRoot();
    }
}
