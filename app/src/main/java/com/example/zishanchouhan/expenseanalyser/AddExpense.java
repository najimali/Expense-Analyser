package com.example.zishanchouhan.expenseanalyser;

import android.app.Activity;
import android.content.Intent;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.lang.CharSequence;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.example.zishanchouhan.expenseanalyser.R.id.parent;
import static com.example.zishanchouhan.expenseanalyser.R.id.save;

/**
 * Created by Zishan Chouhan on 11-Jul-18.
 */

public class AddExpense extends AppCompatActivity {
DatabaseHelper mydb;
    TextView textView;
    Spinner spinner;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_expense);

        editText = (EditText) findViewById(R.id.edittext1);


            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);




        Calendar calendar = Calendar.getInstance();
        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());


        TextView dateView = (TextView) findViewById(R.id.textview3);
        dateView.setText(currentDate);

        spinner = (Spinner) findViewById(R.id.spinner);

        List<String> list = new ArrayList<String>();
        list.add("Food");
        list.add("Transport");
        list.add("Rent");
        list.add("Travel");
        list.add("Education");
        list.add("Household");
        list.add("Gadgets");
        list.add("Health");
        list.add("Others");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinner.setSelection(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.actionbar1,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home)
            finish();

        int id = item.getItemId();


        if(id == R.id.save){



            Intent in = new Intent(this,MainActivity.class);
            startActivity(in);

        }
        return super.onOptionsItemSelected(item);
    }


}
