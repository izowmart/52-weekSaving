package com.example.a52weeksaving;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.a52weeksaving.adapter.CardsAdapter;
import com.example.a52weeksaving.models.AccountDetails;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText startAmount;
    private TextView totalSaved;
    private Button calculate, deleteStartAmount;
    private String  enteredAmount;

    private List<AccountDetails> accountDetailsList;
    private List<AccountDetails> updateList;
    private RecyclerView recyclerView;
    CardsAdapter cardsAdapter;

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    public static final String PREFERENCE= "preference";
    public static final String STARTAMOUNT = "startAmount";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startAmount = findViewById(R.id.start_amount);
        totalSaved = findViewById(R.id.total_saved);
        calculate = findViewById(R.id.btn_calculate);
        deleteStartAmount = findViewById(R.id.btn_del_start_amount);

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validUserData()){
                    mSharedPreferences = getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
                    mEditor = mSharedPreferences.edit();
                    mEditor.putString(STARTAMOUNT,enteredAmount);
                    mEditor.apply();
                    finish();

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

            }
        });

//        recyclerView
        accountDetailsList = new ArrayList<>();
        recyclerView = findViewById(R.id.recycler_view);
//        populate the ArrayList with data from shared preference here
//        todo put dataList from shared preference here to store them
        updateList = new ArrayList<>();


        accountDetailsList.add(new AccountDetails(null,null));
//        todo
        cardsAdapter = new CardsAdapter(getApplicationContext(), accountDetailsList);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        recyclerView.setAdapter(cardsAdapter);

    }

    private boolean validUserData() {
        enteredAmount = startAmount.getText().toString().trim();
        return !(enteredAmount.isEmpty());
    }

}
