package com.example.expenseapp.views.activities;

import android.os.Bundle;
import android.view.Menu;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.expenseapp.adapters.TransactionsAdapter;
import com.example.expenseapp.models.Transaction;
import com.example.expenseapp.utils.Constants;
import com.example.expenseapp.utils.Helper;
import com.example.expenseapp.views.fragments.AddTransactionFragment;
import com.example.expenseapp.R;
import com.example.expenseapp.databinding.ActivityMainBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());

        setSupportActionBar(binding.Toolbar);
        getSupportActionBar().setTitle("Transactions");

        Constants.setCategories();

        calendar = Calendar.getInstance();
        updateDate();

        binding.nextDateBtn.setOnClickListener(c-> {
            calendar.add(Calendar.DATE, 1);
            updateDate();
        });

        binding.prevDateBtn.setOnClickListener(c->{
            calendar.add(Calendar.DATE, -1);
            updateDate();
        });

        binding.floatingActionButton.setOnClickListener(c-> {
            new AddTransactionFragment().show(getSupportFragmentManager(), null);
        });

        ArrayList<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(Constants.INCOME, "Business", "Cash", "Some note here", new Date(), 500,2));
        transactions.add(new Transaction(Constants.EXPENSE, "Investment", "Bank", "Some note here", new Date(), -900,4));
        transactions.add(new Transaction(Constants.INCOME, "Rent", "Card", "Some note here", new Date(), 500,5));
        transactions.add(new Transaction(Constants.INCOME, "Business", "Other", "Some note here", new Date(), 500,6));

        TransactionsAdapter transactionsAdapter = new TransactionsAdapter(this,transactions);
        binding.transactionsList.setLayoutManager(new LinearLayoutManager(this));
        binding.transactionsList.setAdapter(transactionsAdapter);



//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
    }

    void updateDate(){
//        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM, yyyy");
        binding.currentDate.setText(Helper.formatDate(calendar.getTime()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
}