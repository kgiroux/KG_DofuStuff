package com.giroux.kevin.dofustuff.activity.character.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.giroux.kevin.dofustuff.R;
import com.giroux.kevin.dofustuff.adapter.ItemCharacterAdapter;
import com.giroux.kevin.dofustuff.commons.item.Item;
import com.giroux.kevin.dofustuff.constants.DofusRealmSyncConfiguration;
import com.giroux.kevin.dofustuff.dto.Character;
import com.giroux.kevin.dofustuff.dto.ItemDisplay;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.realm.Realm;

/**
 * Created by kevin on 15/12/2016.
 */

public class ItemFragment extends Fragment {
    private Realm realm;

    public ItemFragment() {
    }

    public static ItemFragment newInstance() {
        return new ItemFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView view = (RecyclerView) inflater.inflate(R.layout.fragment_item, container, false);
        long argument = (long) getArguments().get("idCharacter");
        //initView(argument);

        final GridLayoutManager mLayoutManager = new GridLayoutManager(getContext(),1,GridLayoutManager.VERTICAL,false);
        mLayoutManager.setAutoMeasureEnabled(true);

        view.setLayoutManager(mLayoutManager);
        view.setHasFixedSize(false);

        List<Item> displays = initList();

        ItemCharacterAdapter adapter = new ItemCharacterAdapter();
        adapter.setContext(getContext());
        view.setAdapter(adapter);
        adapter.setData(displays);
        return view;
    }

    public List<Item> initList(){

        List<Item> displays = new ArrayList<>();


        Item itemDisplay = new Item();
        itemDisplay.setImageId(R.drawable.defaultcoiffe);
        itemDisplay.setName("Coiffe");
        displays.add(itemDisplay);

        itemDisplay = new Item();
        itemDisplay.setImageId(R.drawable.defaultcape);
        itemDisplay.setName("Cape");
        displays.add(itemDisplay);

        itemDisplay = new Item();
        itemDisplay.setImageId(R.drawable.defaultarmes);
        itemDisplay.setName("Arme");
        displays.add(itemDisplay);

        itemDisplay = new Item();
        itemDisplay.setImageId(R.drawable.defaultring);
        itemDisplay.setName("Anneau");
        displays.add(itemDisplay);

        itemDisplay = new Item();
        itemDisplay.setImageId(R.drawable.defaultring2);
        itemDisplay.setName("Anneau");
        displays.add(itemDisplay);

        itemDisplay = new Item();
        itemDisplay.setImageId(R.drawable.defaultcollier);
        itemDisplay.setName("Amulette");
        displays.add(itemDisplay);

        itemDisplay = new Item();
        itemDisplay.setImageId(R.drawable.defaultceinture);
        itemDisplay.setName("Ceinture");
        displays.add(itemDisplay);

        itemDisplay = new Item();
        itemDisplay.setImageId(R.drawable.defaultbouclier);
        itemDisplay.setName("Bouclier");
        displays.add(itemDisplay);


        itemDisplay = new Item();
        itemDisplay.setImageId(R.drawable.defaultmonture);
        itemDisplay.setName("Monture");
        displays.add(itemDisplay);


        itemDisplay = new Item();
        itemDisplay.setImageId(R.drawable.defaultdofus);
        itemDisplay.setName("Dofus");
        displays.add(itemDisplay);
        itemDisplay = new Item();
        itemDisplay.setImageId(R.drawable.defaultdofus);
        itemDisplay.setName("Dofus");
        displays.add(itemDisplay);
        itemDisplay = new Item();
        itemDisplay.setImageId(R.drawable.defaultdofus);
        itemDisplay.setName("Dofus");
        displays.add(itemDisplay);
        itemDisplay = new Item();
        itemDisplay.setImageId(R.drawable.defaultdofus);
        itemDisplay.setName("Dofus");
        displays.add(itemDisplay);
        itemDisplay = new Item();
        itemDisplay.setImageId(R.drawable.defaultdofus);
        itemDisplay.setName("Dofus");
        displays.add(itemDisplay);
        itemDisplay = new Item();
        itemDisplay.setImageId(R.drawable.defaultdofus);
        itemDisplay.setName("Dofus");
        displays.add(itemDisplay);

        return displays;
    }


    public void initView(long id) {
        realm = Realm.getInstance(DofusRealmSyncConfiguration.getInstance().getSyncConfiguration());
        realm.beginTransaction();
        Character character = realm.where(Character.class).equalTo("id", id).findFirst();
        realm.commitTransaction();


        //String url = "http://nexus-factory.ovh:9001/items/level/200" + character.getLevelString() + "/" + ;
        //ItemLoader loader = new ItemLoader();
        Log.i("Test", character.getName());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
