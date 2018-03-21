package com.udaan18.udaan18.android.events;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.udaan18.udaan18.android.R;
import com.udaan18.udaan18.android.model.eventCategory.Department;
import com.udaan18.udaan18.android.model.eventCategory.Manager;
import com.udaan18.udaan18.android.util.Helper;

import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection;

/**
 * Created by pranshu on 12/3/17.
 */

public class ManagerSection extends StatelessSection {


    private Department department;
    private ManagerCallItemClick listItemClickCallBack;
    private int sectionId;  //1 for Heads and 2 for coHeads
    private String resourceName;
    private Typeface custom_font_dec;
    private Typeface custom_font_label;
    private Typeface custom_font_reg;


    public ManagerSection(Department department, int sectionId, ManagerCallItemClick listItemClickCallBack, Context context) {
        super(R.layout.header_section_item_branch_manager, R.layout.item_section_team_branch_heads);
        this.department = department;
        this.sectionId = sectionId;
        this.listItemClickCallBack = listItemClickCallBack;
        custom_font_dec = Typeface.createFromAsset(context.getAssets(), "fonts/ProzaLibreBold.ttf");
        custom_font_label = Typeface.createFromAsset(context.getAssets(), "fonts/ProzaLibreSemiBold.ttf");
        custom_font_reg = Typeface.createFromAsset(context.getAssets(), "fonts/ProzaLibreRegular.ttf");
    }

    @Override
    public int getContentItemsTotal() {
        if (sectionId == 1) {
            return department.getHeads().size();
        } else {
            return department.getCoHeads().size();
        }
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        Manager manager;
        if (sectionId == 1) {
            manager = department.getHeads().get(position);
        } else {
            manager = department.getCoHeads().get(position);
        }

        ((ItemViewHolder) holder).textViewName.setText(manager.getName());
        ((ItemViewHolder) holder).textViewTitle.setText(manager.getMobile());
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
        super.onBindHeaderViewHolder(holder);
        String headerText;
        if (sectionId == 1) {
            headerText = "Heads";
        } else {
            headerText = "CoHeads";
        }
        ((HeaderViewHolder) holder).textViewTitle.setText(headerText);
    }

    @Override
    public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
        return new HeaderViewHolder(view);
    }

    public interface ManagerCallItemClick {
        public void onItemClick(int position, int sectionId);
    }

    private class HeaderViewHolder extends RecyclerView.ViewHolder {
        private View rootView;
        private AppCompatTextView textViewTitle;

        public HeaderViewHolder(View headerView) {
            super(headerView);
            this.rootView = headerView;
            this.textViewTitle = (AppCompatTextView) this.rootView.findViewById(R.id.header_section_team_title_branch);
            this.textViewTitle.setTypeface(custom_font_dec);

            //textViewTitle.setTextColor(ContextCompat.getColor(rootView.getContext(), R.color.black));
        }
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private CardView rootView;
        private AppCompatTextView textViewName;
        private AppCompatTextView textViewTitle;

        public ItemViewHolder(View itemView) {
            super(itemView);

            this.rootView = (CardView) itemView;
            this.textViewName = (AppCompatTextView) this.rootView.findViewById(R.id.item_section_team_name_branch);
            this.textViewTitle = (AppCompatTextView) this.rootView.findViewById(R.id.item_section_team_title_branch);

            this.rootView.setCardBackgroundColor(ContextCompat.getColor(this.rootView.getContext(), Helper.color_id));
            this.textViewName.setTextColor(ContextCompat.getColor(rootView.getContext(), R.color.colorWhite));
            this.textViewTitle.setTextColor(ContextCompat.getColor(rootView.getContext(), R.color.colorWhite));
            this.textViewName.setTypeface(custom_font_label);
            this.textViewTitle.setTypeface(custom_font_reg);
            rootView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listItemClickCallBack.onItemClick(getAdapterPosition(), sectionId);
        }
    }
}
