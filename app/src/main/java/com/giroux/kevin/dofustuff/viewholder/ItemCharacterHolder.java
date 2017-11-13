package com.giroux.kevin.dofustuff.viewholder;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.giroux.kevin.dofustuff.R;
import com.giroux.kevin.dofustuff.activity.character.fragment.ItemFragment;
import com.giroux.kevin.dofustuff.activity.search.SearchItemActivity;
import com.giroux.kevin.dofustuff.commons.item.Item;
import com.giroux.kevin.dofustuff.dto.Character;

/**
 * Created by girouxkevin on 25/10/2017.
 */

public class ItemCharacterHolder extends ViewHolder{

    private TextView itemCharacterName;
    private ImageView imageView;
    private Button changeItemButton;
    private Item item;
    private int level;
    private Activity context;
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
        changeItemButton = itemView.findViewById(R.id.CharacterChangeItem);

        changeItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.getCategory();
                Intent t = new Intent(context, SearchItemActivity.class);
                t.putExtra("category",item.getCategory());
                t.putExtra("level",level);
                context.startActivityForResult(t, ItemFragment.RequestCodeItemFragment);
            }
        });
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setContext(Activity context) {
        this.context = context;
    }
}
