package com.udaan18.udaan18.android.model.eventCategory;

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

    public TeamSection(Category category) {
        super(R.layout.header_section_team, R.layout.item_section_team);
        this.category = category;
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

    private class HeaderViewHolder extends RecyclerView.ViewHolder {
        private View rootView;
        private AppCompatTextView textViewTitle;

        public HeaderViewHolder(View headerView) {
            super(headerView);
            this.rootView = headerView;
            this.textViewTitle = (AppCompatTextView) this.rootView.findViewById(R.id.header_section_team_title);
        }
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder {
        private View rootView;
        private AppCompatTextView textViewName;
        private AppCompatTextView textViewTitle;

        public ItemViewHolder(View itemView) {
            super(itemView);

            this.rootView = itemView;
            this.textViewName = (AppCompatTextView) this.rootView.findViewById(R.id.item_section_team_name);
            this.textViewTitle = (AppCompatTextView) this.rootView.findViewById(R.id.item_section_team_title);
        }
    }
}
