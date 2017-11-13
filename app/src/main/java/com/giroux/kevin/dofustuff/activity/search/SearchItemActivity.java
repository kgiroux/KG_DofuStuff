package com.giroux.kevin.dofustuff.activity.search;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.giroux.kevin.dofustuff.R;
import com.giroux.kevin.dofustuff.adapter.ItemAdapter;
import com.giroux.kevin.dofustuff.commons.item.ItemCategory;
import com.giroux.kevin.dofustuff.network.ItemLoader;

import java.util.HashMap;
import java.util.Map;

public class SearchItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_item);

        RecyclerView recyclerView = findViewById(R.id.itemCharacters);
        final LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mLayoutManager.setReverseLayout(false);

        ItemCategory category = (ItemCategory) getIntent().getSerializableExtra("category");

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setHasFixedSize(false);
        recyclerView.setNestedScrollingEnabled(false);

        ItemAdapter itemAdapter = new ItemAdapter();
        itemAdapter.setActivity(this);
        recyclerView.setAdapter(itemAdapter);

        String url = "http://nexus-factory.ovh:9001/items/level/200/type/"+ category;
        Map<String,String> paramStr = new HashMap<>();
        Map<String,Object> uiObject = new HashMap<>();


        uiObject.put("adapter",itemAdapter);
        ItemLoader itemLoader = new ItemLoader(url,"GET",paramStr);
        itemLoader.setListObject(uiObject);
        itemLoader.execute();


    }
}
