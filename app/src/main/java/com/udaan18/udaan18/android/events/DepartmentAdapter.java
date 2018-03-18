package com.udaan18.udaan18.android.events;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.udaan18.udaan18.android.R;
import com.udaan18.udaan18.android.model.eventCategory.Department;
import com.udaan18.udaan18.android.util.Helper;
import com.udaan18.udaan18.android.util.listeners.ListItemClickCallBack;

import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by pranshu on 6/3/17.
 */

public class DepartmentAdapter extends RecyclerView.Adapter<DepartmentAdapter.ViewHolder> {

    private Context context;

    private List<Department> departmentList;
    private int lastPosition = -1;
    private ListItemClickCallBack itemClickCallBack;

    public DepartmentAdapter(List<Department> departmentList, Context context) {
        this.context = context;
        this.departmentList = departmentList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView c = (CardView) LayoutInflater.from(context).inflate(R.layout.department_list_item, parent, false);
        return new ViewHolder(c);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        int colorPosition = position % Helper.colors.length;
        String name = Helper.getTitleFromRresource(departmentList.get(position).getAlis());
        holder.container.setCardBackgroundColor(ContextCompat.getColor(context, Helper.colors[colorPosition]));
        holder.departmentTitle.setText(name);

        String resName = Helper.getResourceNameFromTitle(departmentList.get(position).getName());
        Log.d(TAG, "onBindViewHolder: " + resName);
        // Toast.makeText(context, ""+resName, Toast.LENGTH_SHORT).show();
        int resourceId = context.getResources().getIdentifier(resName, "drawable", context.getPackageName());
        holder.departmentImage.setImageResource(resourceId);
    }

    public void setItemClickCallBack(ListItemClickCallBack itemClickCallBack) {
        this.itemClickCallBack = itemClickCallBack;
    }

    @Override
    public int getItemCount() {
        return departmentList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private CardView container;
        private AppCompatTextView departmentTitle;
        private ImageView departmentImage;

        public ViewHolder(View itemView) {
            super(itemView);
            container = (CardView) itemView.findViewById(R.id.department_list_item_card);
            departmentTitle = (AppCompatTextView) itemView.findViewById(R.id.department_list_view_title);
            departmentImage = itemView.findViewById(R.id.department_list_view_image);
            Typeface custom_font = Typeface.createFromAsset(context.getAssets(), "fonts/BungeeRegular.ttf");
            departmentTitle.setTypeface(custom_font);
            container.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemClickCallBack.onItemClick(getAdapterPosition(), v.getId());
        }
    }
}
