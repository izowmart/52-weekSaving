package com.example.a52weeksaving;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {

    private EditText mName,mPasswd;
    private Button mRegisterBtn;
    private String Name,Password;
    public static final String PREFERENCE= "preference";
    public static final String PREF_NAME = "name";
    public static final String PREF_PASSWD = "passwd";
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mName = (EditText)findViewById(R.id.name);
        mPasswd = (EditText)findViewById(R.id.passwd);
        mRegisterBtn = (Button)findViewById(R.id.registerBtn);
        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validUserData()){
                    SharedPreferences mSharedPreference = getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
                    SharedPreferences.Editor mEditor = mSharedPreference.edit();
                    mEditor.putString(PREF_NAME,Name);
                    mEditor.putString(PREF_PASSWD,Password);
                    mEditor.apply();
                    finish();
                }
            }
        });
    }

    private boolean validUserData() {
        Name = mName.getText().toString().trim();
        Password = mPasswd.getText().toString().trim();
        return !(Name.isEmpty() || Password.isEmpty());
    }
}