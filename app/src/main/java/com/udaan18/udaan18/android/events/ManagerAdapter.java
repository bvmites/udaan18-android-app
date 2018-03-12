package com.udaan18.udaan18.android.events;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.udaan18.udaan18.android.R;
import com.udaan18.udaan18.android.model.eventCategory.Manager;
import com.udaan18.udaan18.android.util.listeners.ListItemClickCallBack;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by pranshu on 7/3/17.
 */

public class ManagerAdapter extends RecyclerView.Adapter<ManagerAdapter.ViewHolder> {

    private Context context;
    private ListItemClickCallBack itemClickCallBack;
    private List<Manager> managers;
    private String resourceName;

    public ManagerAdapter(List<Manager> managers, String resourceName, Context context, ListItemClickCallBack itemClickCallBack) {
        if (managers != null) {
            this.managers = managers;
        } else {
            this.managers = new ArrayList<>();
        }
        this.resourceName = resourceName;
        this.context = context;
        this.itemClickCallBack = itemClickCallBack;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.managers_list_item, parent, false);
        return new ViewHolder(view, this.itemClickCallBack);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        int colorId =
                this
                        .context
                        .getResources()
                        .getIdentifier("color_" + this.resourceName, "color", this.context.getPackageName());
//        holder.managerIcon.setColorFilter(ContextCompat.getColor(this.context, colorId));
        holder.managerName.setText(managers.get(position).getName());
        holder.managerMobile.setText(managers.get(position).getMobile());
    }

    @Override
    public int getItemCount() {
        return managers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private View rootView;
        private AppCompatImageView managerIcon;
        private AppCompatTextView managerName;
        private AppCompatTextView managerMobile;
        private ListItemClickCallBack itemClickCallBack;

        private ViewHolder(View rootView, ListItemClickCallBack itemClickCallBack) {
            super(rootView);
            this.rootView = rootView;
            this.managerIcon = (AppCompatImageView) rootView.findViewById(R.id.manager_icon);
            managerName = (AppCompatTextView) rootView.findViewById(R.id.textView_name);
            managerMobile = (AppCompatTextView) rootView.findViewById(R.id.textView_mobile);
            this.itemClickCallBack = itemClickCallBack;

            this.rootView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            this.itemClickCallBack.onItemClick(getAdapterPosition(), view.getId());
        }
    }
}
