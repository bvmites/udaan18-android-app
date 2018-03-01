package com.udaan18.udaan18.android.adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.udaan18.udaan18.android.R;
import com.udaan18.udaan18.android.databinding.EventListItemBinding;
import com.udaan18.udaan18.android.model.eventCategory.Event;

import java.util.List;


public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {

    private Context context;
    // private ListItemClickCallBack itemClickCallBack;
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
        // int colorPosition = position % Helper.colors.length;

        Event event = eventList.get(position);

        // holder.eventIcon.setImageResource(drawableId != 0 ? drawableId : R.drawable.github);
        holder.bind(event);
        // holder.eventShortDescription.setText(event.getDescription());
        //setAnimation(holder.container, position);
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public CardView container;
        private EventListItemBinding itemBinding;

        public ViewHolder(EventListItemBinding itemBinding) {
            super(itemBinding.getRoot());

            this.itemBinding = itemBinding;
        }

        private void bind(Event event) {
            itemBinding
                    .eventListViewTitle
                    .setText(event.getName());
        }
    }
}
