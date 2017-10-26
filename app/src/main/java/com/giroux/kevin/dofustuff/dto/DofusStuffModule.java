package com.giroux.kevin.dofustuff.dto;

import io.realm.annotations.RealmModule;

/**
 * Created by girouxkevin on 26/10/2017.
 */
@RealmModule(classes ={
        Character.class,
        ItemCharacter.class,
        Characteristic.class,
        CharacteristicClass.class,
        CharacteristicSort.class,
        Sort.class,

} )
public class DofusStuffModule {
}
