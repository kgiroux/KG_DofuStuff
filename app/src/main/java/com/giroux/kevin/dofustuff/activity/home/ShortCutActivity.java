package com.giroux.kevin.dofustuff.activity.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.giroux.kevin.dofustuff.R;

import io.realm.log.LogLevel;
import io.realm.log.RealmLog;


public class ShortCutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent.ShortcutIconResource icon = Intent.ShortcutIconResource.fromContext(this, R.drawable.dofus_logosizechange);

        Intent intent = new Intent();

        Intent launchIntent = new Intent(this, WelcomeActivity.class);

        intent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, launchIntent);
        intent.putExtra(Intent.EXTRA_SHORTCUT_NAME, getApplicationContext().getString(R.string.app_name));
        intent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, icon);

    }
}
