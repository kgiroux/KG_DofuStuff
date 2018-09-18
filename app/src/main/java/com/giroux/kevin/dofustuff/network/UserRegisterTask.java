package com.giroux.kevin.dofustuff.network;

import android.app.Activity;
import android.widget.EditText;
import com.giroux.kevin.androidhttprequestlibrairy.AndroidHttpRequest;
import com.giroux.kevin.dofustuff.activity.user.RegisterActivity;
import com.giroux.kevin.dofustuff.error.ErrorProfile;

import java.net.HttpURLConnection;
import java.util.Map;

public class UserRegisterTask extends AndroidHttpRequest {
    /**
     * Activity
     */
    private Activity activity;

    /**
     * Constructeur
     *
     * @param url      Url
     * @param method   method
     * @param paramStr String param
     */
    public UserRegisterTask(String url, String method, Map<String, String> paramStr) {
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
            // Cas d'une inscription réussi
            ((RegisterActivity) activity).showProgress(false);
        }
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }
}
