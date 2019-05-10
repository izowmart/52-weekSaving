package com.example.a52weeksaving.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a52weeksaving.R;

import java.text.MessageFormat;

public class CardsAdapter extends RecyclerView.Adapter<CardsAdapter.CardHolder> {

    private Context context;
    private Integer startAmount;

    public CardsAdapter(Context context) {
        this.context = context;
        this.startAmount = 0;
    }

    @NonNull
    @Override
    public CardsAdapter.CardHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_item_card,viewGroup,false);
        return new CardHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardsAdapter.CardHolder cardHolder, int i) {
        Integer amountToSave = 0;
        Integer totalSaved = 0;

        for (int week = 1; week < 53; week++){
            amountToSave += startAmount;
            totalSaved += amountToSave;

            cardHolder.weekNumber.setText(MessageFormat.format("Week: {0}", week));
            cardHolder.deposit.setText(MessageFormat.format("KES {0}", amountToSave));
            cardHolder.total.setText(MessageFormat.format("KES {0}", totalSaved));
        }

    }

    @Override
    public int getItemCount() {
        return startAmount;
    }

    public void updateUI(Integer start_amount) {
        Log.d("message as at adapter", String.valueOf(start_amount));
        this.startAmount = start_amount;
    }

    public class CardHolder extends RecyclerView.ViewHolder {
        private TextView weekNumber;
        private TextView deposit;
        private TextView total;

        public CardHolder(@NonNull View itemView) {
            super(itemView);
            weekNumber = itemView.findViewById(R.id.week_no);
            deposit = itemView.findViewById(R.id.deposit_amount);
            total = itemView.findViewById(R.id.total);
        }
    }
}
