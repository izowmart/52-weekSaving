package com.example.a52weeksaving.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a52weeksaving.R;
import com.example.a52weeksaving.models.AccountDetails;

import java.util.List;

public class CardsAdapter extends RecyclerView.Adapter<CardsAdapter.CardHolder> {

    Context context;
    private List<AccountDetails> accountDetailsList;

    public CardsAdapter(Context context, List<AccountDetails> accountDetailsList) {
        this.context = context;
        this.accountDetailsList = accountDetailsList;
    }

    @NonNull
    @Override
    public CardsAdapter.CardHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_item_card,viewGroup,false);
        return new CardHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardsAdapter.CardHolder cardHolder, int i) {
        AccountDetails accountDetails = accountDetailsList.get(i);
        cardHolder.total.setText(accountDetails.getTotal_amount());
        cardHolder.deposit.setText(accountDetails.getDeposit());
        cardHolder.weekNumber.setText(accountDetails.getWeek_number());

    }

    @Override
    public int getItemCount() {
        return accountDetailsList.size();
    }

    public class CardHolder extends RecyclerView.ViewHolder {
        private TextView weekNumber;
        private TextView deposit;
        private TextView total;
        private TextView total_savings;

        public CardHolder(@NonNull View itemView) {
            super(itemView);
            weekNumber = itemView.findViewById(R.id.week_no);
            deposit = itemView.findViewById(R.id.deposit_amount);
            total = itemView.findViewById(R.id.total);
        }
    }
}
