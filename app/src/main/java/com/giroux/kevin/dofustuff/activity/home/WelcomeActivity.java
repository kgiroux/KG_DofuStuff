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
import com.giroux.kevin.dofustuff.activity.user.RegisterActivity;
import com.giroux.kevin.dofustuff.commons.security.PasswordAlgo;

public class WelcomeActivity extends AppCompatActivity{
    private Handler handler = new Handler();
    public String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        long SPLASH_LENGTH = 3000;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        /* Récupération du numéro de version de l'application */
        try{
            PackageInfo pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            String version = pInfo.versionName;
            // Récupération du TextView permettant l'affichage de la version
            TextView versionNumber = findViewById (R.id.versionNumber);
            versionNumber.setText (version);
        }catch (PackageManager.NameNotFoundException e ){
            e.printStackTrace ();
        }

		/* Affichage du SplashScreen pendant la durée SPLASH_LENGTH */
        handler.postDelayed(() -> {
            SharedPreferences preferences = this.getSharedPreferences("com.giroux.kevin.dofustuff", Context.MODE_PRIVATE);
            email = preferences.getString("email","");
            String password = preferences.getString("password","");
            String encryptedPassword = PasswordAlgo.encryptSHA512(password);
            if(!"".equals(email) && !"".equals(password)){
               // SyncUser.loginAsync(SyncCredentials.usernamePassword(email, encryptedPassword, false), Constants.AUTH_URL, this);
            }else{
                Intent intent = new Intent(WelcomeActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        }, SPLASH_LENGTH);



    }
}
