package com.giroux.kevin.dofustuff.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.giroux.kevin.dofustuff.R;
import com.giroux.kevin.dofustuff.commons.item.Item;
import com.giroux.kevin.dofustuff.viewholder.CharacterViewHolder;
import com.giroux.kevin.dofustuff.viewholder.ItemCharacterHolder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by girouxkevin on 25/10/2017.
 */

public class ItemCharacterAdapter  extends RecyclerView.Adapter<ItemCharacterHolder>  {

    private Activity context;
    private int level;
    private List<Item> listItem = new ArrayList<>();

    @Override
    public ItemCharacterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemCharacterHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_character_view_holder,parent,false));

    }

    @Override
    public void onBindViewHolder(ItemCharacterHolder holder, int position) {

        holder.getItemCharacterName().setText(listItem.get(position).getName());
        holder.setLevel(this.level);
        holder.setContext(context);
        holder.setItem(listItem.get(position));

        if(null== listItem.get(position).getId()){
            holder.getImageView().setImageResource(listItem.get(position).getImageId());
        }else{
            Glide.with(this.context).load("http://nexus-factory.ovh:9002/medias/"+ this.listItem.get(position).getId()).into(holder.getImageView());
        }
    }

    @Override
    public int getItemCount() {
        return this.listItem.size();
    }


    public void setData(List<Item> items){
        this.listItem.clear();
        this.listItem.addAll(items);
        notifyDataSetChanged();
    }

    public void setActitity(Activity context) {
        this.context = context;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
