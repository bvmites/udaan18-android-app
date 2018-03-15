package com.udaan18.udaan18.android.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.florent37.expansionpanel.ExpansionLayout;
import com.github.florent37.expansionpanel.viewgroup.ExpansionLayoutCollection;
import com.udaan18.udaan18.android.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jack on 15-03-2018.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerHolder> {
    private final List<String> list = new ArrayList<>();

    private final ExpansionLayoutCollection expansionsCollection = new ExpansionLayoutCollection();
    private Context context;

    public RecyclerAdapter(Context context) {
        this.context = context;
        expansionsCollection.openOnlyOne(true);
    }

    @Override
    public RecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.level_expension_list, parent, false);
        return new RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerHolder holder, int position) {
        holder.bind(list.get(position));
        holder.level.setText("Level " + (position + 1));
        holder.description.setText(list.get(position).toString());
        expansionsCollection.add(holder.getExpansionLayout());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setItems(List<String> items) {
        this.list.addAll(items);
        notifyDataSetChanged();
    }

    public final static class RecyclerHolder extends RecyclerView.ViewHolder {

        private static final int LAYOUT = R.layout.level_expension_list;

        // @BindView(R.id.expansionLayout)
        ExpansionLayout expansionLayout;
        TextView level;
        TextView description;

        public RecyclerHolder(View itemView) {
            super(itemView);
            //ButterKnife.bind(this, itemView);
            level = itemView.findViewById(R.id.text_view_detail_event_level_label);
            expansionLayout = itemView.findViewById(R.id.expansionLayout);
            description = itemView.findViewById(R.id.text_view_detail_event_level_description);
        }

        public static RecyclerHolder buildFor(ViewGroup viewGroup) {
            return new RecyclerHolder(LayoutInflater.from(viewGroup.getContext()).inflate(LAYOUT, viewGroup, false));
        }

        public void bind(Object object) {
            expansionLayout.collapse(false);
        }

        public ExpansionLayout getExpansionLayout() {
            return expansionLayout;
        }
    }
}

