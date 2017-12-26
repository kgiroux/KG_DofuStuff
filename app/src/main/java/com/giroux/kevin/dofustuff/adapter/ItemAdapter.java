package com.giroux.kevin.dofustuff.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.giroux.kevin.dofustuff.R;
import com.giroux.kevin.dofustuff.commons.item.Item;
import com.giroux.kevin.dofustuff.dto.ItemCharacter;
import com.giroux.kevin.dofustuff.viewholder.ItemViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by girouxkevin on 02/10/2017.
 */

public class ItemAdapter extends RecyclerView.Adapter {

    private List<Item> itemList = new ArrayList<>();
    private Activity activity;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.search_content_item,parent,false));

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ItemViewHolder){
            ItemViewHolder itemsViewHolder = (ItemViewHolder) holder;
            itemsViewHolder.getItemLevel().setText(new StringBuilder().append("Level : ").append(String.valueOf(this.itemList.get(position).getLevel())).toString());
            itemsViewHolder.getItemName().setText(this.itemList.get(position).getName());
            itemsViewHolder.setContext(activity.getApplicationContext());
            itemsViewHolder.setItem(this.itemList.get(position));
            Glide.with(this.activity).load("http://nexus-factory.ovh:9002/medias/"+ this.itemList.get(position).getImageId()).into(itemsViewHolder.getGifImageView());
        }
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }


    public void updateData(List<Item> itemCharacters){
        for(Item i: itemCharacters){
            if(!this.itemList.contains(i)){
                this.itemList.add(i);
            }
        }
        this.notifyDataSetChanged();
    }

    public void resetData(){
        this.itemList.clear();
        this.notifyDataSetChanged();
    }
    public void setActivity(Activity activity){
        this.activity = activity;
    }
}
