package com.giroux.kevin.dofustuff.dto;

import android.support.annotation.NonNull;

import com.giroux.kevin.dofustuff.database.PrimaryKeyFactory;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

@RealmClass
public class ItemCharacter extends RealmObject implements Comparable<ItemCharacter>{

    @PrimaryKey
    private long id;
    private int itemId;
    private String name;
    private String type;
    private int level;
    private String setName;
    private RealmList<Characteristic> characteristics;

    public ItemCharacter(){
        this.id = PrimaryKeyFactory.getInstance().nextKey(ItemCharacter.class);
    }

    public ItemCharacter(long id, int itemId, String name, String type, int level, String setName, RealmList<Characteristic> caracteristics) {
        this.id = id;
        this.itemId = itemId;
        this.name = name;
        this.type = type;
        this.level = level;
        this.setName = setName;
        this.characteristics = caracteristics;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getSetName() {
        return setName;
    }

    public void setSetName(String setName) {
        this.setName = setName;
    }

    public RealmList<Characteristic> getCaracteristics() {
        return characteristics;
    }

    public void setCaracteristics(RealmList<Characteristic> caracteristics) {
        this.characteristics = caracteristics;
    }

    @Override
    public int compareTo(@NonNull ItemCharacter itemCharacter) {
        return this.getName().compareTo(itemCharacter.getName());
    }
}
