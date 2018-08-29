package com.habib4990gmail.incomeandexpensemanagement.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.habib4990gmail.incomeandexpensemanagement.models.Expense;

import java.util.ArrayList;

public class ExpenseDatabaseManager {
    private Context context;
    private DatabaseHelper databaseHelper;

    public ExpenseDatabaseManager(Context context) {
        this.context = context;
        databaseHelper=new DatabaseHelper(context);
    }

    public long addExpense(Expense expense){
        SQLiteDatabase sqLiteDatabase=databaseHelper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(DatabaseHelper.EXPENSE_CATAGORY,expense.getExpenseCatagory());
        contentValues.put(DatabaseHelper.EXPENSE_DESCRIPTION,expense.getExpenseDescription());
        contentValues.put(DatabaseHelper.EXPENSE_DATE,expense.getIexpenseDate());
        contentValues.put(DatabaseHelper.EXPENSE_AMOUNT,expense.getExpenseAmount());
        long dataInserted=sqLiteDatabase.insert(DatabaseHelper.TABLE_EXPENSE,null,contentValues);
        return dataInserted;
    }

    public ArrayList<Expense> getAllExpense(){
        ArrayList<Expense>expenseArrayList=new ArrayList<>();
        String selectQuery="select * from "+DatabaseHelper.TABLE_EXPENSE;
        SQLiteDatabase sqLiteDatabase=databaseHelper.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery(selectQuery,null);
        if (cursor.moveToFirst()){
            do{
                int id=cursor.getInt(cursor.getColumnIndex(DatabaseHelper.EXPENSE_ID));
                String expenseCatagory=cursor.getString(cursor.getColumnIndex(DatabaseHelper.EXPENSE_CATAGORY));
                String expenseDescription=cursor.getString(cursor.getColumnIndex(DatabaseHelper.EXPENSE_DESCRIPTION));
                String expenseDate=cursor.getString(cursor.getColumnIndex(DatabaseHelper.EXPENSE_DATE));
                String expenseAmount=cursor.getString(cursor.getColumnIndex(DatabaseHelper.EXPENSE_AMOUNT));
                Expense expense=new Expense(id,expenseCatagory,expenseDescription,expenseDate,expenseAmount);
                expenseArrayList.add(expense);
            }while(cursor.moveToNext());
        }
        return expenseArrayList;
    }
}
