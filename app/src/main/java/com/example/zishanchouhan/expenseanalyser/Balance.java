package com.example.zishanchouhan.expenseanalyser;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.content.Intent;

import com.example.zishanchouhan.expenseanalyser.Expensesclass.ExMain;
import com.example.zishanchouhan.expenseanalyser.Incomeclass.*;

/**
 * Created by Zishan Chouhan on 11-Jul-18.
 */

public class Balance extends AppCompatActivity{
  TextView incometcv,expensetxv,balancetxv;
  SQLiteDatabase mDatabase,mdbs;
  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.balance);
incometcv=(TextView) findViewById(R.id.incometxv);
    expensetxv=(TextView) findViewById(R.id.expensetxv);
    balancetxv=(TextView) findViewById(R.id.baltxv);

    mDatabase=openOrCreateDatabase(com.example.zishanchouhan.expenseanalyser.Incomeclass.AddIncome.DATABASE_NAME,MODE_PRIVATE,null);
    String sql="SELECT sum(amount) FROM income";
    Cursor cursor = mDatabase.rawQuery(sql, null);
    if (cursor.moveToFirst()) {
incometcv.setText(cursor.getInt(0)+"");
     /* do {

      } while (cursor.moveToNext());
  */  }
    cursor.close();


      mdbs = openOrCreateDatabase("expenses", MODE_PRIVATE, null);
      String sql1="SELECT sum(amount) FROM table_one";
      Cursor cursor1 = mdbs.rawQuery(sql1, null);
      if (cursor1.moveToFirst()) {
          expensetxv.setText(cursor1.getDouble(0)+"");


  }
      cursor1.close();

balancetxv.setText((Double.parseDouble(incometcv.getText()+"")-Double.parseDouble(expensetxv.getText()+""))+"");
  }
}
