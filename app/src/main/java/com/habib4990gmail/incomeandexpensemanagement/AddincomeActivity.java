package com.habib4990gmail.incomeandexpensemanagement;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.habib4990gmail.incomeandexpensemanagement.database.IncomeDatabaseManager;
import com.habib4990gmail.incomeandexpensemanagement.models.Income;

import java.util.ArrayList;
import java.util.Calendar;

public class AddincomeActivity extends AppCompatActivity {
    EditText mIncomeCatagory;
    EditText mIncomeDescription;
    EditText mIncomeDate;
    EditText mIncomeAmount;
    IncomeDatabaseManager incomeDatabaseManager;
    //ListView listView;
    boolean isUpdating=false;
    Button saveIncomeBtn;
    int selectincomeId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addincome);
        saveIncomeBtn=findViewById(R.id.saveIncomeBtn);
        mIncomeCatagory=findViewById(R.id.incomeCatagory);
        mIncomeDescription=findViewById(R.id.incomeDescription);
        mIncomeDate=findViewById(R.id.incomeDate);
        mIncomeAmount=findViewById(R.id.incomeAmount);
        incomeDatabaseManager=new IncomeDatabaseManager(this);


    }

    public void saveIncome(View view) {
        if (isUpdating){
            Income income=new Income(selectincomeId,mIncomeCatagory.getText().toString(),mIncomeDescription.getText().toString(),mIncomeDate.getText().toString(),mIncomeAmount.getText().toString());
            long updatedRow=incomeDatabaseManager.updateIncome(income);
            if(updatedRow>0){
                relaunch();
            }else {
                Toast.makeText(this,"something is wrong!!", Toast.LENGTH_SHORT).show();
            }
        }else {
            Income income=new Income(mIncomeCatagory.getText().toString(),mIncomeDescription.getText().toString(),mIncomeDate.getText().toString(),mIncomeAmount.getText().toString());
            long insertedRow=incomeDatabaseManager.addIncome(income);
            if (insertedRow>0){
                relaunch();
                Toast.makeText(this,"Insert Sucessfully "+insertedRow, Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this,"something is wrong!!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void relaunch() {
        Intent intent=new Intent(this,AddincomeActivity.class);
        startActivity(intent);
    }


    @Override
    public void onBackPressed() {
    }
}
