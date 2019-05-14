package com.example.a52weeksaving.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a52weeksaving.R;
import com.example.a52weeksaving.models.Account;

import java.util.ArrayList;
import java.util.List;

public class CardsAdapter extends RecyclerView.Adapter<CardsAdapter.CardHolder> {

    private Context context;
    private List<Account> data;

    public CardsAdapter(Context context) {
        this.context = context;
        this.data = new ArrayList<>();
    }

    @NonNull
    @Override
    public CardsAdapter.CardHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_item_card,viewGroup,false);
        return new CardHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardsAdapter.CardHolder cardHolder, int i) {
        Account account_data = data.get(i);

        cardHolder.weekNumber.setText(account_data.getWeek());
        cardHolder.deposit.setText(account_data.getAmount_to_save());
        cardHolder.total.setText(account_data.getTotal_saved());


    }
    public void UpdateUI(List<Account> items) {
        this.data = items;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return data.size();
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
