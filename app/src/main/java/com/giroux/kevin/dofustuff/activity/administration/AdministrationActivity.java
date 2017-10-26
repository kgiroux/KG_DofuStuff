package com.giroux.kevin.dofustuff.activity.administration;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.giroux.kevin.dofustuff.R;
import com.giroux.kevin.dofustuff.constants.Constants;
import com.giroux.kevin.dofustuff.constants.DofusRealmSyncConfiguration;
import com.giroux.kevin.dofustuff.dto.Character;
import com.giroux.kevin.dofustuff.dto.Characteristic;
import com.giroux.kevin.dofustuff.dto.CharacteristicClass;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.SyncConfiguration;
import io.realm.SyncUser;

public class AdministrationActivity extends AppCompatActivity {

    @BindView(R.id.button2)
    Button button2;
    @BindView(R.id.button3)
    Button button3;
    @BindView(R.id.button4)
    Button button4;

    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administration);
        ButterKnife.bind(this);

        RealmConfiguration config = new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build();
        SyncConfiguration configSync = null;
        SyncUser user = SyncUser.currentUser();
        if(user != null){
            DofusRealmSyncConfiguration.setUser(user);
            Log.i("Current User", SyncUser.currentUser().toJson());
            DofusRealmSyncConfiguration.setUrl(Constants.REALM_URL);
            Log.i("Current URL", Constants.REALM_URL);
            configSync  = DofusRealmSyncConfiguration.getInstance().getSyncConfiguration();
        }
        if(configSync != null){
            realm = Realm.getInstance(configSync);
        }else{
            realm = Realm.getInstance(config);
        }
        realm.beginTransaction();
        button2.setOnClickListener(v -> {
            realm.where(Characteristic.class).findAll().deleteAllFromRealm();
            realm.commitTransaction();
        });
        button3.setOnClickListener(v -> {
            realm.where(CharacteristicClass.class).findAll().deleteAllFromRealm();
            realm.commitTransaction();
        });
        button4.setOnClickListener(v -> {
            realm.where(Character.class).findAll().deleteAllFromRealm();
            realm.commitTransaction();
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        realm.close();
    }
}
