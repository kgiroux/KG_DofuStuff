package com.giroux.kevin.dofustuff.activity.search;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.giroux.kevin.dofustuff.R;
import com.giroux.kevin.dofustuff.adapter.ItemAdapter;
import com.giroux.kevin.dofustuff.commons.item.ItemCategory;
import com.giroux.kevin.dofustuff.network.ItemLoader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchItemActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ItemAdapter itemAdapter;
    private ItemLoader itemLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_item);

        recyclerView = findViewById(R.id.itemCharacters);
        final LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mLayoutManager.setReverseLayout(false);

        ItemCategory category = (ItemCategory) getIntent().getSerializableExtra("category");

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setHasFixedSize(false);
        recyclerView.setNestedScrollingEnabled(false);

        itemAdapter = new ItemAdapter();
        itemAdapter.setActivity(this);
        recyclerView.setAdapter(itemAdapter);
        
        Spinner spinner = findViewById(R.id.spinner);

        final List<ItemCategory> categories = Arrays.asList(ItemCategory.values());

        ArrayAdapter<ItemCategory> adapter = new ArrayAdapter<>(getApplication(), android.R.layout.simple_list_item_1, categories);
        spinner.setAdapter(adapter);
        spinner.setSelection(categories.indexOf(ItemCategory.findByCategory(category.getCategory())));

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i("ItemSelected", categories.get(i).toString());
                loadData(categories.get(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    private void loadData(ItemCategory category){

        String url = "http://nexus-factory.ovh:9001/items/level/200/type/"+ category;
        Map<String,String> paramStr = new HashMap<>();
        Map<String,Object> uiObject = new HashMap<>();

        itemAdapter.resetData();

        uiObject.put("adapter",itemAdapter);
        itemLoader = new ItemLoader(url,"GET",paramStr);
        itemLoader.setListObject(uiObject);
        itemLoader.execute();

    }
}
