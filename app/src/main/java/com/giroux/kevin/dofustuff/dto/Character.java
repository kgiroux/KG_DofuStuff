package com.giroux.kevin.dofustuff.dto;

import android.databinding.ObservableField;
import android.os.Build;
import android.util.Log;

import com.giroux.kevin.dofustuff.BR;
import com.giroux.kevin.dofustuff.constants.Constants;
import com.giroux.kevin.dofustuff.database.PrimaryKeyFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

/**
 * Created by kevin on 25/11/2016.
 */
@RealmClass
public class Character extends RealmObject {
    @PrimaryKey
    private long id;
    private String classGame;
    private String sex;


    private String name;
    private RealmList<Item> equipments;
    private RealmList<CharacteristicClass> characteristicClasses;
    private RealmList<Characteristic> parchemin;
    private RealmList<Characteristic> caracteristics;
    private RealmList<Sort> spells;
    @Ignore
    private ObservableField<String> nbCharacteristicPointAvailableStringOb;
    @Ignore
    private String pointChance;
    @Ignore
    private String pointForce;
    @Ignore
    private String pointIntelligence;
    @Ignore
    private String pointVitality;
    @Ignore
    private String pointAgility;
    @Ignore
    private String pointSagesse;

    private int level;
    private String levelString;
    public static Character creater(){
        Character character = new Character();
        character.initCharacter();
        System.out.println("Passage ici 1");
        return character;
    }



    public Character() {
       initCharacter();
        System.out.println("Passage ici 2");

    }

