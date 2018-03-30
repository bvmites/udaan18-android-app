package com.udaan18.udaan18.android.model.eventCategory;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.udaan18.udaan18.android.R;

import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection;

/**
 * Creator: vbarad
 * Date: 2017-03-11
 * Project: udaan17-android-app
 */

public class TeamSection extends StatelessSection {


    private Category category;
    private ManagerCallItemClick listItemClickCallBack;
    private int sectionId;
    private Typeface custom_font_dec;
    private Typeface custom_font_label;
    private Typeface custom_font_reg;

    public TeamSection(Category category, int sectionId, ManagerCallItemClick listItemClickCallBack, Context context) {
        super(R.layout.header_section_team, R.layout.item_section_team);
        this.category = category;
        this.sectionId = sectionId;
        this.listItemClickCallBack = listItemClickCallBack;
        custom_font_dec = Typeface.createFromAsset(context.getAssets(), "fonts/ProzaLibreBold.ttf");
        custom_font_label = Typeface.createFromAsset(context.getAssets(), "fonts/ProzaLibreSemiBold.ttf");
        custom_font_reg = Typeface.createFromAsset(context.getAssets(), "fonts/ProzaLibreRegular.ttf");
    }

    @Override
    public int getContentItemsTotal() {
        return this.category.getMembers().size();
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        Member member = this.category.getMembers().get(position);

        ((ItemViewHolder) holder).textViewName.setText(member.getName());
        ((ItemViewHolder) holder).textViewTitle.setText(member.getTitle());
    }

    @Override
    public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
        return new HeaderViewHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
        super.onBindHeaderViewHolder(holder);

        ((HeaderViewHolder) holder).textViewTitle.setText(this.category.getCategory());
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
            this.textViewTitle = (AppCompatTextView) this.rootView.findViewById(R.id.header_section_team_title);
            this.textViewTitle.setTypeface(custom_font_dec);
        }
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private View rootView;
        private AppCompatTextView textViewName;
        private AppCompatTextView textViewTitle;

        public ItemViewHolder(View itemView) {
            super(itemView);

            this.rootView = itemView;
            this.textViewName = (AppCompatTextView) this.rootView.findViewById(R.id.item_section_team_name);
            this.textViewTitle = (AppCompatTextView) this.rootView.findViewById(R.id.item_section_team_title);
            this.textViewName.setTypeface(custom_font_label);
            this.textViewTitle.setTypeface(custom_font_reg);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listItemClickCallBack.onItemClick(getAdapterPosition(), sectionId);
        }
    }
}
