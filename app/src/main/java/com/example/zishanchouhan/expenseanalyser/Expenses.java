 package com.example.zishanchouhan.expenseanalyser;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.example.zishanchouhan.expenseanalyser.Expensesclass.Adapter_Expenses;
import com.example.zishanchouhan.expenseanalyser.Expensesclass.ExMain;
import com.example.zishanchouhan.expenseanalyser.Expensesclass.ExpensesModel;
import com.example.zishanchouhan.expenseanalyser.Incomeclass.*;
import com.example.zishanchouhan.expenseanalyser.Incomeclass.AddIncome;
import com.github.clans.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


 /**
 * Created by Zishan Chouhan on 11-Jul-18.
 */

public class Expenses extends AppCompatActivity {
     List<ExpensesModel> employeeList;
     SQLiteDatabase mDatabase;
     ListView listViewEmployees;
     Adapter_Expenses adapter;

     List<Model_for_Income> incomeslist;
     SQLiteDatabase mdatabase2;
     ListView listView;
     Adapter_for_Income adapter_for_income;

//
    FloatingActionButton floatingActionButton1 ,floatingActionButton2;
     ListView listview;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expenses);


        floatingActionButton1 = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.fam1);
        floatingActionButton2 = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.fam2);

        floatingActionButton1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent in = new Intent(Expenses.this, com.example.zishanchouhan.expenseanalyser.Incomeclass.AddIncome.class);

                startActivity(in);

            }
        });

        floatingActionButton2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent in = new Intent(Expenses.this, ExMain.class);

                startActivity(in);

            }
        });
        listViewEmployees = (ListView) findViewById(R.id.list_view);
        employeeList = new ArrayList<>();
        listView= (ListView) findViewById(R.id.income_list);
        incomeslist=new ArrayList<>();

        //opening the database
        mDatabase = openOrCreateDatabase(ExMain.DATABASE_NAME, MODE_PRIVATE, null);
        mdatabase2=openOrCreateDatabase(AddIncome.DATABASE_NAME,MODE_PRIVATE,null);

        //this method will display the employees in the list
        showEmployeesFromDatabase();
        Incomedata();


    }

     private void Incomedata() {
         Cursor cursorEmployees = mdatabase2.rawQuery("SELECT * FROM income", null);

         //if the cursor has some data
         if (cursorEmployees.moveToFirst()) {
             //looping through all the records
             do {
                 //pushing each record in the employee list
                 incomeslist.add(new Model_for_Income(
                         cursorEmployees.getInt(0),
                         cursorEmployees.getString(1),
                         cursorEmployees.getString(2),
                         cursorEmployees.getDouble(3)
                 ));
             } while (cursorEmployees.moveToNext());
         }
         //closing the cursor
         cursorEmployees.close();

         //creating the adapter object
         adapter_for_income = new Adapter_for_Income(Expenses.this, R.layout.card_expenses, incomeslist,mdatabase2);

         //adding the adapter to listview
         listView.setAdapter(adapter_for_income);
     }



     private void showEmployeesFromDatabase() {

         //we used rawQuery(sql, selectionargs) for fetching all the employees
         Cursor cursorEmployees = mDatabase.rawQuery("SELECT * FROM table_one", null);

         //if the cursor has some data
         if (cursorEmployees.moveToFirst()) {
             //looping through all the records
             do {
                 //pushing each record in the employee list
                 employeeList.add(new ExpensesModel(
                         cursorEmployees.getInt(0),
                         cursorEmployees.getString(1),
                         cursorEmployees.getString(2),
                         cursorEmployees.getDouble(3)
                 ));
             } while (cursorEmployees.moveToNext());
         }
         //closing the cursor
         cursorEmployees.close();

         //creating the adapter object
         adapter = new Adapter_Expenses(Expenses.this, R.layout.card_expenses, employeeList,mDatabase);

         //adding the adapter to listview
         listViewEmployees.setAdapter(adapter);
     }
}