    private void initCharacter(){
        this.id = PrimaryKeyFactory.getInstance().nextKey(Character.class);

        nbCharacteristicPointAvailableStringOb = new ObservableField<>();
        caracteristics = new RealmList<>();
        parchemin = new RealmList<>();
        spells = new RealmList<>();
         /*
         * Init base caracteristic
         */
        Characteristic caracteristic = new Characteristic(TypeCharacteristic.VITALITE.toString(),0,0,11,0);
        caracteristics.add(caracteristic);
        caracteristic = new Characteristic(TypeCharacteristic.SAGESSE.toString(),0,0,12,0);
        caracteristics.add(caracteristic);
        caracteristic = new Characteristic(TypeCharacteristic.FORCE.toString(),0,0,13,0);
        caracteristics.add(caracteristic);
        caracteristic = new Characteristic(TypeCharacteristic.INTELLIGENCE.toString(),0,0,14,0);
        caracteristics.add(caracteristic);
        caracteristic = new Characteristic(TypeCharacteristic.CHANCE.toString(),0,0,15,0);
        caracteristics.add(caracteristic);
        caracteristic = new Characteristic(TypeCharacteristic.AGILITE.toString(),0,0,16,0);
        caracteristics.add(caracteristic);
        caracteristic = new Characteristic(TypeCharacteristic.LEVEL.toString(),1,200,-1,200);
        caracteristics.add(caracteristic);

        /*
         *  Parchemin caractéristique
         */

        caracteristic = new Characteristic(TypeCharacteristic.VITALITE.toString(),0,100,11,100);
        parchemin.add(caracteristic);
        caracteristic = new Characteristic(TypeCharacteristic.SAGESSE.toString(),0,100,12,100);
        parchemin.add(caracteristic);
        caracteristic = new Characteristic(TypeCharacteristic.FORCE.toString(),0,100,13,100);
        parchemin.add(caracteristic);
        caracteristic = new Characteristic(TypeCharacteristic.INTELLIGENCE.toString(),0,100,14,100);
        parchemin.add(caracteristic);
        caracteristic = new Characteristic(TypeCharacteristic.CHANCE.toString(),0,100,15,100);
        parchemin.add(caracteristic);
        caracteristic = new Characteristic(TypeCharacteristic.AGILITE.toString(),0,100,16,100);
        parchemin.add(caracteristic);


        characteristicClasses = new RealmList<>();
        CharacteristicClass characteristicClass = new CharacteristicClass(0,TypeCharacteristic.VITALITE.toString(),1);
        characteristicClasses.add(characteristicClass);
        characteristicClass = new CharacteristicClass(0,TypeCharacteristic.SAGESSE.toString(),3);
        characteristicClasses.add(characteristicClass);
        // Agility
        characteristicClass = new CharacteristicClass(0,TypeCharacteristic.AGILITE.toString(),1);
        characteristicClasses.add(characteristicClass);
        characteristicClass = new CharacteristicClass(1,TypeCharacteristic.AGILITE.toString(),2);
        characteristicClasses.add(characteristicClass);
        characteristicClass = new CharacteristicClass(2,TypeCharacteristic.AGILITE.toString(),3);
        characteristicClasses.add(characteristicClass);
        characteristicClass = new CharacteristicClass(3,TypeCharacteristic.AGILITE.toString(),4);
        characteristicClasses.add(characteristicClass);

        // Luck
        characteristicClass = new CharacteristicClass(0,TypeCharacteristic.CHANCE.toString(),1);
        characteristicClasses.add(characteristicClass);
        characteristicClass = new CharacteristicClass(1,TypeCharacteristic.CHANCE.toString(),2);
        characteristicClasses.add(characteristicClass);
        characteristicClass = new CharacteristicClass(2,TypeCharacteristic.CHANCE.toString(),3);
        characteristicClasses.add(characteristicClass);
        characteristicClass = new CharacteristicClass(3,TypeCharacteristic.CHANCE.toString(),4);
        characteristicClasses.add(characteristicClass);

        // Strengh
        characteristicClass = new CharacteristicClass(0,TypeCharacteristic.FORCE.toString(),1);
        characteristicClasses.add(characteristicClass);
        characteristicClass = new CharacteristicClass(1,TypeCharacteristic.FORCE.toString(),2);
        characteristicClasses.add(characteristicClass);
        characteristicClass = new CharacteristicClass(2,TypeCharacteristic.FORCE.toString(),3);
        characteristicClasses.add(characteristicClass);
        characteristicClass = new CharacteristicClass(3,TypeCharacteristic.FORCE.toString(),4);
        characteristicClasses.add(characteristicClass);




        characteristicClass = new CharacteristicClass(0,TypeCharacteristic.INTELLIGENCE.toString(),1);
        characteristicClasses.add(characteristicClass);
        characteristicClass = new CharacteristicClass(1,TypeCharacteristic.INTELLIGENCE.toString(),2);
        characteristicClasses.add(characteristicClass);
        characteristicClass = new CharacteristicClass(2,TypeCharacteristic.INTELLIGENCE.toString(),3);
        characteristicClasses.add(characteristicClass);
        characteristicClass = new CharacteristicClass(3,TypeCharacteristic.INTELLIGENCE.toString(),4);
        characteristicClasses.add(characteristicClass);

    }
    public Character(final String name, final int level ){
        System.out.println("Passage ici 3");

        initCharacter();
    }

    public Characteristic getCharacteristic(final String value) {
        if (Build.VERSION.SDK_INT >= 24) {
            return this.caracteristics.stream().filter(caracteristic -> caracteristic.getNameCharacteristic().equals(value)).collect(Collectors.toList()).get(0);
        } else {
            for (Characteristic c: caracteristics) {
                if(c.getNameCharacteristic().equals(value)){
                    return c;
                }
            }
        }
        return null;
    }

    private List<CharacteristicClass> getCharacteristicClass(final String value) {
        if (Build.VERSION.SDK_INT >= 24) {
            return this.characteristicClasses.stream().filter(caracteristic -> caracteristic.getTypeCharacteristic().equals(value)).collect(Collectors.toList());
        } else {
            List<CharacteristicClass> characteristicClasses = new ArrayList<>();
            for (CharacteristicClass c: characteristicClasses) {
                if(c.getTypeCharacteristic().equals(value)){
                    characteristicClasses.add(c);
                }
            }
            return characteristicClasses;
        }
    }


    public String getClassGame() {
        return classGame;
    }
    public void setClassGame(String classGame) {
        this.classGame = classGame;
    }

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RealmList<Item> getEquipments() {
        return equipments;
    }

    public void setEquipments(RealmList<Item> equipments) {
        this.equipments = equipments;
    }

    public RealmList<Characteristic> getParchemin() {
        return parchemin;
    }

