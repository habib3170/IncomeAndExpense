package com.habib4990gmail.incomeandexpensemanagement;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.habib4990gmail.incomeandexpensemanagement.database.ExpenseDatabaseManager;
import com.habib4990gmail.incomeandexpensemanagement.models.Expense;

public class AddexpenseActivity extends AppCompatActivity {
    private EditText mExpenseCatagory;
    private EditText mExpenseDescription;
    private EditText mExpenseDate;
    private EditText mExpenseAmount;
    ExpenseDatabaseManager expenseDatabaseManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addexpense);
        mExpenseCatagory=findViewById(R.id.expenseCatagory);
        mExpenseDescription=findViewById(R.id.expenseDescription);
        mExpenseDate=findViewById(R.id.expenseDate);
        mExpenseAmount=findViewById(R.id.expenseAmount);
        expenseDatabaseManager=new ExpenseDatabaseManager(this);
    }

    public void saveExpense(View view) {
        Expense expense=new Expense(mExpenseCatagory.getText().toString(),mExpenseDescription.getText().toString(),mExpenseDate.getText().toString(),mExpenseAmount.getText().toString());
        long insertedRow=expenseDatabaseManager.addExpense(expense);
        if (insertedRow>0){
            Toast.makeText(this,"Insert Sucessfully "+insertedRow, Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"something is wrong!!", Toast.LENGTH_SHORT).show();
        }

    }
    @Override
    public void onBackPressed() {
    }

}
