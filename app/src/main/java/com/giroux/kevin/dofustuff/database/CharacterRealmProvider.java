package com.giroux.kevin.dofustuff.database;

import com.giroux.kevin.dofustuff.constants.DofusRealmSyncConfiguration;
import com.giroux.kevin.dofustuff.dto.Character;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * Created by girouxkevin on 22/10/2017.
 */

public class CharacterRealmProvider {

    public static List<Character> retrieveListOfCharacter(){
        Realm realm = Realm.getInstance(DofusRealmSyncConfiguration.getInstance().getSyncConfiguration());
        realm.beginTransaction();
        RealmResults<Character> characters = realm.where(Character.class).findAll();


        List<Character> characterList = new ArrayList<>();
        characterList.addAll(characters);

        realm.close();
        return characterList;

    }


}
