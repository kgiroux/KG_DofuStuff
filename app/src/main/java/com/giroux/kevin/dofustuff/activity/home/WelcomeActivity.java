package com.giroux.kevin.dofustuff.activity.home;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.giroux.kevin.dofustuff.R;
import com.giroux.kevin.dofustuff.activity.MainActivity;
import com.giroux.kevin.dofustuff.activity.user.LoginActivity;
import com.giroux.kevin.dofustuff.activity.user.RegisterActivity;
import com.giroux.kevin.dofustuff.activity.user.UserManager;
import com.giroux.kevin.dofustuff.commons.security.PasswordAlgo;
import com.giroux.kevin.dofustuff.constants.Constants;

import io.realm.ObjectServerError;
import io.realm.Realm;
import io.realm.SyncCredentials;
import io.realm.SyncUser;
import io.realm.log.LogLevel;
import io.realm.log.RealmLog;

public class WelcomeActivity extends AppCompatActivity implements SyncUser.Callback<SyncUser>{
    private Handler handler = new Handler();
    public String email;
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
                SyncUser.loginAsync(SyncCredentials.usernamePassword(email, encryptedPassword, false), Constants.AUTH_URL, this);
            }else{
                Intent intent = new Intent(WelcomeActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        }, SPLASH_LENGTH);



    }

    @Override
    public void onSuccess(SyncUser result) {
        loginComplete(result);
    }

    private void loginComplete(SyncUser user) {
        UserManager.setActiveUser(user);
        Toast.makeText(this,"Welcome back " + email, Toast.LENGTH_LONG).show();
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void onError(ObjectServerError error) {
        String errorMsg;
        switch (error.getErrorCode()) {
            case UNKNOWN_ACCOUNT:
                errorMsg = "Account does not exists.";
                break;
            case INVALID_CREDENTIALS:
                errorMsg = "The provided credentials are invalid!"; // This message covers also expired account token
                break;
            default:
                errorMsg = error.toString();
        }
        Toast.makeText(this,"Failed to connect", Toast.LENGTH_LONG).show();
        startActivity(new Intent(this,LoginActivity.class));
        Log.e("ErrorRealm",errorMsg);
    }
}