    public void setParchemin(RealmList<Characteristic> parchemin) {
        this.parchemin = parchemin;
    }

    public RealmList<Characteristic> getCharacteristics() {
        return caracteristics;
    }

    public void setCharacteristics(RealmList<Characteristic> caracteristics) {
        this.caracteristics = caracteristics;
    }

    public RealmList<Sort> getSpells() {
        return spells;
    }

    public void setSpells(RealmList<Sort> spells) {
        this.spells = spells;
    }

    public int getLevel() {
        return level;
    }

    public String getLevelString() {
        return levelString;
    }

    public ObservableField<String> getNbCharacteristicPointAvailableStringOb() {
        return nbCharacteristicPointAvailableStringOb;
    }

    public void setNbCharacteristicPointAvailableStringOb(String nbCharacteristicPointAvailableStringOb) {
        this.nbCharacteristicPointAvailableStringOb.set(nbCharacteristicPointAvailableStringOb);
        this.nbCharacteristicPointAvailableStringOb.notifyPropertyChanged(BR.character);
    }

    public void setLevelString(String levelString) {
        this.levelString = levelString;
        try{
            if(!this.levelString.equals("")){
                this.level = Integer.parseInt(this.levelString);
                if(level>0 && level<=Constants.MAX_LEVEL){
                    this.setNbCharacteristicPointAvailableStringOb(String.valueOf((level -1) * 5));
                }
            }
        }catch (NumberFormatException ex){
            Log.i("Character","emptyString or Invalid");
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }


    public String getPointChance() {
        return pointChance;
    }

    public void setPointChance(String pointChance) {

        this.pointChance = pointChance;
        if(Integer.parseInt(pointChance) <= 995){
            try{
                if(!this.pointChance.equals("")){
                    if(isManyPointAvailable(pointChance,TypeCharacteristic.CHANCE)){
                        this.getCharacteristic(TypeCharacteristic.CHANCE.toString()).setCurrentValue(Integer.parseInt(this.pointChance));
                    }
                }

            }catch (NumberFormatException ex){
                Log.e("ERROR", "Cannot parse");
            }
        }
    }

    private boolean isManyPointAvailable(String nbPointWanted, TypeCharacteristic caracteristic) throws NumberFormatException{
        int value = Integer.parseInt(nbPointWanted);
        List<CharacteristicClass> characteristicClasses = this.getCharacteristicClass(caracteristic.toString());
        Collections.sort(characteristicClasses);
        int requiredPoint = 0;
        for(CharacteristicClass c : characteristicClasses){
                if((value - Constants.SIZE_PALIER_CARA) <= 0 ){
                    requiredPoint = requiredPoint + value * c.getCostForOne();
                    break;
                }else{
                    requiredPoint += c.getCostForOne() * Constants.SIZE_PALIER_CARA;
                    value -= Constants.SIZE_PALIER_CARA;
                }
        }
        if(requiredPoint <Integer.parseInt(this.nbCharacteristicPointAvailableStringOb.get())){
            int lastPoint = Integer.parseInt(this.nbCharacteristicPointAvailableStringOb.get()) - requiredPoint;
            this.nbCharacteristicPointAvailableStringOb.set(String.valueOf(lastPoint));
        }
        return requiredPoint <Integer.parseInt(this.nbCharacteristicPointAvailableStringOb.get());
    }


    public String getPointForce() {
        return pointForce;
    }

    public void setPointForce(String pointForce) {
        this.pointForce = pointForce;
    }

    public String getPointIntelligence() {
        return pointIntelligence;
    }

    public void setPointIntelligence(String pointIntelligence) {
        this.pointIntelligence = pointIntelligence;
    }

    public String getPointVitality() {
        return pointVitality;
    }

    public void setPointVitality(String pointVitality) {
        this.pointVitality = pointVitality;
    }

    public String getPointAgility() {
        return pointAgility;
    }

    public void setPointAgility(String pointAgility) {
        this.pointAgility = pointAgility;
    }

    public String getPointSagesse() {
        return pointSagesse;
    }

    public void setPointSagesse(String pointSagesse) {
        this.pointSagesse = pointSagesse;
    }

}
