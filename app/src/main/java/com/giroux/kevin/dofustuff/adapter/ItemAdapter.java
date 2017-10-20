package com.giroux.kevin.dofustuff.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.giroux.kevin.dofustuff.R;
import com.giroux.kevin.dofustuff.dto.Item;
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
            Glide.with(this.activity).load("http://10.0.2.2:8081/medias/"+ this.itemList.get(position).getItemId()).into(itemsViewHolder.getGifImageView());
        }
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }


    public void updateData(List<Item> items){
        for(Item i:items){
            if(!this.itemList.contains(i)){
                this.itemList.add(i);
            }
        }
        this.notifyDataSetChanged();
    }

    public void setActivity(Activity activity){
        this.activity = activity;
    }
}
