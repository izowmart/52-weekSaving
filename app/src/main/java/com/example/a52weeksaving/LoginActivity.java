package com.example.a52weeksaving;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText mUsername,mUserpasswd;
    private Button mLogin;
    private TextView mRegister;
    private String Name,Password;
    private SharedPreferences mSharedPreferences;
    public static final String PREFERENCE= "preference";
    public static final String PREF_NAME = "name";
    public static final String PREF_PASSWD = "passwd";
    public static final String PREF_SKIP_LOGIN = "skip_login";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mSharedPreferences = getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
        if(mSharedPreferences.contains(PREF_SKIP_LOGIN)){
            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }else {
            mUsername = (EditText)findViewById(R.id.user_name);
            mUserpasswd = (EditText)findViewById(R.id.user_passwd);
            mLogin = (Button)findViewById(R.id.loginBtn);
            mRegister = (TextView)findViewById(R.id.register);
            mLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(validUserData()){
                        if(mSharedPreferences.contains(PREF_NAME) && mSharedPreferences.contains(PREF_PASSWD)){
                            SharedPreferences.Editor mEditor = mSharedPreferences.edit();
                            mEditor.putString(PREF_SKIP_LOGIN,"skip");
                            mEditor.apply();
                            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                            startActivity(intent);
                            finish();
                        }else {
                            Toast.makeText(getApplicationContext(),"Unable to Login kindly Register!!",Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(getApplicationContext(),"Unable to Login Please Enter Valid Data !!",Toast.LENGTH_SHORT).show();
                    }
                }
            });
            mRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                    startActivity(intent);
                }
            });
        }
    }

    private boolean validUserData() {
        Name = mUsername.getText().toString().trim();
        Password = mUserpasswd.getText().toString().trim();
        return !(Name.isEmpty() || Password.isEmpty());
    }
}