package com.giroux.kevin.dofustuff.constants;

import android.util.Log;

import io.realm.SyncConfiguration;
import io.realm.SyncUser;

/**
 * Created by girouxkevin on 24/05/2017.
 */

public class DofusRealmSyncConfiguration {
    private static DofusRealmSyncConfiguration ourInstance = null;

    private static SyncUser user;
    private static String url;
    private SyncConfiguration syncConfiguration;

    public static DofusRealmSyncConfiguration getInstance() {
        if(DofusRealmSyncConfiguration.ourInstance == null){
            DofusRealmSyncConfiguration.ourInstance = new DofusRealmSyncConfiguration();
            return DofusRealmSyncConfiguration.ourInstance;
        }else{
            return ourInstance;
        }

    }

    private DofusRealmSyncConfiguration() {
        if(DofusRealmSyncConfiguration.ourInstance == null){
            syncConfiguration = new SyncConfiguration.Builder(user,url).build();
        }

    }

    public SyncUser getUser() {
        return user;
    }

    public static void setUser(SyncUser userParam) {
        Log.i("Info","Passage ici User");
        user = userParam;
    }

    public String getUrl() {

        return url;
    }

    public static void setUrl(String urlParam) {
        Log.i("Info","Passage ici Url");
        url = urlParam;
    }

    public SyncConfiguration getSyncConfiguration() {
        return syncConfiguration;
    }

    public void setSyncConfiguration(SyncConfiguration syncConfiguration) {
        this.syncConfiguration = syncConfiguration;
    }
}
