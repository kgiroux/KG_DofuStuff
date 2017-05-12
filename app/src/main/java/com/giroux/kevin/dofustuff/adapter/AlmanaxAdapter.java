package com.giroux.kevin.dofustuff.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.giroux.kevin.dofustuff.R;
import com.giroux.kevin.dofustuff.dto.AlmanaxInfo;
import com.giroux.kevin.dofustuff.viewholder.AlmanaxViewHolder;
import com.giroux.kevin.dofustuff.viewholder.CharacterViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kevin on 31/03/2017.
 */

public class AlmanaxAdapter extends RecyclerView.Adapter {

    private List<AlmanaxInfo> almanaxInfoList;

    public AlmanaxAdapter(){
        this.almanaxInfoList = new ArrayList<>();
    }

    public void addElementToList(AlmanaxInfo almanaxInfo){
        this.almanaxInfoList.add(almanaxInfo);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AlmanaxViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.almanax_item,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof AlmanaxViewHolder){

        }
    }

    @Override
    public int getItemCount() {
        return almanaxInfoList.size();
    }
}
