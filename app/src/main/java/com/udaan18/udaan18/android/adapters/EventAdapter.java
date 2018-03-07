package com.udaan18.udaan18.android.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.udaan18.udaan18.android.R;
import com.udaan18.udaan18.android.databinding.EventListItemBinding;
import com.udaan18.udaan18.android.model.eventCategory.Event;
import com.udaan18.udaan18.android.util.Helper;
import com.udaan18.udaan18.android.util.listeners.ListItemClickCallBack;

import java.util.List;


public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {

    int drawableId;
    private Context context;
    private ListItemClickCallBack itemClickCallBack;
    private List<Event> eventList;
    private int lastPosition = -1;

    public EventAdapter(List<Event> eventList, Context context) {
        this.context = context;
        this.eventList = eventList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        EventListItemBinding itemBinding =
                EventListItemBinding.inflate(inflater, parent, false);

        return new ViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        int colorPosition = position % Helper.colors.length;

        Event event = eventList.get(position);
        drawableId = this
                .context
                .getResources()
                .getIdentifier(Helper.getResourceNameFromTitle(event.getName()), "drawable", context.getPackageName());


        // holder.eventIcon.setImageResource(drawableId != 0 ? drawableId : R.drawable.github);
        holder.bind(event);
        // holder.eventShortDescription.setText(event.getDescription());
        //setAnimation(holder.container, position);
    }

    public void setItemClickCallBack(ListItemClickCallBack itemClickCallBack) {
        this.itemClickCallBack = itemClickCallBack;
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public CardView container;
        private EventListItemBinding itemBinding;

        public ViewHolder(EventListItemBinding itemBinding) {
            super(itemBinding.getRoot());

            this.itemBinding = itemBinding;
            this.itemBinding.eventListItemCard.setOnClickListener(this);

        }

        private void bind(Event event) {
            itemBinding
                    .eventListViewTitle
                    .setText(event.getName());
            itemBinding.eventListViewIcon.setImageResource(drawableId != 0 ? drawableId : R.drawable.github);
        }

        @Override
        public void onClick(View v) {
            itemClickCallBack.onItemClick(getAdapterPosition(), v.getId());
        }
    }
}
