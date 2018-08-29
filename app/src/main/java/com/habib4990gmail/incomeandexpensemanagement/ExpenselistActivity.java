package com.habib4990gmail.incomeandexpensemanagement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.habib4990gmail.incomeandexpensemanagement.database.ExpenseDatabaseManager;
import com.habib4990gmail.incomeandexpensemanagement.models.Expense;

import java.util.ArrayList;

public class ExpenselistActivity extends AppCompatActivity {
    ExpenseDatabaseManager expenseDatabaseManager;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenselist);
        listView=findViewById(R.id.expenseLV);
        expenseDatabaseManager=new ExpenseDatabaseManager(this);
        ArrayList<Expense> expenseArrayList=expenseDatabaseManager.getAllExpense();
        ArrayList<String>list=new ArrayList<>();
        for (Expense expense:expenseArrayList){
            list.add(expense.getExpenseCatagory()+"         "+expense.getExpenseAmount()+"\n"+expense.getExpenseDescription());
        }
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this,R.layout.expense_custom_listview, R.id.expenseTitleTVId,list);
        listView.setAdapter(adapter);

    }
    public void onBackPressed() {

    }
}
