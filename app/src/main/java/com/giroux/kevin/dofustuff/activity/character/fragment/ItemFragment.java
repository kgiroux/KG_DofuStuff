package com.giroux.kevin.dofustuff.activity.character.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.giroux.kevin.dofustuff.R;
import com.giroux.kevin.dofustuff.adapter.ItemCharacterAdapter;
import com.giroux.kevin.dofustuff.commons.item.Item;
import com.giroux.kevin.dofustuff.commons.item.ItemCategory;
import com.giroux.kevin.dofustuff.commons.characters.Character;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kevin on 15/12/2016.
 */

public class ItemFragment extends Fragment {
    private Character character;

    public ItemFragment() {
    }

    public static ItemFragment newInstance() {
        return new ItemFragment();
    }

    public static int RequestCodeItemFragment = 1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView view = (RecyclerView) inflater.inflate(R.layout.fragment_item, container, false);
        long argument = (long) getArguments().get("idCharacter");
        initView(argument);

        final LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mLayoutManager.setAutoMeasureEnabled(true);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mLayoutManager.setReverseLayout(false);
        view.setNestedScrollingEnabled(false);
        view.setLayoutManager(mLayoutManager);
        view.setHasFixedSize(false);

        List<Item> displays = initList();

        ItemCharacterAdapter adapter = new ItemCharacterAdapter();
        adapter.setActitity(getActivity());
        adapter.setLevel(character.getLevel());
        view.setAdapter(adapter);
        adapter.setData(displays);
        return view;
    }

    public List<Item> initList(){

        List<Item> displays = new ArrayList<>();


        Item itemDisplay = new Item();
        itemDisplay.setImageId(R.drawable.defaultcoiffe);
        itemDisplay.setName("Coiffe");
        itemDisplay.setCategory(ItemCategory.CHAPEAU);
        displays.add(itemDisplay);

        itemDisplay = new Item();
        itemDisplay.setImageId(R.drawable.defaultcape);
        itemDisplay.setName("Cape");
        itemDisplay.setCategory(ItemCategory.CAPE);
        displays.add(itemDisplay);

        itemDisplay = new Item();
        itemDisplay.setImageId(R.drawable.defaultarmes);
        itemDisplay.setName("Arme");
        itemDisplay.setCategory(ItemCategory.ARC);
        displays.add(itemDisplay);

        itemDisplay = new Item();
        itemDisplay.setImageId(R.drawable.defaultring);
        itemDisplay.setName("Anneau");
        itemDisplay.setCategory(ItemCategory.ANNEAU);
        displays.add(itemDisplay);

        itemDisplay = new Item();
        itemDisplay.setImageId(R.drawable.defaultring2);
        itemDisplay.setName("Anneau");
        itemDisplay.setCategory(ItemCategory.ANNEAU);
        displays.add(itemDisplay);

        itemDisplay = new Item();
        itemDisplay.setImageId(R.drawable.defaultcollier);
        itemDisplay.setCategory(ItemCategory.AMULETTE);
        itemDisplay.setName("Amulette");
        displays.add(itemDisplay);

        itemDisplay = new Item();
        itemDisplay.setImageId(R.drawable.defaultceinture);
        itemDisplay.setName("Ceinture");
        itemDisplay.setCategory(ItemCategory.CEINTURE);
        displays.add(itemDisplay);

        itemDisplay = new Item();
        itemDisplay.setImageId(R.drawable.defaultbouclier);
        itemDisplay.setName("Bouclier");
        itemDisplay.setCategory(ItemCategory.BOUCLIER);
        displays.add(itemDisplay);


        itemDisplay = new Item();
        itemDisplay.setImageId(R.drawable.defaultmonture);
        itemDisplay.setName("Monture");
        itemDisplay.setCategory(ItemCategory.MONTURE);
        displays.add(itemDisplay);


        itemDisplay = new Item();
        itemDisplay.setImageId(R.drawable.defaultdofus);
        itemDisplay.setName("Dofus");
        itemDisplay.setCategory(ItemCategory.DOFUS);
        displays.add(itemDisplay);
        itemDisplay = new Item();
        itemDisplay.setImageId(R.drawable.defaultdofus);
        itemDisplay.setCategory(ItemCategory.DOFUS);
        itemDisplay.setName("Dofus");
        displays.add(itemDisplay);
        itemDisplay = new Item();
        itemDisplay.setImageId(R.drawable.defaultdofus);
        itemDisplay.setCategory(ItemCategory.DOFUS);
        itemDisplay.setName("Dofus");
        displays.add(itemDisplay);
        itemDisplay = new Item();
        itemDisplay.setImageId(R.drawable.defaultdofus);
        itemDisplay.setCategory(ItemCategory.DOFUS);
        itemDisplay.setName("Dofus");
        displays.add(itemDisplay);
        itemDisplay = new Item();
        itemDisplay.setImageId(R.drawable.defaultdofus);
        itemDisplay.setCategory(ItemCategory.DOFUS);
        itemDisplay.setName("Dofus");
        displays.add(itemDisplay);
        itemDisplay = new Item();
        itemDisplay.setImageId(R.drawable.defaultdofus);
        itemDisplay.setCategory(ItemCategory.DOFUS);
        itemDisplay.setName("Dofus");
        displays.add(itemDisplay);

        return displays;
    }


    public void initView(long id) {
        // character = realm.where(Character.class).equalTo("id", id).findFirst();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
