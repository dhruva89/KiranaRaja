package com.dhruvabharadwaj.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignInActivity extends AppCompatActivity {
    EditText inputEmail, inputPassword; // Email & Password
    public static final String LOGIN_CREDENTIALS = "com.barpad.loginCredentials";
    public static final String USER_NAME = "userName";
    public static final String USER_PWD = "password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        LoginCredentials loginCredentials = getSavedCredentials();
        if (loginCredentials != null) {
            authenticateUser(loginCredentials);
        }

        inputEmail = (EditText) findViewById(R.id.userEmail);
        inputPassword = (EditText) findViewById(R.id.userPassword);
        setupSigInButton();
    }

    private static class LoginCredentials {
        private String userName;
        private String password;

        LoginCredentials(String userName, String password) {
            super();
            this.userName = userName;
            this.password = password;
        }

        public String getUserName() {
            return userName;
        }

        public String getPassword() {
            return password;
        }
    }

    public LoginCredentials getSavedCredentials() {
        SharedPreferences prefs = getSharedPreferences(LOGIN_CREDENTIALS, MODE_PRIVATE);
        String savedUserName = prefs.getString(USER_NAME, null);
        String savedUserPassword = prefs.getString(USER_PWD, null);

        if (!TextUtils.isEmpty(savedUserName)
                && !TextUtils.isEmpty(savedUserPassword)) {
            return new LoginCredentials(savedUserName, savedUserPassword);
        }
        return null;
    }

    public void signIn() {
        Intent intent = new Intent(this.getApplicationContext(),
                MainActivity.class);
        startActivity(intent);
    }

    public void setupSigInButton() {
        Button signInButton = (Button) findViewById(R.id.signInButton);
        signInButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (inputEmail.getText().toString().equals("")
                        && inputPassword.getText().toString().equals("")) {
                    Intent registerIntent = new Intent(SignInActivity.this, SignUpActivity.class);
                    startActivity(registerIntent);
                } else if (inputEmail.getText().toString().equals("")) {
                    // Empty Email/Username
                    Toast.makeText(getApplicationContext(),
                            "Password field empty", Toast.LENGTH_SHORT).show();
                } else if (inputPassword.getText().toString().equals("")) {
                    // Empty Password
                    Toast.makeText(getApplicationContext(),
                            "Email/Username field empty", Toast.LENGTH_SHORT)
                            .show();
                } else {
                    authenticateUser(new LoginCredentials(inputEmail.getText()
                            .toString(), inputPassword.getText().toString()));
                }

            }
        });
    }

    public void authenticateUser(LoginCredentials loginCredentials) {

        /*
        RequestLogin loginRequest = new RequestLogin(this, loginCredentials);
        loginRequest.setListener(new ServiceListener<Login>() {
            @Override
            public void onServiceSuccess(Login resultObject) {
                CheckBox rememberCredentialsCheckBox = (CheckBox) findViewById(R.id.rememberLoginCredentials);
                if (rememberCredentialsCheckBox.isChecked()) {
                    String userName = ((EditText) findViewById(R.id.email))
                            .getText().toString();
                    String password = ((EditText) findViewById(R.id.password))
                            .getText().toString();

                    SharedPreferences prefs = SignInActivity.this
                            .getSharedPreferences(LOGIN_CREDENTIALS,
                                    MODE_PRIVATE);
                    prefs.edit().putString(USER_NAME, userName)
                            .putString(USER_PWD, password).commit();
                }

                Intent intent = new Intent(BarPadApplication.getAppContext(),
                        VenueListActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                        | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }

            @Override
            public void onServiceFailed(Login resultObject, int responseCode,
                                        String responseMessage) {
            }
        });
        loginRequest.makeRequest();
        */
        signIn();
    }
}
