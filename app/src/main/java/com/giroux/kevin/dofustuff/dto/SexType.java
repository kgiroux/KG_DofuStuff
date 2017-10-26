package com.giroux.kevin.dofustuff.dto;

import io.realm.annotations.RealmClass;

/**
 * Created by kevin on 11/12/2016.
 */
public enum SexType {

    FEMALE("female"),
    MALE("male");

    private String sexType;

    SexType(final String value){
        this.sexType = value;
    }
}
