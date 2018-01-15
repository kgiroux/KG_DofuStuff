package com.giroux.kevin.dofustuff.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.giroux.kevin.dofustuff.R;
import com.giroux.kevin.dofustuff.activity.administration.AdministrationActivity;
import com.giroux.kevin.dofustuff.activity.almanax.AlmanaxActivity;
import com.giroux.kevin.dofustuff.activity.character.CharacterInformationActivity;
import com.giroux.kevin.dofustuff.activity.character.CreateActivity;
import com.giroux.kevin.dofustuff.activity.search.SearchItemActivity;
import com.giroux.kevin.dofustuff.adapter.CharacterAdapter;
import com.giroux.kevin.dofustuff.database.PrimaryKeyFactory;
import com.giroux.kevin.dofustuff.dto.Character;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Realm realm;
    private CharacterAdapter adapter;
    private RealmResults<Character> characters;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(this);

        recyclerView = findViewById(R.id.characters);
        final LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mLayoutManager.setReverseLayout(false);

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setHasFixedSize(false);
        recyclerView.setNestedScrollingEnabled(false);
        realm = Realm.getDefaultInstance();
        try{
            PrimaryKeyFactory.getInstance().initialize(realm);
        }catch (IllegalStateException ex){
            Log.i("Init","Nothing to do");
        }

        adapter = new CharacterAdapter(realm.where(Character.class).findAll(),getApplicationContext());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        recyclerView.setAdapter(null);
        realm.close();

    }

    @Override
    protected void onPause() {
        super.onPause();
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Intent t = null;
        if (id == R.id.nav_add) {
             t = new Intent(this, CreateActivity.class);
        }else if(id == R.id.nav_load){
            t = new Intent(this, CharacterInformationActivity.class);
        } else if (id == R.id.nav_encyclopedie) {
            t = new Intent(this, SearchItemActivity.class);
        } else if (id == R.id.nav_manage) {
        } else if (id == R.id.nav_share) {
        } else if (id == R.id.nav_send) {

        } else if(id == R.id.nav_lock){
            t = new Intent(this,AdministrationActivity.class);
        } else if(id == R.id.nav_almanax){
            t = new Intent(this, AlmanaxActivity.class);
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        if(t != null){
            startActivity(t);

        }
        return true;
    }
}
