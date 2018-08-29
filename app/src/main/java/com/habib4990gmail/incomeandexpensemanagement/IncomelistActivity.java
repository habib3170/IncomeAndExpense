package com.habib4990gmail.incomeandexpensemanagement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.habib4990gmail.incomeandexpensemanagement.database.IncomeDatabaseManager;
import com.habib4990gmail.incomeandexpensemanagement.models.Income;

import java.util.ArrayList;

public class IncomelistActivity extends AppCompatActivity {
    EditText mIncomeCatagory;
    EditText mIncomeDescription;
    EditText mIncomeDate;
    EditText mIncomeAmount;
    IncomeDatabaseManager incomeDatabaseManager;
    ListView listView;
    boolean isUpdating=false;
    Button saveIncomeBtn;
    int selectincomeId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incomelist);
        mIncomeCatagory=findViewById(R.id.incomeCatagory);
        mIncomeDescription=findViewById(R.id.incomeDescription);
        mIncomeDate=findViewById(R.id.incomeDate);
        mIncomeAmount=findViewById(R.id.incomeAmount);
        saveIncomeBtn=findViewById(R.id.saveIncomeBtn);
        listView=findViewById(R.id.incomeLV);
        incomeDatabaseManager=new IncomeDatabaseManager(this);
        final ArrayList<Income>incomeArrayList=incomeDatabaseManager.getAllIncome();
        ArrayList<String>list=new ArrayList<>();
        for (Income income:incomeArrayList){
            list.add(income.getIncomeCatagory()+"       "+income.getIncomeAmount()+"\n"+income.getIncomeDescription());
        }
        ArrayAdapter<String>adapter=new ArrayAdapter<>(this,R.layout.income_custom_listview, R.id.incomeTitleTVId,list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Income income=incomeDatabaseManager.getSingleIncome(incomeArrayList.get(i).getId());
                mIncomeCatagory.setText(income.getIncomeCatagory());
                mIncomeDescription.setText(income.getIncomeDescription());
                mIncomeDate.setText(income.getIncomeDate());
                mIncomeAmount.setText(income.getIncomeAmount());
                saveIncomeBtn.setText("Update");
                isUpdating=true;
                selectincomeId=income.getId();
            }
        });

    }

    @Override
    public void onBackPressed() {

    }
}
