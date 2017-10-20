package com.giroux.kevin.dofustuff.activity.search;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.giroux.kevin.dofustuff.R;
import com.giroux.kevin.dofustuff.adapter.ItemAdapter;
import com.giroux.kevin.dofustuff.network.ItemLoader;

import java.util.HashMap;
import java.util.Map;

public class SearchItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_item);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.items);
        final LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mLayoutManager.setReverseLayout(false);

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setHasFixedSize(false);
        recyclerView.setNestedScrollingEnabled(false);

        ItemAdapter itemAdapter = new ItemAdapter();
        itemAdapter.setActivity(this);
        recyclerView.setAdapter(itemAdapter);

        String url = "http://10.0.2.2:8080/items/level/200";
        Map<String,String> paramStr = new HashMap<>();
        Map<String,Object> uiObject = new HashMap<>();


        uiObject.put("adapter",itemAdapter);
        ItemLoader itemLoader = new ItemLoader(url,"GET",paramStr);
        itemLoader.setListObject(uiObject);
        itemLoader.execute();


    }
}
