package com.example.a52weeksaving;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.a52weeksaving.adapter.CardsAdapter;
import com.example.a52weeksaving.models.AccountDetails;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText startAmount;
    private TextView totalSaved;
    private Button calculate, deleteStartAmount;
    private Integer enteredAmount;

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
                validUserData();

                mSharedPreferences = getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
                mEditor = mSharedPreferences.edit();
                mEditor.putInt(STARTAMOUNT,enteredAmount);
                mEditor.apply();
                finish();

//                  these data is then populated to the recyclerviews in realtime from the shared preference.
                extractData();


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
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        cardsAdapter = new CardsAdapter(getApplicationContext());
        recyclerView.setAdapter(cardsAdapter);

    }

    private void extractData() {
        mSharedPreferences = getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
        Integer start_amount = mSharedPreferences.getInt(STARTAMOUNT,0);
        cardsAdapter.updateUI(start_amount);
        cardsAdapter.notifyDataSetChanged();
    }

    private void validUserData() {
        enteredAmount = Integer.valueOf(startAmount.getText().toString().trim());
        if (enteredAmount == null){
            startAmount.setError("Please enter a start amount");
        }

    }

}
