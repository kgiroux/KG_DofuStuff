package com.giroux.kevin.dofustuff.dto;

import com.giroux.kevin.dofustuff.database.PrimaryKeyFactory;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

/**
 * Created by kevin on 25/11/2016.
 */
@RealmClass
public class Characteristic  extends RealmObject {
    /**
     * id
     */
    @PrimaryKey
    private long id;

    /**
     * Name of the Characteristic
     */
    private String nameCharacteristic;
    /**
     * minValue
     */
    private int minValue;
    /**
     * maxValue
     */
    private int maxValue;
    /**
     * Correspondance
     */
    private int correspondance;

    /**
     * current value
     */
    private int currentValue;

    /**
     * Constructor
     */
    public Characteristic() {
    }

    /**
     * Constructor with paramater
     * @param nameCharacteristic the name of the Characteristic
     * @param minValue the min value
     * @param maxValue the max value
     * @param correspondance the correspondance
     * @param currentValue the currentValue
     */
    public Characteristic(String nameCharacteristic, int minValue, int maxValue, int correspondance, int currentValue) {
        this.nameCharacteristic = nameCharacteristic;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.correspondance = correspondance;
        this.currentValue = currentValue;
        this.id = PrimaryKeyFactory.getInstance().nextKey(Characteristic.class);
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameCharacteristic() {
        return nameCharacteristic;
    }

    public void setNameCharacteristic(String nameCharacteristic) {
        this.nameCharacteristic = nameCharacteristic;
    }

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public int getCorrespondance() {
        return correspondance;
    }

    public void setCorrespondance(int correspondance) {
        this.correspondance = correspondance;
    }

    public int getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(int currentValue) {
        this.currentValue = currentValue;
    }
}
