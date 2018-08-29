package com.habib4990gmail.incomeandexpensemanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void addIncome(View view) {
        Intent intent=new Intent(this,AddincomeActivity.class);
        startActivity(intent);
    }
    public void addExpense(View view) {
        Intent intent=new Intent(this,AddexpenseActivity.class);
        startActivity(intent);
    }

    public void incomeList(View view) {
        Intent intent=new Intent(this,IncomelistActivity.class);
        startActivity(intent);
    }

    public void expenseList(View view) {
        Intent intent=new Intent(this,ExpenselistActivity.class);
        startActivity(intent);
    }
}
