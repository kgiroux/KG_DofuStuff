package com.giroux.kevin.dofustuff.constants;

import com.giroux.kevin.dofustuff.BuildConfig;
import com.giroux.kevin.dofustuff.R;

import io.realm.SyncConfiguration;

/**
 * Created by kevin on 26/11/2016.
 */

public class Constants {
    public static int NB_MAX_CARACTERISTIC = 995;
    public static int MAX_LEVEL = 200;
    public static int SIZE_PALIER_CARA = 100;
    public static int MAX_LEVEL_CARA = 3;
    public static final String AUTH_URL = "http://" + BuildConfig.OBJECT_SERVER_IP + ":9080/auth";
    public static final String REALM_URL = "realm://" + BuildConfig.OBJECT_SERVER_IP + ":9080/~/realmtasks";
    public static final String ITEM_URL = "http://nexus-factory.ovh:9001/items/level/";
    public static final String TOKEN_URL = "http://nexus-factory.ovh:9001/items/level/";
}
