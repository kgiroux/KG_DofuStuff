package com.giroux.kevin.dofustuff.activity.user;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.giroux.kevin.dofustuff.R;

import io.realm.ObjectServerError;
import io.realm.SyncCredentials;
import io.realm.SyncUser;

import static android.text.TextUtils.isEmpty;
import static com.giroux.kevin.dofustuff.constants.Constants.AUTH_URL;

public class RegisterActivity extends AppCompatActivity implements SyncUser.Callback {

    private AutoCompleteTextView usernameView;
    private EditText passwordView;
    private EditText passwordConfirmationView;
    private View progressView;
    private View registerFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        usernameView = (AutoCompleteTextView) findViewById(R.id.username);
        passwordView = (EditText) findViewById(R.id.password);
        passwordConfirmationView = (EditText) findViewById(R.id.password_confirmation);
        passwordConfirmationView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.register || id == EditorInfo.IME_NULL) {
                    attemptRegister();
                    return true;
                }
                return false;
            }
        });


        final Button mailRegisterButton = (Button) findViewById(R.id.email_register_button);
        mailRegisterButton.setOnClickListener(view -> attemptRegister());

        final Button signButton = (Button) findViewById(R.id.email_sign_in_button);
        signButton.setOnClickListener(view -> {
            Intent t = new Intent(getApplicationContext(),LoginActivity.class);
            startActivity(t);
        });

        registerFormView = findViewById(R.id.register_form);
        progressView = findViewById(R.id.register_progress);
    }

    private void attemptRegister() {
        usernameView.setError(null);
        passwordView.setError(null);
        passwordConfirmationView.setError(null);

        final String username = usernameView.getText().toString();
        final String password = passwordView.getText().toString();
        final String passwordConfirmation = passwordConfirmationView.getText().toString();

        boolean cancel = false;
        View focusView = null;


        if (isEmpty(username)) {
            usernameView.setError(getString(R.string.error_field_required));
            focusView = usernameView;
            cancel = true;
        }

        if (isEmpty(password)) {
            passwordView.setError(getString(R.string.error_field_required));
            focusView = passwordView;
            cancel = true;
        }

        if (isEmpty(passwordConfirmation)) {
            passwordConfirmationView.setError(getString(R.string.error_field_required));
            focusView = passwordConfirmationView;
            cancel = true;
        }

        if (!password.equals(passwordConfirmation)) {
            passwordConfirmationView.setError(getString(R.string.error_incorrect_password));
            focusView = passwordConfirmationView;
            cancel = true;
        }
        if (cancel) {
            focusView.requestFocus();
        } else {
            showProgress(true);
            SyncUser.loginAsync(SyncCredentials.usernamePassword(username, password, true), AUTH_URL, new SyncUser.Callback() {
                @Override
                public void onSuccess(SyncUser user) {
                    registrationComplete(user);
                }

                @Override
                public void onError(ObjectServerError error) {
                    showProgress(false);
                    String errorMsg;
                    switch (error.getErrorCode()) {
                        case EXISTING_ACCOUNT:
                            errorMsg = "Account already exists";
                            break;
                        default:
                            errorMsg = error.toString();
                    }

                   // RealmLog.info(errorMsg);
                    System.out.println("HELLLO" + errorMsg);
                    Toast.makeText(RegisterActivity.this, errorMsg, Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    private void registrationComplete(SyncUser user) {
        UserManager.setActiveUser(user);
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private void showProgress(final boolean show) {
        final int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

        registerFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        registerFormView.animate().setDuration(shortAnimTime).alpha(
                show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                registerFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            }
        });

        progressView.setVisibility(show ? View.VISIBLE : View.GONE);
        progressView.animate().setDuration(shortAnimTime).alpha(
                show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                progressView.setVisibility(show ? View.VISIBLE : View.GONE);
            }
        });
    }

    @Override
    public void onSuccess(SyncUser user) {
        registrationComplete(user);
    }

    @Override
    public void onError(ObjectServerError error) {
        String errorMsg;
        switch (error.getErrorCode()) {
            case EXISTING_ACCOUNT:
                errorMsg = "Account already exists";
                break;
            default:
                errorMsg = error.toString();
        }
        //RealmLog.info(errorMsg);
        System.out.println(errorMsg);
        Toast.makeText(RegisterActivity.this, errorMsg, Toast.LENGTH_LONG).show();
    }
}
