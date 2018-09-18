package com.giroux.kevin.dofustuff.activity.user;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import com.giroux.kevin.dofustuff.R;
import com.giroux.kevin.dofustuff.commons.security.PasswordAlgo;
import com.giroux.kevin.dofustuff.commons.security.TypeUser;
import com.giroux.kevin.dofustuff.commons.security.User;
import com.giroux.kevin.dofustuff.constants.Constants;
import com.giroux.kevin.dofustuff.network.UserRegisterTask;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static android.text.TextUtils.isEmpty;

public class RegisterActivity extends AppCompatActivity {

    private AutoCompleteTextView usernameView;
    private EditText emailView;
    private EditText passwordView;
    private EditText passwordConfirmationView;
    private View progressView;
    private View registerFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        usernameView =  findViewById(R.id.username);
        passwordView = findViewById(R.id.password);
        emailView = findViewById(R.id.email);
        passwordConfirmationView =  findViewById(R.id.password_confirmation);
        passwordConfirmationView.setOnEditorActionListener((textView, id, keyEvent) -> {
            if (id == R.id.register || id == EditorInfo.IME_NULL) {
                attemptRegister();
                return true;
            }
            return false;
        });


        Button mailRegisterButton = (Button) findViewById(R.id.email_register_button);
        mailRegisterButton.setOnClickListener(view -> attemptRegister());

        Button signButton = (Button) findViewById(R.id.email_sign_in_button);
        signButton.setOnClickListener(view -> {
            Intent t = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(t);
        });

        registerFormView = findViewById(R.id.register_form);
        progressView = findViewById(R.id.register_progress);
    }

    private void attemptRegister() {
        usernameView.setError(null);
        passwordView.setError(null);
        passwordConfirmationView.setError(null);

        String username = usernameView.getText().toString();
        String email = emailView.getText().toString();
        String password = passwordView.getText().toString();
        String passwordConfirmation = passwordConfirmationView.getText().toString();

        boolean cancel = false;
        View focusView = null;


        if (isEmpty(username)) {
            usernameView.setError(getString(R.string.error_field_required));
            focusView = usernameView;
            cancel = true;
        }

        if(isEmpty(email)){
            emailView.setError(getString(R.string.error_field_required));
            focusView = emailView;
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
            String encryptedPassword = PasswordAlgo.encryptSHA512(password);
            // On essaye d'ajouter l'utilisateur dans la base de données coté serveur.
            User user = new User();
            user.setPassword(encryptedPassword);
            user.setLogin(username);
            user.setEmail(email);
            user.setTypeUser(TypeUser.ANDROID_USER);
            Map<String, String> paramMap = new HashMap<>();
            String id = generateUUID();
            paramMap.put("id", id);
            paramMap.put("password",encryptedPassword);
            paramMap.put("login",username);
            paramMap.put("email",email);
            paramMap.put("typeUser", TypeUser.ANDROID_USER.toString());
            Map<String, Object> uiObjectMaps = new HashMap<>();
            uiObjectMaps.put("login", usernameView);
            UserRegisterTask userRegisterTask = new UserRegisterTask(Constants.USER_URL, com.giroux.kevin.androidhttprequestlibrairy.constants.Constants.METHOD_PUT,paramMap);
            userRegisterTask.setActivity(this);
            userRegisterTask.setListObject(uiObjectMaps);
            userRegisterTask.execute();

        }
    }

    public void showProgress(boolean show) {
        int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

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

    private String generateUUID(){
        return UUID.randomUUID().toString();
    }
}
