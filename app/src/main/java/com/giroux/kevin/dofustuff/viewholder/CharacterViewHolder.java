package com.giroux.kevin.dofustuff.viewholder;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.giroux.kevin.dofustuff.R;
import com.giroux.kevin.dofustuff.activity.character.CharacterInformationActivity;

import pl.droidsonroids.gif.GifImageView;

/**
 * Created by kevin on 11/12/2016.
 */

public class CharacterViewHolder extends RecyclerView.ViewHolder{

    private GifImageView characterImage;
    private TextView characterLevel;
    private TextView characterName;
    private Context context;
    private long idCharacter;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public CharacterViewHolder(View itemView) {
        super(itemView);
        characterImage = itemView.findViewById(R.id.characterImage);
        characterLevel = itemView.findViewById(R.id.characterLevel);
        characterName = itemView.findViewById(R.id.characterName);
        itemView.setOnClickListener(v -> {

            Intent t = new Intent(getContext(), CharacterInformationActivity.class);
            t.putExtra("idCharacter",this.idCharacter);
            context.startActivity(t);
        });
    }

    public void setCharacterLevel(String level){
        this.characterLevel.setText(level);
    }

    public void setCharacterName(String name){
        this.characterName.setText(name);
    }

    public GifImageView getCharacterImage() {
        return characterImage;
    }

    public long getIdCharacter() {
        return idCharacter;
    }

    public void setIdCharacter(long idCharacter) {
        this.idCharacter = idCharacter;
    }
}
