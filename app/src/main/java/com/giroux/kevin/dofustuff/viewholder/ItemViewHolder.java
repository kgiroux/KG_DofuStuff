package com.giroux.kevin.dofustuff.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.TextureView;
import android.view.View;
import android.widget.TextView;

import com.giroux.kevin.dofustuff.R;

import pl.droidsonroids.gif.GifImageView;

/**
 * Created by girouxkevin on 02/10/2017.
 */

public class ItemViewHolder extends RecyclerView.ViewHolder {

    private TextView itemName;
    private TextView itemLevel;
    private GifImageView gifImageView;

    public ItemViewHolder(View itemView) {
        super(itemView);
        itemName = (TextView)itemView.findViewById(R.id.itemName);
        itemLevel = (TextView)itemView.findViewById(R.id.itemLevel);
        gifImageView = (GifImageView) itemView.findViewById(R.id.itemImage);
    }

    public TextView getItemName() {
        return itemName;
    }

    public void setItemName(TextView itemName) {
        this.itemName = itemName;
    }

    public TextView getItemLevel() {
        return itemLevel;
    }

    public void setItemLevel(TextView itemLevel) {
        this.itemLevel = itemLevel;
    }

    public GifImageView getGifImageView() {
        return gifImageView;
    }

    public void setGifImageView(GifImageView gifImageView) {
        this.gifImageView = gifImageView;
    }
}
