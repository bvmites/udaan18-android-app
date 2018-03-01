package com.udaan18.udaan18.android.team;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.udaan18.udaan18.android.R;
import com.udaan18.udaan18.android.model.eventCategory.Developer;

import java.util.List;


public class DeveloperAdapter extends RecyclerView.Adapter<DeveloperAdapter.ViewHolder> {

    private List<Developer> developers;
    private Context context;

    private int lastPosition = -1;

    public DeveloperAdapter(List<Developer> developers, Context context) {
        this.developers = developers;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView card = (CardView) LayoutInflater.from(context).inflate(R.layout.developer_list_item, parent, false);
        return new ViewHolder(card);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // int colorId = DeveloperAdapter.getColorId(developers.get(position).getCategory(), this.context);
        // int iconId = DeveloperAdapter.getIconId(developers.get(position).getCategory(), this.context);

        // holder.categoryIcon.setImageResource(iconId);
        // holder.categoryIcon.setBackgroundColor(ContextCompat.getColor(this.context, colorId));
        holder.title.setText(developers.get(position).getTitle());
        holder.name.setText(developers.get(position).getName());

        //  setAnimation(holder.container, position);
    }


    @Override
    public int getItemCount() {
        return this.developers.size();
    }

    @Override
    public void onViewDetachedFromWindow(ViewHolder holder) {
        holder.clearAnimation();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private CardView container;
        private AppCompatImageView categoryIcon;
        private AppCompatTextView title;
        private AppCompatTextView name;
        private AppCompatImageView mobile;
        private AppCompatImageView email;
        private AppCompatImageView github;

        public ViewHolder(CardView itemView) {
            super(itemView);
            this.container = itemView;
            this.categoryIcon = (AppCompatImageView) this.container.findViewById(R.id.developer_category_icon);
            this.title = (AppCompatTextView) this.container.findViewById(R.id.developer_title);
            this.name = (AppCompatTextView) this.container.findViewById(R.id.developer_name);
            this.mobile = (AppCompatImageView) this.container.findViewById(R.id.developer_mobile);
            this.email = (AppCompatImageView) this.container.findViewById(R.id.developer_email);
            this.github = (AppCompatImageView) this.container.findViewById(R.id.developer_github);

            this.email.setOnClickListener(this);
            this.github.setOnClickListener(this);
            this.mobile.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            //itemClickCallBack.onItemClick(getAdapterPosition(), view.getId());
        }

        public void clearAnimation() {
            container.clearAnimation();
        }
    }
}
