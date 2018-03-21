package com.udaan18.udaan18.android.events;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.MaterialDialog;
import com.udaan18.udaan18.android.R;
import com.udaan18.udaan18.android.adapters.RecyclerAdapter;
import com.udaan18.udaan18.android.model.eventCategory.Event;
import com.udaan18.udaan18.android.util.Helper;
import com.udaan18.udaan18.android.util.listeners.ListItemClickCallBack;

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
    private AppCompatTextView textViewDetailEventParticipantLabel;
    private AppCompatTextView textViewDetailEventFeesLabel;
    private AppCompatTextView textViewDetailEventPrizeLabel;
    private String resourceName;
    private Typeface custom_font_dec;
    private Typeface custom_font_label;
    private Typeface custom_font_reg;
    public static DetailEventFragment newInstance(Event evnt) {
        DetailEventFragment fragment = new DetailEventFragment();
        event = evnt;
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.manager_contact, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.conact_manager:
                showManagersDialog();
                break;
        }
        return super.onOptionsItemSelected(item);
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
        custom_font_dec = Typeface.createFromAsset(getContext().getAssets(), "fonts/ProzaLibreBold.ttf");
        custom_font_label = Typeface.createFromAsset(getContext().getAssets(), "fonts/ProzaLibreSemiBold.ttf");
        custom_font_reg = Typeface.createFromAsset(getContext().getAssets(), "fonts/ProzaLibreRegular.ttf");

        textViewDetailEventParticipant = rootView.findViewById(R.id.text_view_detail_event_participants);
        textViewDetailEventFees = rootView.findViewById(R.id.text_view_detail_event_fees);
        textViewDetailEventPrize = rootView.findViewById(R.id.text_view_detail_event_prize);
        textViewDetailEventTagline = rootView.findViewById(R.id.event_tagline);
        eventPrizeCard = rootView.findViewById(R.id.event_prize_card);
        textViewDetailEventFeesLabel = rootView.findViewById(R.id.text_view_detail_event_fees_label);
        textViewDetailEventParticipantLabel = rootView.findViewById(R.id.text_view_details_event_participants_label);
        textViewDetailEventPrizeLabel = rootView.findViewById(R.id.text_view_event_details_prize_label);

        textViewDetailEventTagline.setTypeface(custom_font_dec);
        textViewDetailEventFeesLabel.setTypeface(custom_font_label);
        textViewDetailEventPrizeLabel.setTypeface(custom_font_label);
        textViewDetailEventParticipantLabel.setTypeface(custom_font_label);
        textViewDetailEventFees.setTypeface(custom_font_reg);
        textViewDetailEventParticipant.setTypeface(custom_font_reg);
        textViewDetailEventPrize.setTypeface(custom_font_reg);


        resourceName = Helper.getResourceNameFromTitle(getActivity().getIntent().getStringExtra(this.getString(R.string.activity_key_title_name)));
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

    private void showManagersDialog() {

        ManagerAdapter adapter = new ManagerAdapter(this.event.getManagers(),
                this.resourceName,
                getContext(),
                new ListItemClickCallBack() {
                    @Override
                    public void onItemClick(int position, int viewId) {
                        Helper.makeCall(
                                event.getManagers().get(position).getMobile(),
                                getContext()
                        );
                    }
                }
        );

        MaterialDialog dialog =
                new MaterialDialog.Builder(getContext())
                        .title("Event Managers").typeface(custom_font_dec, custom_font_reg)
                        .adapter(adapter, new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false))
                        .cancelable(true)
                        .build();

        dialog.show();
    }
}
