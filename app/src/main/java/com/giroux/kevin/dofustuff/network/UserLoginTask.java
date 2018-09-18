package com.giroux.kevin.dofustuff.network;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.EditText;
import com.giroux.kevin.androidhttprequestlibrairy.AndroidHttpRequest;
import com.giroux.kevin.dofustuff.activity.user.LoginActivity;
import com.giroux.kevin.dofustuff.error.ErrorProfile;

import java.net.HttpURLConnection;
import java.util.Map;

/**
 * RequestHTTP
 * @author Kévin Giroux.
 */
public class UserLoginTask extends AndroidHttpRequest {

    public void setActivity(Activity pActivity) {
        this.activity = pActivity;
    }

    /**
     * Context
     */
    private Activity activity;

    public UserLoginTask(String url, String method, Map<String, String> paramStr) {
        super(url, method, paramStr);
    }

    @Override
    protected void onPostExecute(Object o) {
        if (getErrorCode() != HttpURLConnection.HTTP_OK && getErrorWrapper() != null) {
            // Une erreur est survenue
            if (ErrorProfile.valueOf(getErrorWrapper().getMessage()).equals(ErrorProfile.ERR_PROFILE_11)) {
                ((EditText) getListObject().get("login")).setError("Login non disponible");
            }
        } else if (o instanceof String) {
            Log.i("Test", (String) o);
            SharedPreferences preferences = this.activity.getSharedPreferences("com.giroux.kevin.dofustuff", Context.MODE_PRIVATE);
            preferences.edit().putString("token", (String)o).apply();
            ((LoginActivity)this.activity).showProgress(false);
            ((LoginActivity)this.activity).changeActivity();
        }
    }
}
