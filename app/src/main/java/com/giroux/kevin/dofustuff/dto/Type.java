package com.giroux.kevin.dofustuff.dto;

import io.realm.annotations.RealmClass;

/**
 * Created by kevin on 25/11/2016.
 */
public enum Type {
    /**
     * Ecaflip
     */
    ECAFLIP("ecaflip"),
    /**
     * Eniripsa
     */
    ENIRIPSA("eniripsa"),
    /**
     * Iop
     */
    IOP("iop"),
    /**
     *  CRA
     */
    CRA("cra"),
    /**
     * FECA
     */
    FECA("feca"),
    /**
     * SACRIEUR
     */
    SACRIEUR("sacrieur"),
    /**
     * SADIDA
     */
    SADIDA("sadida"),
    /**
     * OSAMODAS
     */
    OSAMODAS("osamodas"),
    /**
     * ENUTROF
     */
    ENUTROF("enutrof"),
    /**
     * SRAM
     */
    SRAM("sram"),
    /**
     * XELOR
     */
    XELOR("xelor"),
    /**
     * PANDAWA
     */
    PANDAWA("pandawa"),
    /**
     * ROUBLARD
     */
    ROUBLARD("roublard"),
    /**
     * ZOBAL
     */
    ZOBAL("zobal"),
    /**
     * STEAMER
     */
    STEAMER("steamer"),
    /**
     * ELIOTROP
     */
    ELIOTROP("eliotrop"),
    /**
     * HUPPERMAGE
     */
    HUPPERMAGE("huppermage");

    private String typeClass;

    Type (String type){
        this.typeClass = type;
    }
}
