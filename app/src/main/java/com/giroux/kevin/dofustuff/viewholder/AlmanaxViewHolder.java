package com.giroux.kevin.dofustuff.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.giroux.kevin.dofustuff.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kevin on 31/03/2017.
 */

public class AlmanaxViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.imagesAlamanax)
    ImageView imagesAlamanax;
    @BindView(R.id.almanaxTitle)
    TextView almanaxTitle;
    @BindView(R.id.almanaxBoss)
    TextView almanaxBoss;
    @BindView(R.id.alamanaxEffectiveTitle)
    TextView alamanaxEffectiveTitle;
    @BindView(R.id.alamanaxEffectiveContent)
    TextView alamanaxEffectiveContent;
    @BindView(R.id.alamanaxDay)
    TextView alamanaxDay;
    @BindView(R.id.alamanaxMonth)
    TextView alamanaxMonth;
    @BindView(R.id.almanaxQuest)
    TextView alamanaxQuest;



    @BindView(R.id.almanaxSubQuest)
    TextView alamanaxSubQuest;
    @BindView(R.id.almanaxBonus)
    TextView alamanaxBonus;
    @BindView(R.id.almanaxSubBonus)
    TextView alamanaxSubBonus;
    public TextView getAlamanaxBonus() {
        return alamanaxBonus;
    }


    public TextView getAlamanaxSubBonus() {
        return alamanaxSubBonus;
    }
    public TextView getAlamanaxQuest() {
        return alamanaxQuest;
    }

    public TextView getAlamanaxSubQuest() {
        return alamanaxSubQuest;
    }


    public TextView getAlamanaxDay() {
        return alamanaxDay;
    }

    public TextView getAlamanaxMonth() {
        return alamanaxMonth;
    }

    public AlmanaxViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    public ImageView getImagesAlamanax() {
        return imagesAlamanax;
    }

    public TextView getAlmanaxTitle() {
        return almanaxTitle;
    }

    public TextView getAlmanaxBoss() {
        return almanaxBoss;
    }

    public TextView getAlamanaxEffectiveTitle() {
        return alamanaxEffectiveTitle;
    }

    public TextView getAlamanaxEffectiveContent() {
        return alamanaxEffectiveContent;
    }
}
