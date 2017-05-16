package com.giroux.kevin.dofustuff.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.giroux.kevin.androidhttprequestlibrairy.constants.TypeMine;
import com.giroux.kevin.dofustuff.R;
import com.giroux.kevin.dofustuff.dto.AlmanaxInfo;
import com.giroux.kevin.dofustuff.network.ImageLoaderTask;
import com.giroux.kevin.dofustuff.viewholder.AlmanaxViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import pl.droidsonroids.gif.GifImageView;

/**
 * Created by kevin on 31/03/2017.
 */

public class AlmanaxAdapter extends RecyclerView.Adapter {


    private List<AlmanaxInfo> almanaxInfoList;
    private Activity activity;

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public AlmanaxAdapter() {
        this.almanaxInfoList = new ArrayList<>();
    }

    public void addElementToList(AlmanaxInfo almanaxInfo) {
        this.almanaxInfoList.add(almanaxInfo);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AlmanaxViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.almanax_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof AlmanaxViewHolder) {
            AlmanaxViewHolder viewHolder = (AlmanaxViewHolder) holder;
            viewHolder.getAlamanaxEffectiveContent().setText(this.almanaxInfoList.get(position).getEffectContent());
            viewHolder.getAlamanaxEffectiveTitle().setText(this.almanaxInfoList.get(position).getEffectTitle());
            viewHolder.getAlmanaxTitle().setText(this.almanaxInfoList.get(position).getAlamanaxTitle());
            viewHolder.getAlmanaxBoss().setText(this.almanaxInfoList.get(position).getAlamanaxBoss());
            viewHolder.getAlamanaxDay().setText(this.almanaxInfoList.get(position).getDayNumber() + " ");
            viewHolder.getAlamanaxMonth().setText(this.almanaxInfoList.get(position).getDayMonth());
            viewHolder.getAlamanaxQuest().setText(this.almanaxInfoList.get(position).getQuest());
            viewHolder.getAlamanaxSubQuest().setText(this.almanaxInfoList.get(position).getQuestContent());
            viewHolder.getAlamanaxBonus().setText(this.almanaxInfoList.get(position).getBonus());
            viewHolder.getAlamanaxSubBonus().setText(this.almanaxInfoList.get(position).getSubBonus());

            ImageLoaderTask imageLoaderTask = new ImageLoaderTask(this.almanaxInfoList.get(position).getAlamanaxBossImage(),"GET",null);
            Map<String,Object> listUI = new HashMap<>();
            listUI.put("gifVImageView",viewHolder.getImagesAlamanax());
            imageLoaderTask.setListObject(listUI);
            imageLoaderTask.setActivity(getActivity());
            imageLoaderTask.setJSON(false);
            imageLoaderTask.setTypeMine(TypeMine.IMAGE_PNG);
            imageLoaderTask.execute();
        }
    }

    @Override
    public int getItemCount() {
        return almanaxInfoList.size();
    }
}
