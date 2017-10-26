package com.giroux.kevin.dofustuff.viewholder;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.giroux.kevin.dofustuff.R;

/**
 * Created by girouxkevin on 25/10/2017.
 */

public class ItemCharacterHolder extends ViewHolder{

    private TextView itemCharacterName;
    private ImageView imageView;

    public TextView getItemCharacterName() {
        return itemCharacterName;
    }

    public void setItemCharacterName(TextView itemCharacterName) {
        this.itemCharacterName = itemCharacterName;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public ItemCharacterHolder(View itemView) {
        super(itemView);

        itemCharacterName = itemView.findViewById(R.id.itemCharacterName);
        imageView = itemView.findViewById(R.id.itemImage);
    }
}
