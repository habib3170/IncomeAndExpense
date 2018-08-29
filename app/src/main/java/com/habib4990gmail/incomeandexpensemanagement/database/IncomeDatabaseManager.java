package com.habib4990gmail.incomeandexpensemanagement.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.habib4990gmail.incomeandexpensemanagement.models.Income;

import java.util.ArrayList;

public class IncomeDatabaseManager {
    private Context context;
    private DatabaseHelper databaseHelper;

    public IncomeDatabaseManager(Context context) {
        this.context = context;
        databaseHelper=new DatabaseHelper(context);
    }

    public long addIncome(Income income){
        SQLiteDatabase sqLiteDatabase=databaseHelper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(DatabaseHelper.INCOME_CATAGORY,income.getIncomeCatagory());
        contentValues.put(DatabaseHelper.INCOME_DESCRIPTION,income.getIncomeDescription());
        contentValues.put(DatabaseHelper.INCOME_DATE,income.getIncomeDate());
        contentValues.put(DatabaseHelper.INCOME_AMOUNT,income.getIncomeAmount());
        long dataInserted=sqLiteDatabase.insert(DatabaseHelper.TABLE_INCOME,null,contentValues);
        sqLiteDatabase.close();
        return dataInserted;
    }
    public long updateIncome(Income income){
        SQLiteDatabase sqLiteDatabase=databaseHelper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(DatabaseHelper.INCOME_CATAGORY,income.getIncomeCatagory());
        contentValues.put(DatabaseHelper.INCOME_DESCRIPTION,income.getIncomeDescription());
        contentValues.put(DatabaseHelper.INCOME_DATE,income.getIncomeDate());
        contentValues.put(DatabaseHelper.INCOME_AMOUNT,income.getIncomeAmount());
        long dataUpdated=sqLiteDatabase.update(DatabaseHelper.TABLE_INCOME,contentValues,DatabaseHelper.INCOME_ID+" =? ",new String[]{String.valueOf(income.getId())});
        sqLiteDatabase.close();
        return dataUpdated;
    }
    public ArrayList<Income>getAllIncome(){
        ArrayList<Income>incomeArrayList=new ArrayList<>();
        String selectQuery="select * from "+DatabaseHelper.TABLE_INCOME;
        SQLiteDatabase sqLiteDatabase=databaseHelper.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery(selectQuery,null);
        if (cursor.moveToFirst()){
            do{
                int id=cursor.getInt(cursor.getColumnIndex(DatabaseHelper.INCOME_ID));
                String incomeCatagory=cursor.getString(cursor.getColumnIndex(DatabaseHelper.INCOME_CATAGORY));
                String incomeDescription=cursor.getString(cursor.getColumnIndex(DatabaseHelper.INCOME_DESCRIPTION));
                String incomeDate=cursor.getString(cursor.getColumnIndex(DatabaseHelper.INCOME_DATE));
                String incomeAmount=cursor.getString(cursor.getColumnIndex(DatabaseHelper.INCOME_AMOUNT));
                Income income=new Income(id,incomeCatagory,incomeDescription,incomeDate,incomeAmount);
                incomeArrayList.add(income);
            }while(cursor.moveToNext());
        }
        return incomeArrayList;
    }




    public Income getSingleIncome(int id){
        SQLiteDatabase sqLiteDatabase=databaseHelper.getReadableDatabase();
        String selectQuery="select * from "+DatabaseHelper.TABLE_INCOME+" where id = "+id;
        Cursor cursor=sqLiteDatabase.rawQuery(selectQuery,null);
        Income income=null;
        if (cursor.moveToFirst()){
            String incomeCatagory=cursor.getString(cursor.getColumnIndex(DatabaseHelper.INCOME_CATAGORY));
            String incomeDescription=cursor.getString(cursor.getColumnIndex(DatabaseHelper.INCOME_DESCRIPTION));
            String incomeDate=cursor.getString(cursor.getColumnIndex(DatabaseHelper.INCOME_DATE));
            String incomeAmount=cursor.getString(cursor.getColumnIndex(DatabaseHelper.INCOME_AMOUNT));
            income=new Income(id,incomeCatagory,incomeDescription,incomeDate,incomeAmount);
        }
        return income;
    }
}
