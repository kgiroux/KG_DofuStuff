package com.giroux.kevin.dofustuff.dto;

import android.support.annotation.NonNull;

import com.giroux.kevin.dofustuff.database.PrimaryKeyFactory;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

/**
 * Created by kevin on 26/11/2016.
 */
@RealmClass
public class CharacteristicClass extends RealmObject implements Comparable<CharacteristicClass> {
    @PrimaryKey
    private long id;
    private int levelSeuil;
    private String typeCharacteristic;
    private int costForOne;

    public CharacteristicClass(){

    }

    public CharacteristicClass(int levelSeuil, String typeCaracteristic, int costForOne) {
        this.levelSeuil = levelSeuil;
        this.typeCharacteristic = typeCaracteristic;
        this.costForOne = costForOne;
        this.id = PrimaryKeyFactory.getInstance().nextKey(CharacteristicClass.class);
    }

    public int getLevelSeuil() {
        return levelSeuil;
    }

    public void setLevelSeuil(int levelSeuil) {
        this.levelSeuil = levelSeuil;
    }

    public String getTypeCharacteristic() {
        return typeCharacteristic;
    }

    public void setTypeCharacteristic(String typeCaracteristic) {
        this.typeCharacteristic = typeCaracteristic;
    }

    public int getCostForOne() {
        return costForOne;
    }

    public void setCostForOne(int costForOne) {
        this.costForOne = costForOne;
    }

    @Override
    public int compareTo(@NonNull CharacteristicClass characteristicClass) {
        return this.getLevelSeuil() - characteristicClass.getLevelSeuil();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
