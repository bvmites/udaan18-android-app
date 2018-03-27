package com.udaan18.udaan18.android.notification;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.github.florent37.expansionpanel.ExpansionLayout;
import com.github.florent37.expansionpanel.viewgroup.ExpansionLayoutCollection;
import com.udaan18.udaan18.android.R;
import com.udaan18.udaan18.android.model.eventCategory.Feed;
import com.udaan18.udaan18.android.util.Helper;

import java.util.List;

/**
 * Created by abhishek on 3/23/2018.
 */

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.RecyclerHolder> {
    private final ExpansionLayoutCollection expansionsCollection = new ExpansionLayoutCollection();
    List<Feed> udaandataClass;
    private Context context;

    public NotificationAdapter(Context context, List<Feed> list) {
        this.context = context;
        this.udaandataClass=list;
        expansionsCollection.openOnlyOne(true);
    }

    @Override
    public RecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.individualrow, parent, false);
        Typeface custom_font_data = Typeface.createFromAsset(context.getAssets(), "fonts/ProzaLibreRegular.ttf");
        Typeface custom_font_level = Typeface.createFromAsset(context.getAssets(), "fonts/ProzaLibreSemiBold.ttf");
        return new RecyclerHolder(view, custom_font_data, custom_font_level);
    }

    @Override
    public void onBindViewHolder(RecyclerHolder holder, int position) {
        final Feed udaandata = udaandataClass.get(position);
        holder.bind(udaandata);
        holder.title.setText(udaandata.getTitle());
        holder.text.setText(udaandata.getText());
        holder.time.setText(udaandata.getTime());
        holder.url.setBackgroundColor(ContextCompat.getColor(this.context, R.color.color_news));
        if (udaandata.getUrl() != null && udaandata.getUrl().length() > 0)
            holder.url.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Helper.openUrlInBrowser(udaandata.getUrl(), context);
                }
            });
        else
            holder.url.setVisibility(View.GONE);
//
//        if(udaandata.getImages()!=null)
//            Picasso.with(context).load(udaandata.getImages()).into(holder.image);
//        else
//           // Toast.makeText(context,"defult", Toast.LENGTH_LONG).show();


        expansionsCollection.add(holder.getExpansionLayout());
    }

    @Override
    public int getItemCount() {
        return udaandataClass.size();
    }

    public void setItems(List<Feed> items) {
        this.udaandataClass.addAll(items);
        notifyDataSetChanged();
    }

    public final static class RecyclerHolder extends RecyclerView.ViewHolder {

        private static final int LAYOUT = R.layout.individualrow;

        // @BindView(R.id.expansionLayout)
        ExpansionLayout expansionLayout;
        TextView title, text, time;
        //ImageView image;
        Button url;

        public RecyclerHolder(View itemView, Typeface face, Typeface level_face) {
            super(itemView);
            title=(TextView)itemView.findViewById(R.id.t_o_m);
            text=(TextView)itemView.findViewById(R.id.message);
            time=(TextView)itemView.findViewById(R.id.timemessage);
            //link=(TextView)itemView.findViewById(R.id.link);
            //image=(ImageView) itemView.findViewById(R.id.i_o_m);
            url=itemView.findViewById(R.id.url_vist);
            title.setTypeface(level_face);
            text.setTypeface(face);
            expansionLayout=itemView.findViewById(R.id.notification_expenstion_layout);
        }


        public void bind(Object object) {
            expansionLayout.collapse(false);
        }

        public ExpansionLayout getExpansionLayout() {
            return expansionLayout;
        }


    }
}

