package com.udaan18.udaan18.android.events;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.Space;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.MaterialDialog;
import com.udaan18.udaan18.android.R;
import com.udaan18.udaan18.android.model.eventCategory.Event;
import com.udaan18.udaan18.android.util.Helper;
import com.udaan18.udaan18.android.util.listeners.ListItemClickCallBack;

/**
 * Created by jack on 11-03-2018.
 */

public class EventDescriptionFragment extends Fragment implements View.OnClickListener {
    private static Event event;
    View rootView;
    private AppCompatTextView textViewTagLine;
    private Space spaceDescriptionParticipants;
    private AppCompatTextView textViewParticipantsLabel;
    private AppCompatTextView textViewParticipants;
    private Space spaceParticipantsRounds;
    private AppCompatTextView textViewRoundsLabel;
    private AppCompatTextView textViewRounds;
    private Space spaceRoundsFees;
    private AppCompatTextView textViewFeesLabel;
    private AppCompatTextView textViewFees;
    private Space spaceFeesContact;
    private AppCompatButton buttonContact;
    private AppCompatImageView departmentImageView;
    private AppCompatTextView textViewPrize;
    private AppCompatTextView textViewPrizeLabel;
    private Space spacePrize;
    private String resourceName;

    public static EventDescriptionFragment newInstence(Event ev) {
        EventDescriptionFragment fragment = new EventDescriptionFragment();
        event = ev;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_event_description, container, false);
        initObjects();
        fillData();
        return rootView;
    }

    public void initObjects() {

        this.spaceDescriptionParticipants = rootView.findViewById(R.id.space_event_details_description_participants);
        this.textViewParticipantsLabel = (AppCompatTextView) rootView.findViewById(R.id.text_view_event_details_participants_label);
        this.textViewParticipants = (AppCompatTextView) rootView.findViewById(R.id.text_view_event_participants);
        this.spaceParticipantsRounds = (Space) rootView.findViewById(R.id.space_event_details_participants_rounds);
        this.textViewRoundsLabel = (AppCompatTextView) rootView.findViewById(R.id.level_1);
        this.textViewRounds = (AppCompatTextView) rootView.findViewById(R.id.text_view_event_details_level1);
        this.spaceRoundsFees = (Space) rootView.findViewById(R.id.space_event_details_rounds_fees);
        this.textViewFeesLabel = (AppCompatTextView) rootView.findViewById(R.id.text_view_event_details_fees_label);
        this.textViewFees = (AppCompatTextView) rootView.findViewById(R.id.text_view_event_details_fees);
        this.spaceFeesContact = (Space) rootView.findViewById(R.id.space_event_details_fees_contact);
        this.buttonContact = (AppCompatButton) rootView.findViewById(R.id.button_event_details_contact);
        this.textViewTagLine = rootView.findViewById(R.id.text_view_event_details_tagline);
        this.textViewPrizeLabel = (AppCompatTextView) rootView.findViewById(R.id.text_view_event_details_prize_label);
        this.textViewPrize = (AppCompatTextView) rootView.findViewById(R.id.text_view_event_details_prize);
        this.spacePrize = (Space) rootView.findViewById(R.id.space_event_details_fees_contact1);
        this.buttonContact.setOnClickListener(this);
    }

    public void fillData() {
        if (this.event.getTag() != null && this.event.getTag().length() > 0) {
            this.textViewTagLine.setText(this.event.getTag());
        }

        if (this.event.getParticipants() != null && this.event.getParticipants() > 0) {
            this.textViewParticipants.setText("" + this.event.getParticipants());
        } else {
            this.spaceDescriptionParticipants.setVisibility(View.GONE);
            this.textViewParticipantsLabel.setVisibility(View.GONE);
            this.textViewParticipants.setVisibility(View.GONE);
        }

        if (this.event.getRoundsDescription().length() > 0) {
            this.textViewRounds.setText(this.event.getRoundsDescription());
        } else {
            this.spaceParticipantsRounds.setVisibility(View.GONE);
            this.textViewRoundsLabel.setVisibility(View.GONE);
            this.textViewRounds.setVisibility(View.GONE);
        }

        if (this.event.getFees() != null && this.event.getFees() > 0) {
            this.textViewFees.setText(this.getString(R.string.symbol_rupee) + " " + this.event.getFees());
        } else {
            this.spaceRoundsFees.setVisibility(View.GONE);
            this.textViewFeesLabel.setVisibility(View.GONE);
            this.textViewFees.setVisibility(View.GONE);
        }

        if (!(this.event.getManagers() != null && this.event.getManagers().size() > 0)) {
            this.spaceFeesContact.setVisibility(View.GONE);
            this.buttonContact.setVisibility(View.GONE);
        }

        if (this.event.getPrizeDescription(this.getString(R.string.symbol_rupee)).length() > 0) {
            this.textViewPrize.setText(this.event.getPrizeDescription(this.getString(R.string.symbol_rupee)));
        } else {
            this.spacePrize.setVisibility(View.GONE);
            this.textViewPrizeLabel.setVisibility(View.GONE);
            this.textViewPrize.setVisibility(View.GONE);
        }

    }

    private void showManagersDialog() {
        ManagerAdapter adapter = new ManagerAdapter(event.getManagers(),
                this.resourceName,
                getContext(),
                new ListItemClickCallBack() {
                    @Override
                    public void onItemClick(int position, int viewId) {
                        Helper.makeCall(
                                EventDescriptionFragment.event.getManagers().get(position).getMobile(),
                                getContext()
                        );
                    }
                }
        );

        MaterialDialog dialog =
                new MaterialDialog.Builder(getContext())
                        .title("Event Managers")
                        .adapter(adapter, new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false))
                        .cancelable(true)
                        .build();

        dialog.show();
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.button_event_details_contact:
                this.showManagersDialog();
                break;
        }
    }
}

