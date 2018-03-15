package com.udaan18.udaan18.android.events;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.udaan18.udaan18.android.R;
import com.udaan18.udaan18.android.adapters.RecyclerAdapter;
import com.udaan18.udaan18.android.model.eventCategory.Event;

/**
 * Created by jack on 15-03-2018.
 */

public class DetailEventFragment extends Fragment {
    private static Event event;
    RecyclerAdapter adapter;
    private View rootView;
    private RecyclerView recyclerView;
    private CardView eventPrizeCard;
    private AppCompatTextView textViewDetailEventParticipant;
    private AppCompatTextView textViewDetailEventFees;
    private AppCompatTextView textViewDetailEventPrize;
    private AppCompatTextView textViewDetailEventTagline;

    public static DetailEventFragment newInstance(Event evnt) {
        DetailEventFragment fragment = new DetailEventFragment();
        event = evnt;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_detail_event, container, false);
        recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new RecyclerAdapter(getContext());
        recyclerView.setAdapter(adapter);

        //fill with empty objects
        initComponent();
        fillData();
        return rootView;
    }

    public void initComponent() {
        textViewDetailEventParticipant = rootView.findViewById(R.id.text_view_detail_event_participants);
        textViewDetailEventFees = rootView.findViewById(R.id.text_view_detail_event_fees);
        textViewDetailEventPrize = rootView.findViewById(R.id.text_view_detail_event_prize);
        textViewDetailEventTagline = rootView.findViewById(R.id.event_tagline);
        eventPrizeCard = rootView.findViewById(R.id.event_prize_card);
    }

    public void fillData() {
        if (this.event.getTag() != null && this.event.getTag().length() > 0) {
            this.textViewDetailEventTagline.setText(this.event.getTag());
        }

        if (this.event.getParticipants() != null && this.event.getParticipants() > 0) {
            this.textViewDetailEventParticipant.setText("" + this.event.getParticipants());
        } else {
        }

        if (this.event.getRounds().size() > 0) {
            adapter.setItems(event.getRounds());
        } else {

        }

        if (this.event.getFees() != null && this.event.getFees() > 0) {
            this.textViewDetailEventFees.setText(this.getString(R.string.symbol_rupee) + " " + this.event.getFees());
        } else {
        }

        if (!(this.event.getManagers() != null && this.event.getManagers().size() > 0)) {

        }

        if (this.event.getPrizeDescription(this.getString(R.string.symbol_rupee)).length() > 0) {
            this.textViewDetailEventPrize.setText(this.event.getPrizeDescription(this.getString(R.string.symbol_rupee)));
        } else {
            this.eventPrizeCard.setVisibility(View.GONE);
        }
    }
}
