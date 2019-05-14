package com.example.a52weeksaving;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a52weeksaving.adapter.CardsAdapter;
import com.example.a52weeksaving.models.Account;
import org.json.JSONObject;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import static com.example.a52weeksaving.LoginActivity.PREF_SKIP_LOGIN;

public class MainActivity extends AppCompatActivity {

    private EditText startAmount;
    private Button calculate, deleteStartAmount;
    private TextView total_savings;
    private String enteredAmount;

    private RecyclerView recyclerView;
    private CardsAdapter cardsAdapter;
    Account account;
    private List<Account> items;
    private Integer int_enteredAmount;

    private SharedPreferences mSharedPreferences;
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;
    private SharedPreferences.Editor editor;
    public static final String PREFERENCE= "preference";
    public static final String STARTAMOUNT = "startAmount";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPreferences = getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
        if(!mPreferences.contains(PREF_SKIP_LOGIN)){
            editor = mPreferences.edit();
            editor.putString(PREF_SKIP_LOGIN,"skip");
            editor.apply();
        }

        startAmount = findViewById(R.id.start_amount);
        calculate = findViewById(R.id.btn_calculate);
        deleteStartAmount = findViewById(R.id.btn_del_start_amount);
        total_savings = findViewById(R.id.total_saved);

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enteredAmount = startAmount.getText().toString().trim();
                mSharedPreferences = getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
//                this validates numbers only.
                String regexStr = "^[0-9]*$";
                if (enteredAmount.matches(regexStr)){
                    if (!enteredAmount.isEmpty()){
                        int_enteredAmount = Integer.valueOf(enteredAmount);
                        if (int_enteredAmount<=50000000){
                            mEditor = mSharedPreferences.edit();
                            mEditor.putInt(STARTAMOUNT,int_enteredAmount);
                            mEditor.apply();
                            // These data is then populated to the recyclerViews in realtime from the shared preference.
                            extractData();

                        }else{
                            startAmount.setError("Amount must not exceed 50,000,000");
                        }
                    }else{
                        startAmount.setError("Field can't be empty!Your amount must be between 0 to 50,000,000");
                    }
                }else{
                    startAmount.setError("Please enter a valid number!");
                }
            }
        });


//        delete the start amount here from the shared preference
        deleteStartAmount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSharedPreferences = getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
                mEditor = mSharedPreferences.edit();
                mEditor.remove(STARTAMOUNT);
                mEditor.apply();

                // These data is then populated to the recyclerViews in realtime from the shared preference.
                extractData();
            }
        });

//        recyclerView
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        cardsAdapter = new CardsAdapter(getApplicationContext());
        recyclerView.setAdapter(cardsAdapter);

    }

    private void extractData() {
        mSharedPreferences = getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
        Integer int_start_amount = mSharedPreferences.getInt(STARTAMOUNT,0);
        Integer amountToSave = 0;
        Integer totalSaved = 0;
        items = new ArrayList<>();

        for (int week = 1; week< 53; week++) {
            amountToSave += int_start_amount;
            totalSaved += amountToSave;

            Log.d("one card","Week: " + week +"KES " + amountToSave +"KES "+totalSaved);
            account = new Account("Week: " + week,"KES " + amountToSave,"KES "+totalSaved);
            items.add(account);

        }
        //   set the total saved amount for the 52 weeks
        total_savings.setText(MessageFormat.format("KES: {0}", String.valueOf(totalSaved)));

        // this method updates data to the recyclerView in the adapter.
        cardsAdapter.UpdateUI(items);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.logout) {
            if(mPreferences.contains(PREF_SKIP_LOGIN)) {
                editor = mPreferences.edit();
                editor.remove(PREF_SKIP_LOGIN);
                editor.apply();
                Toast.makeText(this, "Logging out!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }

        }
        return super.onOptionsItemSelected(item);
    }
}
