package com.example.zishanchouhan.expenseanalyser;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.example.zishanchouhan.expenseanalyser.R.id.edittext1;
import static com.example.zishanchouhan.expenseanalyser.R.id.save;
import static com.example.zishanchouhan.expenseanalyser.R.id.spinner;

/**
 * Created by Zishan Chouhan on 12-Jul-18.
 */

public class AddIncome extends AppCompatActivity {

    TextView textView;
    Spinner spinner;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_income);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Calendar calendar = Calendar.getInstance();
        String currentDate = new SimpleDateFormat( "dd-MM-yyyy", Locale.getDefault()).format(new Date());
        TextView dateView = (TextView) findViewById(R.id.textview3);
        dateView.setText(currentDate);

        spinner = (Spinner) findViewById(R.id.spinner);

        List<String> list = new ArrayList<String>();
        list.add("Income");


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
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home)
            finish();

        int id = item.getItemId();
        if(id == R.id.save){

            editText = (EditText) findViewById(edittext1);
            String str =editText.getText().toString();

            Intent intent = new Intent(AddIncome.this,MainActivity.class);
            intent.putExtra("",str);
            textView.setText(str);
            Intent in = new Intent(AddIncome.this,MainActivity.class);
            startActivity(in);

        }

        return super.onOptionsItemSelected(item);
    }

}





