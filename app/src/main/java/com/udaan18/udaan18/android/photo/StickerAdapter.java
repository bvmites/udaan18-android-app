package com.udaan18.udaan18.android.photo;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.udaan18.udaan18.android.R;
import com.udaan18.udaan18.android.util.listeners.ListItemClickCallBack;

import java.util.List;

/**
 * Created by jack on 29-03-2018.
 */


public class StickerAdapter extends RecyclerView.Adapter<StickerAdapter.ViewHolder> {

    private Context context;
    private ListItemClickCallBack itemClickCallBack;
    private List<Integer> urlSticker;

    public StickerAdapter(Context context, List<Integer> urls) {
        this.context = context;
        this.urlSticker = urls;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_sticker, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.sticker.setImageDrawable(ContextCompat.getDrawable(context, urlSticker.get(position)));
//        ContextWrapper cw = new ContextWrapper(getApplicationContext());
//        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
//        File myImageFile = new File(directory, "my_image.jpeg");
//        Picasso.with(context).load(myImageFile).into(holder.sticker);
    }

    public void setItemClickCallBack(ListItemClickCallBack itemClickCallBack) {
        this.itemClickCallBack = itemClickCallBack;
    }

    @Override
    public int getItemCount() {
        return urlSticker.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView sticker;

        public ViewHolder(View view) {
            super(view);
            sticker = view.findViewById(R.id.sticker_image);

        }

        @Override
        public void onClick(View v) {
            itemClickCallBack.onItemClick(getAdapterPosition(), v.getId());
        }
    }
}
