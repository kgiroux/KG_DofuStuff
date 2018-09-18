package com.giroux.kevin.dofustuff.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.giroux.kevin.dofustuff.R;
import com.giroux.kevin.dofustuff.commons.characters.Character;
import com.giroux.kevin.dofustuff.viewholder.CharacterViewHolder;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kevin on 11/12/2016.
 */

public class CharacterAdapter extends RecyclerView.Adapter<CharacterViewHolder>{
    private List<Character> characterList;

    private Context context;

    public CharacterAdapter(List<Character> data, Context context){
        this.context = context;
        characterList = new ArrayList<>();
    }

    @Override
    public  CharacterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CharacterViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.main_content_character,parent,false));
    }

    @Override
    public void onBindViewHolder(CharacterViewHolder characterViewHolder, int position) {
            Character character = characterList.get(position);
            characterViewHolder.setCharacterLevel(String.valueOf(character.getLevel()));
            characterViewHolder.setCharacterName(character.getName());
            characterViewHolder.setContext(context);
            int drawableResourceId = context.getResources().getIdentifier(StringUtils.stripAccents(character.getClassGame().toLowerCase())+"_"+character.getSex().toLowerCase(), "drawable", context.getPackageName());
            if (drawableResourceId != -1) {
                characterViewHolder.getCharacterImage().getVisibility();
                characterViewHolder.setIdCharacter(character.getId());
                characterViewHolder.getCharacterImage().setImageResource(drawableResourceId);
            }

    }

    @Override
    public long getItemId(int index) {
        //noinspection ConstantConditions
        return characterList.get(index).getId();
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
