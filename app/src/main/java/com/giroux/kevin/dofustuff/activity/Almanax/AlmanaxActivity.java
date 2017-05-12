package com.giroux.kevin.dofustuff.activity.Almanax;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.giroux.kevin.dofustuff.R;
import com.giroux.kevin.dofustuff.activity.Almanax.network.AlmanaxTask;
import com.giroux.kevin.dofustuff.adapter.AlmanaxAdapter;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;



public class AlmanaxActivity extends AppCompatActivity {

    @BindView(R.id.recyclerViewAlmanax)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_almanax);
        ButterKnife.bind(this);

        final LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mLayoutManager.setReverseLayout(false);

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setHasFixedSize(false);
        recyclerView.setNestedScrollingEnabled(false);

        AlmanaxAdapter almanaxAdapter = new AlmanaxAdapter();


        Map<String,String> listParam = new HashMap<>();
        Map<String,Object> listUI = new HashMap<>();
        listUI.put("adapter",almanaxAdapter);
        AlmanaxTask almanaxTask = new AlmanaxTask("http://www.krosmoz.com/fr/almanax","GET",listParam);
        almanaxTask.setListObject(listUI);
        almanaxTask.execute();

    }
}
