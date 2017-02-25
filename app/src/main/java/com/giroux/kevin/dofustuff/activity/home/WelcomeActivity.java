package com.giroux.kevin.dofustuff.activity.home;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.giroux.kevin.dofustuff.R;
import com.giroux.kevin.dofustuff.activity.MainActivity;
import com.giroux.kevin.dofustuff.activity.user.LoginActivity;
import com.giroux.kevin.dofustuff.activity.user.RegisterActivity;

import io.realm.Realm;
import io.realm.log.LogLevel;
import io.realm.log.RealmLog;

public class WelcomeActivity extends AppCompatActivity {
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        long SPLASH_LENGTH = 3000;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Realm.init(this);
        /* Récupération du numéro de version de l'application */
        try{
            PackageInfo pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            String version = pInfo.versionName;
            // Récupération du TextView permettant l'affichage de la version
            TextView versionNumber = (TextView) findViewById (R.id.versionNumber);
            versionNumber.setText (version);
        }catch (PackageManager.NameNotFoundException e ){
            e.printStackTrace ();
        }

		/* Affichage du SplashScreen pendant la durée SPLASH_LENGTH */
        handler.postDelayed(() -> {

            SharedPreferences preferences = this.getSharedPreferences("com.giroux.kevin.dofustuff", Context.MODE_PRIVATE);
            String email = preferences.getString("email","");
            Intent intent;
            if(!email.equals("")){
                intent = new Intent(WelcomeActivity.this, LoginActivity.class);
            }else{
                intent = new Intent(WelcomeActivity.this, RegisterActivity.class);
            }
            // On Lance l'activité MainActivity lorsqu'on a atteint le temps SPLASH_LENGTH
            startActivity(intent);
            finish();
        }, SPLASH_LENGTH);



    }
}
