package com.giroux.kevin.dofustuff.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.giroux.kevin.dofustuff.R;
import com.giroux.kevin.dofustuff.dto.Character;
import com.giroux.kevin.dofustuff.viewholder.CharacterViewHolder;

import org.apache.commons.lang3.StringUtils;

import io.realm.RealmResults;

/**
 * Created by kevin on 11/12/2016.
 */

public class CharacterAdapter extends RecyclerView.Adapter {

    private RealmResults<Character> characterRealmResults;
    private Context context;
    public CharacterAdapter(RealmResults<Character> charactersList, Context context){
        this.characterRealmResults = charactersList;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CharacterViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.main_content_character,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof CharacterViewHolder){
            CharacterViewHolder characterViewHolder = (CharacterViewHolder) holder;
            Character character = characterRealmResults.get(position);
            characterViewHolder.setCharacterLevel(String.valueOf(character.getLevel()));
            characterViewHolder.setCharacterName(character.getName());
            characterViewHolder.setContext(context);
            int drawableResourceId = context.getResources().getIdentifier(StringUtils.stripAccents(character.getClassGame().toLowerCase())+"_"+character.getSex().toLowerCase(), "drawable", context.getPackageName());
            if (drawableResourceId != -1) {
                characterViewHolder.getCharacterImage().getVisibility();
                characterViewHolder.getCharacterImage().setImageResource(drawableResourceId);
            }
        }
    }

    @Override
    public int getItemCount() {
        return  this.characterRealmResults.size();
    }

    public void setData(RealmResults<Character> newResult){
        this.characterRealmResults = newResult;
        notifyDataSetChanged();
    }
}
