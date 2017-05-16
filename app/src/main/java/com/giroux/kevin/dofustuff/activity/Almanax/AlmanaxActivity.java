package com.giroux.kevin.dofustuff.activity.Almanax;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.giroux.kevin.dofustuff.R;
import com.giroux.kevin.dofustuff.activity.Almanax.network.AlmanaxTask;
import com.giroux.kevin.dofustuff.adapter.AlmanaxAdapter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayDeque;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

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
        almanaxAdapter.setActivity(this);

        Map<String,String> listParam = new HashMap<>();
        Map<String,Object> listUI = new HashMap<>();
        listUI.put("adapter",almanaxAdapter);
        listUI.put("listOfDates",genereteWeekly());
        AlmanaxTask almanaxTask = new AlmanaxTask("http://www.krosmoz.com/fr/almanax","GET",listParam);
        almanaxTask.setListObject(listUI);
        almanaxTask.execute();



        recyclerView.setAdapter(almanaxAdapter);
    }

    private Queue<String> genereteWeekly(){
        Queue<String> dates = new ArrayDeque<String>() ;
        int nbOfNextDay = 6;
        Calendar calendar = Calendar.getInstance();
        for(int i =0; i<nbOfNextDay; i++){
            calendar.add(Calendar.DAY_OF_YEAR, 1);
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.FRANCE);
            String dateToAdd = dateFormat.format(calendar.getTime());
            dates.add(dateToAdd);
        }
        return dates;

    }
}
