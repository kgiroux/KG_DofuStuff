package com.giroux.kevin.dofustuff.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.giroux.kevin.dofustuff.R;
import com.giroux.kevin.dofustuff.commons.item.Effect;
import com.giroux.kevin.dofustuff.commons.item.EffectName;
import com.giroux.kevin.dofustuff.commons.item.Item;

import java.util.ArrayList;
import java.util.List;

import pl.droidsonroids.gif.GifImageView;

/**
 * Created by girouxkevin on 02/10/2017.
 */

public class ItemViewHolder extends RecyclerView.ViewHolder {

    private TextView itemName;
    private TextView itemLevel;
    private GifImageView gifImageView;
    private Item item;
    private ListView listView;
    private List<String> list = new ArrayList<>();
    private StringBuilder builder = new StringBuilder();
    private Context context;

    public ItemViewHolder(View itemView) {
        super(itemView);
        itemName = itemView.findViewById(R.id.itemName);
        itemLevel = itemView.findViewById(R.id.itemLevel);
        gifImageView = itemView.findViewById(R.id.itemImage);
        listView = itemView.findViewById(R.id.itemEffects);
        listView.setScrollContainer(false);
        itemView.setOnClickListener(view -> {
            List<Effect> effectList = item.getEffects();
            for(Effect effect : effectList){
                builder.append(effect.getMin()).append(" à ").append(effect.getMax()).append( " ").append(EffectName.retrieveFromShortName(effect.getName()).getLongName());
                list.add(builder.toString());
                builder.setLength(0);
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, list);
            listView.setAdapter(adapter);
            computeHeight(adapter);

            int visibilityToSet = listView.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE;

            listView.setVisibility(visibilityToSet);
        });
    }

    private void computeHeight(ArrayAdapter arrayAdapter){
        int numberOfItems = arrayAdapter.getCount();

        // Get total height of all items.
        int totalItemsHeight = 0;
        for (int itemPos = 0; itemPos < numberOfItems; itemPos++) {
            View item = arrayAdapter.getView(itemPos, null, listView);
            item.measure(0, 0);
            totalItemsHeight += item.getMeasuredHeight();
        }

        // Get total height of all item dividers.
        int totalDividersHeight = listView.getDividerHeight() *
                (numberOfItems - 1);

        // Set list height.
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalItemsHeight + totalDividersHeight;
        listView.setLayoutParams(params);
        listView.requestLayout();

    }

    public TextView getItemName() {
        return itemName;
    }

    public TextView getItemLevel() {
        return itemLevel;
    }

    public GifImageView getGifImageView() {
        return gifImageView;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
