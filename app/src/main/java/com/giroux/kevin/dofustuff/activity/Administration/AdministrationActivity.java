package com.giroux.kevin.dofustuff.activity.Administration;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.giroux.kevin.dofustuff.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import io.realm.RealmConfiguration;
import io.realm.SyncUser;

public class AdministrationActivity extends AppCompatActivity {
    private RealmConfiguration config;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administration);

        config = new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build();
        Map<String, SyncUser>  users = SyncUser.all();
        List<String> identities = new ArrayList<>();
        if (Build.VERSION.SDK_INT >= 24) {
            identities = users.values().stream().map(SyncUser::getIdentity).collect(Collectors.toList());
            identities.forEach(System.out::println);
        }else{
            for(SyncUser u : users.values()){
                identities.add(u.getIdentity());
                Log.i("TEST",u.getAccessToken() + "_" +u.getIdentity());
            }
        }


    }
}
