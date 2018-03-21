package com.udaan18.udaan18.android.team;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.udaan18.udaan18.android.R;
import com.udaan18.udaan18.android.model.eventCategory.Developer;
import com.udaan18.udaan18.android.util.Helper;
import com.udaan18.udaan18.android.util.listeners.ListItemClickCallBack;

import java.util.List;


public class DeveloperAdapter extends RecyclerView.Adapter<DeveloperAdapter.ViewHolder> {

    ListItemClickCallBack itemClickCallBack;
    private List<Developer> developers;
    private Context context;
    private int lastPosition = -1;
    private Typeface custom_font_dec;
    private Typeface custom_font_label;
    private Typeface custom_font_reg;

    public DeveloperAdapter(List<Developer> developers, Context context) {
        this.developers = developers;
        this.context = context;
        custom_font_dec = Typeface.createFromAsset(context.getAssets(), "fonts/ProzaLibreBold.ttf");
        custom_font_label = Typeface.createFromAsset(context.getAssets(), "fonts/ProzaLibreSemiBold.ttf");
        custom_font_reg = Typeface.createFromAsset(context.getAssets(), "fonts/ProzaLibreRegular.ttf");
    }

    private static int getColorId(String category, Context context) {
        int id = context
                .getResources()
                .getIdentifier("color_" + Helper.getResourceNameFromTitle(category), "color", context.getPackageName());

        if (id == 0) {
            id = R.color.colorTeal;
        }
        return id;
    }

    private static int getIconId(String category, Context context) {
        int id = context
                .getResources()
                .getIdentifier(Helper.getResourceNameFromTitle(category), "drawable", context.getPackageName());

        if (id == 0) {
            id = R.drawable.github;
        }
        return id;
    }

    public void setItemClickCallBack(ListItemClickCallBack itemClickCallBack) {
        this.itemClickCallBack = itemClickCallBack;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView card = (CardView) LayoutInflater.from(context).inflate(R.layout.developer_list_item, parent, false);
        return new ViewHolder(card);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        int colorId = DeveloperAdapter.getColorId(developers.get(position).getCategory(), this.context);
        int iconId = DeveloperAdapter.getIconId(developers.get(position).getCategory(), this.context);

        holder.categoryIcon.setImageResource(iconId);
        holder.categoryIcon.setBackgroundColor(ContextCompat.getColor(this.context, colorId));
        holder.title.setText(developers.get(position).getTitle());
        holder.name.setText(developers.get(position).getName());
    }


    @Override
    public int getItemCount() {
        return this.developers.size();
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
            this.title.setTypeface(custom_font_reg);
            this.name.setTypeface(custom_font_label);

            this.email.setOnClickListener(this);
            this.github.setOnClickListener(this);
            this.mobile.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            itemClickCallBack.onItemClick(getAdapterPosition(), view.getId());
        }

    }
}
